package com.zy.spark

import java.sql.DriverManager

object SparkSQLThriftServerApp {
  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("jdbc:hive2://hadoop00:10000","zhaoyan","")
    val ps = conn.prepareStatement("select * from stu")
    val result = ps.executeQuery()
    while (result.next()){
      println(result.getInt("id")+" "+result.getString("name")+" "+result.getInt("age"))
    }
    result.close()
    ps.close()
    conn.close()
  }
}
