package pkgJavaTraining;

public class printasterisktriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 7;
		for(int i = 1; i<=7; i++) {
			for(int j=1;j<=k;j++) {
				System.out.print("*");
			}
			k--;
			System.out.println();
		}
	}

}
