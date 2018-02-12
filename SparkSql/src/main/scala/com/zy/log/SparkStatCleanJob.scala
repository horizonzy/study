package com.zy.log

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkStatCleanJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkStatCleanJob").master("local[2]").getOrCreate()
    val accessRDD = spark.sparkContext.textFile("file:///Users/zhaoyan/Documents/Atom/access.log")
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)), AccessConvertUtil.struct)
    accessDF.coalesce(1).write.format("parquet").partitionBy("day").mode(SaveMode.Overwrite).save("hdfs://hadoop00:9000/user/zhaoyan/spark_clean_output")
    spark.stop()
  }
}
