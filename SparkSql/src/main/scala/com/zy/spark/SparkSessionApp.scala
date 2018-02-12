package com.zy.spark

import org.apache.spark.sql.SparkSession

object SparkSessionApp {

  def main(args: Array[String]): Unit = {
    //    val session = SparkSession.builder().appName("SparkSessionApp").master("local[2]").getOrCreate()
    //    val people = session.read.json("file:///usr/local/Cellar/spark/2.2.0/examples/src/main/resources/people.json")
    //    people.show()
    //    session.stop()
    val spark = SparkSession.builder().appName("SparkSessionApp").master("local[2]").getOrCreate()
//    val df = spark.read.json("file:///usr/local/Cellar/spark/2.2.0/examples/src/main/resources/people.json")
//    df.select(df.col("name"),(df.col("age")+10).as("age2")).show()
//    df.filter(df.col("age")>19).show()
//    df.groupBy("age").count().show()
//    df.printSchema()
//    df.show()
    spark.sql("show tables").show()
    spark.stop()

   }

}
