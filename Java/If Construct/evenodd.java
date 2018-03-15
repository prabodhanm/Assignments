package pkgJavaTraining;
import java.util.*;
public class evenodd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 0;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter a number");
		num = scan.nextInt();
		
		if(num%2 == 0) {
			System.out.println("Number is even");
		}
		else {
			System.out.println("Number is Odd");
		}
		
	}

}
