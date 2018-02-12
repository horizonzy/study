package com.zy.spark

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object SQLContextApp {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    //    sparkConf.setAppName("sparksql").setMaster("local[2]")
    sparkConf.setMaster("local[2]").setAppName("sqlsession")
    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)
    //    val sqlContext = new SQLContext(sc)
    //    val people = sqlContext.read.format("json").load("file:///usr/local/Cellar/spark/2.2.0/examples/src/main/resources/people.json")
    //    people.show()
    //    val people = sqlContext.read.format("json").load(path)
    //    people.printSchema()
    //    people.show()

    hiveContext.table("stu").show()
    sc.stop()
  }
}
