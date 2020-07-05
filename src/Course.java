import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Course class provides method to read the courses.txt and student.txt file and
 * convert it to 2-d array
 *
 */
public class Course extends School {

	// instance variable to store the credits of each courses
	private int[] courseCredits;

	/**
	 * This method reads the text file and converts it to a 2-d array
	 * 
	 * @param file      text file to be read
	 * @param fileLines no of lines to be read in the file (required to initialize
	 *                  the array size) NOTE: Functions getNoOfStudents() and
	 *                  getNoOfCourses() might be helpful here.
	 * @return
	 * @throws IOException might throw an IO Exception
	 */
	public String[][] fileToArray(File file, int fileLines) throws IOException {

		// initialize the array with no of lines passed
		String[][] fileArray = new String[fileLines][];
		int rowNo = 0;

		scannerFile = new Scanner(file);

		// read the text file and split each line with ANY no of spaces in between
		while (scannerFile.hasNextLine()) {
			fileArray[rowNo++] = scannerFile.nextLine().split("\\s+");
		}
		return fileArray;
	}

	/**
	 * This method generates the course_report.txt file
	 * 
	 * @param fileArray array containing courses.txt file details
	 * @throws IOException                    might throw an IO Exception
	 * @throws ArrayIndexOutOfBoundsException might throw ArrayIndexOutOfBound
	 *                                        exception
	 */
	public void generateCourseReport(String[][] fileArray) throws IOException, ArrayIndexOutOfBoundsException {

		String row = "";
		int index;

		// create a new file or overwrite an existing file
		FileWriter writer = new FileWriter("course_report.txt");

		// get enrollment and average score details for each course
		int[] courseEnrollCount = this.getCourseEnrollCount();
		int[] courseAvgScore = this.getCourseAvgScore();

		// array to store lines to be written in output file
		String[] lines = new String[this.getNoOfCourses()];

		// get the courseIDs array to get the order in which courses appear in
		// scores.txt file
		String[] courseIDs = this.getCourseIDArr();

		// loop through the course array
		for (int i = 0; i < fileArray.length; i++) {

			// initialize index variable as i
			index = i;

			// again loop through courses array and see if a particular course in
			// courses.txt is at the same index as that of scores.txt
			for (int k = 0; k < fileArray.length; k++) {

				// update the index
				if (fileArray[k][0].equals(courseIDs[i])) {
					index = k;
					break;
				}
			}

			// read the course id, name and credits at the index
			for (int j = 0; j < fileArray[index].length; j++) {
				row += fileArray[index][j] + "  ";
			}

			// append the enrollment count and the average course details
			row += courseEnrollCount[i] + "  " + courseAvgScore[i] + "\n";

			// store the row at that index
			lines[index] = row;
			row = "";
		}

		// loop through lines array and write to file
		for (int i = 0; i < lines.length; i++)
			writer.write(lines[i]);

		System.out.println("courses_report.txt generated!");

		// flush the output and close the writer object
		writer.flush();
		writer.close();
	}

	/**
	 * This method stores the course credits in the courseCredits array
	 * 
	 * @param courseFileArr
	 */
	public void setCourseCredits(String[][] courseFileArr) {

		int index;

		// initialize the courseCredits array with no of courses available
		courseCredits = new int[this.getNoOfCourses()];

		// get the courseIDs array to get the order in which courses appear in
		// scores.txt file
		String[] courseIDs = this.getCourseIDArr();

		// loop the courses array
		for (int i = 0; i < courseFileArr.length; i++) {

			// initialize index variable as i
			index = i;

			// again loop through courses array and see if a particular course in
			// courses.txt is at the same index as that of scores.txt
			for (int j = 0; j < courseFileArr.length; j++) {

				if (courseFileArr[j][0].equals(courseIDs[i])) {
					index = j;
					break;
				}
			}

			// get the credit from that index and store it
			this.courseCredits[i] = Integer.parseInt(courseFileArr[index][2]);
		}
	}

	/**
	 * This method returns the course credit array
	 * 
	 * @return
	 */
	public int[] getCourseCredits() {
		return this.courseCredits;
	}
}
