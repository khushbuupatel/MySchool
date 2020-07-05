import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;


public class Student extends Course {

	private int[] studentTotalCredits;
	private float[] studentAdjustedGPA;

	/**
	 * This method calculates the adjusted GPA for each student and their total
	 * credits based on the subjects enrolled
	 * 
	 * @param studentFileArr
	 */
	public void calculateAdjustedGPA(String[][] studentFileArr) {

		DecimalFormat df = new DecimalFormat("#0.####");
		
		// initialize helper variables
		int totalCount = 0;
		int totalCredit = 0;
		int index;

		// initialize the arrays to with size as no of students
		this.studentAdjustedGPA = new float[this.getNoOfStudents()];
		this.studentTotalCredits = new int[this.getNoOfStudents()];

		// get the course credit array to calculate the adjusted GPA
		int[] courseCredits = this.getCourseCredits();

		// get the GPA 2d array for each student based on their scores
		float[][] studentGPAArr = this.getStudentaGPA();

		// get the studentIDs array to get the order in which students appear in
		// scores.txt file
		String[] studentIDArr = this.getStudentIDArr();

		// loop through student GPA array
		for (int i = 0; i < studentGPAArr.length; i++) {

			// initialize index variable as i
			index = i;

			for (int j = 0; j < studentGPAArr[i].length; j++) {

				// calculate the total credits and total count for adjusted GPA only if the
				// student is enrolled
				if (studentGPAArr[i][j] != -1) {
					totalCredit += courseCredits[j];
					totalCount += studentGPAArr[i][j] * courseCredits[j];
				}
			}

			// again loop through student file array and see if a particular student in
			// student.txt is at the same index as that of scores.txt
			for (int k = 0; k < studentFileArr.length; k++) {

				if (studentFileArr[k][0].equals(studentIDArr[i])) {
					// update the index accordingly
					index = k;
					break;
				}
			}

			// store the total credit at the update index
			this.studentTotalCredits[index] = totalCredit;

			// calculate the adjusted GPA and store it at the updated index
			this.studentAdjustedGPA[index] = (float) totalCount / this.studentTotalCredits[index];
			this.studentAdjustedGPA[index] = Math.round( this.studentAdjustedGPA[index] * 100.0 / 100.0);

			// reset the variables
			totalCredit = 0;
			totalCount = 0;
		}
	}

	/**
	 * This method generates the student_report.txt from the student file 2-D array
	 * and add the count of the total credit of the students and the adjusted GRA
	 * 
	 * @param fileArray
	 * 
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void generateStudentReport(String[][] fileArray) throws IOException, ArrayIndexOutOfBoundsException {

		String row = "";

		// create the file or overwrite existing file
		FileWriter writer = new FileWriter("student_report.txt");

		// loop the students file 2-d array
		for (int i = 0; i < fileArray.length; i++) {
			
			for (int j = 0; j < fileArray[i].length; j++) {

				// append the student no, name and age to the row
				row += fileArray[i][j] + "  ";
			}
			// append the total credits and the adjusted GPA to the row
			row += this.studentTotalCredits[i] + "  " + this.studentAdjustedGPA[i] + "\n";

			// write the row to the file
			writer.write(row);

			// reset the row once written in file
			row = "";
		}

		System.out.println("student_report.txt generated!");

		// flush the information to the file
		writer.flush();
		// close the file writer object
		writer.close();
	}
}
