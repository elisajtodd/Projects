package utep.cs3331.lab5.chess;
/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for pawn attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * [2/03/2020] = added show results method
 */

public class Pawn extends Piece{
	
	//constructors
	public Pawn(){
		super();
	}
	
	public Pawn(String type, String color, char x, int y){
		super(type, color, x, y);
	}
	
	/* validatePawn checks that a move is valid for a pawn 
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	
	@Override
	public boolean validate(char posX, int posY) {
		
		if(getPosX() != posX && Math.abs(getPosX()-posX) != 1 || (Math.abs(getPosX()-posX) == 1 && Math.abs(posY-getPosX()) > 1)) { //checks if change on letter coordinate is not possible
			return false;
		}
		if(getColor().equals("white")) {	//checks difference in number coordinate for white pawn
			if (posY-getPosY() > 2 || (getPosY() != 2 && posY-getPosY() > 1) || posY-getPosY() <= 0) {
				return false;
			}
		}
		if(getColor().equals("black")) {	//checks difference in number coordinate for black pawn
			if (getPosY()-posY > 2 || (getPosY() != 7 && getPosY()-posY > 1) || getPosY()-posY <= 0) {
				return false;
			}
		}
		return true;
	}
	
}