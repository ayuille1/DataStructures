import java.util.Arrays;
import java.util.Random;

/*
 * Austin Yuille
 * Lab4
 * F 11 - 11:50
 * 
 * takes an array of 12 random ints and finds the sums of the first six and last six
 * inserts the sums after each group
 * removes any zeros from the sum array and then sorts the array without zeros
 */

public class Lab4 {
	public static void main(String[] args) {
		int[] arr = make(); //makes an array 14 elements long with 12 random ints
		printArr(arr); //prints the array
		sum(arr); //finds the sums of the array and inserts the sums
		int[] newArr = removeZero(arr); //makes a new array that doesn't contain any zeros
		printArr(newArr); //prints the new array
		Arrays.sort(newArr); //sorts the array
		printArr(newArr); //prints the sorted array
	}
	
	
	// creates an array that is 14 elements long with 12 random integer values
	public static int[] make() {
		int[] arr = new int[14]; //creates an array that is 14 elements long
		Random r = new Random(); //used to generate a random number
		
		for(int i = 0; i < 12; i++) { //fills the first 12 elements of the array with a random number
			arr[i]= r.nextInt(5); //generates a random number between 0-4
		}
			
		return arr; //returns the filled array
	}
	
	//prints an array
	public static void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) { //runs through every element of an array
			System.out.print(arr[i] + ", "); //prints each element of the array
		}
		
		System.out.println();
	}
	
	//finds the sum of a block of six numbers and inserts it after that block
	public static void sum(int[] arr) {
		int sum1 = 0;
		int sum2 =0;
		
		// finds the sum of the first six numbers
		for (int i = 0; i < 6; i++) {
			sum1 = arr[i] + sum1;
		}
		
		//finds the sum of the second six numbers
		for (int i = 6; i < 12; i++) {
			sum2 = arr[i] + sum2;
		}

		//shifts the second set of six numbers to the right one space
		for (int i = 11; i > 5; i--) {
			arr[i+1] = arr[i];
		}
		
		//adds the sum of the first six numbers behind the first set of six numbers
		arr[6] = sum1;
		//adds the sum of the second six numbers behind the second set of six numbers
		arr[13] = sum2;
		
		//prints the array
		printArr(arr);
	}
	
	//removes the zeros from the array
	public static int[] removeZero(int[] arr) {
		int count = 0;
		
		//determines how many zeros are in the array
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				count++;
			}
		}
		
		//creates an array that can contain all of the elements that are not zeros from the first array
		int arr2[] = new int[14-count];
		int j = 0;
		
		//adds any element that isnt a zero to the shorter array
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				arr2[j] = arr[i];
				j++;
			}
		}
		//returns the short array
		return arr2;
	}
	
	
}
