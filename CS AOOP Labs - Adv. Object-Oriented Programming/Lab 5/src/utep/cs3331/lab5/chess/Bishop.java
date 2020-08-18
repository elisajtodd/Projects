package utep.cs3331.lab5.chess;
/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for bishop attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * [2/03/2020] = added show results method
 */

public class Bishop extends Piece{
	
	//constructors
	public Bishop(){
		super();
	}
	
	public Bishop(String type, String color, char x, int y){
		super(type, color, x, y);
	}

	/*validateBishop checks that a move is valid for a bishop
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	
	@Override
	public boolean validate(char posX, int posY) {
		
		//checks that the bishop moves diagonally by making sure the difference of the letter coordinate and integer coordinate is the same
		return(Math.abs(posY-getPosY()) == Math.abs(getPosX()-posX) && Math.abs(posY-getPosY())>0);
	}
}