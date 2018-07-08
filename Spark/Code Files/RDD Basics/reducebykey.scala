package com.spark.training.pkgsparktraining
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object wordoccurance {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
        val filerdd = sc.textFile("C:/Users/Administrator/Desktop/test.txt")
        val splitrdd = filerdd.flatMap(f => f.split(" ")) 
        val pairrdd = splitrdd.map(w => (w,1))
        val shufflerdd = pairrdd.reduceByKey((p,q) => p + q)
        shufflerdd.collect().foreach(println)
        

  }
}