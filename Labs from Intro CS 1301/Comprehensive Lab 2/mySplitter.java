
public class mySplitter {
	public static void main(String[] args) {
		String s= "12, 5, 35, 17, 1";
		String[] sArray = s.split(", ");
		
		for (int i = 0; i< sArray.length; i++) {
			System.out.println(sArray[i]);
		}
	}

}
