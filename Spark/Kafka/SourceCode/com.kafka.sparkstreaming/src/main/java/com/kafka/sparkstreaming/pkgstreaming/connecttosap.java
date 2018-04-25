package com.kafka.sparkstreaming.pkgstreaming;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
//import com.sap.db.jdbc.trace.Statement;

import java.sql.Connection;
import java.sql.Statement;
//import com.sap.db.jdbcext.wrapper.Connection;
public class connecttosap {
	public static String connectionString = "jdbc:sap://OKCDCBWHD01:30015";
	public static String user = "HDP_READ";
	public static String password = "P0c4Hadoop";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			Class.forName("com.sap.db.jdbc.Driver");
			//conn = DriverManager.getConnection(connectionString, user, password);
			String url = "jdbc:sap://OKCDCBWHD01:30015";
		    String user = "HDP_READ";
		    String password = "P0c4Hadoop";

		    Connection cn = java.sql.DriverManager.getConnection(url, user, password);

		    ResultSet rs = cn.createStatement().executeQuery("CALL \"_SYS_BIC\".\"Finance.Spotfire.Deploy/ZFI_LOS_SUMMARY_NET_GROSS\"");
		} catch (Exception e) {
			System.err.println("Connection Failed. User/Passwd Error? Message: " + e.getMessage());
			return;
		}
		
		/*if (conn != null) {
			try {
				System.out.println("Connection to HANA successful!");
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("select * from \"_SYS_BIC\".\"Finance.Spotfire.Deploy/ZFI_LOS_SUMMARY_NET_GROSS\"");
				resultSet.next();
				String hello = resultSet.getString(1);
				System.out.println(hello);
			} catch (SQLException e) {
				System.err.println("Query failed!");
			}
		}
		else {
			System.out.println("Connection not established");
		}*/
			
	}

}
