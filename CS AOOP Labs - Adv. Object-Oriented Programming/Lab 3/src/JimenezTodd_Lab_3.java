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
 * [2/10/2020] Created lab 3
 * [2/11/2020] Modified main to adapt to polymorphism
 * [2/12/2020] Added move method to print only when piece can move
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class JimenezTodd_Lab_3{
	
	/* readPieces reads the file that contains the pieces information
	 * takes the name of the text file to read and returns an array of
	 * Piece objects
	 */
	public static Piece[] readPieces(String filename) {
		String currentLine;
		Piece[] board = new Piece[16]; //array of pieces
		
		//reads
		try{
	        BufferedReader textReader = new BufferedReader(new FileReader(filename));
	        int i = 0;
	        while ((currentLine = textReader.readLine()) != null) {
	        	String[] piece = currentLine.replaceAll("\\s", "").split(",");	//splits by comma, removes whitespace
	         	board[i] = makePiece(piece);	//calls a method to make a piece object
	        	i++;
	        }
	        textReader.close();
		}catch(Exception e){
			System.out.println(e.getClass().getCanonicalName());e.printStackTrace();
		}
		return board; //returns the array of pieces
	}
	
	/* makePiece creates an object Piece from information read on file 
	 * it takes an array of strings with information of a piece and
	 * returns a Piece object with those attributes
	 */
	public static Piece makePiece(String[] piece) {
		Piece pieceObj = new Piece();
		if(piece.length == 4) {
			//sends adequate information
			switch(piece[0].substring(0, piece[0].length()-2).toUpperCase()) {
				case "PAWN":	//call to method to check pawn
					pieceObj = new Pawn(piece[0], piece[1],  Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); 
					break;
					
				case "KNIGHT":	//call to method to check knight
					pieceObj = new Knight(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); 
					break;
					
				case "BISHOP":	//call to method to check bishop
					pieceObj = new Bishop(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3]));
					break;
					
				case "ROOK":	//call to method to check rook
					pieceObj = new Rook(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); 
					break;
					
				case "QUEEN":	//call to method to check queen
					pieceObj = new Queen(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); 					
					break;
					
				case "KING":	//call to method to check king
					pieceObj = new King(piece[0], piece[1], Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3])); 
					break;
				default:
					System.out.println("WRONG PIECE");
			}
		}return pieceObj;
	}
	
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
		char[] positions = new char[2];
		char posX;
		int posY;
		boolean test = true;
		String another ="";
		Piece[] board = readPieces("StartGameWhitePieces.txt"); //creates array of pieces
		
		while(test) { //loops until the user wants to exit trying new positions
			
			positions = validMoves(scnr);
			posX = positions[0];
			posY = positions[1]-'0';
			
			for(int i = 0; i<16; i++) {	//iterates through array of pieces
				if(board[i].validate(posX, posY)) {
					board[i].move(posX, posY);
				}
			}

			System.out.println("\nIf you want to exit enter \"exit\"\nIf you want to test another position enter anything else.");
			another = scnr.nextLine();
			if (another.equals("exit")){ //changes value of test to exit loop
				test = false;
			}
		}
		scnr.close();
	}
}
