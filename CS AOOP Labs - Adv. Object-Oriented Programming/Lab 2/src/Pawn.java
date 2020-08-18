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

public class Pawn {
	
	private String type;
	private String color;	//black or white
	private char posX;		//letter position
	private int posY;		//number position
	
	//constructors
	public Pawn(){
		this.type = "Pawn";
		this.color = "white";
		this.posX = 'A';
		this.posY = 1;
	}
	
	public Pawn(String type, String color, char x, int y){
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
		return ("Pawn, " + color + ", " + posX + ", " + posY);
	}
	
	/* validatePawn checks that a move is valid for a pawn 
	 * it takes the information of the piece and the new position
	 * and returns a boolean value true if the move is valid
	 */
	public Boolean validate(char posX, int posY) {
		
		if(this.posX != posX && Math.abs(this.posX-posX) != 1 || (Math.abs(this.posX-posX) == 1 && Math.abs(posY-this.posY) > 1)) { //checks if change on letter coordinate is not possible
			return false;
		}
		if(this.color.equals("white")) {	//checks difference in number coordinate for white pawn
			if (posY-this.posY > 2 || (this.posY != 2 && posY-this.posY > 1) || posY-this.posY <= 0) {
				return false;
			}
		}
		if(this.color.equals("black")) {	//checks difference in number coordinate for black pawn
			if (this.posY-posY > 2 || (this.posY != 7 && this.posY-posY > 1) || this.posY-posY <= 0) {
				return false;
			}
		}
		return true;
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