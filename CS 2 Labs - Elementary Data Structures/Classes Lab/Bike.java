public class Bike {

    private String modelNum;		//3 numbers one letter
    private int purchasePrice;
    private int retailPrice;
    private String color;
    private int numAvailable;
    private int ageRange1;			//smaller number
    private int ageRange2;			//larger number
    private static int totalBikes = 0; 	//different bikes read in inventory

    //constructors
    public Bike() {
    	totalBikes++;
    }

    public Bike(String modelNum, int pPrice, int rPrice, String color, int numAvailable, int age1, int age2) { //all attributes
        this.modelNum = modelNum;
        purchasePrice = pPrice;
        retailPrice = rPrice;
        this.color = color;
        this.numAvailable = numAvailable;
        ageRange1 = age1;
        ageRange2 = age2;
        totalBikes++;
    }

    //setters
    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

    public void setAgeRange1(int ageRange1) {
        this.ageRange1 = ageRange1;
    }

    public void setAgeRange2(int ageRange2) {
        this.ageRange2 = ageRange2;
    }

    //getters
    public String getModelNum() {
        return this.modelNum;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getRetailPrice() {
        return this.retailPrice;
    }

    public String getColor() {
        return this.color;
    }

    public int getNumAvailable() {
        return this.numAvailable;
    }

    public int getAgeRange1() {
        return this.ageRange1;
    }

    public int getAgeRange2() {
        return this.ageRange2;
    }
    

    //more
    public String toString() {		//puts all the information on a string
        String result = ("\n\tMODEL: " + modelNum + "\n\tPURCHASE PRICE: $"
                + purchasePrice + "\n\tRETAIL PRICE: $" + retailPrice + "\n\tCOLOR: " + color +
                "\n\t# AVAILABLE: " + numAvailable + "\n\tAGES: " + ageRange1 + "-" + ageRange2);
        return result;
    }
    
    public void printTotalBikes() {
		System.out.println("Total bikes in inventory: " + totalBikes);
	}

}