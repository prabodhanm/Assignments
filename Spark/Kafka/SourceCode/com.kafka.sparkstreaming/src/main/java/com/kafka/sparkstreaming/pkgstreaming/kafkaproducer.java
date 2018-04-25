package com.kafka.sparkstreaming.pkgstreaming;
import java.io.*;
import java.util.Iterator;

import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable; 

import org.bson.json.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class kafkaproducer {
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
		
		FindIterable<Document> iterateDoc = collection.find();
		
		Iterator it = iterateDoc.iterator();
		
		
		//Create producer
		final Producer<Long, String> producer = createProducer();
		
		while(it.hasNext()) {
			System.out.println(it.next());
			
			final ProducerRecord<Long, String> record =
                    new ProducerRecord<>(TOPIC, 
                    		it.next().toString() );
            try {
				RecordMetadata metadata = producer.send(record).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                            BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                                        LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                                    StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }


	
}
