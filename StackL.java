/*
 * Austin Yuille
 * MW 12:30-1:45
 * Assignment 2 Part 1
 * 
 * Checks to see if a string is part of a language
 * 
 * Rules of the language:
 * 1) String is just a $.
 * 2) String is empty.
 * 3) String is a string of characters that is divided into two equal parts by a $.
 * 4) The two equal parts are reverse of each other.
 */

public class StackL {
	// keeps track of the top value
	int top = -1;

	// sets the max stack size
	int max = 10;

	// creates the stack
	char[] stack = new char[max];

	public static void main(String[] args) {

		// creates a new stack
		StackL stack = new StackL();

		// initializes the string you want to be tested (must be less than 21 characters
		// or less or the max must be changed)
		String str = "ABC$CBA";

		// tests to see if the string is in language w$w'
		stack.TestL(str);

		// initializes the string you want to be tested (must be less than 21 characters
		// or less or the max must be changed)
		str = "$";

		// tests to see if the string is in language w$w'
		stack.TestL(str);
		// initializes the string you want to be tested (must be less than 21 characters
		// or less or the max must be changed)
		str = "$ABC$CBA$";

		// tests to see if the string is in language w$w'
		stack.TestL(str);
	}

	//checks if the stack is empty
	//used when popping a value from the stack
	public boolean isEmpty() {
		return (top < 0);
	}
	
	//checks if the stack is full
	//used when pushing a value onto the stack
	public boolean isFull() {
		return (top >= max - 1);
	}

	// pushes a char onto the stack
	public void push(char c) {
		// if there is room in the stack then it will add a char
		if (!isFull()) {

			// keeps track of the top variable
			top++;

			// adds the char at the top of the stack
			stack[top] = c;
		
		//lets the user know if the stack is full
		} else {
			System.out.println("The stack is full so the value can't be added.");
		}
	}

	public char pop() {
		//if the stack isn't empty it will return the top value
		//it will also decrease the top counter meaning the value
		//before it is now the new top value
		if (!isEmpty()) {
			return stack[top--];

		//if the stack is empty it notifies the user
		} else {
			System.out.println("The stack is empty .");
			return 0;
		}
	}

	// prints the stack from the top down
	public void printStack() {
		System.out.print("Stack: ");
		int a = top;

		// prints the stack
		while (top >= 0) {
			System.out.print(stack[top] + ",");
			top--;
		}
		top = a;
		System.out.println();
	}

	// checks if a string belongs to a language
	// language is w$w'
	// Ex: ABC$CBA
	public void TestL(String S) {
		// the string must be empty, have an odd length, or just a "$"

		// if the string length is odd then it will go through these checks
		if (S.length() % 2 != 0) {
			
			// if the string length is 1
			if (S.length() == 1) {
				
				// if the single character is "$"
				if (S.charAt(0) == '$') {
					System.out.println("The only character is a \"$\" so it is in the language.");
					
					// if the single character is something other than a "$"
				} else {
					System.out.println("The string " + S + " does not belong to +the language.");
				}
				
			// if the string length is odd, but not one
			} else {
				int a = (S.length() / 2);
				
				//checks to see if the middle character is a "$"
				if (S.charAt(a) == '$') {
					//adds all chars after the dollar sign to the stack
					for (int i = a + 1; i < S.length(); i++) {
						if(S.charAt(i) != '$') {
							push(S.charAt(i));
						// if there is an "$" character anywhere other than the middle
						} else {
							System.out.println("The string " + S + " does not belong to the language.");
							return;
						}
					}
					//check to see if the top value of the stack is equal
					//to the first value of the string
					//checks for all values until either the stack is empty
					//or the "$" is found
					for (int i = 0; i < a; i++) {
						if (S.charAt(i) != pop()) {
							System.out.println("The string " + S + " does not belong to the language.");
							return;
						}
					}
					
					//prints if the string meets the requirements to be in the language
					System.out.println("The string " + S + " belongs to the language.");
				}
			}
			// if the string length is even it will go through these checks
		} else {
			// if it is an empty string
			if (S.length() == 0) {
				System.out.println("The string is empty so it is part of the language.");
				// if the string length is even, but not empty
			} else {
				System.out.println("The string " + S + " does not belong to the language.");
			}
		}
	}
}
