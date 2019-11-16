public class Node {

    private int data;
    private Node next;
    
    // Constructors ****************************************************************
    public Node() {}
    
    public Node(int d) {
        data = d;
        next = null;
    }
	
	public Node(int d, Node next){
		this.data = d;
		this.next = next;
	}
    
    // Setters *********************************************************************
    public void setData(int d) {
        data = d;   
    }
    
    public void setNext(Node N) {
        next = N;
    }
    
    // Getters **********************************************************************
    public int getData() {
        return data;   
    }
    
    public Node getNext() {
        return next;
    }
}