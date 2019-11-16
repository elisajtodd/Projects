public class Lab8{
	public static void main(String[] args){
		QueueA arrayQ = new QueueA(10);
		StackA arrayS = new StackA(10);
		Node num = new Node(15);
		QueueL listQ = new QueueL(num);
		StackL listS = new StackL(num);
		
		System.out.println("STACK A");
		arrayS.push(15);
		arrayS.push(6);
		arrayS.push(1);
		arrayS.push(2);
		System.out.println("" + arrayS.pop() + arrayS.pop() + arrayS.peek() + arrayS.pop() + arrayS.pop());
		arrayS.clear();
		arrayS.push(2);
		arrayS.push(3);
		arrayS.push(15);
		arrayS.push(6);
		arrayS.push(1);
		arrayS.push(2);
		arrayS.push(3);
		arrayS.push(15);
		arrayS.push(6);
		arrayS.push(1);
		arrayS.push(2);
		arrayS.push(3);
		System.out.println(arrayS.getSize());
		
		System.out.println("\nSTACK L");
		listS.push(15);
		listS.push(6);
		listS.push(1);
		listS.push(2);
		System.out.println("" + listS.pop() + listS.pop() + listS.peek() + listS.pop() + listS.pop());
		listS.clear();
		listS.push(2);
		listS.push(3);
		listS.push(15);
		listS.push(6);
		listS.push(1);
		listS.push(2);
		listS.push(3);
		listS.push(15);
		listS.push(6);
		listS.push(1);
		listS.push(2);
		listS.push(3);
		System.out.println(listS.getSize());
		
		
		System.out.println("\nQUEUE A");
		arrayQ.enqueue(15);
		arrayQ.enqueue(6);
		arrayQ.enqueue(1);
		arrayQ.enqueue(2);
		System.out.println("" + arrayQ.dequeue() + arrayQ.dequeue() + arrayQ.peek() + arrayQ.dequeue() + arrayQ.dequeue());
		arrayQ.clear();
		arrayQ.enqueue(2);
		arrayQ.enqueue(3);
		arrayQ.enqueue(15);
		arrayQ.enqueue(6);
		arrayQ.enqueue(1);
		arrayQ.enqueue(2);
		arrayQ.enqueue(3);
		arrayQ.enqueue(15);
		arrayQ.enqueue(6);
		arrayQ.enqueue(1);
		arrayQ.enqueue(2);
		arrayQ.enqueue(3);
		System.out.println(arrayQ.getSize());
		
		
		System.out.println("\nQUEUE L");
		listQ.enqueue(6);
		listQ.enqueue(1);
		listQ.enqueue(2);
		System.out.println("" + listQ.dequeue() + listQ.dequeue() + listQ.peek() + listQ.dequeue() + listQ.dequeue());
		listQ.clear();
		listQ.enqueue(2);
		listQ.enqueue(3);
		listQ.enqueue(15);
		listQ.enqueue(6);
		listQ.enqueue(1);
		listQ.enqueue(2);
		listQ.enqueue(3);
		listQ.enqueue(15);
		listQ.enqueue(6);
		listQ.enqueue(1);
		listQ.enqueue(2);
		listQ.enqueue(3);
		System.out.println(listQ.getSize());
		
	
	}
}