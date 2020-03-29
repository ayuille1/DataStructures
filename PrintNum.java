/*
 * prints the numbers between 50 and 25 that are divisible by 5 
 */
public class PrintNum {
	public static void main(String[] args) {
		int i = 50; //makes a variable that is equal to 50
		while (i >= 25) { //will keep running until the variable 'i' is less than 25
			if(i%5 == 0) { //if the variable is evenly divisible by 5 it will print the value of the variable
				System.out.println(i);
			}
			i--; //increments the variable down one every time the loop is run
		}
	}
}
