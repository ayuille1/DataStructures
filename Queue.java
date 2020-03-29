/*
 * Austin Yuille
 * F 11:00-11:50
 * Lab7 - Queue
 * 
 * Creates a queue data structure without using queue libraries
 */

import java.util.Random;

public class Queue {
	
	Node last;
	Node first;
	
	static class Node{
		
		double data;
		
		Node next;
		
		Node(double d) {
			data = d;
			next = null;
		}
	}
	
	//determines if the queue is empty
	public boolean isEmpty() {
		return last == null;
	}
	
	//inserts a double into the queue
	public void insert (double data) {
		// Create a new node with given data
		Node newNode = new Node(data);
		
		if(isEmpty()) {
			//if the queue is empty it makes the node the first and last node
			first = newNode;
			last = newNode;
			
		} else {
			//makes the old last node point to the new node
			last.next = newNode;
			
		}
		//makes the last node the new node
		last = newNode;
	}
	
	//prints the queue
	public void printQ () {
		//makes a new node to keep track of where to print in the queue
		Node print = first;
		
		//executes if the queue isn't empty
		if(!isEmpty()) {
			
			//prints out the queue
			System.out.print("The queue is: ");
			//runs whiles the tracker is not null
			while (print != null) {
					// Print the data of the node
					System.out.print(print.data + ", ");
					//moves from the current node to the next one
					print = print.next;
				}
			System.out.println();
		//if the queue is empty it notifies the user
		} else {
			System.out.println("The stack is empty.");
			System.out.println();
		}
	}
	
	//deletes the first node
	public void delete() {
		//makes the second node the first node
		first = first.next;
	}
	
	public static void main(String[] args) {
		//makes a new queue
		Queue queue = new Queue();
		
		//makes a random generator
		Random rand = new Random();
		
		//makes a random double, inserts it, and prints the queue
		double r = rand.nextDouble();
		queue.insert(r);
		queue.printQ();
		
		//makes a random double, inserts it, and prints the queue
		r = rand.nextDouble();
		queue.insert(r);
		queue.printQ();
		
		//makes a random double, inserts it, and prints the queue
		r = rand.nextDouble();
		queue.insert(r);
		queue.printQ();
		
		//makes a random double, inserts it, and prints the queue
		r = rand.nextDouble();
		queue.insert(r);
		queue.printQ();
		
		//makes a random double, inserts it, and prints the queue
		r = rand.nextDouble();
		queue.insert(r);
		queue.printQ();
		
		//deletes the first double from the queue and prints the queue
		queue.delete();
		queue.printQ();
		
		//deletes the first double from the queue and prints the queue
		queue.delete();
		queue.printQ();
	}
}
