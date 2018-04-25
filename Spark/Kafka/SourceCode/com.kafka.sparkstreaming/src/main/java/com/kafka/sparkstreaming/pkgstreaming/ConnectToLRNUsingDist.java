package com.kafka.sparkstreaming.pkgstreaming;
import java.io.*;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable; 

import org.bson.json.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
public class ConnectToLRNUsingDist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String user1; // the user name
		String db; // the name of the database in which the user is defined
		char[] password1; // the password as a character array
		password1 = new char[]{'S','t','r','i','n','g','P','a','s','s','w','o','r','d'};
	    user1 = "analytics";
		db="analytics";
		
		MongoCredential credential = MongoCredential.createCredential(user1, db, password1);
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		DB database = mongoClient.getDB("LRN");
		//MongoCollection<Document> collection = database.getCollection("Dashboard");
		DBCollection collection = database.getCollection("sessions");
		DBCollection collection1 = database.getCollection("Dashboarcoursetemp");
		//FindIterable<Document> iterateDoc = collection.find();
		
		//Iterator it = iterateDoc.iterator();
		
		Block<Document> printBlock = new Block<Document>() {
	        public void apply(final Document document) {
	            System.out.println(document.toJson());
	        }
	    };
        
	    
	    String date1 = "21/02/2017";
	 	String date2 = "25/02/2017";
	    
		Date dt1=null,dt2=null;
		try {
			 dt1=new SimpleDateFormat("dd/MM/yyyy").parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		  dt2=new SimpleDateFormat("dd/MM/yyyy").parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    
	    String startUTCDate = sdf.format(dt1);
	    String endUTCDate = sdf.format(dt2);
	    
	    Date startdate = new DateTime(startUTCDate).toDate();
	    Date enddate = new DateTime(endUTCDate).toDate();
	 
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date sdf1 = null,sdf2=null;
		try {
			sdf1 = (Date)formatter.parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sdf2 = (Date)formatter.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    long fromdate = sdf1.getTime();
	    long todate = sdf2.getTime();
	    
	    System.out.println(fromdate);
	    System.out.println(todate);
	    
	   List output1 = collection.distinct("_id", 
			   new BasicDBObject("created_at",new BasicDBObject("$gt",startdate)
					   .append("$lte", enddate)));
	   
	   List output2 = collection1.distinct("_id", 
			   new BasicDBObject("date",new BasicDBObject("$gt",fromdate)
					   .append("$lte", todate)));
	 //   AggregationOutput output = collection.aggregate(Arrays.asList(match));
	  
	   output1.removeAll(output2);
	    
	    System.out.println(output1);
	   //List<String> result = Arrays.asList(output1);
//	   System.out.println("printing Output2...");
//	   System.out.println(output2);
	   
	 //  System.out.println(output2);
	   // AggregationOutput output2 = collection1.aggregate(Arrays.asList(match,group,project));
        
	    
//	    for ( DBObject result : resultoutput.results() ) {
//	        System.out.println(result);
//	    }
	  //  for ( DBObject result1 : output2.results() ) {
	    //    System.out.println(result1);
	    //}
	   
	   
	}
	
	private static Object array_intersect(Object output1, Object output2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String ConvertToISO(Date date) {
		  TimeZone tz = TimeZone.getTimeZone("UTC");
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		  df.setTimeZone(tz);
		  return df.format(date);
		}


}
