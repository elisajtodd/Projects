/* CS1101 Intro to Computer Science
 * Instructor: Villanueva
 * TA: Caro
 * Comprehensive Lab 1
 * Submitted by: Elisa Jimenez Todd 
*/

import java.util.Scanner;
public class ArraysPractice{
	public static void main(String [] args){
		
		Scanner scnr = new Scanner(System.in);
		/*movies*/
		
		String[] movies = new String[5];
		System.out.println("Type 5 movie titles:" );
		for (int i=0; i<5; i++) {
			System.out.print("Movie " + (i+1)+ ": " );
			movies[i]=scnr.nextLine();
		}
		System.out.println("Your movies are: ");
		for (int i=0;i<5; i++) {
			System.out.print(movies[i]);
		}
		/*Finding arrays*/
		String gName;
		String[] names = {"John", "Robert", "Eddy", "Alex", "Peter", "Alice", "Andrea", "Nicole", "Mary","Natalie"};
		System.out.print("Type a name: ");
		gName=scnr.next();
		
		for (int i=0; i<10; i++) {
			if (gName.equals(names[i])) {
				System.out.println("Name found at index: "+ i);
				names [i]="CS1301";
				break;
			}
			else if(1==9) {
				System.out.println("Name not found in array");
			}
		}
		for(int i=0; i<10; i++) {
			System.out.print(names[i]+ " ");
		}
	}
}