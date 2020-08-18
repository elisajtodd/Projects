/* CS1101 Intro to Computer Science
 * Instructor: Villanueva
 * Objects
 * Modified and submitted by: Elisa Jimenez Todd
 * last modified on: 11/14/2019
*/
import java.io.File;
import java.util.Scanner;

public class BookShelf {
    private Book[] collection;
    private int numOfBooks;
	
	//default constructor
    public BookShelf(){
      collection = new Book[10]; //default capacity
    }
	
	//Constructor passing the capacity of my BookShelf
    public BookShelf(int capacity){
        collection = new Book[capacity];
    }
	
	//Input: Book title
    //Output: returns the index where the received title is stored,
    //           -1 if the book is not in the array.
    public int findBook(String title){
    	 for (int i=0; i<this.numOfBooks; i++){
			 if (this.collection[i].getTitle().equals(title)){
				 return i;
			 }
		 }return -1; //book title not found
    }

    //Adds a Book to the array collection.
    //Input: A Book.
    //Output: if array is full the method displays a message and does not add the Book.
    //        If the title of the Book is found in the array, the method displays a
    //        message and does not add the book.
    public void addBook(Book newBook){
    	 if(this.numOfBooks==this.collection.length){
			 System.out.println("Couldn't add book. Collection is full.");
		 }if(newBook.getTitle().findBook!=-1)){
			 System.out.println("The book is already in the collection.");
		 }else{
			 this.collection[numOfBooks]=newBook;
			 this.numOfBooks++;
			 System.out.println("Book added.")
		 }
    }


	//print all of the elements of the collection inside your bookshelf 
	//	by using the method to print from the Book class named "printBook()"
	// 	this will print all of the elements that the object Book has
    public void printBookShelf(){
    	 for(int i=0; i< collection.length;i++){
 			collection[i].printBook();
 		 }
    }

    public static void main(String [] args){
        Book b1 = new Book("Peter Pan", "J. M. Berrie", 1911, 9.74);
        Book b2 = new Book("The Little Mermaid", "Hans Christian Andersen", 1837, 8.00);
        Book b3 = new Book("Cinderella", "RH Disney", 2005,2.07);
        Book b4 = b1;
		BookShelf firstBookShelf = new BookShelf(); 
        BookShelf myBookShelf = new BookShelf(5);
        myBookShelf.addBook(b1);
        myBookShelf.addBook(b2);
        myBookShelf.addBook(b3);
        myBookShelf.addBook(b4);
        myBookShelf.printBookShelf();
    }

}
