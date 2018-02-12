package com.zy.log

import java.util.{Date, Locale}

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 日期解析
  **/
object DateUtils {
  def main(args: Array[String]): Unit = {
    println(parseDate("[10/Nov/2016:00:01:02 +0800]"))
  }

  val SOURCE_DATE_FORMAT = FastDateFormat.getInstance("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)

  val TARGET_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")

  def parseDate(sourceDate: String) = {
    val tempDate = SOURCE_DATE_FORMAT.parse(sourceDate.substring(sourceDate.indexOf("[") + 1, sourceDate.lastIndexOf("]")))
    TARGET_DATE_FORMAT.format(tempDate)
  }

}
