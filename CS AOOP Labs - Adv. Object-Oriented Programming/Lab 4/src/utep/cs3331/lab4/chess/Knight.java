package utep.cs3331.lab4.chess;
/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Class for knight attributes
 * 
 * Log History:
 * [1/29/2020] = created with same attributes as piece and moved validate method
 * [2/03/2020] = added show results method
 */

public class Knight extends Piece{
	
	//constructors
	public Knight(){
		super();
	}
	
	public Knight(String type, String color, char x, int y){
		super(type, color, x, y);
	}
	
	/*validateKnight checks that a move is valid for a knight
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public Boolean validate(char posX, int posY) {
		
		//validates that the piece moves 2 forward/backward and 1 to a side or 1 forward/backward and 2 to a side
		return((Math.abs(posY-getPosY()) == 2 && Math.abs(getPosX()-posX) == 1)||(Math.abs(posY-getPosY()) == 1 && Math.abs(getPosX()-posX) == 2));
	}
	
	
	public void showResult(char posX, int posY) {
		if (this.validate(posX, posY)){
			System.out.println(getType() + " at " + getPosX() + getPosY() + " moved to " + posX + posY);
		}
	}
}