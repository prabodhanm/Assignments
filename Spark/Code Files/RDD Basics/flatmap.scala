package com.spark.training.pkgsparktraining

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object flatmapexample {
    def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val filerdd = sc.textFile("C:/Users/Administrator/Desktop/productsale.csv")
    val splitrdd = filerdd.flatMap(f => f.split(","))
    println("Total no of words = " + splitrdd.count())
  // splitrdd.collect().foreach(println)    
    
    }
}