import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lab2 {
	
	public static int[][] readSudoku(File sudokuFile) throws FileNotFoundException, IOException{
		int[][] sudokuBoard = new int[9][9];	//stores integer numbers of the sudoku
		try{
			BufferedReader br = new BufferedReader(new FileReader(sudokuFile));	//reader
			String[] str = new String[9];			//stores lines of the text document
			for (int i=0; i<9;i++) {				//goes through 9 rows of the sudoku in text file
				str = br.readLine().split(" ");		//reads line and stores each number on a space in the array
				if(str.length>9) throw new Exception();
				for(int j=0;j<9;j++) {				//stores the string array into complete integer array
					sudokuBoard[i][j]= Integer.valueOf(str[j]); //converts to integer
					if (sudokuBoard[i][j]>9 || sudokuBoard[i][j]<1) throw new ArithmeticException();
				}
			}br.close();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Your board cannot be read: Missing numbers in the sudoku.");
			sudokuBoard[0][0]=0;
		}catch(NumberFormatException e){
			System.out.println("Your board cannot be read: Your board should contain only numbers from 1 through 9");	
			sudokuBoard[0][0]=0;
		}catch(ArithmeticException e) {
			System.out.println("Your board cannot be read: Board should only be filled with numbers from 1 through 9.");	
			sudokuBoard[0][0]=0;
		}catch(NullPointerException e){
			System.out.println("Your board cannot be read: Missing numbers in the sudoku.");	
			sudokuBoard[0][0]=0;
		}catch(Exception e){
			System.out.println("Your board cannot be read.");
			sudokuBoard[0][0]=0;
		}
		return sudokuBoard;
	}
	
	public static void printSudoku(int[][] sudokuBoard) {
		if(sudokuBoard[0][0]!=0) {
			for(int i=0;i<9;i++) {
				for (int j=0; j<9; j++) {		//iterates through sudoku board
					if (j==3 || j==6)System.out.print(" | " + sudokuBoard[i][j]);
					else System.out.print("   " + sudokuBoard[i][j]);
				}
				System.out.println();
				if (i==2 || i==5) System.out.println("  -----------|-----------|-----------");
				else if (i<8) System.out.println    ("             |           |"); //prints next line for every row
			}
		}
	}
	public static boolean checkSudoku(int[][] sudokuBoard) {
		boolean found = false;
		for(int i=1;i<=9; i++){						//checks that there are numbers 1 through 9 in rows
			for(int j=0; j<9; j++){
				for (int k=0; k<9; k++){
					if(sudokuBoard[j][k]==i) found=true;
				}if (!found) return false;			//if the number that i is checking (from 1 to 9) was not found, the sudoku was false
				else found = false;				
			}
		}
			
		for(int i=1;i<=9; i++){						//checks that there are numbers 1 through 9 in columns
			for(int j=0; j<9; j++){
				for (int k=0; k<9; k++){
					if(sudokuBoard[j][k]==i) found=true;
				}if (!found) return false;			//if the number that i is checking (from 1 to 9) was not found, the sudoku was false
				else found = false;
			}
		}
		for (int i=1; i<=9; i++){					//checks that there are numbers 1 through 9 in boxes
		int b=0;
			for(int j=0; j<3;j++){					//iterates 3 times for boxes up to down
				int a=0;							//reset box left, middle, right
				for(int k=0;k<3;k++) {				//iterates 3 times(9 total) for boxes left to right
					for(int l=b; l<b+3;l++) {		//these next 2 for loops go through each box
						for(int m=a; m<a+3;m++) {
								if(sudokuBoard[l][m]== i) found=true;
						}
					}if (!found) return false;		//if the number that i is checking (from 1 to 9) was not found, the sudoku was false
					else found = false;
					a+=3;							//start of next box, to the right
				}b+=3;
			}
		}
		return true;	
	}
	
	public static void resultSudoku(boolean winning) {
		if(winning) System.out.println("You have a winning sudoku!\n"); //checksudoku was true(correct) 
		else System.out.println("Failed sudoku!\n");
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		if(args.length == 0) System.out.println("Please input the file(s) to read in the arguments"); //the user did not write the txt file in  the arguments
		for(int i=0; i<args.length; i++){			//if the user input more than 1 files, the program will read them all
			File f = new File(args[i]);				//using file 1,2,3...
			
			int[][] sudokuBoard = new int[9][9];	//store the board	
			System.out.println("Sudoku " + (i+1) + ":");	//sudoku that is being shown
			
			sudokuBoard=(readSudoku(f));			//read file and store it
			printSudoku(sudokuBoard);				//print board
			System.out.print("RESULT: ");
			resultSudoku(checkSudoku(sudokuBoard));	//failed or won
		}

	}

}