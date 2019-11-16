public class QueueL {
	private int size=0;
	private Node head;
	private Node tail;
	
	
	//constructors
	public QueueL() {
	}
	
	public QueueL(Node N) {
		head = N;
		tail = N;
		size++;
	}
	
	//getters
	public int getSize() {
		return size;
	}
	
	//other methods
	
	//takes integer into the queue
	public void enqueue(int num){
		if(isEmpty()) head = tail = new Node(num);	//if empty
		else{
			tail.setNext(new Node(num));	//add node
			tail = tail.getNext();
		}
		size++;
	}
	
	public int dequeue(){
		if(isEmpty()) {	//if queue is empty
			return -1;
		}else {
			size--;
			Node temp = head;
			head = head.getNext();
			return temp.getData();	//returns head and makes head next index
		}
	}
	
	public int peek(){
		if(isEmpty()) {	//if queue is empty
			return -1;
		}else {
			return head.getData();	//returns head
		}
	}
	
	public void clear(){
		size=0;	//reset size
		head=null;
		tail=null;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
}
