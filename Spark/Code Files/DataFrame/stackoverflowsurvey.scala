package com.basics.spark.sparkbasics
import org.apache.spark.sql.SparkSession
object stackoverflowsurvey {
  def main(Args:Array[String]){
    System.setProperty("hadoop.home.dir","c:/hadoop")
     val spark = new SparkSession.Builder()
      .appName("dataframe")
      .master("local")
      .getOrCreate()
      
    import spark.implicits._
    
     
    //Read History file
    

    val dfresponse = spark.read.format("csv").option("header", true).option("inferschema", true)
            .load("D:/Projects/Data/stackoverflow-survey-responses.csv")
    
    val dfselectedcols = dfresponse.select("country", "occupation","age_midpoint","salary_midpoint")
    
    //Responses from India
    
    val dffilterres = dfselectedcols.filter(dfselectedcols.col("country") ==="India")
    
    //Filter on age
    
    val dffilterage = dfselectedcols.filter(dfselectedcols.col("age_midpoint").lt(30))
    
    //Occupation count
    
    val dfoccupationcount = dfselectedcols.groupBy(dfselectedcols.col("occupation")).count()
    
    //salary sort
    
    val dfsortsalary = dfselectedcols.orderBy(dfselectedcols.col("salary_midpoint").desc).show()
    
    //Average salary per country
    
    val dfavgsalarypercountry = dfselectedcols.groupBy(dfselectedcols.col("country")) 
    val dfavg = dfavgsalarypercountry.avg("salary_midpoint")
    val sorteddf = dfavg.orderBy("country").show()
    
   
    
    
    
    
    
    
    
  }
}