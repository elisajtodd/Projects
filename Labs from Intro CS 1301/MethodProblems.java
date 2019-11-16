/* CS 1101
 * Instructor: Villanueva
 * Method Problems
 * Modified and submitted by: Elisa Jimenez Todd
 */

public class MethodProblems{
	  /*
	  *This method receives a string and returns a new string where all the
	  * lowercase 'x' chars have been changed to 'y' chars
	  */
	  public static String changeXY(String str){
	    return str.replace('x','y'); //function to replace characters in a string, x to y
	  }
	  
	  /*
	   * Given a string, return the longest substring that appears at both the
	   * beginning and end of the string without overlapping. For example,
	   * sameEnds("abXab") is "ab".
	   */

	    public static String sameEnds(String string) {
		   String equal=""; //initializes string to be printed, first and last letters that are the same
		   for (int i=1; i<=(string.length()/2); i++) { //checks substrings, from first character to the middle, and from middle to last character
			   if(string.substring(string.length()-i).equals(string.substring(0,i))){//compares last character to first, then last 2 to first 2
				   equal=(string.substring(0,i)); //if the above condition is true
			   }
		   }
	     return equal;
	  }

	  //This method evaluates the ouputs by comparing them against the expected outputs
	  //and returning an array of booleans with the evaluation true -> passed, false -> failed
	  public static boolean[] check(String[] actual, String[] expected){
	    boolean[] evaluation = new boolean[actual.length];
	    for(int i = 0; i < evaluation.length; i++){
	      evaluation[i] = actual[i].equals(expected[i]);
	    }
	    return evaluation;
	  }


	  public static void main(String[] args){
	   
	    /*****************TESTING changeXY()**********************/
	    String[] input = {"codex","xxhixx","xhixhix","hiy","h","x","","xxx","yyhxyi","hihi"};
	    String[] answer = {"codey", "yyhiyy", "yhiyhiy", "hiy", "h", "y", "", "yyy", "yyhyyi", "hihi"};
	    String[] output = new String[10];
	    for (int i=0; i < 10;i++){
	        output[i] = changeXY(input[i]);
	      }
	    boolean[] evaluation = check(output,answer);
	    System.out.println("----------- TESTING changeXY() ----------- ");
	    System.out.printf("Test Case    Input \t Actual Output \t\t Expected Output \t Evaluation \n");
	    System.out.println("-------------------------------------------------------------------------------");
	    for(int i = 0; i < evaluation.length; i++){
	      System.out.printf("%4d \t %10s \t %s \t\t\t %10s \t\t %s \n", i+1, input[i], output[i], answer[i], evaluation[i]);
	    }


	      /*****************TESTING sameEnds()**********************/
	      String[] input2 = {"abXYab","xx", "xxx", "xxxx", "javaXYZjava", "x", ""};
	      String[] answer2 = {"ab","x","x","xx", "java","", ""};
	      String[] output2 = new String[7];

	      for (int i=0; i < 7;i++){
	          output2[i] = sameEnds(input2[i]);
	        }
	      boolean [] evaluation2 = check(output2,answer2);
	      System.out.println("----------- TESTING sameEnds() ----------- ");
	      System.out.printf("Test Case    Input \t Actual Output \t\t Expected Output \t Evaluation \n");
	      System.out.println("-------------------------------------------------------------------------------");
	      for(int i = 0; i < evaluation2.length; i++){
	        System.out.printf("%4d \t %10s \t %s \t\t\t %10s \t\t %s \n", i+1, input2[i], output2[i], answer2[i], evaluation2[i]);
	      }

	  }


}
