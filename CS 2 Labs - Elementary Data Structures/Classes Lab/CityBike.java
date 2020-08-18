public class CityBike extends Bike{
	
	private int numBaskets;
	private String brakes;
	private static int totalBikes = 0;	//different bikes read in inventory
	
	//constructor
	public CityBike(){
		totalBikes++;
	}
	
	public CityBike(String modelNum, int pPrice, int rPrice, String color, int numAvailable, int age1, int age2, int baskets, String brakes){
		super(modelNum, pPrice, rPrice, color, numAvailable, age1, age2);
		numBaskets=baskets;
		this.brakes=brakes;
		totalBikes++;
	}
	
	//setters
	public void setNumBaskets(int baskets){
		numBaskets=baskets;
	}
	
	public void setBrakes(String brakes){
		this.brakes=brakes;
	}
	
	//getters
	public int getNumBaskets(){
		return numBaskets;
	}
	
	public String getBrakes(){
		return brakes;
	}
	//actuators
	public String toString() {		//puts all the information on a string
		String result = "\nCITY BIKE:" + super.toString()+ "\n\t# of Baskets: " + numBaskets + "\n\tBrakes: " + brakes;
		return result;
	}
	
	//extra method
	
		public void printTotalBikes() {
			System.out.println("# City Bikes: " + totalBikes + " model(s)");
		}
}