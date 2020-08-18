package utep.cs3331.lab5.chess;
/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for queen attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * 
 * [2/03/2020] = added show results method
 */

public class Queen extends Bishop{
		
	//constructors
	public Queen(){
		super();
	}
	
	public Queen(String type, String color, char x, int y){
		super(type, color, x, y);
	}
	
	/*validateQueen checks that a move is valid for a queen
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	
	@Override
	public boolean validate(char posX, int posY) {
		
		//checks that queen moves like a bishop or like a rook
		return (super.validate(posX, posY) || ((posY == getPosY()) != (posX == getPosX())));
	}
}