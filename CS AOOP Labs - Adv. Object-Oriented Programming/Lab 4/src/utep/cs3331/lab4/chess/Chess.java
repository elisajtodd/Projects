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
 * [06/03/2020] Handle exceptions, divide in packages
 */

package utep.cs3331.lab4.chess;

import utep.cs3331.lab4.players.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Chess {
	
	static Scanner scnr = new Scanner(System.in); //static scanner to use on methods and main
	
	/*creates a random id*/
	public static String createID() { //creates random ID
		Random random = new Random();
		String id = "";
		for(int i = 0; i<6; i++) { //first 6 characters are letters
			id = id + (char) (random.nextInt(26) + 97);
		}
		for(int i = 0; i<2; i++) { //last 2 characters are numbers
			id = id + random.nextInt(10);
		}
		return id;
	}
	
	/*prints stored pieces on current game*/
	public static void printPieces(ArrayList<Piece> objectPieces) {
		for(Piece piece: objectPieces) {
			piece.print();
		}
	}
	
	/*moves pieces*/
	public static void play(Player player, List<Element> listPieces, ArrayList<Piece> objectPieces, Board board, boolean autosave) {
		
		Element selected = new Element("piece");
		char x;	//position x
		int y;	//position y
		int index = 0;	//index corresponding in array list of objects
		boolean isValid = false;	//move is valid
		
		System.out.print("Piece to move: (x y)\n:");
		
		while(!isValid) {
			x = scnr.next().charAt(0);
			y = scnr.nextInt();
			index = 0;
			for(Element piece: listPieces) {
				//finds the piece and verifies it is a color that the current player can move
				if(piece.getAttributeValue("locationX").charAt(0) == x && Integer.valueOf(piece.getAttributeValue("locationY")) == y && piece.getAttributeValue("color").equals(player.getColor())) {
					System.out.println("move " + piece.getValue() + " from " +x + " " + y + " to: (x y)");
					selected = piece;
					isValid = true;
					break;
				}
				index++;
			}
			
			if(isValid) {
				char a = scnr.next().charAt(0);
				int b = scnr.nextInt();
				//verifies if the move is valid for the piece and that there is no other piece on the new location
				if(objectPieces.get(index).validate(a, b) && board.move(x, y, a, b)) {
					selected.setAttribute("locationX", Character.toString(a));
					selected.setAttribute("locationY", Integer.toString(b));
					objectPieces.get(index).change(a, b);
					
				}else {
					System.out.println("Invalid move for " + selected.getValue());
				}
			}else {
				System.out.println("Not a valid piece, try again");
			}
		}
	}
	
	public static ArrayList<Piece> parse(List<Element> listPieces) {
		ArrayList<Piece> objectPieces = new ArrayList<Piece>();	//list that stores the pieces
		Piece pieceObj = null;
		for(Element piece: listPieces) {
			if(piece.getAttributeValue("play").equals("True")) {	//checks that the piece is active
				switch(piece.getText().toLowerCase()) {
					case "pawn":	//call to method to check pawn
						pieceObj = new Pawn(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					case "knight":	//call to method to check knight
						pieceObj = new Knight(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					case "bishop":	//call to method to check bishop
						pieceObj = new Bishop(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					case "rook":	//call to method to check rook
						pieceObj = new Rook(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					case "queen":	//call to method to check queen
						pieceObj = new Queen(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					case "king":	//call to method to check king
						pieceObj = new King(piece.getText(), piece.getAttributeValue("color"),  piece.getAttributeValue("locationX").charAt(0), Integer.valueOf(piece.getAttributeValue("locationY"))); 
						break;
						
					default:
						System.out.println("WRONG PIECE");
				}
			}
			objectPieces.add(pieceObj); //adds pieces to array list
		}
		return objectPieces;
	}

	public static void saveGame(Document userFile, Document game, String gameID){
        try{	     	
	    	XMLOutputter write = new XMLOutputter(Format.getPrettyFormat()); //writing to xml file
	    	write.output(userFile, new FileOutputStream("src/utep/cs3331/lab4/files/Users.xml", false)); //updates users id
	    	write.output(game, new FileOutputStream("src/utep/cs3331/lab4/files/" + gameID + ".xml", false)); //writes in game file current game
	    	
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
   
	public static void main(String[] args) {
    	try {
    		//Create a document builder
    		SAXBuilder saxBuilder = new SAXBuilder();

            //Create a DOM tree Obj and read the XML USER file
            Document userFile = saxBuilder.build(new File("src/utep/cs3331/lab4/files/Users.xml"));
            //chess xml
            Document chessFile;
            
            //get the root element
            Element root = userFile.getRootElement(); //users
            List<Element> users = root.getChildren(); //user, user,
            
            //will store user's and game information
        	Element inUser = new Element("user");
        	String expertise = "Novice";
        	int level = 1;
        	String color = "white";
        	String opColor = "black";
        	String gameID = null;
        	boolean newUser = true;
        	boolean load = true;
        	boolean autosave = true;
        	String saveStatus = "active";
        	
            //ask user for credentials
        	System.out.println("Enter your username:");
        	String username = scnr.nextLine();
        	
        	//looks for existing user
        	for (Element user: users ) {
                if (user.getChild("name").getValue().equals(username)) {
                	System.out.println("\nWelcome back " + username + "!");
                	newUser = false;
                	inUser = user;
                	if(inUser.getChild("games").getChild("id") != null) {
	                	System.out.print("Do you want to load a previous game?\n\t1) Yes\n\t2) No\n:");
	                	load = scnr.nextInt() != 1;
                	}
                	break;
                }
            }
            
        	//creates new user
            if (newUser) {
            	System.out.println("\nWelcome " + username + "!");
            	
            	//choosing level
            	System.out.print("Please indicate your level of expertise:\n\t1) Novice\n\t2) Medium\n\t3) Advanced\n\t4) Master\n:");
            	switch(scnr.nextInt()) {
            		case 1:
            			expertise = "Novice";
            			level = 1;
            			break;
            		case 2:
            			expertise = "Medium";
            			level = 2;
            			break;
            		case 3:
            			expertise = "Advanced";
            			level = 3;
            			break;
            		case 4:
            			expertise = "Master";
            			level = 4;
            		default:
            			expertise = "unknown";
            	}
            	//choosing color
            	System.out.print("\nChoose your color:\n\t1) white\n\t2) black\n:");
            	switch(scnr.nextInt()) {
	        		case 1:
	        			color = "white";
	        			opColor = "black";
	        			break;
	        		default:
	        			color = "black";
	        			opColor = "white";
            	}
            	//adds new user info to user file
            	inUser.addContent(new Element("name").setText(username)); //adds user name
            	inUser.addContent(new Element("expertise_level").setText(expertise)); //adds expertise level
            	inUser.addContent(new Element("color").setText(color)); // adds color
            	inUser.addContent(new Element("games"));
            	root.addContent(inUser); //adds to root
            	
            	XMLOutputter write = new XMLOutputter(Format.getPrettyFormat()); //writing to xml file
            	write.output(userFile, new FileOutputStream("src/utep/cs3331/lab4/files/Users.xml", false));
            }
            
            //creates player objects
            Player p1 = new Player(username, level, color);
            //Player p2 = new Player("CPU", level, color);
            
            //display games
            if (!load) {
            	System.out.println("\nThese are your saved games:");
            	List<Element> games = inUser.getChildren().get(3).getChildren(); //list of game id's
            	for(Element game: games) {
            		System.out.println("\t" + (games.indexOf(game)+1) + ") " + game.getValue());
            	}
            	System.out.print("Which one would you like to load?\n:");
            	gameID = games.get(scnr.nextInt()-1).getValue();
            	
            	System.out.println("Loading game...");
            	chessFile = saxBuilder.build(new File("src/utep/cs3331/lab4/files/" + gameID + ".xml"));
            	
            //make new game
            }else {
                System.out.print("\nStarting new game\nDo you want your game to auto save?\n\t1) Yes\n\t2) No\n:");
                autosave = scnr.nextInt() == 1;	//auto save
                saveStatus = autosave ? "active" : "inactive"; //set auto save to active or inactive
            	System.out.println("\nLoading game...");            	
            	chessFile = saxBuilder.build(new File("src/utep/cs3331/lab4/files/newChessGame.xml"));
            }
            
        	List<Element> elements = chessFile.getRootElement().getChildren();	//list of children
            Element pieces = elements.get(0);	//pieces
            List<Element> listPieces = pieces.getChildren(); //list of pieces
            
            Element board = elements.get(1);	//board config
            
            Element chess_user = elements.get(2);	//user of current game
            chess_user.getChild("name").setText(username);
            chess_user.getChild("expertise_level").setText(expertise);
            chess_user.getChild("user_color").setText(color);
            
            Element chess_user2 = elements.get(3);	//user 2 of current game
            chess_user2.getChild("expertise_level").setText(expertise); //match level with user
            chess_user2.getChild("user_color").setText(opColor);
            
            Element game = elements.get(4);	//game
            if(gameID == null) {
            	gameID = createID();	//create game id
            }
            
            if(load) {
	            //save gameID
	            inUser.getChildren().get(3).addContent(new Element("id").setText(gameID)); // adds id
	            game.getChild("id").setText(gameID);
            }
            
            //set game auto save, and save variables
            if (newUser) {
            	game.getChild("auto_save").setText(saveStatus);
            }else {
            	saveStatus=game.getChildText("auto_save");
            	autosave = saveStatus.equals("active");
            }
            	
            ArrayList<Piece> objectPieces = parse(listPieces);
            
            Board currBoard = new Board(Integer.valueOf(board.getChildText("rows")), Integer.valueOf(board.getChildText("columns")), objectPieces);
            
            printPieces(objectPieces);
            currBoard.print();
            
            boolean active = true;
            
            while(active) {
	            System.out.print("\nWhat do you want to do?\n\t1) Play the Game\n\t2) Print current board\n\t3) Save and exit\n\t4) Exit\n:");
		        switch(scnr.nextInt()) {
		        
		        	//move pieces
		        	case 1:	
		        		boolean play = true;
		        		while (play) {
		        			
		        			play(p1, listPieces, objectPieces, currBoard, autosave);
		        			//handles auto save
		        			if(autosave) {
		        				saveGame(userFile, chessFile, gameID);
		        			}
		        			
		        			System.out.print("Enter 1 to move a piece, and 2 to stop\n:");
		        			play = scnr.nextInt() == 1;
		        		}
		        		break;
		        	
		        	//print board
		            case 2:	
		            	currBoard.print();
		            	break;
		            	
		            //save and exit
	            	case 3:	
	            		//save the game
	                    System.out.println("Saving...\nYour game ID is: " + gameID);
	                    saveGame(userFile, chessFile, gameID);
	                
	                //exit
	                default:	
	                	active = false;
	            }
            }
            scnr.close();
             
    	 } catch (JDOMException e) {
             e.printStackTrace();
    	 } catch (FileNotFoundException e) {
    		 System.out.println("File not found.\nMake sure that you have all needed files...");
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("input/output exception...");
         } catch (InputMismatchException e) {
             System.out.println("Error: Make sure that you input the required value...");
         } finally {
        	 System.out.println("See you soon!");
         }
    }
}