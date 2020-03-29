
/*
 * Austin Yuille
 * F 11:00 - 11:50
 * Lab 12 - Spanning Trees(Extra Credit)
 * 
 * Finds the minimum spanning tree given the edge matrix of a graph and prints the weight
 */

public class MSTGraph {
	// makes a matrix to store the connections between nodes
	int[][] matrix = new int[10][10];
	// makes an array that stores whether or not a node has been visited
	int[] visited = new int[10];
	// makes an array of all of the nodes
	Node[] list = new Node[10];
	// keeps track of the total weight of a spanning tree
	int weight = 0;
	// keeps track of the cycles
	int[][] cycleMat = new int[10][10];
	
	/*
	 * makes a stack in the graph class
	 */
	// keeps track of the top value
	int top = -1;
	// sets the max stack size
	int max = 10;
	// creates the stack
	Node[] stack = new Node[max];

	// creates a node object
	static class Node {
		// the value that the node holds
		int num;

		// an array that holds what other nodes it is connected to and the weights of
		// those edges
		int[] connect = new int[10];

		public Node(int n) {
			this.num = n;
		}

		public Node() {
			num = 0;
		}
	}

	public static void main(String[] args) {
		// makes the graph
		MSTGraph graph = new MSTGraph();

		// adds the edges to the matrix
		graph.addEdge(0, 3, 2);
		graph.addEdge(0, 4, 4);
		graph.addEdge(3, 8, 3);
		graph.addEdge(3, 9, 1);
		graph.addEdge(4, 6, 2);
		graph.addEdge(6, 2, 4);
		graph.addEdge(6, 7, 3);
		graph.addEdge(7, 2, 2);
		graph.addEdge(7, 9, 3);
		graph.addEdge(2, 1, 1);
		graph.addEdge(1, 5, 6);
		graph.addEdge(9, 5, 17);

		// prints the matrix
		graph.printMatrix();
		System.out.println();

		// makes a list of the nodes
		graph.makeList();
		
		graph.PrintDFS(graph.list[0]);
		
		// resets the visited list so another search can be performed
		graph.reset();

		// uses Prim's algorithm to find the MST
		graph.primMST(graph.list[0]);
		
		
	}

	// resets the visited array to where all elements are 0 and resets weight to
	// zero
	public void reset() {
		for (int i = 0; i < 10; i++) {
			visited[i] = 0;
		}
		weight = 0;
	}

	// changes the value in the matrix from 0 to 1 to represent an edge
	public void addEdge(int a, int b, int wgt) {
		// adds an edge for both sides of the matrix since it's not a directed graph
		matrix[a][b] = wgt;
		matrix[b][a] = wgt;
	}

	// deletes an edge from the matrix
	public void deleteEdge(int a, int b) {
		// replaces the weight in the matrix with 0, deleting the edge from the graph
		matrix[a][b] = 0;
		matrix[b][a] = 0;
	}

	// creates a list of the nodes
	// and creates a list of connections for each node
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

	// prints the adjacency matrix
	public void printMatrix() {
		// runs through all of the rows
		for (int i = 0; i < 10; i++) {
			System.out.print("[");

			// runs through all of the columns
			for (int j = 0; j < 10; j++) {
				// prints the element in row i and column j
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
						//pushed that node onto the stack
						push(list[i]);
						//prints the number of that node
						System.out.print(list[i].num + ", ");
						//marks that node as visited
						visited[i] = 1;
						//makes that node the current node
						curr = list[i];
						//resets the iteration variable to zero
						i = 0;
					} else if (curr.connect[i] != 0 && visited[i] != 0) {
						visited[i]++;
						cycleMat[curr.num][i] = 1;
					}
					//removes a node from the stack if the iteration finishes and no connections are found
					if (i == 9) {
						curr = pop();
						i = 0;
					}
					//increases i
					i++;
				}
				
				System.out.println();
				for (int j = 0; j < 10; j++) {

					for (int k = 0; k < 10; k++) {
						if (cycleMat[j][k] == 1) {
							System.out.println("Cycle at: " + j + ", " + k);
						}
					}
				}
			}

	// finds the minimum spanning tree using Prim's algorithm
	public void primMST(Node curr) {
		// keeps track of how many edges have been added(# of nodes - 1 should be added)
		int count = 0;

		// keeps track of the current minimum node
		int minNode = 0;

		// keeps track of the current minimum weight
		int minWgt = 0;

		// marks the first node as visited
		visited[curr.num] = 1;

		// prints the first node
		System.out.print("Minimum spanning tree: ");
		System.out.print(curr.num + ", ");

		// increases the count
		count++;
		while (count < 10) {
			// resets the minimum weight and node
			minWgt = 0;
			minNode = 0;

			// runs through every row that has been visited
			for (int i = 0; i < 10; i++) {
				// checks if the row has been visited
				if (visited[i] == 1) {
					// checks each element of the row if it has been visited
					for (int j = 0; j < 10; j++) {
						// if the current matrix element is less than the current minimum weight and is
						// not zero
						// or if the mimimum weight is zero and if that column has not been marked as
						// visited yet
						if (((matrix[i][j] < minWgt && matrix[i][j] != 0) || minWgt == 0) && visited[j] == 0) {
							// set that column to the current minimum
							minNode = j;
							// set the current minimum weight to the element of the matrix
							minWgt = matrix[i][j];
						}
					}
				}
			}
			// prints the node that was visited
			System.out.print(minNode + ", ");

			// marks that node as visited
			visited[minNode] = 1;

			// increases the total weight of the MST
			weight += minWgt;

			// increases the count
			count++;
		}

		// prints the total weight
		System.out.println("\nTotal weight: " + weight);
		System.out.println();
	}
}
