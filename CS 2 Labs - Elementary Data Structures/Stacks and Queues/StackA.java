public class StackA{
	private int[] arrInt;
	private int size=0;
	
	//constructors
	public StackA(int n){
		arrInt = new int[n];	//max size = A
	}
	//accessors
	 public int getSize(){
		 return size;
	 }
	//other
	//push receives one number
	public void push(int n){
		if(isFull()) {
			System.out.println("Stack is full, cannot add "+ n);
			return; 	//checks that array was full
		}
		arrInt[size++]= n;			//adds on corresponding index and size +1
	}
	//push that receives an array
	public void push(int[] n) {
		int i=0;
		while(!isFull() && i<n.length) {	//checks that array is not full
			push(n[i++]);		//sends each number in array to push
		}
	}
	
	
	public int pop(){
		return (isEmpty()) ? -1 : arrInt[--size];	// if array is empty, return -1, else removes last index
	}
	public int peek(){
		return (isEmpty()) ? -1 : arrInt[size-1];	// if array is empty, return -1, else returns last index
	}
	
	//clears array
	public void clear(){
		size = 0;			
	}
	
	//checks the size to return if empty or full
	public boolean isEmpty(){
		return size == 0;
	}
	public boolean isFull(){
		return size == arrInt.length;
	}
	
}