package com.spark.training.pkgsparktraining
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object sortbykeyexample {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
   val rdd = sc.parallelize(Array(("Maths",56),("English",78),("Science",82),("Computer",78),("Maths", 87))) 
   val sortedrdd = rdd.sortByKey()
   sortedrdd.collect().foreach(println)
  }
}