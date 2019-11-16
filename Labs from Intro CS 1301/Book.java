
public class Book {
    private String title;
    private String author;
    private int yearPublised;
    private double price;

	//default constructor
    public Book(){

    }

	//constructor with all the attributes in your parameters 
    public Book(String title, String author, int yearPublised, double price) {
        this.title = title;
        this.author = author;
        this.yearPublised = yearPublised;
        this.price = price;
    }
	
	//GETTERS 
    public String getTitle() {
        return this.title; //works with both "return title;" & "return this.title;"
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublised() {
        return yearPublised;
    }

    public double getPrice() {
        return price;
    }

	//SETTERS 
    public void setTitle(String titleIn) {
        this.title = titleIn;
    }

    public void setAuthor(String authorIn) {
        this.author = authorIn;
    }

    public void setYearPublised(int yearPublisedIn) {
        this.yearPublised = yearPublisedIn;
    }

    public void setPrice(double priceIn) {
        this.price = priceIn;
    }
	
	//ACTUATOR to print the object
    public void printBook(){
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Year published: " + this.yearPublised);
        System.out.printf("\nPrice: %.2f" , this.price);
    }

}
