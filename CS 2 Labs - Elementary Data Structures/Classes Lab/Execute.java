
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Execute{

	public static Bike[] readInventoryFromFile(String filename) throws FileNotFoundException, IOException{
		Bike[] B = new Bike[10];
		String currentLine;
		try{
			FileReader fr = new FileReader(filename);
	        BufferedReader textReader = new BufferedReader(fr);
	        int i=0;
	        while ((currentLine = textReader.readLine()) != null) {
	        	String[] line = currentLine.replace(" ",  "").split(",");
				String bikeType = line[0];
				String model = line[1].replace("model","");
				int purchase = Integer.valueOf(line[2].replace("$",""));
				int retail = Integer.valueOf(line[3].replace("$",""));
				String color = line[4];
				int available = Integer.valueOf(line[5]);
				String[] ageRange = line[6].split("-");
				int age1 = Integer.valueOf(ageRange[0]);
				int age2 = Integer.valueOf(ageRange[1]);
				if(line[0].equals("Mountain Bike")){
					String userLevel = line[7];
					int wheelSize = Integer.valueOf(line[8]);
					B[i] = new MountainBike(model, purchase, retail, color, available, age1, age2, userLevel, wheelSize);
				}
				else if(line[0].equals("Road Bike")){
					int numGears = Integer.valueOf(line[7]);
					double weight = Double.valueOf(line[8]);
					B[i] = new RoadBike(model, purchase, retail, color, available, age1, age2, numGears, weight);
				}
				else if(line[0].equals("City Bike")){
					int numBaskets = Integer.valueOf(line[7]);
					String breaks = line[8];
					B[i] = new CityBike(model, purchase, retail, color, available, age1, age2, numBaskets, breaks);
					
				}
				else {
					B[i] = new Bike(model, purchase, retail, color, available, age1, age2);
				} 
				i = i+1;   
	        }
	        
	        textReader.close();
   		 }
   		catch(Exception e){
   			System.out.println(e.getClass().getCanonicalName());e.printStackTrace();
   		}

        return B;
    
	}
/*
	public static double updateInventoryBasedOnClientsRequests(Bike[] B, String filename) throws FileNotFoundException, IOException{

	} */

	public static void printInventory(Bike[] B){
		for(int i=0; i<B.length; i++) {
			if(B[i]!=null)System.out.print(B[i].toString());
            }
        System.out.println();
	} 

	public static void main(String[] args) throws FileNotFoundException, IOException{
		String filename = "inventory5.txt";
        Bike[] bikes = readInventoryFromFile(filename);
        printInventory(bikes);

	}

}