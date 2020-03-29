/*
 * Completes actions using the linked list libraries
 */

import java.util.LinkedList;
import java.util.Random;

public class LinkedL {
	
	public static void main(String[] args) {
		
		//makes new linked list of generic objects
		LinkedList<Object> list = new LinkedList<Object>();
		
		//makes a random generator
		Random r = new Random();
		
		//adds ten values to the list(alternating between strings and random ints from 0-990
		list.add("str1");
		list.add(r.nextInt(100));
		list.add("str2");
		list.add(r.nextInt(100));
		list.add("str3");
		list.add(r.nextInt(100));
		list.add("str4");
		list.add(r.nextInt(100));
		list.add("str5");
		list.add(r.nextInt(100));
		
		//prints the list and its size
		System.out.print("List: "+ list + "	  Size: " + list.size());
		System.out.println();
		
		//deletes the first node(head)
		list.remove(0);
		
		//prints the list and its size
		System.out.print("List: "+ list + "	  Size: " + list.size());
		System.out.println();
		
		//deletes the third node from the list
		list.remove(2);
		
		//prints the list and its size
		System.out.print("List: "+ list + "	  Size: " + list.size());
		System.out.println();
		
		//deletes the last node from the list
		list.removeLast();
		
		//prints the list and its size
		System.out.print("List: "+ list + "	  Size: " + list.size());
		System.out.println();
	}
}
