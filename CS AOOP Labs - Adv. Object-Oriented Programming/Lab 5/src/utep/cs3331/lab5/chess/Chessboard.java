/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: 
 * 
 * Log History:
 * [5/3/2020] created class, made methods to store pieces, validate move and retrieve a piece
 */

package utep.cs3331.lab5.chess;

import java.util.ArrayList;
import java.util.Iterator;

public class Chessboard {
	
	private String code;
	private Piece[][] board;
	private int rows;
	private int columns;
	private static boolean whiteTurn = true;
	
	public Chessboard () {
	}
	
	//constructor	
	public Chessboard (String code, int rows, int columns, ArrayList<Piece> pieces) {
		this.code = code;
		this.rows = rows;
		this.columns = columns;
		this.board = new Piece[rows][columns];

		//stores pieces in the board
		for (Iterator<Piece> piece = pieces.iterator(); piece.hasNext();) { //stores pieces array into board and deletes array
			Piece p = piece.next();
			int x = p.getPosX() - 97;
			int y = p.getPosY() - 1;
			board[y][x] = p;
			piece.remove();
		}
	}
	public Piece[][] getBoard(){
		return this.board;
	}
	
	//show code
	public void printCode() {
		System.out.print("Your game code is: " + this.code);
	}
	
	public void printTurn() {
		String turn = whiteTurn ? "White" : "Black";
		System.out.println("Turn: " + turn);
	}
	
	//validates the turn
	public boolean validTurn(String color) {
		return(whiteTurn == color.equals("white"));
	}
	//getPiece
	public boolean getPiece(char x, int y) {
		if(board[y-1][x-97] == null || !validTurn(board[y-1][x-97].getColor())) return false; //checks piece exists and is the right color
		board[y-1][x-97].print();
		return true;
	}
	
	//move
	public boolean move(char x, int y, char newx, int newy) {
		if(!getPiece(newx, newy) && board[y-1][x-97].validate(newx, newy)) {	//checks if new position is available and valid
			board[newy-1][newx-97] = board[y-1][x-97]; //changes position
			board[y-1][x-97].move(newx, newy); //change piece info
			board[y-1][x-97] = null;
			whiteTurn = !whiteTurn;
			return true;
		}
		return false;
	}
	
	//print board
	public void print () {
		System.out.println("BOARD:");
		System.out.println(" |-----------------------------------------------|");
		for (int i = rows-1; i>=0; i --) {
			System.out.print(i+1);
			for (int j = 0; j<columns; j++) {
				if(board[i][j]!= null){
					System.out.print("| " + board[i][j].toString() + " ");
				}else {
					System.out.print("|     ");
				}
			}
			System.out.println("|");
			System.out.println(" |-----------------------------------------------|");
		}
		System.out.println("    a     b     c     d     e     f     g     h");
	}
}
