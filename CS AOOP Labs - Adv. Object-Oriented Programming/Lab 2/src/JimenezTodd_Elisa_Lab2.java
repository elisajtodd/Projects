/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: This program reads a file containing the information of 
 * chess pieces: type, color, and position. It stores this information on
 * a Piece object array.
 * The program asks the user to input a new position, the program then
 * checks if this position is valid for each of the pieces in the array
 * and prints the result.
 * 
 * Log History:
 * [1/24/2020] Created file with main and Piece class. Define 
 * Piece.java 's attributes and methods
 * [1/25/2020] Made readPieces which reads the text file into a Piece array
 * [1/25/2020] Made countLines to better manage a text file of any size
 * [1/26/2020] Created the validate method and the skeleton for the methods
 * that validate each piece.
 * [1/27/2020] Completed each validate method and made the main method to
 * test the program and handle any user input so that no errors (or few)
 * may occur
 * [1/29/2020] Changed read to read into the class of each piece, corrected a few methods
 * placed the switch case on the readPieces method
 * [1/29/2020] Removed now unnecessary methods
 * [2/03/2020] = modified switch to be shorter, added a while loop to keep
 * testing pieces and sent scanner as parameter to fix bug caused in validMoves
 */

import java.util.Scanner;

public class JimenezTodd_Elisa_Lab2{
	
	/* validate checks the validity of a move to all the pieces
	 * it takes the piece array and the positions that we are checking
	 * and prints the result. It does not return anything
	 */
	public static char[] validMoves(Scanner scnr) {

		boolean test = true;	//to keep testing positions
		char posX = 'a';
		int posY = 1;
		//asks for first input
		System.out.println("\nWhich position do you want to move to? (letter,number)");
		String[] position = scnr.nextLine().replaceAll("\\s", "").split(",");
		
		//iterates until user exits
		while(test) {
			//makes sure input is valid for format
			while(position.length < 2 || !Character.isLetter(position[0].charAt(0)) || 
					!Character.isDigit(position[1].charAt(0))) {
				System.out.println("Enter the position in this format: \"letter,number\"");
				position = scnr.nextLine().replaceAll("\\s", "").split(",");
			}
			
			posX = Character.toUpperCase(position[0].charAt(0)); //makes first input a capital letter
			posY = Integer.valueOf(position[1]);	//makes second input an integer value
			
			//makes sure input is in bounds
			if((posX > 72 || posX < 65) || (posY > 8 || posY < 1 )) {
				System.out.println("Range goes from A-H and 1-8, try again");
				position = scnr.nextLine().replaceAll("\\s", "").split(",");
			}else {
				test=false;
			}			
		}
		char[] positions = {posX, Integer.toString(posY).charAt(0)};
		return positions;
	}

	/*main method tests the program*/
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);	//will take user input
		String[] piece = new String[4];
		char[] positions = new char[2];
		char posX;
		int posY;
		boolean test = true;
		String another ="";
		
		while(test) {
						
			System.out.println("Input the piece you want to create. (type, color, letter, number)");
			piece = scnr.nextLine().replaceAll("\\s", "").split(",");
			piece[2]= piece[2].toUpperCase();
			
			positions = validMoves(scnr);
			posX = positions[0];
			posY = positions[1]-'0';
			
			switch(piece[0].toUpperCase()) {
				case "PAWN":
					Pawn pawn = new Pawn(piece[0], piece[1],  Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					pawn.showResult(posX, posY);
					break;
					
				case "KNIGHT":
					Knight knight = new Knight(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					knight.showResult(posX, posY);
					break;
					
				case "BISHOP":
					Bishop bishop = new Bishop(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					bishop.showResult(posX, posY);
					break;
					
				case "ROOK":
					Rook rook = new Rook(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					rook.showResult(posX, posY);
					break;
					
				case "QUEEN":
					Queen queen = new Queen(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					queen.showResult(posX, posY);
					break;
					
				case "KING":
					King king = new King(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); //call to method to check pawn
					king.showResult(posX, posY);
					break;
					
				default:
					System.out.println("WRONG PIECE");
			}	
			System.out.println("\nIf you want to exit enter \"exit\"\nIf you want to test another piece enter any other string");
			another = scnr.nextLine();
			if (another.equals("exit")){
				test = false;
			}
		}
		scnr.close();
	}
}
