import java.util.Scanner;
/*
 * Given the number of disks, recursively solves the Tower of Hanoi problem and 
 * gives the minumum numbers of moves required for that number of disks
 */
public class Hanoi {
	int step;
	public static void main(String[] args) {
		
		//gets number of disks from the user
		Scanner sc = new Scanner(System.in);
		System.out.println("How many discs are there?");
		int num = sc.nextInt();
		sc.close();
		
		//variables to represent the poles
		int one = 1;
		int two = 2;
		int three = 3;
		
		//used to count and print the number of steps
		Hanoi h = new Hanoi();
		move(num, two, one, three, h);
		System.out.println("Total moves: " + h.step);
		
	}
	
	// moves disks using the format: from, to, and extra(poles).
	public static void move(int num, int two, int one, int three, Hanoi h) {
		
		//prints if there are on disks
		if (num == 0) {
			return;
		}
		
		//end of each recursive call(base case)
		if (num == 1) {
			h.step++;
			System.out.println(h.step + ") " + two + " to " + one) ;
			return;
		}
		
		//moves the disks from the second pole to the third pole
		move(num-1, two, three, one, h); 
		h.step++;
		System.out.println(h.step + ") "+ two + " to " + one);
		
		//moves the disks from the third pole to the first pole
		move(num-1, three, one, two, h);
	}
	
}
