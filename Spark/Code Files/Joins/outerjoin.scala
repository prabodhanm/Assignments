package com.spark.training.pkgsparktraining
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object outerjoinexample {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val storerdd = sc.textFile("C:/Users/Administrator/Desktop/StoreAddress.txt")
     val ratingrdd = sc.textFile("C:/Users/Administrator/Desktop/StoreRatings.txt")
     val splitedstorerdd = storerdd.map(f => f.split(","))
     val pairstorerdd = splitedstorerdd.map(f => (f(0),f(1)))
     val splitedratingrdd = ratingrdd.map(f => f.split(","))
     val pairratingrdd = splitedratingrdd.map(f => (f(0),f(1)))
     val joinrdd = pairstorerdd.rightOuterJoin(pairratingrdd)
     joinrdd.collect().foreach(println)
     
    
    
  }
}