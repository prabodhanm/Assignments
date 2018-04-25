package com.kafka.sparkstreaming.pkgstreaming;

public class regexdemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String myname = "Select * From Employee Where emp_id = {emp_id}";
			
			String yourname = myname.replaceAll("\\{emp_id\\}", "100");
			System.out.println(yourname);
	}

}
