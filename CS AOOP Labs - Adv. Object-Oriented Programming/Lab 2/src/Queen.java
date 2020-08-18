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

public class Queen {
	
	private String type;
	private String color;	//black or white
	private char posX;		//letter position
	private int posY;		//number position
	
	//constructors
	public Queen (){	
		this.type = "Queen";
		this.color = "white";
		this.posX = 'A';
		this.posY = 4;
	}
	
	public Queen (String type, String color, char x, int y){
		this.type = type;
		this.color = color;
		this.posX = x;
		this.posY = y;
	}
	
	//getters
	public String getType() {
		return this.type;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public char getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	//other
	
	public String toString() {
		return ("Queen, " + color + ", " + posX + ", " + posY);
	}
	
	/*validateQueen checks that a move is valid for a queen
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public Boolean validate(char posX, int posY) {
		
		//checks that queen moves like a bishop or like a rook
		return((Math.abs(posY-this.posY) == Math.abs(this.posX-posX) && Math.abs(posY-this.posY)>0) || (posY == this.posY) != (posX == this.posX));
	}
	
	public void showResult(char posX, int posY) {
		System.out.print(this.type + " at " + this.posX + this.posY);
		if (this.validate(posX, posY)){
			System.out.println(" CAN move to " + posX + posY);
		}else{
			System.out.println(" CANNOT move to " + posX + posY);
		}
	}
}