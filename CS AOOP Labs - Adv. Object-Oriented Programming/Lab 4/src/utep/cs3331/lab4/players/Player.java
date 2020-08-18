/* Author: Elisa Jimenez Todd
 * Instructor: Dr. Oscar Mondragon
 * Class: CS 3331-Object-Oriented Programming
 * 
 * Description: 
 * 
 * Log History:
 * [06/03/2020] Created class
 */

package utep.cs3331.lab4.players;

public class Player {

	private String username;	//username
	private int level;	//1-Novice	2-Medium	3-Advanced	4-Expert
	private String color;	//white if true, black if false
	
	//constructor
	public Player() {
		
	}
	
	public Player(String username, int level, String color) {
		this.username = username;
		this.level = level;
		this.color = color;
	}
	
	//getters
	public String getUsername() {
		return username;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getColor() {
		return color;
	}
	
	//setters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}
