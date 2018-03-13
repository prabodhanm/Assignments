package com.basics.spark.sparkbasics
import org.apache.spark.sql.SparkSession
object joinexample {
  def main(Args:Array[String]){
      System.setProperty("hadoop.home.dir","c:/hadoop")
     val spark = new SparkSession.Builder()
      .appName("dataframe")
      .master("local")
      .getOrCreate()
      
    import spark.implicits._
    
     
    //Read History file
    

    val dfcust = spark.read.format("csv").option("header", true).option("inferschema", true)
            .load("D:/Projects/Data/customer.csv")
    val dfprodreview = spark.read.format("csv").option("header", true).option("inferschema", true)
            .load("D:/Projects/Data/prodreview.csv")
    val dfprod = spark.read.format("csv").option("header", true).option("inferschema", true)
            .load("D:/Projects/Data/prod.csv")        
            
    val dfcustprodreview = dfcust.join(dfprodreview, dfcust.col("custid")===dfprodreview.col("custid"), "inner")
                                  .join(dfprod, dfprod.col("prodid")===dfprodreview.col("pid"), "inner")
                              
       dfcustprodreview.select("custname", "prodname","Review")
       
       
    //Left outer join
       
    val dfLJcustprodreview = dfcust.join(dfprodreview, dfcust.col("custid")===dfprodreview.col("custid"), "left_outer")
                                  .join(dfprod, dfprod.col("prodid")===dfprodreview.col("pid"), "left_outer")
                              
       dfLJcustprodreview.select("custname", "prodname","Review").show()
  }
}