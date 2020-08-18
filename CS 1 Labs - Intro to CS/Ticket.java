/* CS 1301 - Inttro to Computer Science
 * Instructor: Villanueva
 * Ticket
 * Submitted by: Elisa Jimenez
 */

public class Ticket{
	private String eventName;	//name of eventName
	private String seat;		//seat number, row
	private double price;		//ticket price
	private boolean available;	//seat is available or not
	
	//Default constructor
	public Ticket(){
	}
	//Constructor receives attributes
	public Ticket( String eventName, String seat, double price, boolean available){
		this.eventName = eventName;
		this.seat = seat;
		this.price = price;
		this.available = available;
	}
	//setters
	public void setEventName(String eventName){
		this.eventName= eventName;
	}
	public void setSeat (String seat){
		this.seat=seat;
	}
	public void setPrice (double price){
		this.price = price;
	}
	public void setAvailable (boolean available){
		this.available = available;
	}
	//getters
	public String getSeat(){
		return this.seat;
	}
	public String getEventName(){
		return this.eventName;
	}
	public double getPrice(){
		return this.price;
	}
	public boolean getAvailable(){
		return this.available;
	}
	//print Ticket
	public void printTicket(){
		System.out.print("Event: " + this.eventName + ", Seat: " + this.seat + ", Price: $" + this.price);
		if (this.available){
			System.out.println(", AVAILABLE");
		}else
			System.out.println(", NOT AVAILABLE");
	}
	public static void main (String[] args){
		Ticket myTicket = new Ticket("The Nutcracker", "2A", 75.0, true);
		
		myTicket.printTicket();
	 }
 }