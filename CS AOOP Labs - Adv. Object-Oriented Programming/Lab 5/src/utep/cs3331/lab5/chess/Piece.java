package utep.cs3331.lab5.chess;
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
 * [2/13/2020] Add attributes, move method and validate method
 */

public abstract class Piece {

	private String type;	//Knight, pawn, rook, bishop, queen, or king
	private String color;	//black or white
	private char posX;		//letter position
	private int posY;		//number position
	
	//constructors
	public Piece(){	
		this.type = "Pawn";
		this.color = "white";
		this.posX = 'A';
		this.posY = 1;
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
		String code = (color.equals("black"))? "B:" : "W:"; 
		if (type.equals("knight")) code += "N";
		else code += type.substring(0,1).toUpperCase();
		return code;
	}
	
	public void change(char newX, int newY) {
		this.posX = newX;
		this.posY = newY;
	}
	
	public void print() {
		System.out.println("- " + color + " " +type + " at " +  posX + " " + posY);
	}
	
	public boolean validate(char posX, int posY) {
		return false;
	}
	
	public void move(char newX, int newY) {
		System.out.println(type + " at " + posX + posY + " moved to " + newX + newY );
		change(newX, newY);
	}
}
