/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for rook attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * 
 * [2/03/2020] = added show results method
 */
public class Rook extends Piece{
	
	//constructors
	public Rook(){
		super();
	}
	
	public Rook(String type, String color, char x, int y){
		super(type, color, x, y);
	}
	
	/*validateRook checks that a move is valid for a rook 
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	
	@Override
	public Boolean validate(char posX, int posY) {
		//checks that the letter coordinate or integer coordinate stayed the same: meaning vertical or horizontal movement
		return((posY == getPosY()) != (posX == getPosX()));
	}
}