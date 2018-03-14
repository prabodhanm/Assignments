package pkgJavaTraining;

public class switchstatementdemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num = 0;
//		switch(variable)
//		{
//			case 10:
//				statments;
//				break;
//			case 20
//				statement;
//			    break;
//			default:
//		}
		
		String s = "Bike";
		
		
		switch(s){
		case "Car":
			System.out.println("You own Car");
			break;
		case "Bike":
			System.out.println("You own Bike");
			break;
		case "Scooter":
			System.out.println("You own Scooter");
			break;
		default:
			System.out.println("You dont own vehicle");
			break;
			
		}
				
	}

}
