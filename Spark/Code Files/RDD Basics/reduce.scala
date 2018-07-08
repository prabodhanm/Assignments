package com.spark.training.pkgsparktraining
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object reduceexample {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(Array(1,2,3,4,5,6,7))
    val reducerdd = rdd.reduce( (f,g)=> f+g)
    println("sum ="+reducerdd)
  }
}