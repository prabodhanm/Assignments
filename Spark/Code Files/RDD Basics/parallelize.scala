package com.spark.training.pkgsparktraining

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object testprogram {
  
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val rdd =sc.parallelize(Seq(1,2,3,4,5,6,7,8,9,0)) 
    println(rdd.collect().mkString(","))
      
  }
  
  
}