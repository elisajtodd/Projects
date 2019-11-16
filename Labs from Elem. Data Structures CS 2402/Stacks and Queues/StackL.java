public class StackL{
	private Node top;
	private int size=0;
	
	//constructors
	public StackL(){
		
	}
	public StackL(Node N){
		top=N;
		size++;
	}
	//accessors
	public int getSize(){
		return size;
	}
	//other
	public void push(int A){
		top = new Node(A,top);
		size++;		//size + 1
	}
	
	//push that receives an array
	public void push(int[] n) {
		for(int i=0; i<n.length;i++) {	//checks that array is not full
			push(n[i]);		//sends each number in array to push
		}
	}
	
	public int pop(){
		if(isEmpty()) return -1;	//if stack is empty
		int toReturn = top.getData();	// get data from top
		top = top.getNext();			// set top next value
		size--;
		return toReturn;
	}
	public int peek(){
		return (isEmpty()) ? -1 : top.getData();	//if stack is empty return -1, else return data from top 
	}
	public void clear(){
		top = null;			//lose head, lose everything else
		size = 0;
	}
	public boolean isEmpty(){
		return size == 0;
	}
}