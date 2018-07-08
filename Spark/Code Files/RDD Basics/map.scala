package com.spark.training.pkgsparktraining

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object filterexamples {
   def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
     val filerdd = sc.textFile("C:/Users/Administrator/Desktop/productsale.csv")
     val splitrdd = filerdd.map(f => f.split(","))
     val filterrdd = splitrdd.filter( f => f(1)=="Shirts").map(f => f.mkString(","))
     //val maprdd = splitrdd.map( f => f.mkString(","))
     filterrdd.collect().foreach(println)
   }
  
}