# MySchool

MySchool is a Java application for schools. The program reads from files specified in command line and stores them in 2D integer array. Below are the sample files.

##### courses.txt
C082  Science       12
C081  Mathematics  12
C083  English      24
C084  Technologies  6

##### student.txt
S2023  Sue_Vaneer   14
S1909  Barry_Banks  15
S2025  Robin_Smith  13

##### scores.txt
34      C081  C082  C083  C084  
S2023   99    75    85    62
S2025   -1    92    67    52
S1909   100   83    45    -1

The program finds the student with the highest average and displays on the command line. The program also generates the below files.

Here the fourth column is the number of enrolled students. The fifth column is the average score of each course.

##### course_report.txt 
C081 Mathematics  12   2  99
C082 Science      12   3  83
C083 English      24   3  65
C084 Technologies 6    2  57

Here the fourth column is the number of courses that student enrolled im. The fifth column is the average GPA. A
course result of 80+ receives 4 GPA points. A result of 70-79 receives 3 points. A result in between
60-69 is 2 points. 50-59 gets 1 points. Under 50 has 0 points. For example Sue Vaneer has 2 HD, 1
DI and 1 CR. So her GPA is ( 4 x 2 + 3 + 2 ) / 4 = 3.25.

##### student_report.txt
S2023 Sue_Vaneer  14 4 3.25
S2025 Robin_Smith 13 3 2.33
S1909 Barry_Banks 15 3 2.66
