/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: 
 * 
 * Log History:
 * [5/3/2020] created class, made methods to read users and chess files, moved parser to this class
 */
package utep.cs3331.lab5.chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import utep.cs3331.lab5.players.Player;

public class ChessReader{
	
	private SAXBuilder saxBuilder;
	private Document userFile;
	private Element root;
	private List<Element> users;
	private Document chessFile;
	private String gameID;
	private String username;
	private boolean newGame = false;
	
	public ChessReader() {
		try {
			//Create a document builder
			this.saxBuilder = new SAXBuilder();

	        //Create a DOM tree Object and read the XML USER file
	        this.userFile = saxBuilder.build(new File("src/utep/cs3331/lab5/files/Users.xml"));
	        
	        //get the root element
	        this.root = userFile.getRootElement(); //users
	        this.users = root.getChildren(); //user1, user2...
	        
		 } catch (JDOMException e) {
	         e.printStackTrace();
		 } catch (FileNotFoundException e) {
			 System.out.println("File not found.\nMake sure that you have all needed files...");
	     } catch (IOException e) {
	         e.printStackTrace();
	         System.out.println("input/output exception...");
	     }
	}
	
	public Document getUserFile() {
		return this.userFile;
	}
	
	public Document getChessFile() {
		return this.chessFile;
	}
	
	public String getGameID() {
		return this.gameID;
	}
	
	public Player findUser(String username) {
		this.username = username;
		Player currPlayer = new Player();
		currPlayer.setUsername(username);
		boolean newPlayer = true;
		//looks for existing user
    	for (Element user: this.users ) {
    		//load values into player
            if (user.getChild("name").getValue().equals(username)) {
            	currPlayer.setLevel(user.getChild("expertise_level").getValue());
            	currPlayer.setColor(user.getChild("color").getValue());
            	List<Element> gamesEl = user.getChild("games").getChildren();
            	List<String> games = new Vector<String>();
            	for (Element game : gamesEl) {
            	    games.add(game.getValue());   
            	}
            	currPlayer.setGames(games);
            	System.out.println("Welcome back " + username + "!");
            	newPlayer = false;
            }
        }
    	//create new user if not found
    	if(newPlayer) {
    		System.out.println("Welcome " + username + "!");
    		currPlayer.newPlayer();
    		//adds new user info to user file
    		Element user = new Element("user");
    		user.addContent(new Element("name").setText(username)); //adds user name
    		user.addContent(new Element("expertise_level").setText(currPlayer.getLevel())); //adds expertise level
    		user.addContent(new Element("color").setText(currPlayer.getColor())); // adds color
    		user.addContent(new Element("games"));
    		root.addContent(user); //adds to root
    	}
		return currPlayer;
	}
	
	/* loads game from xml */
	public Chessboard findGame(String code) {
		this.gameID = code;
		Chessboard board = new Chessboard();
		try {
			this.chessFile = saxBuilder.build(new File("src/utep/cs3331/lab5/files/" + code + ".xml"));
			
			Element chessRoot = chessFile.getRootElement();
			List<Element> elements = chessRoot.getChildren();	//list of elements
			
	        List<Element> listPieces = elements.get(0).getChildren(); //list of pieces
	        ArrayList<Piece> objectPieces = parse(listPieces);	//pieces made objects
	        	       
	        Element dimensions = elements.get(1);	//board configuration
	        int row = Integer.valueOf(dimensions.getChild("rows").getValue());
	        int column = Integer.valueOf(dimensions.getChild("columns").getValue());
	        if(newGame) {
	        	code = createID();	        
		       	for(Element user: root.getChildren()) {
		       		if (user.getChild("name").getValue().equals(this.username)) {
		       			user.getChild("games").addContent(new Element("id").setText(this.gameID)); // adds id to user
		       		}
		       	}
	       	}   
			//save gameID on game
		    chessRoot.getChild("game").getChild("id").setText(this.gameID);
	        board = new Chessboard(code, row, column, objectPieces);
	        
		} catch (JDOMException e) {
	        e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.\nMake sure that you have all needed files...");
		} catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("input/output exception...");
		}
		return board;
	}	
	
	/* load new game */
	public Chessboard loadGame() {
		this.newGame = true;
		return findGame("newChessGame");
	}	
	
	/* creates a random id */
	public String createID() { //creates random ID
		Random random = new Random();
		String id = "";
		for(int i = 0; i<4; i++) { //first 6 characters are letters
			id = id + (char) (random.nextInt(26) + 97);
		}
		for(int i = 0; i<2; i++) { //last 2 characters are numbers
			id = id + random.nextInt(10);
		}
		this.gameID = id;
		return id;
	}
	
	/* parses information to an ArrayList */
	private ArrayList<Piece> parse(List<Element> listPieces) {
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
}
