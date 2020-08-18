public class QueueA{
	private int head=0;
	private int tail=-1;
	private int size=0;
	private int[] arrInt;

	public QueueA(int max){
		arrInt = new int[max];	//initialize with max size
	}
	
	public int getSize(){
		return size;		//returns size
	}
	
	public void enqueue(int num){
		if(!isFull()) {
		tail = (tail + 1)%arrInt.length; //new tail
		arrInt[tail] = num;
		size++;				//adds size +1
		}else System.out.println("Queue is full.");
	}
	
	public int dequeue(){
		if(isEmpty()) {	//if queue is empty
			return -1;
		}else {
			size--;
			return arrInt[(head++%arrInt.length)];	//returns head and makes head next index
		}
	}
	
	public int peek(){
		if(isEmpty()) {	//if queue is empty
			return -1;
		}else {
			return arrInt[head];	//returns head
		}
	}
	
	public void clear(){
		size=0;	//reset size
		head=0;
		tail=-1;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public boolean isFull(){
		return (size==arrInt.length);
	}
}