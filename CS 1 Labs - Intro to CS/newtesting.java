/* Alan Moya 
 * CS1101 Intro to Computer Science
 * Instructor: Villanueva
 * Comprehnesive_Lab2
*/

import java.util.Scanner;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.io.FileWriter;
public class newtesting{
    
    // Luhn Method 
    
    public static boolean cardCheck(int[] card){
     
        int answer;    //This will be final answer  
        int reminder; //This will hold the reminder varible, this for 
        int cardSum =0; //This will be the credit cardsum 
        
        //This for loop will excute will multipy by 2 fro every other number
        for(int i = 0; i < 16; i++){
            
            //This conditon verify the that it's every other number;
            if(i%2 == 0){
                card[i] = card[i]*2;
               
                
                //When you double your number and equals 10 then it is 1 every time, this the only special number
                if(card[i] ==10){
                    card[i] = 1;
                    
                }
                
                //This conditions is for numbers greater than 10, if number is "11" you would do (1 + 1) = 2
                if(card[i] >= 11){
                    reminder = card[i] % 10;
                    card[i] = reminder +1;
                }
            }else{
                
            }
        }
        
        //This Loop adds up the credit card
        for(int i =0; i < 16; i++){
            cardSum = cardSum + card[i];
            //System.out.println(cardSum);
        }
        //System.out.println(cardSum);
        //The last condition is 
        answer = cardSum % 10;
        //System.out.println(answer);
        if(answer == 0){
            return true;
        }
        if(answer > 0){
            return false;
        }else{
            return false;
        }
    
    
    }

        
    // string to integer
    
    //This loops converts strings of numbers into an integer array 
    public static int[] stringToInteger(String creditCardList){
        int[] creditCard = new int[16];
        for(int i =0; i < 16; i++){
            creditCard[i]= Character.getNumericValue(creditCardList.charAt(i));
        }
        return creditCard; 
        
    }
    
    
    
     // iteration
    
    public static void customerCheckIter(String[][] array) throws  FileNotFoundException{
        
        PrintWriter pw = new PrintWriter("Customer_Error_Moya.txt");
        
        //rowLength is different from my main method, out of scope
        //This array will hold credit cardNumbers to check if they have any erros 
        int rowLength = array.length;
        int[] card = new int[16];
        
        //luhn method uses boolean return 
        boolean work;
        
        for(int i = 0; i < 10; i++){
            
            work = cardCheck(stringToInteger(array[i][1]));
            
            if(work == false){
            
                pw.print(array[i][0] + " " + array[i][1]);
                pw.println();
                
                
            }
            
        }
        //Closing input stream 
        pw.close();
        
        
    }
     //Recursive 
	 
    public static void customerCheckRec(String[][] array, int rowLength)throws IOException{
        
        //I need to append since the method is calling itself over and over again 
        FileWriter recFileWriter = new FileWriter("Customer_Error_Rec_Moya.txt", true);
        /*
        *The code stacks itself from top to bottom, starts with 10 then moves slowly checks itself
        *Base case is row length equals 1 
        *I used two parameters, 2D String array and integer of row length 
        */
        try(PrintWriter pw = new PrintWriter(recFileWriter)){
            
            //X will be integer parameter that passes through
            int x = rowLength;
            
            
            if(rowLength == 1){
                
                //Checks the final line
                if(!cardCheck(stringToInteger(array[rowLength-1][1]))){
                    pw.print(array[rowLength-1][0] + " " + array[rowLength-1][1]);
                    pw.println();
                }
                
                //Closing streams 
                recFileWriter.close();
                pw.close();
            }else{
                customerCheckRec(array, x-1);
                //once stacks checks itself these line will verfiy which goes into the file
                boolean work = cardCheck(stringToInteger(array[rowLength-1][1]));
                if(work==false){
                
                pw.print(array[rowLength-1][0] + " " + array[rowLength-1][1]);
                pw.println();
                }
            
            
            
            
            
            }
        
        }
    }
    
     //Random Card
    
    public static int[] generateCC(int x){
        Random rand = new Random();
        int[] newCreditCard = new int[16];
        
        
        boolean work = true;
        
        while(work){
            for(int i = 0; i < 16; i++){
                int random = rand.nextInt(9);
                newCreditCard[i] = random;
            }
            if(cardCheck(newCreditCard)){
                work = false;
            }
        }
        return newCreditCard;
    }
    
    
    
    
    
    
    
    
    public static void main(String[] args)throws IOException {
        //This print writer is for my recurssive method 
        PrintWriter recFile = new PrintWriter("Customer_Error_Rec_Moya.txt");
        
        //Menue 
        Scanner scnr = new Scanner(System.in);
        boolean work = true;
        while(work){
            System.out.println("Select one");
            System.out.println("-----------------");
            System.out.println("1.Test Program");
            System.out.println("2.Generate Random Card");
            System.out.println("3.Exit");
            int num = scnr.nextInt();
            
            if(num == 3){
                work = false;
                System.out.println("GoodBye");
            }
        
        
        
            //Activity 1
            
            /*
            //Lines below are for testing Luhn method and StringToInteger Method 
        
            
            //Work boolean is meant to pick up the reutrn of cardCheck method
            //Delete this
            boolean blue = cardCheck(stringToInteger("7604537160134788"));
            System.out.println(blue);
            */
            
            
            if(num == 1){
                System.out.println("----------------------------");
                System.out.println("    Activity 1");
                System.out.println("----------------------------");
                System.out.println();
                //System.out.println();
                System.out.print("Credit Card");
                System.out.println("         Validity");
                System.out.println("---------------------------");
                
                //The purpose of using a row length varible will be for my RECURSION 
                int rowLength = 10;
                String[] fileInputArray = new String[rowLength];
                
                //This opens an input stream from "CcandnamesExamples.txt", will get 
                FileInputStream fileOpen = new FileInputStream("ccandnamesExamples.txt");
                    Scanner FileInput = new Scanner(fileOpen);
                
                //THIS LOOP STORES NAMES AND CREDIT CARD STRINGS FROM TEXT FILE
                for(int i =0; i < 10; i++){
                    fileInputArray[i] = FileInput.nextLine();
                }
                
                
                //This array will hold "names" and numbers in seperate strings,work as a placeholder varible  
                String[] part = null;
                
                //FIXME
                fileInputArray[0].split("(?<=\\D) (?=\\d)");
                
                //This is merely seperating Credit card numbers from names 
                for(int i = 0; i < 10; i++){
                    //The array "part" is a placeholder for names and credit card 
                    //part array will store the name in index 0, store card number in index 1
                    part = fileInputArray[i].split("(?<=\\D) (?=\\d)");
                    
                    //if credit card numbers are valid then they'll print valid, vice versa
                    //STRING TO INTEGER THEN TO LUHN METHOD 
                    if(cardCheck(stringToInteger(part[1]))){
                        System.out.print(part[1]);
                        System.out.println("    Valid");
                    }else{
                        System.out.print(part[1]);
                        System.out.println("    Invalid");
                    }
                }
                //fileOpen.close();
                
                // Activity 2
                
                boolean activity2Boolean = true;
                
                /*
                *This array will be the 2D array of file names and credit card numbers 
                *EX fileInput2DArray[0][0] = "James Smith"
                *EX fileInput2DArray[0][1] = "1234567897654806"
                */
                
                String[][] fileInput2DArray = new String[10][2];
                
                //The purpose of this loop is to populate my 2D array 
                for(int i = 0; i < 10; i++){
                    part = fileInputArray[i].split("(?<=\\D) (?=\\d)");
                    fileInput2DArray[i][0] = part[0];
                    fileInput2DArray[i][1] = part[1];
                    
                }
                System.out.println("---------------------------");
                
                /*
                for(int i = 0; i < 10; i++){
                    for(int x = 0; x < 2; x++){
                        System.out.print(fileInput2DArray[i][x] + " ");
                        System.out.print("");
                    }
                    System.out.println();
                }
                */
                
                
                while(activity2Boolean){
                
                    System.out.println();
                    System.out.println("----------------------------");
                    System.out.println("    Activity 2");
                    System.out.println("----------------------------");
                    System.out.println();
                    System.out.println("Select one");
                    System.out.println("1. Iteration");
                    System.out.println("2. Recursion");
                    System.out.println("3. Back to main meune");
                    
                    num = scnr.nextInt();
                    
                    if(num == 3){
                        activity2Boolean = false;
                    }
                    if(num == 1){
                        System.out.println();
                        System.out.println("Iteration file created, Customer_Error_Moya.txt file");
                        System.out.println();
                        customerCheckIter(fileInput2DArray);
                          
                    }
                    //This is my recurssive condition 
                    if(num == 2){
                        System.out.println();
                        System.out.println("Recusion file created, Customer_Error_Rec_Moya.txt file");
                        System.out.println();
                    
                        customerCheckRec(fileInput2DArray, rowLength);
                        
                    }
                }
                //These two statements are closing input streams 
                recFile.close();
                fileOpen.close();
                
                
                
            }
            //All this is for my random credit card generator---
            String newCardNumber;
            
            int[] randomCreditCard = new int[16];
            
            
            if(num == 2){
                //Calling method
                randomCreditCard = generateCC(num);
                
                //
                System.out.println("");
                System.out.println("-----------------");
                for(int i = 0; i < 16; i++){
                    System.out.print(randomCreditCard[i]);
                }
                System.out.println();
                System.out.println("-----------------");
                System.out.println();
            }
            
        
            
        
        
        
        }
    }
}
