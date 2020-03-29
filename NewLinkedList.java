import java.util.Random;


/*
 * Austin Yuille
 * Lab 5
 * F 11-11:50
 * 
 * Java program to implement 
 * a Linked List without using libraries
 */

public class NewLinkedList {

	Node head; // head of list

	// Linked list Node.
	// This inner class is made static
	// so that main() can access it
	static class Node {

		Object data;
		Node next;

		// Constructor
		Node(Object d) {
			data = d;
			next = null;
		}
	}

	// **************INSERTION**************

	// Method to insert a new node
	public static NewLinkedList insert(NewLinkedList list, Object data) {
		// Create a new node with given data
		Node new_node = new Node(data);
		new_node.next = null;

		// If the Linked List is empty,
		// then make the new node a head
		if (list.head == null) {
			list.head = new_node;
		} else {
			// Else traverse till the last node is found
			// and insert the new node there
			Node last = list.head;
			while (last.next != null) {
				last = last.next;
			}

			// Insert the new_node at last node
			last.next = new_node;
		}

		// Return the list by head
		return list;
	}


	// ***************RECURSIVE TRAVERSAL**************
	//traverse through the list and print out each node
	//
	public static void printList(NewLinkedList list, Node currNode, int count) {
		if (currNode != null) {
			// Print the data of the node
			System.out.print(currNode.data + " ");
			// Increase the counter to find the length of the list
			count++;

			// Go to next Node
			printList(list, currNode.next, count);
		} else {
			System.out.println();
			System.out.println("The length of the list is " + count);
			System.out.println();
		}
	}

	// **************DELETION AT A POSITION**************

	// Method to delete a node in the LinkedList by POSITION
	public static NewLinkedList deleteAtPosition(NewLinkedList list, int index) {
		// Store head node
		Node currNode = list.head, prev = null;

		//
		// CASE 1:
		// If index is 0, then head node itself is deleted

		if (index == 0 && currNode != null) {
			list.head = currNode.next; // Changed head

			// Display the message
			System.out.println(index + " position element deleted");

			// Return the updated List
			return list;
		}

		//
		// CASE 2:
		// If the index is greater than 0 but less than the size of LinkedList
		//
		// The counter
		int count = 0;

		// Count for the index to be deleted,
		// keep track of the previous node
		// as it is needed to change currNode.next
		while (currNode != null) {

			if (count == index) {
				// Since the currNode is the required position
				// Unlink currNode from linked list
				prev.next = currNode.next;

				// Display the message
				System.out.println(index + " position element deleted");
				break;
			} else {
				// If current position is not the index
				// continue to next node
				prev = currNode;
				currNode = currNode.next;
				count++;
			}
		}

		// If the position element was found, it should be at currNode
		// Therefore the currNode shall not be null
		//
		// CASE 3: The index is greater than the size of the LinkedList
		//
		// In this case, the currNode should be null
		if (currNode == null) {
			System.out.println(index + " position element not found");
		}

		// return the List
		return list;
	}

	// *************DELETE CERTAIN STRING*************
	public static void deleteStr(NewLinkedList list, int num) {
		// Store head node
		Node currNode = list.head, prev = null;

		//
		// CASE 1:
		// If that string is in the list
		//
		// The counter
		int count = 0;

		// 
		while (currNode != null) {
			if (currNode.data instanceof String) {
				if (count == num-1) {
					System.out.println(currNode.data + " was deleted.");
					// Since the currNode is the required position
					// Unlink currNode from linked list
					prev.next = currNode.next;
					break;
				} else {
					//increases the counter if the node contains a string
					count++;
				}
			} 
			prev = currNode;
			currNode = currNode.next;
		}

		// Case 2:
		// if that number string doesn't exist
		// currNode should point to null in this case

		if (currNode == null) {
			System.out.println("That string does not exist.");
		}

	}

	// **************MAIN METHOD**************

	public static void main(String[] args) {
		// Start with the empty list.
		NewLinkedList list = new NewLinkedList();

		// used to generate a random number
		Random r = new Random();

		// Inserts the values
		list = insert(list, "str1");
		list = insert(list, r.nextInt(100));
		list = insert(list, "str2");
		list = insert(list, r.nextInt(100));
		list = insert(list, "str3");
		list = insert(list, r.nextInt(100));
		list = insert(list, "str4");
		list = insert(list, r.nextInt(100));
		list = insert(list, "str5");
		list = insert(list, r.nextInt(100));

		//prints the list
		printList(list, list.head, 0);
		
		//deletes the node at index 0 (the head)
		deleteAtPosition(list, 0);
		
		//prints the list
		printList(list, list.head, 0);

		//deletes the third string in the list
		deleteStr(list, 1);

		//prints the list
		printList(list, list.head, 0);
		
		//deletes the last element
		deleteAtPosition(list, 7);

		//prints the list
		printList(list, list.head, 0);
	}
}
