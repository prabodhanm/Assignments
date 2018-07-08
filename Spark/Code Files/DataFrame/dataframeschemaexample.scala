package pkgsparkdataframe

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

case class custmor(custmorname:String,product:String,Quantity:Int,Amount:Double)

object dataframeschemaexample {
   def main(Args:Array[String]){
    val spark = new SparkSession.Builder()
      .appName("dataframe")
      .master("local")
      .getOrCreate()
      
   val filerdd=  spark.sparkContext.textFile("C:/Users/Administrator/Desktop/scala/productsale.csv")
   val splitrdd = filerdd.map(f => f.split(",")).map(f => custmor(f(0),f(1),f(2).toInt,f(3).toDouble))
   
   import spark.implicits._
   val df = splitrdd.toDF()
   val dffilter = df.select("custmorname", "product","Amount")
   val sortdf = dffilter.filter($"product"==="Shirts")
   sortdf.show() 
      
   }
}