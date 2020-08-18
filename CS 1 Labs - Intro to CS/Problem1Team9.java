/* Problem 1 
*Lauren Alvarado
*Michael Fuentes
*Angel Holguin
*Kevin Ramirez
*Elisa Jimenez Todd
*/

import java.util.Scanner;
public class Problem1Team9{
		public static void main(String [] args){
			Scanner scnr = new Scanner(System.in);
			
			double radius; 		//radius
			double perimeter; 	//perimeter
			double area; 		//area
			
			System.out.println ("Enter radius:");
			radius = scnr.nextDouble();
			perimeter = (2 * radius) * 3.1415926;
			area = 3.1415926 * radius * radius;
			
			System.out.printf ("Perimeter: %.2f \nArea: %.2f", perimeter, area);
		}
}