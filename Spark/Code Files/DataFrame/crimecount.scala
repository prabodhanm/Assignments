package com.spark.hdinsight.pkgreadhdinsight
import org.apache.spark.sql.SparkSession

object crimecount {
  def main(args: Array[String]){
    val spark = new SparkSession.Builder()
      .appName("basicDataframe")
      .master("local")
      .getOrCreate()
    
    val dfcrime = spark.read
              .format("csv")
              .option("header", true)
              .option("inferschema", true)
              .load("D:/Projects/Data/crimes.csv")    
    
    val crimeselected = dfcrime.select("Category", "pddistrict") 
    
    crimeselected.createOrReplaceTempView("vwCrime")
    
    var crimesql = "Select pddistrict,Category,count(Category) as crimecount"
    crimesql = crimesql + " From vwCrime "
   // crimesql = crimesql + " Where pddistrict not in('BAYVIEW','CENTRAL')"
    crimesql = crimesql + " Group By pddistrict, Category "
    crimesql = crimesql + " Order by pddistrict ,Category, crimecount desc"
    
    
    val dfGrpCrime = spark.sql(crimesql)
    //dfGrpCrime.show()
    dfGrpCrime.createOrReplaceTempView("vwCrimeGroup")
    
    var denssql = "select df.*, dense_rank() "
    denssql = denssql + "over(partition by  pddistrict  order by crimecount desc) "
    denssql = denssql + " as rank from vwCrimeGroup df "
    
    val densgrpcrime = spark.sql(denssql)
    
//    densgrpcrime.show()
//    
//    print("Dense rank printed")

    densgrpcrime.createOrReplaceTempView("vwdensegrpcrime")
    
    var grpdenssql = "select pddistrict as Area, Category as Crime, crimecount as Count "
    grpdenssql = grpdenssql + " from vwdensegrpcrime where rank < 4"
    
    val finaldensdf = spark.sql(grpdenssql)
    finaldensdf.show()
    
    
  }
}