package com.zy.spark

import java.util.Properties

import org.apache.spark.sql.SparkSession

object JDBCApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("JDCApp").master("local[2]").getOrCreate()
    val connectionProperties = new Properties()
    connectionProperties.put("user", "hive")
    connectionProperties.put("password", "123456")
    connectionProperties.put("driver","com.mysql.jdbc.Driver")
    val jdbcDF2 = spark.read
      .jdbc("jdbc:mysql://localhost:3306/score", "stu", connectionProperties)
    jdbcDF2.show()
  }
}
