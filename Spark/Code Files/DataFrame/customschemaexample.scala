package com.basics.spark.sparkbasics
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructType,StructField,StringType,IntegerType,DoubleType}
import org.apache.spark.sql.Row

object customschemaexample {
  def main(args: Array[String]){
    val spark = new SparkSession.Builder()
      .appName("dataframeschema")
      .master("local")
      .getOrCreate()
    
    val filerdd = spark.sparkContext.textFile("D:/Projects/Data/productsale.csv")
    
    val custschema = StructType(Array(
        StructField("CustomerName",StringType,true),
        StructField("Product",StringType,true),
        StructField("Qty",IntegerType,true),
        StructField("Amount",DoubleType,true)
    ) 
    )
 
    
    val rowrdd = filerdd.map(f => f.split(",")).map(f => Row(f(0),f(1),f(2).toInt,f(3).toDouble) )
    
    
    val df = spark.sqlContext.createDataFrame(rowrdd, custschema).show()
  }
}