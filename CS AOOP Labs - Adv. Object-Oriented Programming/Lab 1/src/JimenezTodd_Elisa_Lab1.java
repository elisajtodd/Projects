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
 */


import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;


public class JimenezTodd_Elisa_Lab1{
	
	/* readPieces reads the file that contains the pieces information
	 * takes the name of the text file to read and returns an array of
	 * Piece objects
	 */
	public static Piece[] readPieces(String filename) {
		String currentLine;
		Piece[] board = new Piece[countLines(filename)]; //array of pieces
		
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
	
	/* countLines counts the pieces listed on a text file
	 * it takes the filename as input and outputs the
	 * integer number of lines on the file
	 */
	public static int countLines(String filename) {
		int count = 0;	//counter
		try{
	        BufferedReader textReader = new BufferedReader(new FileReader(filename));
	        while ((textReader.readLine()) != null) {	//counts lines
	        	count++;
	        }
	        textReader.close();
		}catch(Exception e){
			System.out.println(e.getClass().getCanonicalName());e.printStackTrace();
		}
		return count;
	}
	
	/* makePiece creates an object Piece from information read on file 
	 * it takes an array of strings with information of a piece and
	 * returns a Piece object with those attributes
	 */
	public static Piece makePiece(String[] piece) {
		if(piece.length == 4) {
			//sends adequate information
			Piece pieceObj = new Piece(piece[0], piece[1],  Character.toUpperCase(piece[2].charAt(0)), Integer.valueOf(piece[3]));
			return pieceObj;
		}return new Piece();
	}
	
	/* validate checks the validity of a move to all the pieces
	 * it takes the piece array and the positions that we are checking
	 * and prints the result. It does not return anything
	 */
	public static void validate(Piece[] board, char posX, int posY) {
		
		System.out.println("====================================================");		
		for(int i=0; i<board.length; i++) {	//iterates through array of pieces to test move
			
			String color = board[i].getColor();	//the following variables get information from the piece
			char origX = board[i].getPosX();
			int origY = board[i].getPosY();
			
			boolean valid = false;	//will tell if the move was valid or not
			if(board[i].getType()==null) { //wont check null pieces
				continue;
			}
			switch(board[i].getType().toUpperCase()) {
		    	case "PAWN":
		    		valid = (validatePawn(color, posX, origX, posY, origY)); //call to method to check pawn
		    		break;
		    	case "KNIGHT":
		    		valid = (validateKnight(posX, origX, posY, origY));	//call to check knight
		    		break;
		    	case "BISHOP":
		    		valid = (validateBishop(posX, origX, posY, origY));	//call to check bishop
		    		break;
		    	case "ROOK":
		    		valid = (validateRook(posX, origX, posY, origY));	//call to check rook
		    		break;
		    	case "QUEEN":
		    		valid = (validateQueen(posX, origX, posY, origY));	//call to check queen
		    		break;
		    	case "KING":
		    		valid = (validateKing(posX, origX, posY, origY));	//call to check king
		    		break;
			}
			
			//print results if valid or not
			System.out.print("-The "+ board[i].getType() + " at " + origX + ", " + origY);
			if(valid){
	    		System.out.println(" can move to " + posX + ", " + posY);
	    	}else {
	    		System.out.println(" can NOT move to " + posX + ", " + posY);
	    	}
		}
		System.out.println("====================================================");
	}
	
	/* validatePawn checks that a move is valid for a pawn 
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validatePawn(String color, char posX, char origX, int posY, int origY) {
		
		if(origX != posX && Math.abs(origX-posX) != 1 || (Math.abs(origX-posX) == 1 && Math.abs(posY-origY) > 1)) { //checks if change on letter coordinate is not possible
			return false;
		}
		if(color.equals("white")) {	//checks difference in number coordinate for white pawn
			if (posY-origY > 2 || (origY != 2 && posY-origY > 1) || posY-origY <= 0) {
				return false;
			}
		}
		if(color.equals("black")) {	//checks difference in number coordinate for black pawn
			if (origY-posY > 2 || (origY != 7 && origY-posY > 1) || origY-posY <= 0) {
				return false;
			}
		}
		return true;
	}
	
	/*validateKnight checks that a move is valid for a knight
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validateKnight(char posX, char origX, int posY, int origY) {
		
		//validates that the piece moves 2 forward/backward and 1 to a side or 1 forward/backward and 2 to a side
		return((Math.abs(posY-origY) == 2 && Math.abs(origX-posX) == 1)||(Math.abs(posY-origY) == 1 && Math.abs(origX-posX) == 2));
	}
	
	/*validateBishop checks that a move is valid for a bishop
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validateBishop(char posX, char origX, int posY, int origY) {
		
		//checks that the bishop moves diagonally by making sure the difference of the letter coordinate and integer coordinate is the same
		return(Math.abs(posY-origY) == Math.abs(origX-posX) && Math.abs(posY-origY)>0);
	}
	
	/*validateRook checks that a move is valid for a rook 
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validateRook(char posX, char origX, int posY, int origY) {
		
		//checks that the letter coordinate or integer coordinate stayed the same: meaning vertical or horizontal movement
		return((posY == origY) != (posX == origX));
	}
	
	/*validateKing checks that a move is valid for a king
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validateKing(char posX, char origX, int posY, int origY) {
		
		//checks that king moves at most one space on each side and that it moves
		return(Math.abs(posY-origY) <= 1 && Math.abs(origX-posX) <= 1 && Math.abs(posY-origY)+ Math.abs(origX-posX)>0);
	}
	
	/*validateQueen checks that a move is valid for a queen
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public static Boolean validateQueen(char posX, char origX, int posY, int origY) {
		
		//checks that queen moves like a bishop or like a rook
		return((Math.abs(posY-origY) == Math.abs(origX-posX) && Math.abs(posY-origY)>0) || (posY == origY) != (posX == origX));
	}

	/*main method tests the program*/
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);	//will take user input
		Piece[] board = readPieces("Chess_Piece.txt");	//array of pieces
		boolean test = true;	//to keep testing positions
		
		System.out.println("The following pieces are considered:");
		
		//prints pieces read
		for(int i=0; i<board.length; i++) {
			System.out.println(board[i].toString());
		}
		
		//asks for first input
		System.out.println("\nWhich position do you want to move to? (letter,number)");
		String[] position = scnr.next().replaceAll("\\s", "").split(",");
		
		//iterates until user exits
		while(test) {
			
			//makes sure input is valid for format
			while(position.length < 2 || !Character.isLetter(position[0].charAt(0)) || 
					!Character.isDigit(position[1].charAt(0))) {
				System.out.println("Enter the position in this format: \"letter,number\"");
				position = scnr.next().replaceAll("\\s", "").split(",");
			}
			
			char posX = Character.toUpperCase(position[0].charAt(0)); //makes first input a capital letter
			int posY = Integer.valueOf(position[1]);	//makes second input an integer value
			
			//makes sure input is in bounds
			if(!(posX > 72 || posX < 65 || posY > 8 || posY < 1 )) {
				validate(board, posX , posY); // calls validate
				
				System.out.println("\nEnter another position to test (letter,number), or type 'exit' to finish"); //loop input
				position = scnr.next().replaceAll("\\s", "").split(",");
				
				if(position[0].equals("exit")) { //changes value of test to exit loop
					test=false;
					System.out.println("exiting...");
				}
			}
			
			else{
				System.out.println("Range goes from A-H and 1-8, try again");
				position = scnr.next().replaceAll("\\s", "").split(",");
			}			
		}
		scnr.close();
	}
}
