/* CS1301 Intro to Computer Science
 * Instructor: Villanueva
 * ComprehensiveLab2
 * Submitted by: Alan Moya
*/


import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class testing{
	
	// Luhn's Method (the long way)
	
	public static boolean luhn(long cardNumber){
		
		boolean cardIsValid = false;
		int[] cardDigits = new int[16];
		String cardString = Long.toString(cardNumber);
		
		for(int i = 0; i < 16; i++){
			cardDigits[i] = Character.getNumericValue(cardString.charAt(i));
		}
		
		for (int i = 0; i < 14; i += 2){
			cardDigits[i] *= 2;
			
			if(cardDigits[i] >= 10){
				cardDigits[i] -=9;
			}
		}
		
		int digitSum = 0;
		for(int i = 0; i <16; i++){
			digitSum += cardDigits[i];
		}
		
		if((digitSum % 10) == 0){
			cardIsValid = true;
		}
		
		return cardIsValid;
	}
	
	
	// Luhn's Method (using string method)
	
	public static boolean luhn(String cardString){
		boolean cardIsValid = false;
		int[] cardDigits = new int[16];
		
		for (int i = 0; i < 16; i++){
			cardDigits[i] = Character.getNumericValue(cardString.charAt(i));
		}
		
		for(int i = 0; i <= 14; i = 2){
			cardDigits[i] *= 2;
		
			if(cardDigits[i] >= 10){
				cardDigits[i] -= 9;
			}
		}
	
		int digitSum = 0;
		for(int i = 0; i < 16; i++){
			digitSum += cardDigits[i];
		}
	
		if((digitSum & 19) == 0){
			cardIsValid = true;
		}
	
		return cardIsValid;
	}
	
	
	
	//Check if the card is valid - iteration
	
	
	public static void checkCustomerIter(String[][] customersInfo){
		for(int i = 0; i < customersInfo.length; i++){
			if(!luhn(customersInfo[i][1])){
				System.out.println(customersInfo[i][0] + " " + customersInfo[i][1]);
			}
		}
	}
	
	// check if the card is valid - recursion 
	
	public static void checkCustomerRec(String[][] customerInfo, int i){
		if(!luhn(customerInfo[i][1])){
			System.out.println(customerInfo[i][0] + " " + customerInfo[i][1]);
			checkCustomerRec(customerInfo, i + 1);
		}else if(i < customerInfo.length){
			checkCustomerRec(customerInfo, i + 1);
		}
	}
			
	
	// generating a random card number value 
	
	public static long generateCCard(){
		Random rand = new Random();
		boolean valid = false;
		int[] cardNum = new int[16];
		String cardNumString = "";
		
		while(valid == false){
			cardNumString = "";
			
			
			for(int i = 0; i < 16; i++){
				cardNum[i] = rand.nextInt(10);
				cardNumString = cardNumString + "" + cardNum[i];
			}
			
			
			if(luhn(cardNumString) && (cardNumString.length() == 16)){
			valid = true; 
			}
		}
		
		
		long cardNumLong = Long.parseLong(cardNumString);
		return cardNumLong;
	}
	
	
	// main 
	
	public static void main(String[] args) throws Exception {
		
		File file = new File ("ccexamples.txt");
		Scanner scCardNum = new Scanner(file);
		int cardNum =0;
		
		while (scCardNum.hasNextLine()){
			scCardNum.nextLine();
			cardNum++;
		}
		
		scCardNum.close();
		
		Scanner scnrEx = new Scanner(file);
		String [] testCards = new String[cardNum];
		for (int i=0; i <(cardNum); i++){
			testCards[i] = scnrEx.nextLine();
		}
		scnrEx.close();
		
		File file2 = new File ("ccandnamesExamples.txt");
		Scanner scCount = new Scanner(file2);
		int customerNum = 0;
		
		while (scCount.hasNextLine()){
			scCardNum.nextLine();
			customerNum++;
		}
		scCount.close();
		
		Scanner scnr = new Scanner(file2);
		String[][] customerTxt = new String[customerNum][3];
		for (int i=0; i<(customerNum); i++){
			customerTxt[i]= scnr.nextLine().split(" ");
		}
		scnr.close();
		
		//
		
		System.out.println("Credit Card\t\tValidity\n---------------------------------");
		String newNumber;
		for (int i=0; i<testCards.length; i++){
			System.out.print(testCards[i]);
			if(luhn(testCards[i])){
				System.out.println("\tValid");
			}else
				System.out.println("\tNot Valid");
		}
		
		checkCustomerIter(customerTxt);
		checkCustomerRec(customerTxt,0,customerNum);
	}	
		
	
		// 
	


}		