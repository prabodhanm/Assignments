package com.basics.spark.sparkbasics
import org.apache.spark.sql.SparkSession
object joinwithsparksql {
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
            
    dfcust.registerTempTable("Customer")
    dfprodreview.registerTempTable("ProductReview")
    dfprod.registerTempTable("Product")
    
    var sqlquery = "Select C.custname,P.prodname,PW.Review From Customer C " 
    sqlquery = sqlquery + " left outer join ProductReview PW On C.custid=PW.custid "
    sqlquery = sqlquery + " left outer join Product P on P.prodid=PW.pid"
    
    val custprodreview = spark.sql(sqlquery)
    
    custprodreview.write.format("parquet").save("D:/Projects/Data/CusomerReview.parquet")
    
    
    //custprodreview.show()
    
  }
}