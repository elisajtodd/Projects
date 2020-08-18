package utep.cs3331.lab5.chess;
/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for king attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * [2/03/2020] = added show results method
 */

public class King extends Piece{
		
	//constructors
	public King(){
		super();
	}
	
	public King(String type, String color, char x, int y){
		super(type, color, x, y);
	}
	
	/*validateKing checks that a move is valid for a king
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	
	@Override
	public boolean validate(char posX, int posY) {
		
		//checks that king moves at most one space on each side and that it moves
		return(Math.abs(posY-getPosY()) <= 1 && Math.abs( getPosX()-posX) <= 1 && Math.abs(posY-getPosY())+ Math.abs( getPosX()-posX)>0);
	}
}