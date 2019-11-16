/*********************************************************************
 * Linked List practice 
 * 
 * Instructor:  Villanueva
 * Modified and submitted by: Elisa Jimenez Todd
 * Last modified on: 12/4/2018
 *
 *********************************************************************/
public class LinkedList{
	private int size;		//size of linked list
	private Node head;		//reference to the head
	private Node tail;		//reference to the end of linked list
	
	public class Node{
		int data;
		Node next; 
		//constructor that takes in data
		Node(int d){
			data = d;
			next = null;
		}
	}
	
	/*****************************************************************
	*                           Constructors
	*****************************************************************/
	//Default constructor setting the size of the list to 0
	public LinkedList(){
		this.size = 0;
	}

	//Constructor with a single element in the list 
	public LinkedList(Node firstNode){
		this.head = this.tail = firstNode;
		this.size = 1;
	}
		
	/*****************************************************************
	*                           Getters
	*****************************************************************/
	public int getSize(){
		return this.size;
	}
	public Node getHead(){
		return this.head;
	}
	public Node getTail(){
		return this.tail;
	}
	
	
	/*****************************************************************
	*                           Actuators
	*****************************************************************/
	//add at the end of the list, also known as "append" to the list
	public void addToTail(int n){
		Node newNode= new Node(n);
		//if the list is empty
		if(head == null && this.size==0){
			this.head = this.tail = newNode;
		}
		//when the list is not empty
		else{
			this.tail.next = newNode;
			this.tail = newNode;
		}this.size++;
	}
	
	//add at the beginning of the list, also known as "push" to the list
	public void addToHead(int n){
		Node newNode= new Node(n);
		//if list is empty
		if(this.size==0 && this.head ==null){
			this.head = this.tail = newNode;
		}//if not empty
		else{
			newNode.next = this.head;
			this.head = newNode;
		}this.size++;
	}
	
	//print all the node’s data in the list
	public void printList(){
		System.out.println("printing list...");
		Node temp = this.head;		//temporary reference to traverse the list
		while(temp != null){		//we have not reached the end of the list
			System.out.print(temp.data + "-> ");	//data inside temp
			temp = temp.next;		//next value on list
		}System.out.println("Size: " +this.size);
	}
	
	//remove the last element of the list 
	public void deleteLast(){
		if(size==1){
			this.tail = this.head = null;
			size=0;
		}
		else{
			Node temp = this.head;
			for (int i=1; i<size-1; i++){
				temp=temp.next;
			}
			this.tail = temp;
			tail.next = null;
			this.size--;
		}
	}
}