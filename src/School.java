import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class provides functionality to display the student with highest average
 * and contains certain helper methods and variables to validate other
 * functionalities of Course and Student classes
 *
 */
public class School {

	// variable to store total no of students and courses
	private int noOfStudents = 0;
	private int noOfCourses = 0;

	// int[] to store no of students enrolled in each course and their average score
	private int[] courseEnrollCountArr;
	private int[] courseAvgScoreArr;

	// String[] to store the course ids from the scores.txt to preserve the ordering
	// of the courses
	private String[] courseIDArr;

	// int[] to store student IDs (to preserve ordering) and 2d array to store GPA
	// score for each student
	private String[] studentIDArr;
	private float[][] studentGPAArr;

	Scanner scannerFile;

	public String[][] fileToArray(File file) throws IOException {

		// row no to read from file
		int rowNo = 0;

		// 2-d array to store data from text file
		String[][] scoresFileArr;

		scannerFile = new Scanner(file);

		// read first line from scores.txt file to get student and courses counts
		String studentCourses = scannerFile.nextLine();

		// split the line with ANY no of spaces in between
		// divide the first element of first row by 10 to get the students count
		noOfStudents = Integer.parseInt(studentCourses.split("\\s+")[0]) / 10;

		// perform modulo on first element of first row with 10 to get the courses count
		this.noOfCourses = Integer.parseInt(studentCourses.split("\\s+")[0]) % 10;

		// create array with size as (no of Student) X (no of course)
		scoresFileArr = new String[noOfStudents + 1][noOfCourses + 1];

		// store the first line (header containing Course ID)
		scoresFileArr[rowNo++] = studentCourses.split("\\s+");

		// read rest of the rows and store it in array
		while (scannerFile.hasNextLine()) {
			scoresFileArr[rowNo++] = scannerFile.nextLine().split("\\s+");
		}

		// close the scanner object and return the array file
		scannerFile.close();
		return scoresFileArr;
	}

	/**
	 * This method displays the student with highest average score among rest of the
	 * students. This method also store other informations like courseEnrollCountArr
	 * to store no of students enrolled in the course. Look at the method
	 * implementation to check other information stored
	 * 
	 * @param fileArray
	 */
	public void getHighestScoreStudent(String[][] fileArray) {

		// variables initialized to calculate average marks, no of subjects in which
		// student has enrolled, etc
		int score;
		int totalMarks = 0;
		int avgScore = 0;
		int subjectsEnrolled = 0;
		int maxAvgScore = 0;
		int studentNo = 0;

		// arrays to store no of students enrolled in a course, average score per course
		// and the course IDs
		courseEnrollCountArr = new int[this.noOfCourses];
		courseAvgScoreArr = new int[this.noOfCourses];
		this.courseIDArr = new String[this.noOfCourses];

		// 2-d array to store student's GPA per subject
		this.studentGPAArr = new float[this.noOfStudents][this.noOfCourses];
		this.studentIDArr = new String[this.noOfStudents];

		// for loop to iterate over marks present in the scores.txt file
		for (int i = 1; i < fileArray.length; i++) {

			// store the student ids to preserve the order of students
			this.studentIDArr[i - 1] = fileArray[i][0];

			for (int j = 1; j < fileArray[i].length; j++) {

				// store course ids to preserve the order of courses
				this.courseIDArr[j - 1] = fileArray[0][j];

				// convert the scores to integers
				score = convertToInteger(fileArray[i][j]);

				// calculate scores if only studen is enrolled in it
				if (score != -1) {

					// calculate total marks for a student
					totalMarks += score;

					// increment the subject enroll count by 1
					subjectsEnrolled++;

					// increment the course student enrollment count by 1
					courseEnrollCountArr[j - 1]++;

					// store the scores to calculate average score per course
					courseAvgScoreArr[j - 1] += score;

					// convert the score to GPA and store it in 2d array for each student
					this.studentGPAArr[i - 1][j - 1] = convertToGPA(score);

				} else {
					// if student is not enrolled than add -1 in student GPA array
					this.studentGPAArr[i - 1][j - 1] = -1;
				}
			}

			// calculate student's average marks
			avgScore = totalMarks / subjectsEnrolled;

			// if student's average score is greater than the max score then
			if (avgScore > maxAvgScore) {

				// store the studentNo and the maximum score
				studentNo = i;
				maxAvgScore = avgScore;
			}

			// re set the total marks and subjects enroll count
			totalMarks = 0;
			subjectsEnrolled = 0;
		}

		// calculate the course average score which will be used in the Course.java file
		for (int i = 0; i < courseEnrollCountArr.length; i++) {
			courseAvgScoreArr[i] = courseAvgScoreArr[i] / courseEnrollCountArr[i];
		}

		// display student no having highest avg score along with his/her average score
		System.out.println("The top student is " + fileArray[studentNo][0] + " with and average " + maxAvgScore);

	}

	/**
	 * This method converts the scores to integers and assigns -1 to any string or
	 * characters
	 * 
	 * @param score
	 * @return
	 */
	public int convertToInteger(String score) {
		try {
			return Integer.parseInt(score);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	/**
	 * This method converts the marks obtained to a GPA score
	 * 
	 * @param score
	 * @return
	 */
	public float convertToGPA(int score) {
		float gpa = -1;

		if (score >= 80)
			gpa = 4;
		else if (score >= 70 && score <= 79)
			gpa = 3;
		else if (score >= 60 && score <= 69)
			gpa = 2;
		else if (score >= 50 && score <= 59)
			gpa = 1;
		else if (score < 50)
			gpa = 0;

		return gpa;
	}

	/**
	 * Returns the no of courses
	 * 
	 * @return
	 */
	public int getNoOfCourses() {
		return this.noOfCourses;
	}

	/**
	 * Returns the no of students
	 * 
	 * @return
	 */
	public int getNoOfStudents() {
		return this.noOfStudents;
	}

	/**
	 * Returns the array containing students enrolled in each course
	 * 
	 * @return
	 */
	public int[] getCourseEnrollCount() {
		return this.courseEnrollCountArr;
	}

	/**
	 * Returns the array containing average scores for each course
	 * 
	 * @return
	 */
	public int[] getCourseAvgScore() {
		return this.courseAvgScoreArr;
	}

	/**
	 * Returns the course ID array w.r.t scores.txt file
	 * 
	 * @return
	 */
	public String[] getCourseIDArr() {
		return this.courseIDArr;
	}

	/**
	 * Returns the 2-d array containing GPA scores of each student in subjects
	 * enrolled
	 * 
	 * @return
	 */
	public float[][] getStudentaGPA() {
		return this.studentGPAArr;
	}

	/**
	 * Returns the array containing student ids w.r.t to scores.txt
	 * 
	 * @return
	 */
	public String[] getStudentIDArr() {
		return this.studentIDArr;
	}

}
