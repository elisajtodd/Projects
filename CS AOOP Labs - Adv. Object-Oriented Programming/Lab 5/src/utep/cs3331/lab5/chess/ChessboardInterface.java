package utep.cs3331.lab5.chess;

public interface ChessboardInterface {
	
	public Piece[][] getBoard();
	
	//show code
	public void printCode();
	
	public void printTurn() ;
	
	//validates the turn
	public boolean validTurn(String color) ;
	//getPiece
	public boolean getPiece(String movement) ; //receives in chess Notation Qb2
	//move
	public boolean move(String moveFrom, String moveTo) ;  //receives in chess Notation Qb2
	//print board
	public void print () ;
}
