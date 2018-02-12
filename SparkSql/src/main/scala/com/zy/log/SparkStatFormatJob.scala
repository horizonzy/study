package com.zy.log

import org.apache.spark.sql.SparkSession

object SparkStatFormatJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkStatFormatJob")
      .master("local[2]").getOrCreate()
    val access = spark.sparkContext.textFile("hdfs://hadoop00:9000/user/zhaoyan/10000_access.log")
    access.map(line => {
      val splits = line.split(" ")
      val ip = splits(0)
      val time = splits(3) + " " + splits(4)
      val traffic = splits(9)
      val url = splits(11).replaceAll("\"", "")
      DateUtils.parseDate(time) + "\t" + url + "\t" + traffic + "\t" + ip
    }).filter(line => {
      val splits = line.split("\t")
      splits(1).matches("http://www.imooc.com/\\w+/\\d+")
    }).saveAsTextFile("hdfs://hadoop00:9000/user/zhaoyan/imooc_source_data")

    spark.stop()
  }


}
