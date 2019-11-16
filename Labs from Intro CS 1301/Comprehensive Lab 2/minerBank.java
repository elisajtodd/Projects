 
/* CS1101 – Intro to Computer Science 
Instructor: Aguirre OR Akbar OR Villanueva [LEAVE ONLY ONE]

Comprehensive Lab 1 

By including my name below, I confirm that:
-	I am submitting my original work.
-	If I include code obtained from another source or I received help I am giving attribution to those sources as comments.
-	This submission does not incur in any academic dishonesty practice as described in the course syllabus.


Modified and submitted by: [YOUR NAME GOES HERE] 
*/ 

import java.util.Scanner;
import  java.io.File;

public class minerBank {
	public static void main(String[] args)throws Exception {		
		/* YOUR CODE GOES HERE, FOLLOW INSTRUCTIONS OF THE 
		COMPREHENSIVE LAB 1 DESCRIPTION.
		GOOD LUCK!
		*/
		Scanner scnr = new Scanner(System.in);
		File nameOfFile = new File("users.txt");//to read the file of the user with all the information
		Scanner fileScnr = new Scanner(nameOfFile);
		 String usrnUser;//usrn=user name
		 String usrnFile;//read the user you from file
		 int pinUser;//pin of the user
		 int pinFile;//read pin from file
		 int chB;// read the checking balance 
		 int svB;// read the saving balance
         String option;//for te menu anwer
         String op1CB;//option 1 checking balance
         String op2WB;//option 2 withdraw money		 
		 for (int i = 0; i<3; i++){
			 System.out.println("enter Username");
		     usrnUser = scnr.next();
		     usrnFile = fileScnr.next();
		     System.out.println("enter PIN");
		     pinUser = scnr.nextInt();
		     pinFile = fileScnr.nextInt();
		if ((pinFile==pinUser) && usrnFile==usrnUser){
			 chB = fileScnr.nextInt();
			 svB = fileScnr.nextInt();
			 System.out.println("you user name is " + usrnUser + "this is your checking balance " + chB + "and this is your saving balance " + svB);
		 }
		 else {
			 System.out.println("your user name or/and pin are wrong good bye");
			 
		 }
		 }     
		 System.out.println("1.Chek Balance, 2.Withdraw Money, 3.Deposit Money, 4.transfer Money, 5.Exit")
		 option = scnr.nextLn();
		 if (option.equals(check balance)){
			 System.out.println("what acoount you whant to use checking acoount or saving account?");
			 op1CB = scnr.nextLn();
			 if (op1CB.equals(checking acoount)){
				 System.out.println("your checking account is" + chb);
			 }
	         if (op1CB.equals(savings account)){
				 System.out.println("your saving account is" + svB);
			 }
		 }
		 option = scnr.nextLn();
		 if (option.equals(Withdraw Money)){
			 System.out.println("what acoount you whant to use checking acoount or saving account?");
			 op2WM = scnr.nextLn();
			 
			 
			 
	}
}