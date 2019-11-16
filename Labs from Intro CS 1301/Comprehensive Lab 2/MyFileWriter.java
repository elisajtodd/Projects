import java.io.*;
public class MyFileWriter {
	public static void main(String[] args) throws Exception{
		//print writer and file writer alone rewrites
		
		
		FileWriter fw = new FileWriter ("C:\\Users\\Elisa\\Documents\\UTEP\\CS 1301\\myOutputFile.txt", true);//appends
		
		PrintWriter fpw = new PrintWriter(fw);
		fpw.print("Hello");
		fpw.println(" World!");
		fpw.close();
		
	}
}
