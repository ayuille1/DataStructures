/*
 * Austin Yuille
 * MW 12:30-1:45
 * Assignment 2 Part 2
 * 
 * Creates a "stack" of Employee files.
 * 
 * I made two methods for question 4. printAll() clears the stack while printing.
 * display() prints the stack but doesn't remove any of the values.
 */
public class EmpStack {
	Node top;
	int maxSize;
	int count;
		
	static class Node {
		Employee emp;
		Node next;
			
		Node (Employee e) {
			emp = e;
			next = null;
		}
	}
	
	public EmpStack() {
		top = null;
		maxSize = 10;
		count = 0;
	}
	
	public static void main(String[] args) {
		//makes a new stack
		EmpStack stack = new EmpStack();
		
		// ********for question 5**********
		// ********************************
		//checks if the stack is empty
		System.out.println(stack.isEmpty());
		System.out.println();
				
		//creates new employees
		Employee emp1 = new Employee("Austin", "000-00-0001", 100000);
		Employee emp2 = new Employee("Rachel", "000-00-0002", 15000);
		Employee emp3 = new Employee("George", "000-00-0003", 45000);
		Employee emp4 = new Employee("Greg", "000-00-0004", 80000);
		Employee emp5 = new Employee("Susan", "000-00-0005", 95000);
		
		// ********for question 1**********
		// ********************************
		//adds employees to the stack
		stack.push(emp1);
		stack.push(emp2);
		stack.push(emp3);
		stack.push(emp4);
		stack.push(emp5);
		
		// ********for question 4**********
		// ********************************
		//displays the stack without removing any of the employees
		stack.display();
		System.out.println();
		
		// ********for question 3**********
		// ********************************
		//retrieves and prints the top of the stack without removing it
		stack.printEmp(stack.peek());
		System.out.println();
		
		// ********for question 2**********
		// ********************************
		//removes and prints the top of the stack
		stack.printEmp(stack.pop());
		System.out.println();
		
		//// ********for question 4**********
		// ********************************
		//removes and prints all employees still in the stack
		stack.printAll();
		System.out.println();
		
		//checks if the stack is empty
		System.out.println(stack.isEmpty());
		System.out.println();
	}
	
	// **********for question 5*********
	// *********************************
	//checks if the stack is empty
	public boolean isEmpty() {
		return top == null;
	}
	
	//checks if the stack is full
	public boolean isFull() {
		return count >= maxSize;
	}
	
	// ********for question 1**********
	// ********************************
	// Method to insert a new node
	public void push(Employee emp) {
		//check for overflow
		if(!isFull()) {		
			count++;
			// Create a new node with given employee
			Node new_node = new Node(emp);
			new_node.next = null;

			// if the stack is empty
			//makes the new node the top of the stack
			if (isEmpty()) {
				top = new_node;
				return;
			}
			
			//creates the link from the top
			//to the old top
			new_node.next = top;
			//assigns the new node to the top of the stack
			top = new_node;
			return;
		}
		System.out.println("The stack is already full you can't add anymore.");
		System.out.println();
	}
	
	// **********for question 2*********
	// **********************************
	//retrieves and removes the top of the stack
	public Employee pop() {
		//check for underflow
		if(!isEmpty()) {
			//stores the value of the top value
			Node temp1 = top;
			//
			top = top.next;
			count--;
			return temp1.emp;
		}
		//prints if the stack is empty
		//also returns a "blank"
		System.out.println("The stack is empty.");
		Employee empty = new Employee();
		return empty;
	}
	
	// ***********for question 3************
	// *************************************
	//retrieves the top of the stack
	public Employee peek() {
		//check for underflow
		if(!isEmpty()) {
			//returns the top value of the stack
			return top.emp;
		}
		//prints is the stack is empty
		//also returns a "blank" employee
		System.out.println("The stack is empty.");
		Employee empty = new Employee();
		return empty;
	}
	
	//prints all data from an Employee
	public void printEmp(Employee emp) {
		//checks to make sure it is a real employee
		if(emp.name != "Unknown") {
			System.out.println("Name: " + emp.name + "; SSN: " + emp.ssn + "; Salary: " + emp.sal);
		}
	}
	
	// ********for question 4**********
	// ********************************
	//prints the stack without removing any employees from the stack
	public void display() {
		Node curr_node = top;
		while(curr_node != null) {
			System.out.println("Name: " + curr_node.emp.name + "; SSN: " + curr_node.emp.ssn + "; Salary: " + curr_node.emp.sal);
			curr_node = curr_node.next;
		}
	}
	
	// ********for question 4**********
	// ********************************
	//prints all data from all employees in a stack
	public void printAll() {
		while(!isEmpty()) {
			printEmp(pop());
		}
		System.out.println("The stack is empty now.");
	}
	
}
