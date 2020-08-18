/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: Stores player's information
 * 
 * Log History:
 * [06/03/2020] Created class
 * [5/3/2020] added Games feature
 */

package utep.cs3331.lab5.players;

import java.util.Scanner;
import java.util.List;

public class Player {

	private String username;	//username
	private String level;	//1-Novice	2-Medium	3-Advanced	4-Expert
	private String color;	//white if true, black if false
	private List<String> savedGames; //list of player's games
	
	//constructor
	public Player() {
		
	}
	
	public Player(String username, String level, String color) {
		this.username = username;
		this.level = level;
		this.color = color;
	}
	
	//getters
	public String getUsername() {
		return username;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getColor() {
		return color;
	}
	
	public List<String> getGames() {
		return savedGames;
	}
	
	//setters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setGames(List<String> games) {
		this.savedGames = games;
	}
	
	//other
	public void newPlayer() {
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		//choosing level
    	System.out.print("Please indicate your level of expertise:\n\t1) Novice\n\t2) Medium\n\t3) Advanced\n\t4) Master\n:");
    	switch(scnr.nextInt()) {
    		case 1:
    			level = "Novice";
    			break;
    		case 2:
    			level = "Medium";
    			break;
    		case 3:
    			level =  "Advanced";
    			break;
    		case 4:
    			level = "Master";
    			break;
    		default:
    			level = "unknown";
    	}
    	//choosing color
    	System.out.print("\nChoose your color:\n\t1) white\n\t2) black\n:");
    	switch(scnr.nextInt()) {
    		case 1:
    			color = "white";
    			break;
    		default:
    			color = "black";
    	}
	}
}
