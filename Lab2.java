/*
 * checks if there is a value that is followed by a value that is 10 times its value
 */
public class Lab2 {
	
	public static void main(String[] args) {
	int[] array220 = {1,2,20}; //given array of numbers
	timesTen(array220,0); //takes the given array and the index of the first pass is always zero
	}
	
	//method that recursively calls the array and an int value to determine whether a value is followed by a value that is ten times that value
	public static int timesTen(int[] array220, int index) {
		
		if(index==array220.length-1) { //makes sure that the index doesn't go out of bounds, if it reaches the end of the array it prints false
			System.out.println("false");
			return 0;
		} else if(array220[index] * 10 == array220[index+1]) { //checks if the next value is 10 times the current value
			System.out.println("true");
			return 1;
		}else { //recursively calls the next index and checks it
			return timesTen(array220, index+1);
		}
	}
	
}
