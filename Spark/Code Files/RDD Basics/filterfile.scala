package com.spark.training.pkgsparktraining


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object filterfile {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val filerdd = sc.textFile("C:/Users/Administrator/Desktop/test.txt")
    val filterrdd = filerdd.filter(line => line.contains("my"))
    filterrdd.collect().foreach(println)
  }
}