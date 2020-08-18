public class RoadBike extends Bike{
	private int numGears;
	private double weight;
	private static int totalBikes = 0;
	
	//constructors
	public RoadBike(){
		totalBikes++;
	}
	
	public RoadBike(String modelNum, int pPrice, int rPrice, String color, int numAvailable, int age1, int age2, int numGears, double weight){
		super(modelNum, pPrice, rPrice, color, numAvailable, age1, age2);
		this.numGears=numGears;
		this.weight=weight;
		totalBikes++; 	//different bikes read in inventory
	}
	
	//setters
	public void setNumGears(int numGears){
		this.numGears=numGears;
	}
	
	public void setWeight(double weight){
		this.weight=weight;
	}
	
	//getters
	public int getNumGears(){
		return numGears;
	}
	
	public double getWeight(){
		return weight;
	}
	//actuators
	public String toString() { 		//puts all the information on a string
		String result = "\nROAD BIKE:" + super.toString()+ "\n\t# of Gears: " + numGears + "\n\tWeight: " + weight;
		return result;
	}
	
	//extra method
	
	public void printTotalBikes(){
		System.out.println("# Road Bikes: " + totalBikes + " model(s)");
	}
}