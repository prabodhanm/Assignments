package pkgJavaTraining;
import java.util.*;
public class reversenumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 0;
		int num1 = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter a number");
		num = scan.nextInt();
		
		num1 = num % 10;
		
		num1 = num1 * 10;
		num1 = num1 + num / 10;
		
		System.out.println("Reverse number is: " + num1);
		 
		
		
	}

}
