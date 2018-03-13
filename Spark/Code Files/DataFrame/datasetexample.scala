package com.basics.spark.sparkbasics
import org.apache.spark.sql.SparkSession

case class customer(custname:String,prodname:String,Qty:Integer,Amount:Double)

object datasetexample {
  def main(args: Array[String]){
    val spark = new SparkSession.Builder()
      .appName("basicDataframe")
      .master("local")
      .getOrCreate()
    
    val filerdd = spark.sparkContext.textFile("D:/Projects/Data/productsale.csv")
    
    val splittedrdd = filerdd.map(f => f.split(","))
    
    val maprdd = splittedrdd.map(f => Customer(f(0),f(1),f(2).toInt,f(3).toDouble))
    
    import spark.implicits._

    val dfcust = maprdd.toDF()
    
    val dsCust = dfcust.as[customer]
    
    val dsfilter = dsCust.filter(x => x.Amount > 3000).show()
    
  }
}