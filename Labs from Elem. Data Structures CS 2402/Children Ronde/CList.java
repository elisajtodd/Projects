/*************************************************************************
 * This class is for a circular linked list. You will notice that it has 
 * the same attributes as in a regular linked list. What will be different 
 * though is the way we manipulate the list.
 * Also, instead of calling a node the head, we call it start since there 
 * is no more head of the list
 *************************************************************************/ 

public class CList<T> {
    private Node<T> start;
    private int size;
    
    // CONSTRUCTORS ******************************************************
    public CList() {}
    
    // TODO 1: Complete the following constructor that takes a node as a parameter
    // Pre-condition: N is a single node
    public CList(Node<T> N) { 
		start = N;
		Node temp = N;
		size++;
    }
    
    // SETTERS ***********************************************************
    // TODO 2: Write a setter method for setting the attribute start:
    public void setStart(Node<T> N){
		start = N;
		size = N.sizeFromNode();
	}
    // no setter for the size since it is a consequence of other methods
        
    // GETTERS ***********************************************************
    // TODO 3 & TODO 4: Write a getter method for accessing each of the attributes:
    public Node<T> getStart(){
		return start;
	}
	
	public int getSize(){
		return size;
	}
    
    // OTHER METHODS *****************************************************
    
    // ADDING NODES OR SEQUENCES OF NODES ////////////////////////////////
    /* Method addAtEnd: 
     *      Takes a node N 
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
	public void addAtEnd(Node<T> N){
		if(start != null) N.setNext(start);
		else start = N;
		size++;
	}
    /* Method addDataAtEnd: 
     *      Takes data of type T 
     *      Creates a node that contains T
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addDataAtEnd(T data) {
        addAtEnd(new Node(data));
    }
    
    /* Method addAtStart: 
     *      Takes a node N 
     *      Adds it to the circle just before start and makes it the new start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtStart(Node<T> N) {
        if(start != null) N.setNext(start);
		start = N;
		size++;
    }
    
    /* Method addAtLocation: 
     *      Takes a node N and an integer n
     *      Adds N to the circle just after the n-th node in the circle
     *          (where start is the first node)
     *      Notes: 1/ take into account when the list is null or has 
     *          less than n nodes
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtLocation(Node<T> N, int n) {
		Node temp=start;
        for(int i=0;i<n;i++){
			temp=temp.getNext();
		}
		temp.setNext(N);
    }   
    
    /* Method addMultiAtEnd: 
     *      Similar to addAtEnd
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtEnd(Node<T> N) {
		Node temp = start;
		while(temp.getNext()!=null){
			temp=temp.getNext();
		}
		temp.setNext(N);
		size+=N.sizeFromNode();
    }
    
    /* Method addMultiAtStart: 
     *      Similar to addAtStart
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtStart(Node<T> N) {
        Node temp = N;
		while(temp.getNext()!=null){
			temp=temp.getNext();
		}
		temp.setNext(start);
		start=N;
		size+=N.sizeFromNode();
    }
    
    /* Method addMultiAtLocation: 
     *      Similar to addAtLocation
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtLocation(Node<T> N, int n) {  
		Node temp = start;
		for(int i=0;i<n;i++){
			temp=temp.getNext();
		}
		temp.setNext(N);
		Node temp2 = N;
		while(temp2.getNext()!=null){
			temp2=temp2.getNext();
		}
		temp = temp.getNext();
		temp2.setNext(temp);
		size+=N.sizeFromNode();
    }   
    
    // REMOVING NODES OR SEQUENCES OF NODES ////////////////////////////////

    /* Method removeStart: 
     *      Removes the start node
     *      Makes the next node in sequence the start
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeStart() {
		if(start!=null && start.getNext()!=null)start = start.getNext();
		else start=null;
    }
    
    /* Method removeLast: 
     *      Removes the node just before start in the circle (i.e., the last node)
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeLast() {
		Node temp=start;
        while(temp.getNext().getNext()!=start){
			temp=temp.getNext();
		}
		temp.setNext(start);
		size--;
    }
    
    /* Method removeNode: 
     *      Takes a node N
     *      Removes this node N from the list if it is in the list
     *  Notes: 1/ take into account the case where N is not in the list,
     *      or the list is empty 
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeNode(Node<T> N) {
		if(N==start) removeStart();
		else{
			int count=0;
			boolean found = false;
			Node temp=start;
			while(count<size && !found){
				if(N==temp.getNext()){
					temp.setNext(temp.getNext().getNext());
					found=true;
				}
				count++;
			}
		}
		size--;
    }
    
    /* Method removeLocation: 
     *      Takes an integer n
     *      Removes the n-th node from the list if there is such a node
     *  Notes: 1/ take into account the case there are fewer nodes than n
     *      in the list
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeLocation(int n) {
        if(n==1) removeStart();
		else if (n<=size){
			Node temp=start;
			for(int i=1; i<n; i++){
				temp=temp.getNext();
			}
			temp.setNext(temp.getNext().getNext());
		}
		size--;
    }
    
    // PRINTING THE CONTENT OF A CLIST //////////////////////////////////
    /* Method print: 
     *      Prints every element of the circle once
     *      Prints "There is nothing to print" if the list is empty
     ********************************************************************/
    public void print() {
        // YOUR CODE GOES HERE...
    }
    
    /*******************************************************************/
    /* Method: ChildrenRonde: 
     * It applies to a circular linked list and takes an integer s 
     *      (given a CList L, you will call it as L.ChildrenRonde(s)). 
     * It successively removes every s-th child from the circle until 
     *      only one child is left. 
     * It does not return anything, but it directly modifies the list 
     *      of children, which contains only one child at the end of 
     *      the game, the winner. 
     * NOTE: make sure to handle special cases like when the list may
     *      be empty
     * ALSO: if the list contains only one element, print out:
     *      "There is only one element remaining: "
     *      and then print the node (its content) using the appropriate
     *      method
     *******************************************************************/
    public void ChildrenRonde(int s) {
        // YOUR CODE GOES HERE...
    }

}
public class CList {

}
