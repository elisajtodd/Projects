/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: This class aids Lab 1 with the Piece class and attributes
 * it holds type of piece, color, posX(letter) and posY(number)
 * 
 * Log History:
 * [1/24/2020] Created file with main and Piece class. Define 
 * Piece.java 's attributes and methods
 * [1/25/2020] Modified types from string to char, changed variable names
 */

public class Piece {
	
	private String type; 	//type of piece: rook, bishop, queen, king, knight, or pawn
	private String color;	//black or white
	private char posX;		//letter position
	private int posY;		//number position
	
	//constructors
	public Piece(){	
	}
	
	public Piece(String type, String color, char x, int y){
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
		return (type + ", " + color + ", " + posX + ", " + posY);
	}
}
