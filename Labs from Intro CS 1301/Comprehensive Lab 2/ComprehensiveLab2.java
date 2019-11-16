/* ELISA JIMENEZ TODD
 * CS 1101
 * Instructor: Villanueva
 * Comprehensive Lab 2
 * Submitted by: Elisa Jimenez Todd
 */
import java.io.*;
import java.util.Scanner;
public class ComprehensiveLab2 {
	
    /************* ACTIVITY 1 ****************************************
     * Implement method Luhn
     * input: an integer (usually 16-digit integer if it checks the 
     *          validity of a credit card)
     * output: a boolean value -- true if the (credit card) number is
     *          valid, false otherwise
     ******************************************************************/
	public static boolean Luhn(String creditCard) { //method for checking validity of a card, luhn method
		int[] num = new int[16];	//array that will store each value of the card number
		int sum = 0;				//variable that will store the addition of each final number
		boolean valid = false;		//returns if the card number was valid or not
		for (int i = 0; i < 16; i++) {			//stores the numbers into an array
			num[i] = creditCard.charAt(i) - '0';
		}
		for (int i = 0; i < 15; i += 2) {		//multiplies every other number *2
			num[i] = num[i] * 2;
			if (num[i] > 9) {				//adds two digit numbers
				num[i] = (num[i] / 10) + (num[i] % 10);
			}
		}
		for (int i = 0; i < 16; i++) {			//adds all remaining numbers
			sum = sum + num[i];
		}
		return valid=(sum % 10 == 0);				//valid if multiple of 10, invalid otherwise
	}

	  /************* ACTIVITY 2 ****************************************
     * Implement method checkCustomers
     * input: a 2D array of Strings (n lines and 2 columns; each line 
     *          contains a name and a credit card number)
     * output: nothing but it creates and writes in a file, where it
     *          it stores the names and corresponding credit card numbers
     *          of customers with fake credit card numbers
     * You should implement two such methods:
     *      1/ an iterative version: checkCustomersIter
     *      2/ a recursive version: checkCustomerRec
     ******************************************************************/
    public static void checkCustomersIter(String[][] customersInfo) throws Exception {
    	PrintWriter fw=new PrintWriter("customers_error_JimenezTodd.txt"); //prints in this address
    	String line;
    	for (int i=0; i < customersInfo.length; i++) {	//iterates for the number of customers
    		if (!Luhn(customersInfo[i][2])) {			//checks if the credit card is fake
    			line = "Iterative: " + customersInfo[i][0] + " " + customersInfo[i][1] + "\t" + customersInfo[i][2]; 
    			fw.println(line);  //stores that name, lastname and card number into file
    		}
    	}fw.println();
    	fw.close(); 
    }
    
    /*----------------------------------------------------------------*/
    public static void checkCustomersRec(String[][] customersInfo,int row, int custNum) throws Exception{
    	FileWriter fw=new FileWriter("customers_error_JimenezTodd.txt",true);//prints in this address and appends
    	PrintWriter fpw = new PrintWriter(fw);
    	if(row<custNum) { //checks that the row is not past the last row BASE CASE
    		if (!Luhn(customersInfo[row][2])){		//tests everyline, from top to bottom
    		fpw.println("Recursive: " + customersInfo[row][0] + " " + customersInfo[row][1] + "\t" + customersInfo[row][2]);//writes in the file
    		fpw.close();
    		}
    		row++;
    		checkCustomersRec(customersInfo,row, custNum); //recursive
    	}
    	
    }
    	
    /************* BONUS ACTIVITY **************************************
     * Implement method generateCCard
     * input: nothing
     * output: a credit card number randomly generated but valid 
     ******************************************************************/
	public static String generateCCard() {
		String newCard = "";				//stores the new credit card
		int [] num = new int [16];			//each number of the credit card
		int [] check = new int [15];		//final values that will be added to check validity of original values
		int sum = 0;
		for(int i = 0; i<15; i++) {			//creates all random numbers save the last one
			num[i]= (int)(Math.random()* 10);
		}
		for (int i = 0; i < 15; i+=2) {		//multiplies every other number *2
			check[i] = num[i] * 2;
			if (check[i] > 9) {				//adds two digit numbers
				check[i] = (check[i] / 10) + (check[i] % 10);
			}
		}
		for (int i = 0; i < 16; i+=2) {			//adds all remaining numbers
			sum = sum + num[i+1] + check[i];
		}
		num[15]= (10 - (sum%10));
		for (int i=0; i<16; i++) {
			newCard= newCard + num[i];
		}
		return newCard;
	}
	/*Main method*/
	public static void main(String[] args) throws Exception {
		
		/*Storing elements of the credit card examples text file into an array*/
		File file = new File("ccexamples.txt"); //scanner to read through lines
		Scanner scCardNum = new Scanner(file);
		int cardNum = 0;	//number of card examples in the text file
		
		while (scCardNum.hasNextLine()) { //counts the lines to assign number of cards
			scCardNum.nextLine();
			cardNum++;
		}
		scCardNum.close();
		
		Scanner scnrEx = new Scanner(file);
		String [] testCards = new String[cardNum]; //array that stores numbers
		for (int i=0; i <(cardNum); i++) {
				testCards[i]= scnrEx.nextLine();
		}
		scnrEx.close();
		
		/*storing the elements of the Customers text file into an array*/
		File file2 = new 9File("ccandnamesExamples.txt"); //scanner to read through lines
		Scanner scCount = new Scanner(file2);
		int customerNum = 0;	//number of customers in the text file
		
		while (scCount.hasNextLine()) { //counts the lines to assign number of customers
			scCount.nextLine();
			customerNum++;
		}
		scCount.close();
		
		Scanner scnr = new Scanner(file2);
		String [][] customerTxt = new String[customerNum][3]; //array that stores names, lastnames and numbers
		for (int i=0; i <(customerNum); i++) {
				customerTxt[i]= scnr.nextLine().split(" ");
		}
		scnr.close();
		
		/*testing Luhn method, Act 1*/
		System.out.println("Credit Card\t\tValidity\n---------------------------------");
		String newNumber;
		for (int i=0; i<testCards.length; i++) {
			System.out.print(testCards[i]);
			if (Luhn(testCards[i])) {
				System.out.println("\tValid");
			}else
				System.out.println("\tNot valid");
		}
		
		/*Act 2*/
		checkCustomersIter(customerTxt);
		checkCustomersRec(customerTxt,0,customerNum);
		
		/*Bonus activity*/
		newNumber = generateCCard();
		System.out.println("\nGenerating new card\n-----------------------------\n" + newNumber + "\t" + Luhn(newNumber));
		
	}
}
