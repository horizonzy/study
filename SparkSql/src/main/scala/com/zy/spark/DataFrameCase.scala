package com.zy.spark

import org.apache.spark.sql.SparkSession

/**
  * DataFrame中的其他操作
  */
object DataFrameCase {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameCase").master("local[2]").getOrCreate()
    val rdd = spark.sparkContext.textFile("/Users/zhaoyan/Documents/Atom/spark/info.txt")
    import spark.implicits._
    val df = rdd.map(x => x.split("\\|")).map(x => Student(x(0).toInt, x(1), x(2), x(3))).toDF()
    val df2 = rdd.map(x => x.split("\\|")).map(x => Student(x(0).toInt, x(1), x(2), x(3))).toDF()
    //show默认只显示20条
    df.select(df.col("name").as("stu_name"), df.col("id")).show()
    df.join(df2, df2.col("id") === df.col("id")).show()
  }

  case class Student(id: Int, name: String, phone: String, email: String) {

  }

}
