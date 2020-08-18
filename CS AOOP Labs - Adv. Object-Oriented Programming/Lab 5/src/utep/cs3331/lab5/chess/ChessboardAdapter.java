package utep.cs3331.lab5.chess;

public class ChessboardAdapter implements ChessboardInterface{
	
	private Chessboard board;
	
	public ChessboardAdapter(Chessboard board) {
		this.board = board;
	}
	
	public Piece[][] getBoard() {
		return board.getBoard();
	}

	public void printCode() {
		board.printCode();
	}

	public void printTurn() {
		board.printTurn();
	}

	public boolean validTurn(String color) {
		return board.validTurn(color);
	}

	//receives a string for chess notation, adapts values to call method on chessboard
	public boolean getPiece(String movement) {
		boolean available = false;
		char piece = movement.toUpperCase().charAt(0);
		char x = movement.charAt(1);
		int y = movement.charAt(2) - '0';
		if (board.getPiece(x, y)){
			if (board.getBoard()[y-1][x-97].toString().charAt(2) != piece) {
				System.out.println("Piece type at location does not match the requested type...");
			}
			else {
				available = true;
			}
		}
		return available;
	}

	public boolean move(String m1, String m2) {
		char x = m1.charAt(1);
		int y = m1.charAt(2) - '0';
		char newx = m2.charAt(1);
		int newy = m2.charAt(2) - '0';
		return board.move(x,y,newx,newy);
	}

	public void print() {
		board.print();		
	}

}
