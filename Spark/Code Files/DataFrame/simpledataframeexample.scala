package pkgsparkdataframe

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession


object simpledataframeexample {
  
  def main(Args:Array[String]){
    val spark = new SparkSession.Builder()
      .appName("dataframe")
      .master("local")
      .getOrCreate()
      
      val df = spark.read.format("csv").option("header", true).option("inferschema", false).load("C:/Users/Administrator/Desktop/scala/productsale.csv")
      df.show()
  }
    
}