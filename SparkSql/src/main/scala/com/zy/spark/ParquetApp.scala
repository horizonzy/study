package com.zy.spark

import org.apache.spark.sql.SparkSession

object ParquetApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("ParquetApp").master("local[2]").getOrCreate()
    val df = spark.read.format("parquet").option("path","file:///usr/local/Cellar/spark/2.2.0/examples/src/main/resources/users.parquet").load()
    df.show()
    spark.stop()
  }

}
