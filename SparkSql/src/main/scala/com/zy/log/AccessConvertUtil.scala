package com.zy.log

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object AccessConvertUtil {

  val struct = StructType(
    Array(
      StructField("url", StringType),
      StructField("cmstype", StringType),
      StructField("cmsId", LongType),
      StructField("traffic", LongType),
      StructField("ip", StringType),
      StructField("city", StringType),
      StructField("time", StringType),
      StructField("day", StringType)
    )
  )

  def convert(rdd: RDD[String]) = {

    rdd.map(line => line.split("\t")).map(line => Info(line(0), line(1), line(2), line(3)))
  }

  def parseLog(log: String) = {
    try {
      val splits = log.split("\t")
      val url = splits(1)
      val traffic = splits(2).toLong
      val ip = splits(3)
      val time = splits(0)
      val domain = "http://www.imooc.com/"
      val cms = url.substring(url.indexOf(domain) + domain.length)
      val cmsTypeId = cms.split("/")
      var cmsType = ""
      var cmsId = 0l
      if (cmsTypeId.length > 1) {
        cmsType = cmsTypeId(0)
        cmsId = cmsTypeId(1).toLong
      }
      val city = IpUtils.getCity(ip)
      val day = time.substring(0, 10).replaceAll("-", "")

      Row(url, cmsType, cmsId, traffic, ip, city, time, day)
    } catch {
      case e: Exception => Row(0)
    }
  }

  case class Info(time: String, url: String, traffic: String, ip: String) {

  }

}
