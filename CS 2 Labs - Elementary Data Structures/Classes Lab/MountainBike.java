public class MountainBike extends Bike{
	
	private String level;
	private int wheelSize;
	private static int totalBikes = 0;	//different bikes read in inventory
	
	//constructor
	public MountainBike(){
		totalBikes++;
	}
	
	public MountainBike(String modelNum, int pPrice, int rPrice, String color, int numAvailable, int age1, int age2, String level, int wheelSize){
		super(modelNum, pPrice, rPrice, color, numAvailable, age1, age2);
		this.level=level;
		this.wheelSize=wheelSize;
		totalBikes++;
	}
	
	//setters
	public void setLevel(String level){
		this.level = level;
	}
	public void setWheelSize(int wheelSize){
		this.wheelSize = wheelSize;
	}
	//getters
	public String getLevel(){
		return level;
	}
	public int getWheelSize(){
		return wheelSize;
	}
	//actuators
	public String toString() { 		//puts all the information on a string
		String result = "\nMOUNTAIN BIKE:" + super.toString()+ "\n\tLevel: " + level + "\n\tWheel Size: " + wheelSize;
		return result;
	}
	//extra method
	
		public void printTotalBikes() {
			System.out.println("# Mountain Bikes: " + totalBikes + " model(s)");
		}
}