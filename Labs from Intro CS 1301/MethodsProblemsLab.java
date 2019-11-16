/*CS 1101
 *Instructor: Villanueva
 *Methods problems in lab
 *submitted by Elisa Jimenez
*/
import java.util.Scanner;
public class MethodsProblemsLab{

/*Problem 1*/
	public static int randNum() {
		return (int)(Math.random()*100); //creates an int random number to 100
	}
	public static void main (String[] args) {
		Scanner scnr = new Scanner(System.in);
		int num;			//number of random numbers
		System.out.println("How many numbers do you want?");
		num= scnr.nextInt();
		
		for(int i=0; i<num; i++) {
			System.out.println("Random number " +(i+1)+": " + randNum()); //repeats for the number of random numbers the user chose, calls the method
		}
		
	}

/*Problem 2*/
	public static double farenheit(double t) {
		return ((t-32.0)*(5.0/9));			//converts farenheit to celsius
	}
	public static double celsius(double t) {
		return ((t*(9.0/5))+32.0);			//converts celsius to farenheit
	}
	public static void main (String[] args) {
		Scanner scnr = new Scanner(System.in);
		double temperature;				//stores temp value
		String type;					//either farenheit or celsius
		System.out.println("What is the temperature?");
		temperature= scnr.nextDouble();
		System.out.println("Type 'F' for farenheit, 'C' for celsius");
		type = scnr.next();
		
		if (type.equals("F")) {
			System.out.printf("%.2f C\n", farenheit(temperature)); //if farenheit calls for method that converts to celsius
		}
		else
			System.out.printf("%.2f F\n", celsius(temperature));   //if celsius calls for method to convert to farenheit
		
	}
}