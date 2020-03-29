import java.util.Scanner;

/*
 * Austin Yuille
 * F 11-11:50
 * Lab 8 (3/29/2019)
 * 
 * Takes a given radius and finds the area of a circle with that radius
 * Adds or subtracts two numbers and checks for overflow. If no overflow, prints the value.
 */
public class Lab8 {
	public static void main(String[] args) {
		
		//takes a double value from the user and finds the area of that circle
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the radius of the circle.");
		double radius = sc.nextDouble();
		circleArea(radius);
		
		//takes two ints from the user and adds them
		System.out.println("Please input the first int you would like to add.");
		int int1 = sc.nextInt();
		System.out.println("Please input the second int you would like to add.");
		int int2 = sc.nextInt();
		add(int1, int2);
		
		//takes two ints from the user and subtracts them
		System.out.println("Please input the first int you would like to subtract.");
		int int3= sc.nextInt();
		System.out.println("Please input the second int you would like to subtract.");
		int int4 = sc.nextInt();
		subtract(int3, int4);
		
		sc.close();
	}

	// takes a radius(double) from the user and uses it to find the area of a circle
	public static void circleArea(double radius) {
	
		// uses the radius to find the area
		System.out.println("The area of the circle with a radius of " + radius + " is: " + radius * radius * Math.PI
				+ " units sq.");
	}

	// takes two ints from the user and adds them(checks for underflow and overflow)
	public static void add(int int1, int int2) {

		// since long has more space we can put the int values in a long variable to
		// check for overflow
		long lInt1 = int1;
		long lInt2 = int2;
		long sum = lInt1 + lInt2;

		// checks for overflow and underflow or overflow and throws an error if it
		// exists
		if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
			throw new ArithmeticException("There is an int overflow!");
		}

		// prints the sum
		System.out.println("The sum of the two values is: " + (int1 + int2));
	}

	// takes two ints from the user and subtracts them(checks for underflow and
	// overflow)
	public static void subtract(int int1, int int2) {

		// since long has more space we can put the int values in a long variable to
		// check for overflow
		long lInt1 = int1;
		long lInt2 = int2;
		long sum = lInt1 - lInt2;

		// checks for overflow and underflow or overflow and throws an error if it
		// exists
		if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
			throw new ArithmeticException("There is an int overflow!");
		}

		// prints the sum
		System.out.println("The difference of the two values is: " + (int1 - int2));
	}
}
