package practice;

import genericUtilities.JavaUtility;

public class GenericMethodsPractice {
	
	public static void main(String[] args) { //calling/caller function
		
		//hardcoding
//		int a = 30; //30 and 40
//		int b = 40;
//		int c = a+b;
//		System.out.println(c);

		//Call add here
		int sum = add(10,20);
		System.out.println(sum);
		int v = add(sum,70);
		System.out.println(v);
		System.out.println(add(100,200));
		
		
		JavaUtility j = new JavaUtility();
		System.out.println(j.getSystemDateInformat()); 
	}
	
	public static int add(int a, int b) //called function - generic - business logic 
	{
		int c = a+b; //result
		return c;
	}

}
