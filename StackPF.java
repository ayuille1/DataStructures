/*
 * Austin Yuille
 * MW 12:30-1:45
 * Assignment 2 Part 3
 * 
 * Converts an infix expression to a post fix expression.
 */
public class StackPF {
	// keeps track of the top value
	int top = -1;

	// sets the max stack size
	int max = 10;

	// creates the stack
	char[] stack = new char[max];

	public static void main(String[] args) {
		
		// creates a new stack
		StackPF stack = new StackPF();

		// initializes the string you want to be converted (must be less than 21 characters
		// or less or the max must be changed)
		String str = "a+b*(c-d)";
		System.out.println("The infix expression is: " + str);
		
		// converts a string from in fix to post fix
		System.out.println(stack.convert(str));
		System.out.println();
		
		// initializes the string you want to be converted (must be less than 21 characters
		// or less or the max must be changed)
		str = "a*b*c";
		System.out.println("The infix expression is: " + str);
		
		// converts a string from in fix to post fix
		System.out.println(stack.convert(str));
		System.out.println();
		
		// initializes the string you want to be converted (must be less than 21 characters
		// or less or the max must be changed)
		str = "a*d+c-(b/e)";
		System.out.println("The infix expression is: " + str);
		
		// converts a string from in fix to post fix
		System.out.println(stack.convert(str));
		System.out.println();
		
		// initializes the string you want to be converted (must be less than 21 characters
		// or less or the max must be changed)
		str = "a+b+c+d";
		System.out.println("The infix expression is: " + str);
		
		// converts a string from in fix to post fix
		System.out.println(stack.convert(str));
		System.out.println();
		
	}

	// checks if the stack is empty
	// used when popping a value from the stack
	public boolean isEmpty() {
		return (top < 0);
	}

	// checks if the stack is full
	// used when pushing a value onto the stack
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

			// lets the user know if the stack is full
		} else {
			System.out.println("The stack is full so the value can't be added.");
		}
	}
	
	//retrieves the top value from the stack without removing it
	public char peek() {
		// if the stack isn't empty it will return the top value
		if (!isEmpty()) {
			return stack[top];
		} else {
			return 0;
		}
	}
	
	//retrieves and removes the top value from the stack
	public char pop() {
		// if the stack isn't empty it will return the top value
		// it will also decrease the top counter meaning the value
		// before it is now the new top value
		if (!isEmpty()) {
			return stack[top--];

			// if the stack is empty it notifies the user
		} else {
			System.out.println("The stack is empty.");
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

	// converts a string from in fix to post fix
	public String convert(String in) {
		//makes a string for appending
		String post = "The postfix expression is: ";
		
		//runs for the whole string
		for (int i = 0; i < in.length(); i++) {
			//looks at each character in the string
			char c = in.charAt(i);
			
			//checks if the char is an operator
			//if it is it immediately appends it to the string
			if (c != '(' && c != ')' && c != '*' && c != '/' && c != '+' && c != '-') {
				post = post + c;
			
			//checks if the char is a '(' 
			//if it is it pushes it to the stack
			} else if(c == '(') {
				push(c);
				
			//checks if the char is a ')' 
			//if it is it appends all of the chars until it reaches a '(' 
			} else if (c == ')') {
				
				//gets the top value from the stack and removes it
				char ch = pop();
				
				//appends all of the operators in the stack until it reaches '('
				while(ch != '(') {
					post = post + ch;
					ch = pop();
				}
				
			//checks if the char is a '/'
			//if it is it pushes it to the stack
			//and pops all operators of greater or equal precedence
			} else if(c == '/') {
				
				//checks if the stack is empty
				//if not checks for values of equal or greater precedence
				if (!isEmpty()) {
					while(peek() == '*' || peek() == '/') {
						post = post + pop();
					}
				}
				push(c);
				
				
			//checks if the char is a '*'
			//if it is it pushes it to the stack
			//and pops all operators of greater or equal precedence
			} else if(c == '*') {
				
				//checks if the stack is empty
				//if not checks for values of equal or greater precedence
				if (!isEmpty()) {
					while(peek() == '*' || peek() == '/') {
						post = post + pop();
					}
				}	
				push(c);
				
			//checks if the char is a '+'
			//if it is it looks for an operator of higher importance in the stack
			//if one exists it shifts the operators up in the stack and adds the char to the stack
			//if it encounters a '(' it stops
			} else if(c == '+') {
				
				//checks if the stack is empty
				//if not checks for values of equal or greater precedence
				if (!isEmpty()) {
					while (peek() == '-' || peek() == '*' || peek() == '/' || peek() == '+') {
						post = post + pop();
					}
				}	
				push(c);
				
			//checks if the char is a '-'
			//if it is it looks for an operator of higher importance in the stack
			//if one exists it shifts the operators up in the stack and adds the char to the stack
			//if it encounters a '(' it stops
			} else if(c == '-') {
				
				//checks if the stack is empty
				//if not checks for values of equal or greater precedence
				if (!isEmpty()) {
					while (peek() == '-' || peek() == '*' || peek() == '/' || peek() == '+') {
						post = post + pop();
					}
				}
				push(c);
			}
		}
		
		//appends the rest of the stack to the string
		while(!isEmpty()) {
			post = post + pop();
		}
		//returns the post fix expression
		return post;
	}
}
