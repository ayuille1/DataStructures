/*
 * Austin Yuille
 * MW 12:30-1:45
 * Assignment 2 Part 2
 * 
 * 
 * 
 * This is the object used in EmpStack.java
 */
public class Employee {
	String name;
	String ssn;
	int sal;
	
	public Employee() {
		name = "Unknown";
		ssn = "000-00-0000";
		sal = 10000;
	}
	
	public Employee(String name, String ssn, int sal) {
		this.name = name;
		this.ssn = ssn;
		this.sal = sal;
	}
}
