import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * Program that finds the number of x's in a given file
 */
public class FindX {
	public static void main(String[] args) {
		
		//gets the file from the user
		Scanner sc;
		Scanner sr = new Scanner(System.in);
		System.out.println("Type in the exact path to your file.");
		File file= new File(sr.nextLine());
		
		//variable used for counting the amount of x's
		int count = 0;
		
		//tries to read the file and checks for x's
		try {
			sc= new Scanner(file);
			
			//takes every line of text, turns it into a string, and checks every character to see if it is an x
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				for(int i = 0; i < str.length(); i++) {
					if(str.charAt(i) == 'x') {
						count++;
					}
				}
			}
			
			//prints the amount of x's
			sc.close();
			System.out.println("There were " + count + " x's in the file.");
		}
		
		//prevents an error if the file can't be found
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
		} 
		
	}
	
}
