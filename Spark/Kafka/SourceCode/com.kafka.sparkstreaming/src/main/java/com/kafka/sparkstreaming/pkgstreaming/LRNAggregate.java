package com.kafka.sparkstreaming.pkgstreaming;
import java.io.*;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;



public class LRNAggregate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoCredential credential;
		//MongoDatabase database = mongoClient.getDatabase("LRN");
		DB database = mongoClient.getDB("LRN");
		System.out.println("Connected to Dashboard");
		//MongoCollection<Document> collection = database.getCollection("Dashboard");
		DBCollection collection = database.getCollection("sessions");
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
	    
	    
//	    Date startdate = new DateTime(2017, 2, 22, 18, 30, 0, DateTimeZone.UTC).toDate();
//	    Date enddate = new DateTime(2017, 2, 25, 18, 30, 0, DateTimeZone.UTC).toDate();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    
	    String startUTCDate = sdf.format(dt1);
	    String endUTCDate = sdf.format(dt2);
	    
	    Date startdate = new DateTime(startUTCDate).toDate();
	    Date enddate = new DateTime(endUTCDate).toDate();
	    System.out.println(startdate);
	    System.out.println(enddate);
	    //startdate
	    DBObject query = QueryBuilder.start()
	            .put("created_at").greaterThanEquals(startdate)
	            .lessThan(enddate).get();
	            //.and("dup").exists(false).get();

	    DBObject match = new BasicDBObject("$match",query);
	    
	    AggregationOutput output = collection.aggregate(Arrays.asList(match));

	    for ( DBObject result : output.results() ) {
	        System.out.println(result);
	    }
	}
	
	public static String ConvertToISO(Date date) {
		  TimeZone tz = TimeZone.getTimeZone("UTC");
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		  df.setTimeZone(tz);
		  return df.format(date);
		}

	

}
