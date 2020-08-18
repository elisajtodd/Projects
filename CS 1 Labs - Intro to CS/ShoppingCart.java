/**
* You have been given the task of creating a program that creates a shopping cart for an online company
* that sells tickets for events. Your program will have a menu that allows a user to create a shopping cart,
* add tickets and checkout. For simplicity, the user will input the information of the tickets but in real life
* you would provide that information from a database (wait until you take the DB Management course!).
* Each ticket will be an instance of the class Ticket also provided in this exam.
* Complete the Java code to complete the functionality of the program.
* Please read carefully the comments before each method and statement so you can understand your task.
**/


import java.util.Scanner;

/**
* The class Cart contains an order for tickets sold online.
* It contains a list of tickets purchased, the name of the client, and the price.
**/


public class ShoppingCart {
 private String clientName; // setter and getter
 private Ticket[] ticketsPurchased = new Ticket[20];
 private double totalPurchase; // setter and getter
 private int numOfTickets;// setter and getter

 //Default constructor
 public ShoppingCart(){

 }

 //Second constructor for the class ShoppingCarts that receives as parameter the clientName.
 public ShoppingCart(String clientName){
    this.clientName = clientName;
 }
 
 
 // Write setters for the attributes clientName, totalPurchase, numOfTickets
 
 // Write the getters for the attributes clientName, totalPurchase and numOfTickets

 //Setter method for the  attribute name
 public void setClientName(String clientNameIn){
  this.clientName = clientNameIn;
 }

 //Setter method for the  attribute totalPurchase
 public void setTotalPurchase(double totalPurchaseIn){
  this.totalPurchase = totalPurchaseIn;
 }

 //Getter method for the  attribute name
 public String getClientName(){
  return this.clientName;
 }

 //Getter method for the  attribute totalPurchase
 public double getTotalPurchase(){
  return this.totalPurchase;
 }


 // Other methods - Actuator methods.

 // The method addToCart that adds a new ticket into the list
 // of purchased tickets.
 // This method receives newTicket, an instance of the class Ticket, adds it to the
 // array of ticketsPurchased and makes the newTicket unavailable.
  public void addToCart(Ticket newTicket){
	  
	    // this line makes the newTicket unavailable
	  newTicket.setAvailable(false);
	  
	  // this line assigns the new ticket to the next available position in the array ticketsPurchased
	  this.ticketsPurchased [numOfTickets]= newTicket;
	  
	  // this line is increasing the number of tickets by 1 , in practice its increasing the index o the array ticketsPurchased
	  this.numOfTickets=numOfTickets+1;   

 }

  // The method calculateTotal that calculates the total purchase by:
  // 1. Adding the price of all the tickets in the list
  // 2. Adding 15% of taxes.
  // 3. Set the totalPurchase to the total purchase calculated.

 public double calculateTotal(){
	 double totalTickets=0;				//stores each ticket price
	 for (int i=0; i<numOfTickets;i++) {
		 totalTickets += this.ticketsPurchased[i].getPrice();//iterates for all tickets purchased, gets price for each 
	 }
	 this.totalPurchase =(totalTickets*1.15);//adds 15% of taxes
  return this.totalPurchase;
 }


 // The method printShoppingCart prints the information of the shopping cart.
 public void printShoppingCart(){
	 System.out.println("Client: " + shoppingCart.getClientName() +"\nTickets Purchased: ");
	 for(int i = 0; i<this.numOfTickets; i++) {
		 System.out.println("\t" + ticketsPurchased[i].getEventName());
	 }
	 System.out.println("\nTotal price: " + shoppingCart.calculateTotal());
 }



 // Main method of the class TestShoppingCart which creates an instance of the class
 // TestShoppingCart and enables input from the user with a menu.

 public static void main(String[] args){



 }
}
