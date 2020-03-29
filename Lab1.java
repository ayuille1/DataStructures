package lab1_2720;
import java.util.Scanner;
/*
 * 
 * 
 */

public class Lab1 {
	
	public static void main(String[] args) {
		String str = user(); //creates the string from the user input
		String[] splitStr = part(str); //creates the array of the two components of the string
		splitStr = testArr(splitStr); //makes takes the string array and makes sure it is only two elements long
		int num1 = Integer.parseInt(splitStr[1]); //converts the second part of the string array into an int variable
		num1 = testNum(num1); //reassigns num1 just in case the value was changed in the check method
		order(splitStr[0]); //passes the first element of the array to check if it has the correct order
	}
	
	//gets a string input from the user
	public static String user() {
		Scanner sc = new Scanner(System.in); //allows the user to input a string
		System.out.println("Please enter a string of characters and a number separated by one space.");
		String str = sc.nextLine();
		sc.close(); //closes the scanner
		return str;
	}
	
	//splits the given string into its two components
	public static String[] part(String str) {
		String[] splitStr = str.split(" "); //splits a string at a space character
		return splitStr;
	}
	
	//makes sure the array is the correct length so errors don't interrupt the program
	public static String[] testArr(String[] splitStr) {
		//if the array is longer than two characters it makes a new array using the first two elements
		try {
			String[] strt = {splitStr[0],splitStr[1]};
			return strt;
		} catch (ArrayIndexOutOfBoundsException e) { //if the array was not originally 2 elements long it makes the second element 1
			System.out.println("There was not a space in your input. Your int value has been made 1.");
			String[] strt = {splitStr[0], "1"};
			return strt;
		}
	}
	
	//makes sure the second part of array can divide the number 2
	public static int testNum(int num1) {
		try { //tests to see if the number can divide 2
			int num = 2/num1;
		} catch (ArithmeticException e){ //if the number is zero it reassigns the value of the variable to 1
			System.out.println("You must be able to divide 2 by the number you input. The number is now 1.");
			num1 = 1;
		}
		return num1;
	}
	
	//checks the order of the string
	public static void order(String str) {
		int countC = 0; 
		int countA = 0;
		int countB = 0;
		
		//checks to see how many 'c' characters are in the string
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'c') {
				countC++;
			}
		}
		
		//if there is only one 'c' character then it checks to see if there are only 'a' characters in front of it.
		if (countC == 1) {
			for (int i = 0; i < str.indexOf('c'); i++) { //counts how many letters aren't 'a' before 'c', to print yes the count should be 0
				if (str.charAt(i) != 'a') { 
					countA++; 
				}
			}
		}
		
		//if there are no characters other than 'a' in front of the 'c' then it checks to see if there are only 'b' characters after the 'c'
		if (countA == 0) {
			for (int i = (str.indexOf('c'))+1; i < str.length(); i++ ) { //counts how many letters aren't 'b' after 'c', to print count should be 0
				if (str.charAt(i) != 'b') {
					countB++;
				}
			}
		}
		if (countB == 0 && countA == 0 && countC == 1) { // prints yes if all of the conditions are true
			System.out.println("Yes.");
		} else { //prints no if one or more of the conditions is false
			System.out.println("No.");
		}
		
	}
}
