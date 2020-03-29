import java.util.Random;

/*
 * Austin Yuille
 * F 11:00-11:50
 * Lab 9
 * 
 * Takes an array and inserts all of its values into a binary search tree
 * Then, prints the post order traversal of the binary search tree
 */
public class PostOTree {
	Node root;
	int count;
	
	static class Node {
		int data;
		Node leftChild;
		Node rightChild;
		
		public Node(int n) {
			data = n;
			leftChild = null;
			rightChild = null;
		}
	}
	
	public static void main(String[] args) {
		//makes a new tree
		PostOTree tree = new PostOTree();
		
		//makes an array from 0-9 in a random order
		//int[] arr = {6, 4, 2, 3, 5, 7, 1, 8, 9, 0};
		int[] arr = {1, 4, 5, 3, 6, 9, 7, 8, 2, 0};
		
		//prints the array
		tree.printArr(arr);
		
		//fills the tree with the values from the array
		tree.makeTree(arr);
		
		//prints the tree in postorder
		System.out.print("The tree in postorder is: ");
		postOPrint(tree.root);
		System.out.println();
		
		//deletes a number from the tree and prints out the new tree postorder
		delete(6, tree.root);
		System.out.print("The tree in postorder is: ");
		postOPrint(tree.root);
		System.out.println();
		
		
		/*
		 * Does the same thing as above but with a different tree
		 */
		//makes an array from 0-9 in a random order
		PostOTree tree2 = new PostOTree();
		
		int[] arr2 = {4, 1, 6, 5, 9, 3, 0, 8, 2, 7};
		
		//prints the array
		tree2.printArr(arr2);
		
		//fills the tree with the values from the array
		tree2.makeTree(arr2);
		
		//prints the tree in postorder
		System.out.print("The tree in postorder is: ");
		postOPrint(tree2.root);
		System.out.println();
		
		//deletes a number from the tree and prints out the new tree postorder
		delete(6, tree2.root);
		System.out.print("The tree in postorder is: ");
		postOPrint(tree2.root);
		System.out.println();
	}
	
	//prints an array
	public void printArr(int[] arr) {
		System.out.print("The array is: ");
		
		//traverses through the array and prints all of the values
		for(int i = 0; i < 10; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
	}
	
	//prints the tree in post order(left child, right child, root)
	public static void postOPrint(Node root) {
		if (root != null) {
			postOPrint(root.leftChild);
			postOPrint(root.rightChild);
			System.out.print(root.data + ", ");
		}
	}
	
	//deletes a node from the tree
	public static Node delete(int n, Node root) {
		//if the tree is empty
		if (root == null) {
			return root;
		}
		
		//if the number you are looking for is less than the root go down the left side
		if (n < root.data) {
			root.leftChild = delete(n, root.leftChild);
			
		//if the number you are looking for is greater than the root go down the right side
		} else if (n > root.data) {
			root.rightChild = delete(n, root.rightChild);
			
		//if the number you are looking for is equal to the root then delete this node
		} else {
			
			//if the left child is null
			if (root.leftChild == null) {
				return root.rightChild;
				
			//if the right child is null
			} else if (root.rightChild == null) {
				return root.leftChild;
			} 
		
		//if there are two children
		//get the minimum of the right side(inorder successor)
		root.data = minNode(root.rightChild);
		
		//delete the inorder successor
		root.rightChild = delete(root.data, root.rightChild);
		}
		return root;
	}
	
	public static int minNode(Node root) {
		int min = root.data;
		while (root.leftChild != null) {
			min = root.leftChild.data;
			root = root.leftChild;
		}
		return min;
	}
	
	//checks if the tree is empty
	public Boolean isEmpty() {
		return root == null;
	}
	
	//adds an element to the tree
	public void insert(int n) {
		//creates a placeholder node
		Node temp;
		
		//if the tree is empty it creates the root of the tree
		if(isEmpty()) {
			root = new Node(n);
		
		//if the number being inserted is less than the root
		//and the root does not already have a left child
		//the root's left child is made
		} else if(n < root.data && root.leftChild == null) {
			root.leftChild = new Node(n);
			
		//if the number being inserted is greater than the root
		//and the root does not already have a right child
		//the root's right child is made
		} else if (n > root.data && root.rightChild == null) {
			root.rightChild = new Node(n);
			
			//if the number being inserted is less than the root
			//and the root already has a left child
		} else if (n < root.data && root.leftChild != null) {
			
			//placeholder is set equal to the root
			temp = root.leftChild;
			
			//runs while the placeholder has at least one child
			while(temp.leftChild != null || temp.rightChild != null) {
				
				//if the value being inserted is greater than the placeholder value
				//and the right child of the placeholder is not null
				if (n > temp.data && temp.rightChild != null) {
					//the placeholder is moved to its right child
					temp = temp.rightChild;
					
				//if the value being inserted is greater than the placeholder value
				//and the right child of the placeholder is not null
				} else if (n < temp.data && temp.leftChild != null) {
					//the placeholder is moved to its left child
					temp = temp.leftChild;
				
				} else {
					//stops the loop if none of the conditions are met
					break;
				}
			}
			
			//if the left child of the placeholder is null
			//and the value being inserted is less than the placeholder value
			if (temp.leftChild == null && n < temp.data) {
				//creates a new node which is the left child of the placeholder
				temp.leftChild = new Node(n);
				
			//if the right child of the placeholder is null
			//and the value being inserted is greater than the placeholder value
			} else if(temp.rightChild == null && n > temp.data) {
				//creates a new node which is the right child of the placeholder
				temp.rightChild = new Node(n);
			}
		
		//if the right child of the placeholder is not null
		//and the value being inserted is greater than the placeholder value
		} else if (n > root.data && root.rightChild != null) {
			//the placeholder is moved to the root's right child
			temp = root.rightChild;
			
			//runs while at least of the children of the placeholder is not null
			while(temp.leftChild != null || temp.rightChild != null) {
				//if the value being inserted is greater than the value of the placeholder
				//and the right child of the placeholder is not null
				if (n > temp.data && temp.rightChild != null) {
					//the placeholder is moved to its right child
					temp = temp.rightChild;
				
				//if the value being inserted is less than the value of the placeholder
				//and the left child of the placeholder is not null
				} else if (n < temp.data && temp.leftChild != null) {
					//the placeholder is moved to its left child
					temp = temp.leftChild;
				
				//stops the loop if none of the conditions are met
				} else {
					break;
				}
			}
			
			//if the left child of the placeholder is null
			//and the value being inserted is less than the value of the placeholder
			if (temp.leftChild == null && n < temp.data) {
				//the value is inserted as the left child of the placeholder
				temp.leftChild = new Node(n);
				
			//if the right child of the placeholder is null
			//and the value being inserted is greater than the value of the placeholder
			} else if(temp.rightChild == null && n > temp.data) {
				//the value is inserted as the right child of the placeholder
				temp.rightChild = new Node(n);
			}
			
		}
	}
	
	//inserts all of the values of an int array into a binary tree
	public void makeTree(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			insert(arr[i]);
		}
	}
	
}
