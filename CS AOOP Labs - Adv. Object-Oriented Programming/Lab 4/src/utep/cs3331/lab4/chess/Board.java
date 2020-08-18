package utep.cs3331.lab4.chess;

import java.util.ArrayList;

public class Board {
	private char[][] board;
	private int rows;
	private int columns;
	
	public Board () {
	}
	
	//constructor
	public Board (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		board = new char[rows][columns];
		for (int i = 0; i<rows; i++) {
			for (int j = 0; j<columns; j++) {
				board[i][j] = '-';
			}
		}
	}
	
	public Board (int rows, int columns, ArrayList<Piece> pieces) {
		this.rows = rows;
		this.columns = columns;
		board = new char[rows][columns];
		for (int i = 0; i<rows; i++) {
			for (int j = 0; j<columns; j++) {
				board[i][j] = '-';
			}
		}
		//stores pieces in the board
		for (Piece piece: pieces) {
			int x = piece.getPosX() - 97;
			int y = piece.getPosY() - 1;
			if (piece.getColor().toLowerCase().equals("white")) {
				board[y][x] = 'w';
			}else {
				board[y][x] = 'b';
			}
			
		}
	}
	
	//move
	public boolean move(char x, int y, char newx, int newy) {
		if(board[newy-1][newx-97] != '-') {	//checks if new position is available
			return false;
		}
		board[newy-1][newx-97] = board[y-1][x-97]; //changes position
		board[y-1][x-97] = '-';
		return true;
	}
	
	//print board
	public void print () {
		System.out.println("BOARD:");
		System.out.println(" |———+———+———+———+———+———+———+———|");
		for (int i = rows-1; i>=0; i --) {
			System.out.print(i+1);
			for (int j = 0; j<columns; j++) {
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.println("|");
			System.out.println(" |———+———+———+———+———+———+———+———|");
		}
		System.out.println("   a   b   c   d   e   f   g   h");
	}
}
