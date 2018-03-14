package pkgJavaTraining;


public class staticvariabledemo {
	public static String classvar = "This is a static variable";
	public static void main(String[] args) {
		staticvariabledemo obj1 = new staticvariabledemo();
		staticvariabledemo obj2 = new staticvariabledemo();
		staticvariabledemo obj3 = new staticvariabledemo();
		
		System.out.println("Value of variable = " + obj1.classvar);
		System.out.println("Value of variable = " + obj2.classvar);
		System.out.println("Value of variable = " + obj3.classvar);
		
		obj2.classvar = "This is a new value";
		
		System.out.println("Value of variable = " + obj1.classvar);
		System.out.println("Value of variable = " + obj2.classvar);
		System.out.println("Value of variable = " + obj3.classvar);
	}
}
