package com.zy.spark

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}


object DataFrameRDDApp {

  def program(input: String, spark: SparkSession): Unit = {
    val rdd = spark.sparkContext.textFile(input)
    val infoRdd = rdd.map(_.split(",")).map(line => Row(line(0), line(1).substring(1).toInt))
    val structType = StructType(Array(StructField("name", StringType, true), StructField("age", IntegerType, true)))
    val df = spark.createDataFrame(infoRdd, structType)
    df.createOrReplaceTempView("infos")
    spark.sql("select * from infos").show()
  }

  def dataset(input1: String, spark: SparkSession) = {
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv(input1)
    //    import spark.implicits._
    //    val ds = df.as[Name]
    //    ds.map(x=>x.last_name).show()
    df.createOrReplaceTempView("share")
    spark.sql("select company_name from share").show()
    //    df.show()
  }

  def main(args: Array[String]): Unit = {
    val input = "file:///usr/local/Cellar/spark/2.2.0/examples/src/main/resources/people.txt"
    val input1 = "file:///Users/zhaoyan/Documents/ShareholderList.csv"
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()
    //    program(input, spark)
    dataset(input1, spark)
    //    inferReflection(input, spark)
    spark.stop()
  }

  private def inferReflection(input: String, spark: SparkSession) = {
    val rdd = spark.sparkContext.textFile(input)
    import spark.implicits._
    val df = rdd.map(x => x.split(",")).map(x => Info(x(0), x(1).substring(1).toInt)).toDF()
    df.select(df.col("age").as("year"), df.col("name").as("nick")).filter(df.col("age") > 20).show()
  }

  case class Info(name: String, age: Int) {

  }

  case class Name(last_name: String, name: String) {

  }

}
