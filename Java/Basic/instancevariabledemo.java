package pkgJavaTraining;

public class instancevariabledemo {
	public String classvar = "This is a static variable";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		instancevariabledemo obj1 = new instancevariabledemo();
		instancevariabledemo obj2 = new instancevariabledemo();
		instancevariabledemo obj3 = new instancevariabledemo();
		
		System.out.println("Value of variable = " + obj1.classvar);
		System.out.println("Value of variable = " + obj2.classvar);
		System.out.println("Value of variable = " + obj3.classvar);
		
		obj2.classvar = "This is a new value";
		
		System.out.println("Value of variable = " + obj1.classvar);
		System.out.println("Value of variable = " + obj2.classvar);
		System.out.println("Value of variable = " + obj3.classvar);
	}

}
