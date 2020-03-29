import java.util.Random;
import java.util.Scanner;

public class RandomGraph {
	// makes a matrix to store the connections between nodes
	int[][] matrix;
	// makes an array that stores whether or not a node has been visited
	int[] visited = new int[10];
	// makes an array of all of the nodes
	Node[] list = new Node[10];
	// keeps track of the total weight of a spanning tree
	int weight = 0;
	
	/*
	 * makes a stack in the graph class
	 */
	// keeps track of the top value
	int top = -1;
	// sets the max stack size
	int max = 30;
	// creates the stack
	Node[] stack = new Node[max];

	/*
	 * makes a queue in the graph class
	 */
	//keeps track of the front of the queue
	int front = -1;
	//keeps track of the back of the queue
	int back = 0;
	//creates the queue
	Node[] queue = new Node[10];
	
	//makes a matrix of a certain size
	public RandomGraph(int size) {
		matrix = new int[size][size];
	}
	
	//creates a node object
	static class Node {
		//the value that the node holds
		int num;
		
		//an array that holds what other nodes it is connected to and the weights of those edges
		int[] connect = new int[10];
		
		public Node(int n) {
			this.num = n;
		}

		public Node() {
			num = 0;
		}
	}

		public static void main(String[] args) {
			
			//gets the amount of nodes
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the number of nodes: ");
			int size = sc.nextInt();
			
			//makes the graph
			RandomGraph graph = new RandomGraph(size);
			
			//makes a matrix
			graph.makeMatrix(size);
			
			//makes a list of the nodes
			graph.makeList();
			
			//prints the matrix
			graph.printMatrix();
			System.out.println();
			
			//runs DFS to check if it is connected
			graph.PrintDFS(graph.list[0]);
			
			//checks if the graph is connected
			//the weight must be 9 because that means each node has been visited once
			while (graph.weight != 9) {
				//resets the arrays for traversing
				graph.reset();
				//clears the matrix
				graph.resetMatrix(size);
				//makes a new matrix
				graph.makeMatrix(size);
				//makes a new List
				graph.makeList();
			}
			//reset the visited list so another search can be performed
			graph.reset();
			
			//does a depth first search on the graph
			graph.PrintDFS(graph.list[0]);
			
			//resets the visited list so another search can be performed
			graph.reset(); 
			
			//does a breadth first search
			graph.PrintBFS(graph.list[0]);
			
			//resets the visited list so another search can be performed
			graph.reset(); 
			
		}
		
		//resets the visited array to where all elements are 0 and resets weight to zero
		public void reset() {
			for (int i = 0; i < 10; i++) {
				visited[i] = 0;
			}
			weight = 0;
		}
		
		//makes a matrix of a certain size
		public void makeMatrix(int size) {
			Random rand = new Random();
			//runs through every element in the matrix
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					//if i does not equal j(no self edges)
					if (i != j) {
						//randomly makes to vertexes connected or not
						//does it in the adjacent spot in the matrix too so it is undirected
						matrix[i][j] = rand.nextInt(2);
						matrix[j][i] = matrix[i][j];
					}
				}
			}
		}
		
		//clears the matrix
		public void resetMatrix(int size) {
			//runs through all of the elements of the matrix
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					//makes all of the elements equal 0
					matrix[i][j] = 0;
					matrix[j][i] = 0;
				}
			}
		}

		
		// changes the value in the matrix from 0 to 1 to represent an edge
		public void addEdge(int a, int b) {
			// adds an edge for both sides of the matrix since it's not a directed graph
			matrix[a][b] = 1;
			matrix[b][a] = 1;
		}
		
		//creates a list of the nodes
		//and creates a list of connections for each node
		public void makeList() {
			Node curr;
			// adds the each node to the list of Nodes
			for (int i = 0; i < 10; i++) {
				curr = new Node(i);
				list[i] = curr;

				// copies each the list of edges to each Node from the matrix
				for (int j = 0; j < 10; j++) {
					curr.connect[j] = matrix[i][j];
				}
			}
		}

		//prints the adjacency matrix
		public void printMatrix() {
			//runs through all of the rows
			for (int i = 0; i < 10; i++) {
				System.out.print("[");
				
				//runs through all of the columns
				for (int j = 0; j < 10; j++) {
					//prints the element in row i and column j
					System.out.print(matrix[i][j] + ", ");
				}
				System.out.print("]");
				System.out.println();
			}
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
		public void push(Node curr) {
			// if there is room in the stack then it will add a node
			if (!isFull()) {

				// keeps track of the top variable
				top++;

				// adds the node at the top of the stack
				stack[top] = curr;

				// lets the user know if the stack is full
			} else {
				System.out.println("The stack is full so the node can't be added.");
			}
		}

		public Node pop() {
			// if the stack isn't empty it will return the top value
			// it will also decrease the top counter meaning the value
			// before it is now the new top value
			if (!isEmpty()) {
				return stack[top--];

				// if the stack is empty it notifies the user
			} else {
				System.out.println("The stack is empty .");
				return new Node(-1);
			}
		}

		// prints the stack from the top down
		public void printStack() {
			System.out.print("Stack: ");
			int a = top;

			// prints the stack
			while (top >= 0) {
				System.out.print(stack[top].num + ",");
				top--;
			}
			top = a;
			System.out.println();
		}
		
		//prints the graph is Depth First Search order
		public void PrintDFS(Node curr) {
			//pushes a node to the top of the stack
			push(curr);
			//marks that node as visited
			visited[curr.num] = 1;
			System.out.print("Depth First Search: " + curr.num + ", ");
			
			//keeps track for iteration
			int i = 0;
			
			//runs while the stack isn't empty
			while (!isEmpty()) {
				//if there is a connection between the current node and the node being looked at
				//and that node hasn't been visited yet
				if (curr.connect[i] != 0 && visited[i] == 0) {
					//pushes that node onto the stack
					push(list[i]);
					
					//increases the weight of the current tree
					weight += curr.connect[i];
					
					//prints the number of that node
					System.out.print(list[i].num + ", ");
					//marks that node as visited
					visited[i] = 1;
					//makes that node the current node
					curr = list[i];
					//resets the iteration variable to zero
					i = 0;
				}
				//removes a node from the stack if the iteration finishes and no connections are found
				if (i == 9) {
					curr = pop();
					i = 0;
				}
				//increases i
				i++;
			}
			
			System.out.println("\nTotal weight is: " + weight);
			System.out.println();
		}
		
		//determines if the queue is empty
		public Boolean qIsEmpty() {
			//if the front has passed the back or the front is -1 it is empty
			return (front == -1 || front > back);
		}
		
		//determines if the queue is full
		public Boolean qIsFull() {
			//if there is something in the last element of the queue then the queue is full
			return back == 9;
		}
		
		//adds a node to the queue
		public void enqueue(Node curr) {
			//if this is the first node to be added
			if (front == -1) {
				//increases the front to zero
				front++;
				//adds the node to the front of the queue
				queue[front] = curr;
				
			//if the queue is not full
			} else if (!qIsFull()) {
				//increases the back
				back++;
				//adds the node to the back of the queue
				queue[back] = curr;
			//if the queue is full
			} else {
				System.out.println("The queue is full so the node can't be added.");
			}
		}
		
		//removes a node from the queue
		public Node dequeue() {
			//if the queue is not empty
			if(!qIsEmpty()) {
				//returns the node at the front of the queue
				return queue[front++];
				
			//if the queue is empty
			} else {
				System.out.println("The queue is empty.");
				//garbage value
				return new Node(-1);
			}
			
		}
		
		//prints out the graph is Breadth First Search order
		public void PrintBFS(Node curr) {
			//adds a node to the queue
			enqueue(curr);
			//sets this node to be visited
			visited[curr.num] = 1;
			System.out.print("Breadth First Search: ");
			
			//runs while the queue isn't empty
			while (!qIsEmpty()) {
				//sets the current node to the front node
				//removes this node from the queue
				curr = dequeue();
				
				//runs through each number for ever node
				for(int i = 0; i < 10; i++) {
					//if there is a connection with the current node and the node in the loop
					//and that node hasn't been visited yet
					if (curr.connect[i] != 0 && visited[i] == 0) {
						//marks the node as visited
						visited[i] = 1;
						
						//adds the node to the queue
						enqueue(list[i]);
											
						//increases the weight of the current tree
						weight += curr.connect[i];
					}
				}
				//prints the number of the current node
				System.out.print(curr.num + ", ");
				
			}
			System.out.println("\nTotal weight is: " + weight);
			System.out.println();

		}
}
