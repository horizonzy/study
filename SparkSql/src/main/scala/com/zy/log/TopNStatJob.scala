package com.zy.log

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.collection.mutable.ListBuffer
import org.apache.spark.sql.functions._

object TopNStatJob {


  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("TopNStatJob").master("local[2]").config("spark.sql.sources.partitionColumnTypeInference.enabled", false).getOrCreate()

    val accessDF = spark.read.format("parquet").load("hdfs://hadoop00:9000/user/zhaoyan/spark_clean_output")
    //    accessDF.printSchema()
    //    accessDF.show(false)
    val day = "20161110"
    StatDAO.deleteData(day)
    vedioAccessTopNStas(spark, accessDF, day)
    //    articleAccessTopNStas(spark, accessDF,day)
    cityAccessTopNStas(spark, accessDF, day)
    videoTrafficsAccessTopNStat(spark, accessDF, day)
    spark.stop()
  }

  def vedioAccessTopNStas(spark: SparkSession, accessDF: DataFrame, day: String) = {
    /**
      * 使用df方式
      **/
    //    import spark.implicits._
    //    val videoAccessTopNDF = accessDF.filter($"day" === 20161110 && $"cmsType" === "video").groupBy($"day", $"cmsId").agg(count("cmsId").as("times")).orderBy($"times".desc)
    //    videoAccessTopNDF.show(false)
    /**
      * 使用sql方式
      **/
    accessDF.createOrReplaceTempView("access_log")

    /**
      * 将video的topn数据存放到数据库
      **/
    val df = spark.sql(s"select day,cmsId,count(1) as times from access_log where day = $day and cmsType = 'video' group by day,cmsId order by times desc")
    try {
      df.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVideoAccessStat]
        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val times = info.getAs[Long]("times")
          list.append(DayVideoAccessStat(day, cmsId, times))
        })

        StatDAO.insertVideoAccessTopN(list)

      })
    } catch {
      case e: Exception => e.printStackTrace()
    }


  }

  def articleAccessTopNStas(spark: SparkSession, accessDF: DataFrame, day: String) = {
    /**
      * 将article的topn数据存放到数据库
      **/
    accessDF.createOrReplaceTempView("access_log")
    val df = spark.sql(s"select day,cmsId,count(1) as times  from access_log where day = $day and cmstype = 'article' group by day,cmsId order by times desc")
    try {
      df.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayArticleAccessStat]
        partitionOfRecords.foreach(ele => {
          val day = ele.getAs[String]("day")
          val cms_id = ele.getAs[Long]("cmsId")
          val times = ele.getAs[Long]("times")
          list.append(DayArticleAccessStat(day, cms_id, times))
        })
        StatDAO.insertArticleAccessTopN(list)

      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }


  def cityAccessTopNStas(spark: SparkSession, accessDF: DataFrame, day: String) = {
    /**
      * 按照地市统计topn的课程
      **/
    import spark.implicits._
    val cityAccessDF = accessDF.filter($"day" === day && $"cmsType" === "video").groupBy("day", "city", "cmsId").agg(count("cmsId").as("times")).orderBy($"times".desc)
    //    cityAccessDF.printSchema()

    //Window函数在Spark Sql使用
    val resultDF = cityAccessDF.select($"day", $"city", $"cmsId", $"times", row_number().over(Window.partitionBy($"city").orderBy($"times".desc)).as("times_rank")).filter($"times_rank" <= 3)
    resultDF.show(100)
    try {
      resultDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVideoCityAccessStat]
        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val city = info.getAs[String]("city")
          val cmsId = info.getAs[Long]("cmsId")
          val times = info.getAs[Long]("times")
          val timesRank = info.getAs[Int]("times_rank")
          list.append(DayVideoCityAccessStat(day, city, cmsId, times, timesRank))
        })
        StatDAO.insertVideoCityAccessTopN(list)
      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def videoTrafficsAccessTopNStat(spark: SparkSession, accessDF: DataFrame, day: String) = {
    import spark.implicits._
    val resultDF = accessDF.filter($"day" === day && $"cmsType" === "video").groupBy($"day", $"cmsId").agg(sum($"traffic").as("traffics")).orderBy($"traffics".desc)
    resultDF.foreachPartition(partition => {
      val list = new ListBuffer[DayVideoTrafficStat]
      partition.foreach(ele => {
        val day = ele.getAs[String]("day")
        val cmsId = ele.getAs[Long]("cmsId")
        val traffics = ele.getAs[Long]("traffics")
        list.append(DayVideoTrafficStat(day, cmsId, traffics))
      })
      StatDAO.insertVideoTrafficAccessTopN(list)
    })
  }

}
