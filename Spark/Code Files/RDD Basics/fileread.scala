package com.spark.training.pkgsparktraining

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object fileread {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val filerdd = sc.textFile("C:/Users/Administrator/Desktop/test.txt")
    filerdd.collect().foreach(println)
    
  }
}