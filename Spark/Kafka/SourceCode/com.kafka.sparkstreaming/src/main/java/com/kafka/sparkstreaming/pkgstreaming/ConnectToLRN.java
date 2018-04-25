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

import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable; 

import org.bson.json.*;
import org.joda.time.DateTime;

import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
public class ConnectToLRN {
	private final static String TOPIC = "test";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9092";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Connect to mongo
		//Retrieve data
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoCredential credential;
		MongoDatabase database = mongoClient.getDatabase("LRN");
		System.out.println("Connected to Dashboard");
		MongoCollection<Document> collection = database.getCollection("Dashboard");
		
		//FindIterable<Document> iterateDoc = collection.find();
		
		//Iterator it = iterateDoc.iterator();
		
		Block<Document> printBlock = new Block<Document>() {
	        public void apply(final Document document) {
	            System.out.println(document.toJson());
	        }
	    };
	    
	    System.out.println("Getting records from collection");
	    
	    String date1 = "02-21-2017";
	    String date2 = "02-25-2017";
	    
	    Date dt1=null,dt2=null;
		try {
			 dt1=new SimpleDateFormat("MM-dd-yyyy").parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		  dt2=new SimpleDateFormat("MM-dd-yyyy").parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//date1 = ConvertToISO(dt1);
		//date2 = ConvertToISO(dt2);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		    
		    String startUTCDate = sdf.format(dt1);
		    String endUTCDate = sdf.format(dt2);
		    
		    Date startdate = new DateTime(startUTCDate).toDate();
		    Date enddate = new DateTime(endUTCDate).toDate();
		    
		    
		    System.out.println(startdate);
		    System.out.println(enddate);
		
	    	    AggregateIterable<Document> obj1 =   collection.aggregate(
	    	      Arrays.asList(
	    	              Aggregates.match(Filters.gt("created_at",  "ISODate(" + startdate.toString() + ")")),
	    	              Aggregates.match(Filters.lte("created_at", "ISODate(" + enddate.toString() + ")"))
	    	      )
	    	);
	    
//		AggregateIterable<Document> obj1 = collection.aggregate(Arrays.asList(
//                new Document("$match",
//                        new Document("_id.course_type", "Custom"))
//                                //.append("region", "India")),
//                //new Document("$group",
//                //        new Document("_id", "$" + "deptId").append("count", new Document("$sum", 1)))
//                ));
		
		
	    obj1.forEach(printBlock);
	    
	    System.out.println("Done...");
		//Create producer
//		final Producer<Long, String> producer = createProducer();
//		
//		while(it.hasNext()) {
//			System.out.println(it.next());
//			
//			final ProducerRecord<Long, String> record =
//                    new ProducerRecord<>(TOPIC, 
//                    		it.next().toString() );
//            try {
//				RecordMetadata metadata = producer.send(record).get();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
		
	}
	
	public static String ConvertToISO(Date date) {
		  TimeZone tz = TimeZone.getTimeZone("UTC");
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		  df.setTimeZone(tz);
		  return df.format(date);
		}
//	private static Producer<Long, String> createProducer() {
//        Properties props = new Properties();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                                            BOOTSTRAP_SERVERS);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                                        LongSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                                    StringSerializer.class.getName());
//        return new KafkaProducer<>(props);
//    }


	
}
