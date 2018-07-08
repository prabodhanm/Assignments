package com.spark.training.pkgsparktraining
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object groupbykeyexample {
  def main(Args:Array[String]){
    
    val conf = new SparkConf()
    .setAppName("sampleprogram")
          .setMaster("local")
    val sc = new SparkContext(conf)
     val filerdd = sc.textFile("C:/Users/Administrator/Desktop/test.txt")
        val splitrdd = filerdd.flatMap(f => f.split(" ")) 
        val pairrdd = splitrdd.map(w => (w,1))
        val grprdd = pairrdd.groupByKey()
        val maprdd = grprdd.map(f => (f._1,f._2.sum))
        maprdd.collect().foreach(println)

  }
}