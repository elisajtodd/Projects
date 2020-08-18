package utep.cs3331.lab5.chess;

import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.util.List;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ChessWriter {
	
	SAXBuilder saxBuilder;
	Document userFile;
	Document chessFile;
	String gameID;
	
	public ChessWriter(ChessReader chessReader) {
		//Create a document builder
		this.saxBuilder = new SAXBuilder();
		this.userFile = chessReader.getUserFile();
		this.chessFile = chessReader.getChessFile();
		this.gameID = chessReader.getGameID();
	}
	
	public void setAutoSave(boolean autosave) {
		Element root = chessFile.getRootElement(); //chess
	    Element data = root.getChildren().get(4);
		  //set game auto save, and save variables
	    data.getChild("auto_save").setText(autosave ? "active" : "inactive");
	}
	
	public void saveUsers(){
        try{	     	
	    	XMLOutputter write = new XMLOutputter(Format.getPrettyFormat()); //writing to xml file
	    	write.output(userFile, new FileOutputStream("src/utep/cs3331/lab5/files/Users.xml", false)); //updates users id
	    }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void savePieces(Chessboard board) {
		Element root = chessFile.getRootElement(); //chess
	    Element pieceData = root.getChildren().get(0);
	    List<Element> piecesXML = pieceData.getChildren();
	    
	    Piece[][] pieces = board.getBoard();
	    int index = 0;
	    for(int i = 0; i<pieces.length; i++) {
	    	for(int j = 0; j<pieces.length; j++) {
	    		if (pieces[i][j]!= null) {
			    	piecesXML.get(index).setAttribute("locationX", "" + pieces[i][j].getPosX());
				    piecesXML.get(index).setAttribute("locationY", "" + pieces[i][j].getPosY());
				    piecesXML.get(index).setAttribute("color", "" + pieces[i][j].getColor());
				    piecesXML.get(index).setText(pieces[i][j].getType());
			    	index++;
	    		}
	    	}
	    }
		
        try{	     	
	    	XMLOutputter write = new XMLOutputter(Format.getPrettyFormat()); //writing to xml file
	    	write.output(chessFile, new FileOutputStream("src/utep/cs3331/lab5/files/" + this.gameID + ".xml", false)); //writes in game file current game
	    	
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void saveGame(Chessboard board){	    
        saveUsers();
        savePieces(board);
	}
}
