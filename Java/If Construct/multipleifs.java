package pkgJavaTraining;
import java.util.*;
public class multipleifs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int marks = 0;
		
		System.out.println("Enter marks:");
		Scanner scan = new Scanner(System.in);
		
		marks = scan.nextInt();
		
		
		/*if(marks>=75) {
			System.out.println("You got the distinction");
		}
		else if(marks >= 60)
		{
			System.out.println("You got the first class");
		}
		else if(marks >= 45) {
			System.out.println("You got the second class");
		}
		else if(marks >= 35) {
			System.out.println("You got pass class");
		}
		else {
			System.out.println("Fail");
		}*/
		
		
		if(marks >= 75) {
			System.out.println("You got the distinction");
		}
		else
		{
			if (marks >= 60) {
				System.out.println("You got the first class");
			}
			else {
				if(marks >= 45) {
					System.out.println("You got the second class");
				}
				else {
					if(marks >= 35) {
						System.out.println("You got pass class");
					}
					else {
						System.out.println("Fail");
					}
				}
			}
		}
		
	}

}
