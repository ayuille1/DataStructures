import java.util.Random;
import java.util.Arrays;


/*
 * Makes an array 100 elements long made up of random values that range between 0 and 99. 
 * Then sorts the array three separate times using selection sort, merge sort, and bubble sort.
 * It displays how long it took each method and which was the fastest and which was slowest.
 * 
 * Generally speaking selection sort was the slowest and merge sort was the fastest, but I saw this wasn't always the case.
 * So, I added the compare method to find out which algorithm was fastest for that execution.
 */


public class Lab3 {
	public static void main(String[] args) {
		//creates and duplicates the array so that all algorithms sort the same array
		int[] arr = make();
		int[] arr2 = Arrays.copyOf(arr, arr.length);
		int[] arr3 = Arrays.copyOf(arr, arr.length);
		
		
		//sorts the array using selection sort and checks how long it takes. Also prints the array before and after printing
		printArr(arr); 
		
		long startTime1 = System.currentTimeMillis(); 
		selectionSort(arr); 
		printArr(arr); 
		long elapsedTime1 = (System.currentTimeMillis() - startTime1); 
		System.out.println("It took selection sort " + elapsedTime1 + " time units to sort the array.");
		System.out.println();
		
		//sorts the array using bubble sort and checks how long it takes. Also prints the array before and after printing
		printArr(arr2); 
		
		long startTime2 = System.currentTimeMillis(); 
		bubbleSort(arr2);
		printArr(arr2);
		long elapsedTime2 = (System.currentTimeMillis() - startTime2);
		System.out.println("It took bubble sort " + elapsedTime2 + " time units to sort the array.");
		System.out.println();
		
		//sorts the array using merge sort and checks how long it takes. Also prints the array before and after printing
		printArr(arr3); 
		
		long startTime3 = System.currentTimeMillis(); 
		mergeSort(arr3, 0, arr3.length-1); 
		printArr(arr3);
		long elapsedTime3 = (System.currentTimeMillis() - startTime3);
		System.out.println("It took merge sort " + elapsedTime3 + " time units to sort the array.");
		System.out.println();
		
		compare(elapsedTime1, elapsedTime2, elapsedTime3);
		
	}
	
	/*
	 * Selection Sort
	 * 
	 * 1) set min location to 0
	 * 		minIndex = i
	 * 2) find the minimum element
	 * 		for j = i+1; j< arrLength; j++
	 * 		if arr[j] < min
	 * 3) move the minimum value to the min location
	 * 		temp = arr[i]
	 * 		arr[i] = min
	 * 		arr[minIndex] = temp
	 * 4) move th1e min location to the next element
	 * 		minIndex = i
	 * 5) Repeat
	 * 		for i=0 to arr.length i++
	 * 
	 */
	
	public static void selectionSort(int[] arr) { //sorts the array with the selection sort algorithm
		
		
		//Step 5(repeats)
		for (int i = 0; i < arr.length; i++) { //runs through all elements of an array
			
			//Step 1, Step 4(it moves it when it repeats) 
			int minIndex = i; //sets the lowest unsorted index
			int min = arr[i]; //sets the value at the min index to the minimum value
			
			
			//Step 2(finds the minimum)    
			for (int j = i + 1; j < arr.length; j++) { //run for all values higher than the current position
				if (arr[j] < min) { //if an element is greater than the current minimum element
					minIndex = j; //sets the new index of the lowest value
					min = arr[j]; //sets the new min to the new lowest value
			    }
			}
			
			//Step 3
			int temp = arr[i]; //stores the value of the original minimum value during the swap
			arr[i] = min; //places the smaller value in the lower index
			arr[minIndex] = temp; //places the original minimum into the higher index
		}
	}
	
	/*
	 * Bubble Sort
	 * 
	 * 1) Compare the first and second elements
	 * 		if arr[j-1] > arr[j]
	 * 2) Swap if the second element is smaller
	 * 		temp = arr[j-1]
	 * 		arr[j-1] = arr[j]
	 * 		arr[j] = temp
	 * 3) Repeat for the second and third, then third and fourth, etc. to the end of the array
	 * 		for int j =1; j < n-1; j++
	 * 4) Then repeat from beginning, but don't check the last elements because it is now the highest, until the array is sorted
	 * 		for int i=0; i < n; i++
	 */
	
	public static void bubbleSort(int[] arr) {
	    //check time
	    int n = arr.length; 
	    int temp = 0;
	    //Step 4
	    for (int i = 0; i < n; i++) { //runs for all elements of an array
	    	//Step 3
	        for (int j = 1; j < (n-i); j++) { //checks all elements in the array but checks less elements each pass because the highest remaining value is found each pass
	            //Step 1
	        	if (arr[j-1] > arr[j]) { //checks if the lower element is greater than the element after it
	                //Step 2
	            	temp = arr[j-1];     
	                arr[j-1] = arr[j];
	                arr[j] = temp;
	            }
	        }
	    }
	    
	}
	
	/*
	 * Merge Sort
	 * 
	 * 1) Find the middle point of the array
	 *    mid = (left + right)/2
	 * 2) recursively call mergeSort to sort the first half
	 *    mergeSort(arr, left, mid);
	 * 3) recursively call mergeSort to sort the second half
	 *    mergeSort(arr , mid+1, right);
	 * 4) merge the two sorted halves of the array
	 *    merge(arr, left, mid, right);
	 * 5) Compare the first elements of both arrays
	 * 	  while i < LeftLength and j < RightLength
	 *    if Left[i] < Right[j]
	 * 	  	add Left[i] to arr
	 * 	    increment i
	 *    else 
	 *      add Right[j] to arr
	 *      increment j
	 *    increment arr index
	 * 6) add any remaining elements to arr
	 *    while i < LeftLength
	 *    	add Left[i];
	 *      increment i and arr index
	 *    while j < RightLength
	 *    	add Right[j];
	 *      increment j and arr index
	 */
	
    public static void merge(int arr[], int left, int mid, int right) {
        //finds the size of the two subarrays
        int n1 = mid - left + 1; 
        int n2 = right - mid; 
        
        //creates two temporary arrays
        int Left[] = new int [n1]; 
        int Right[] = new int [n2]; 
  
        //Initializes the two subarrays
        for (int i = 0; i < n1; ++i) 
            Left[i] = arr[left + i]; 
        
        for (int j = 0; j < n2; ++j) 
            Right[j] = arr[mid + 1+ j]; 
  
  
        // Merges the two subarrays
  
        // Initial indexes of the two subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarray array 
        int k = left; 
        
        //Step 5
        while (i < n1 && j < n2) { //compares the values of an element from each array 
            if (Left[i] <= Right[j]) //if the value in the left array is lower than or equal to the value from the right array
            {                        //then the left value is put in merged array, and it looks at the next value in the left array
                arr[k] = Left[i]; 
                i++; 
            } 
            else{                    //if the value in the right array is lower than the value from the left array
                arr[k] = Right[j];   //then the right value is put in merged array, and it looks at the next value in the right array
                j++; 
            } 
            k++; //this is the raises the index of the merged array that is being considered
        } 
  
        // Copies remaining elements of Left[] if any remain
        //Step 6
        while (i < n1) { 
            arr[k] = Left[i]; 
            i++; 
            k++; 
        } 
  
        // Copies remaining elements of Right[] if any remain 
        while (j < n2) {
            arr[k] = Right[j]; 
            j++; 
            k++; 
        } 
    } 
   
    //sorts the array
    public static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            // Find the middle point of the unsorted array
        	// Step 1
            int mid = (left + right)/2; 
  
            //Step 2
            mergeSort(arr, left, mid); 
            
            //Step 3
            mergeSort(arr , mid+1, right); 
  
            // Merge the sorted halves 
            // Step 4
            merge(arr, left, mid, right); 
        } 
    }
	
    // creates an array of 100 random integer values
	public static int[] make() {
		int[] arr = new int[100]; //creates an array that is 100 elements long
		Random r = new Random();
		
		for(int i = 0; i < 100; i++) { //fills every element of the array with a random number
			arr[i]= r.nextInt(100); //generates a random number between 0-99
		}
		
		return arr; //returns the filled array
	}
	
	// prints an array
	public static void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) { //fills every element of the array with a random number
			System.out.print(arr[i] + ", "); //prints each element of the array
		}
		
		System.out.println();
	}
	
	public static void compare(long et1, long et2, long et3) {
		if (et1 >= et2){ 
			if (et1 >= et3) { 
				System.out.println("Selection sort took the most amount of time");
				if (et2 >= et3) {
					System.out.println("Merge sort took the least amount of time"); //prints if selection sort was slowest and merge sort was fastest
				
				} else {
					System.out.println("Bubble sort took the least amount of time"); //prints if selection sort was slowest and bubble sort was fastest
				}
				
			} else if(et3 > et1) {
				System.out.println("Merge sort took the most amount of time"); //prints if merge sort was slowest and bubble sort was fastest
				System.out.println("Bubble sort took the least amount of time");
			}
			
		} else if(et2 >= et3) {
			System.out.println("Bubble sort took the most amount of time"); 
			if (et1 >= et3) {
				System.out.println("Merge sort took the least amount of time"); //prints if bubble sort was slowest and merge sort was fastest
			} else if(et3 > et1) {
				System.out.println("Selection sort took the least amount of time"); //prints if bubble sort was slowest and selection sort was fastest
			}
			
		}  else {
			System.out.println("Merge sort took the most amount of time"); //prints if merge sort was slowest and selection sort was fastest
			System.out.println("Selection sort took the least amount of time");
		}
	}
}
