/* CS1101 Intro to Computer Science
 * Instructor: Villanueva
 * TA: Caro
 * Comprehensive Lab 1
 * Submitted by: Elisa Jimenez Todd 
*/

import java.util.Scanner;
public class JimenezTodd_Elisa_ComprehensiveLab1{
	public static void main(String [] args){
		
		Scanner scnr = new Scanner(System.in);
		int menuOption;							//menu option chosen 1,2,3
		int numAssignments;						//number of assignments to repeat for
		double maxScore;						//maximum score for each category
		double indScore;						//individual score for each assignment
		double sumScore = 0;					//sum of each category's score (grade)
		double catPercentage;					//total grade percentage of each category
		double grade = 0;						//sum of all category grades
		boolean exit = false;					//condition to exit the while loop
		int percentage = 20;
		String category = "lab assignments";
		String assignment = "the homeworks";
		String wGrade = "Lab";
		
		while (exit == false){
			System.out.print ("\n\t\t1.Calculate 1301 Grades\n\t\t2.Calculate Letter Grade\n\t\t3.Exit\n\n");//menu options
			System.out.print ("Welcome to uGrade! Enter the menu option you want (1, 2, or 3): ");
			menuOption = scnr.nextInt();					//user selects menu option
			
			if (menuOption == 1 ){		//menu option 1
					grade = 0; 			//resets grade if the user had previously calculated it
				for (int i = 0; i <5; i++){					//for loop will repeat 5 times as there are 5 categories to ask grades about
					System.out.print ("\n\tInput the number of grades you have received for " + category + ": ");
					numAssignments = scnr.nextInt();			//number of assignments in that category
					System.out.print ("\tInput the maximum score for all " + assignment + ": ");
					maxScore = scnr.nextDouble();				//maximum score for that category
					for (int j = 1; j<= numAssignments; j++){	//repeats by the number of assignments as it asks for each assignment grade
						System.out.print ("\t\tInput Grade " + j + ": ");
						indScore = scnr.nextDouble();			//reads one score
						sumScore = sumScore + indScore;			//add individual scores
					}
					catPercentage = (sumScore/maxScore)* percentage;
					System.out.printf(wGrade + " Grade Percentage: %.2f%%\n", catPercentage); //prints the category grade, only 2 decimals
					grade = grade + catPercentage;
					sumScore = 0;				//reset sum of the score
					switch (wGrade){
					case "Lab":						//changes strings to ask for quizzes instead of lab (this will be useful the 2nd time the for loop runs)
						percentage = 15;
						category = "quizzes";
						assignment = "the quizzes";
						wGrade = "Quiz";
						break;
					case "Quiz":					//changes strings to ask for exams instead of quizzes
						percentage = 50;
						category = "exams";
						assignment = "the exams";
						wGrade = "Exam";
						break;
					case "Exam":					//changes strings to ask for student engagement
						percentage = 5;
						category = "student engagement";
						assignment = "student engagement";
						wGrade = "Student Engagement";
						break;
					case "Student Engagement":		//changes strings to ask for class participation
						percentage = 10;
						category = "class participation";
						assignment = "class participation";
						wGrade = "Class Participation";
						break;
					case "Class Participation":		//resets strings to start on the lab category if the user wants to calculate his grade again
						percentage = 20;
						category = "lab assignments";
						assignment = "the homeworks";
						wGrade = "Lab";
						break;
					}
				}	
				System.out.printf ("\nYour current grade is: %.2f\n", grade);		//print grade
			}
			if (menuOption == 2 && grade == 0){					// menu option 2: checks if there was not a grade previously calculated
				System.out.println ("\nYou need to calculate your grade first."); 
			}
			if (menuOption == 2 && grade != 0){					// menu option 2: checks if there was a grade already calculated
				if (grade < 60){								//checks grade value for appropriate letter grade
					System.out.printf ("Your current grade of %.2f corresponds to an F.\n", grade); //prints only 2 decimals
				}else if (grade < 70){
					System.out.printf ("Your current grade of %.2f corresponds to a D.\n", grade);
				}else if (grade < 80){
					System.out.printf ("Your current grade of %.2f corresponds to a C.\n", grade);
				}else if (grade < 90){
					System.out.printf ("Your current grade of %.2f corresponds to a B.\n", grade);
				}else{
					System.out.printf ("Your current grade of %.2f corresponds to an A.\n", grade);
				}
			}
			if (menuOption == 3){								//menu option 3	
				System.out.println ("Goodbye, thank you for using uGrade!");	//goodbye greeting
				exit = true;				//makes exit true to get out of the loop
			}
			if (menuOption != 3 && menuOption !=2 && menuOption !=1){
			System.out.print ("Choose only an option between 1 and 3.\n");
			}
		}
	}
}