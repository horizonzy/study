package com.zy.spark

import org.apache.spark.sql.SparkSession

object HiveApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("HiveApp").master("local[2]").getOrCreate()
    spark.sql("show tables").show()
  }
}
