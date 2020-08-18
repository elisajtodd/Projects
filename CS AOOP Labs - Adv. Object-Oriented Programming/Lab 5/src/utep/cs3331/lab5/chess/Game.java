/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: 
 * This program asks the user for its name and looks if the user is a
 * returning one or a new one. If the user is returning and has stored games, the program 
 * asks the user if he wants to continue a previous game.
 * 
 * The program loads a new or a previous games and stores the pieces on an arraylist
 * using Piece.java
 * It also stores the user's information on a player object in Player.java
 * With the object pieces, the program creates a board from Board.java
 * 
 * The program asks the user if he wants to play and then allows it to move the pieces
 * specific to the user's chosen color and verifies if the piece's movement is valid.
 * The input is the letter and number where the piece is, the program looks
 * what kind of piece is on that position and verifies the move from there and changes
 * it and updates it on the board, the object and the xml file if autosave is selected.
 * 
 * The user can also print the current board to check position.
 * 
 * Log History:
 * [29/02/2020] Installed JDOM, created class 
 * [02/03/2020] Created new user xml, Saved user info and update xml
 * [03/03/2020] Start new game, change info, save game
 * [04/03/2020] Store information, Move pieces, 
 * [05/03/2020] Include classes, check valid move of a piece
 * [05/03/2020] Handle exceptions, divide in packages
 * [05/06/2020] Add collections and JUnit testing
 */

package utep.cs3331.lab5.chess;

import utep.cs3331.lab5.chess.Chessboard;
import utep.cs3331.lab5.players.Player;

import java.util.List;
import java.util.Scanner;

public class Game{
		
	static Scanner scnr = new Scanner(System.in);
   
	//1 - load user
	public static Player loadPlayer(ChessReader chessReader) {
        //ask user for credentials
    	System.out.println("Enter your username:");
    	String username = scnr.nextLine();
        return chessReader.findUser(username);
	}
	
	//2 - load chess
	public static Chessboard loadBoard(ChessReader chessReader, Player p1) {
		
		Chessboard board = new Chessboard();
		try {
    	System.out.println("Select an option:\n\t1) Load New Game\n\t2) Load Saved Game");
    	int load = scnr.nextInt();
    	
    	//load
    	if(load != 1) {
    		System.out.println("\nThese are your saved games:");
    		List<String> games = p1.getGames();
    		games.stream().forEach(e -> System.out.println("\t" + (games.indexOf(e)+1) + ") " + e));
        	System.out.print("Which one would you like to load?\n:");
        	String code = games.get(scnr.nextInt()-1);
        	board = chessReader.findGame(code);
    	}
    	//new
    	else{
    		board = chessReader.loadGame();
    	}
    	System.out.println("Loading game...");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Wrong input.");
			board = loadBoard(chessReader, p1);
		}
    	return board;
	}
	
	//3 - move pieces
	public static void play(Player player, Chessboard board1) {
		ChessboardAdapter board = new ChessboardAdapter(board1);
		//get piece to move
		board.printTurn();
		System.out.print("Piece to move: (in chess notation E.g. Qd1 for queen at d 1) \n:");
		String movement = scnr.next();
		while (!board.getPiece(movement)) {
			board.printTurn();
			System.out.print("Invalid piece, make another selection: (in chess notation Txy)\n:");
			movement = scnr.next();
		}
		//get position to move to
		System.out.print("Move to:  (in chess notation Txy) \n:");
		String movementTo = scnr.next();
		while (!board.move(movement, movementTo)) {
			System.out.print("Invalid move, make another selection: (x y)\n:");
			movementTo = scnr.next();
		}
	}
	
	public static void main(String[] args) {
		
		 ChessReader chessReader = new ChessReader();
		
		 Player p1 = loadPlayer(chessReader);		 
		 Chessboard board = loadBoard(chessReader, p1);
		 
		 ChessWriter chessWriter = new ChessWriter(chessReader);
		 chessWriter.saveUsers();
		 
		 board.print();
		 
		 System.out.println("Would you like your game to autosave?\n\t1) Yes\n\t2) No");
		 boolean autosave = scnr.nextInt()==1;
		 chessWriter.setAutoSave(autosave);
		 if(autosave) {
			chessWriter.saveGame(board);//TO-DO saveGame();
		 }
		 
		 boolean active = true;
	        
	        while(active) {
	            System.out.print("\nWhat do you want to do?\n\t1) Play the Game\n\t2) Save and exit\n\t3) Exit\n:");
		        switch(scnr.nextInt()) {
		        
		        	//move pieces
		        	case 1:	
		        		boolean play = true;
		        		while (play) {
		        			
		        			play(p1, board);
		        			board.print();
		        			//handles auto save
		        			if(autosave) {
		        				chessWriter.saveGame(board);//TO-DO saveGame();
		        			}
		        			
		        			if(board.validTurn("white")) { //loops every 2 turns
			        			System.out.print("Enter 1 to move a piece, and 2 to stop\n:");
			        			play = scnr.nextInt() == 1;
		        			}
		        		}
		        		break;
		        	
		        	//print board
		            case 2:	
	            		//save the game
	                    System.out.println("Saving...");
	                    chessWriter.saveGame(board);//TO-DO saveGame();
	                    
	                //exit
	                default:	
	                	active = false;
	                	board.printCode();
	            }
	        }
		 scnr.close();       
    }
}