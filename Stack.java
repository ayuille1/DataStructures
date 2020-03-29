/*
 * Creates a stack of five elements and prints it in LIFO order
 */
import java.util.Random;

public class Stack {
	//keeps track of the top value
	int top = 0;
	
	//sets the max stack size
	int max = 5;
	
	//creates the stack
	int[] stack = new int[max];
	
	public static void main(String[] args) {
		//creates a new stack
		Stack stk = new Stack();
		
		//adds ints(0-99) to the top of the stack
		push(stk);
		push(stk);
		push(stk);
		push(stk);
		push(stk);
		
		//prints the stack from the top down
		printStack(stk);
	}

	//pushes an int onto the stack
	public static void push(Stack stk) {
		//if there is room in the stack then it will add an int
		if (stk.top < stk.max) {
			//creates a random integer from 0-99
			Random r = new Random();
			int a = r.nextInt(100);
			
			//adds the int at the end
			stk.stack[stk.top] = a;
			
			//keeps track of the top variable
			stk.top++;
		}
	}
	
	//prints the stack from the top down
	public static void printStack(Stack stk) {
		System.out.print("Stack: ");
		
		//prints the stack
		while (stk.top-1 >= 0) {
			System.out.print(stk.stack[stk.top-1] + ",");
			stk.top--;
		}
		
		System.out.println();
	}
	
}
