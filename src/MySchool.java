import java.io.File;
import java.io.IOException;

/**
 * MySchool demonstrates the functionality to find the student with highest
 * average and generate course_report.txt and student_report.txt by reading
 * different files
 * 
 * @author Khushbu Manojkumar Patel (S3823274)
 *
 */
public class MySchool {

	public static void main(String[] args) {

		// get no of files passed from the command line
		int noOfFiles = args.length;

		// check if all three files have been passed, if not exit gracefully
		if (noOfFiles > 0 && noOfFiles == 3) {

			try {

				// create three file objects
				File scoreFile = new File(args[0]);
				File coursesFile = new File(args[1]);
				File studentsFile = new File(args[2]);

				// check if all the files passed exists, else exit from program gracefully
				if (scoreFile.exists() && coursesFile.exists() && studentsFile.exists()) {

					// create an object of Student class to perform the required functionality
					Student student = new Student();

					// store file text into 2-D arrays for all three files
					String[][] scoresFileArr = student.fileToArray(scoreFile);
					String[][] courseFileArr = student.fileToArray(coursesFile, student.getNoOfCourses());
					String[][] studentFileArr = student.fileToArray(studentsFile, student.getNoOfStudents());

					// display student with Highest score average
					student.getHighestScoreStudent(scoresFileArr);

					// generate course_report.txt file
					student.generateCourseReport(courseFileArr);

					// set the course credits for all the courses
					student.setCourseCredits(courseFileArr);

					// calculate the total credits of student and their GPA
					student.calculateAdjustedGPA(studentFileArr);

					// generate student_report.txt
					student.generateStudentReport(studentFileArr);

				} else {
					System.out.println("File(s) does not exists. Please provide existing file(s)!");
				}

				// catch relevant exceptions that might be thrown by inner methods
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Something went wrong! Please try again.");
				e.printStackTrace();

			} catch (IOException e) {
				System.out.println("Something went wrong with file operations! Please try again.");
				e.printStackTrace();
			}

		} else {

			// display relevant error message
			System.out.println("Please enter 3 files in correct order to generate the reports!");
		}
	}
}
