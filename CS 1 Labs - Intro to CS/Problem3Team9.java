/* Problem 3 
*Lauren Alvarado
*Michael Fuentes
*Angel Holguin
*Kevin Ramirez
*Elisa Jimenez Todd
*/

import java.util.Scanner;
public class Problem3Team9{
		public static void main(String [] args){
			Scanner scnr = new Scanner(System.in);
			int oddNum = 1;
			int sum = 0;		
			int num;
			
			System.out.print("How many odd numbers?");
			num = scnr.nextInt();
			for (int i = num; i >0 ; i --){
				System.out.println (oddNum);
				sum = sum + oddNum;
				oddNum = oddNum +2;
			}System.out.print ("The sum of odd natural numbers up to " + num + " term is " + sum);
	}
}		