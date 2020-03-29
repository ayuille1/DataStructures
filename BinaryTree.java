

public class BinaryTree {
	Node root;
	int[] arr = {1, 4, 5, 3, 6, 9, 7, 8, 2, 0};
	
	
	
	static class Node {
		Node leftChild;
		Node rightChild;
		int data;
		
		public Node(int data) {
			leftChild = null;
			rightChild = null;
			this.data = data;
		}
	}
	
	public static void main(String[] args) {
		//makes a new binary tree
		BinaryTree tree = new BinaryTree();
		
		//makes a tree of all the elements in the array
		tree.makeListBased();
		
		//prints the tree in post order
		System.out.print("Post order of the list based tree: ");
		postOPrint(tree.root);
		System.out.println();
		System.out.println();
		
		//makes a new array based binary tree
		BinaryTree arrTree = new BinaryTree();
		int[][] treeArr = arrTree.makeArrBased();
		
		//prints the array based binary tree
		printArrBased(treeArr);
	}
	
	//counts how many children a node has
	public static int countChildren(Node curr) {
		int count = 0;
		
		if (curr.leftChild != null) {
			count++;
		}
		if (curr.rightChild != null) {
			count++;
		}
		return count;
	}
	
	//prints the tree in post order(left child, right child, root)
	public static void postOPrint(Node root) {
		if (root != null) {
			postOPrint(root.leftChild);
			postOPrint(root.rightChild);
			System.out.print(root.data + ", ");
		}
	}
	
	//returns whether or not the root is null(if the tree is empty)
	public Boolean isEmpty() {
		return root == null;
	}
	
	//adds an element to a list based tree
	public void listInsert(Node curr, int value) {
		//if the tree is empty then the root is created
		if(isEmpty()) {
			root = new Node(value);
		
		//if the current node has no children then a new left child is created
		} else if (countChildren(curr) == 0) {
			curr.leftChild = new Node(value);
			
		//if the current node has one child then a new right child is created
		} else if (countChildren(curr) == 1) {
			curr.rightChild = new Node(value);
			
		//if the current node's left child has two children and its right child doesn't
		//then this method is run again starting at the right child
		} else if (countChildren(curr.leftChild) == 2 && countChildren(curr.rightChild) < 2){
			listInsert(curr.rightChild, value);
		
		//if the current node's left child has two children and its right child does too
		//then this method is run again starting at the left child
		} else {
			listInsert(curr.leftChild, value);
		}
		
	}
	
	//adds all elements from the array to a list based tree
	public void makeListBased() {
		for(int i = 0; i < 10; i++) {
			listInsert(root, arr[i]);
		}
	}
	
	//makes the array based tree
	public int[][] makeArrBased() {
		//makes a 2D matrix to store the tree
		int[][] treeArr = new int[10][3];
		//keeps track of the position in the array
		int track = 1;
		
		//makes every element -1(empty tree)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				treeArr [i][j] = -1;
			}
		}
		
		//adds the elements of the array to the tree
		for (int i = 0; i < 10; i++) {
			
			//makes a node for each element
			treeArr[i][0] = arr[i];
			//if all of the elements haven't been given a parent it is added
			if (track < 10) {
				treeArr[i][1] = arr[track++];
			}
			//if all of the elements haven't been given a parent it is added
			if (track < 10) {
				treeArr[i][2] = arr[track++];
			}
		}
		//returns the tree
		return treeArr;
	}

	//print the tree in matrix format
	public static void printArrBased(int[][] treeArr) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(treeArr[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("Each row represents a node. The first element is the data in the node.");
		System.out.println("The second element is the left child, and the third element is the right child.(-1 represents no child)");
		
	}
}
