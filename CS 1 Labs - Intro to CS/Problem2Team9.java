/* Problem 2 
*Lauren Alvarado
*Michael Fuentes
*Angel Holguin
*Kevin Ramirez
*Elisa Jimenez Todd
*/

import java.util.Scanner;
public class Problem2Team9{
		public static void main(String [] args){
			Scanner scnr = new Scanner(System.in);
			
			int num;		//input number
			int maxNum;		//greatest number
			
			System.out.println ("Enter three numbers:");
			maxNum = scnr.nextInt();
			
			for ( int i = 0; i <2; i++){
				num = scnr.nextInt();
				if (num > maxNum){
					maxNum = num;	
				}
			}System.out.print (maxNum +" is the greatest number among the three numbers input.");
		}
}		