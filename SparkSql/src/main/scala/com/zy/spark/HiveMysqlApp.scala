package com.zy.spark

import org.apache.spark.sql.SparkSession


object HiveMysqlApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("HiveMysqlApp").master("local[2]").enableHiveSupport().getOrCreate()

    val hiveDF = spark.table("emp")

    val mysqlDF = spark.read.format("jdbc").option("user","hive").option("password","123456")
      .option("url","jdbc:mysql://localhost:3306/spark").option("dbtable","dept").load()

    val resultDF = hiveDF.join(mysqlDF,hiveDF.col("deptno") === mysqlDF.col("deptno"))
    resultDF.select("empno","ename","job","sal","comm","dname","loc").write.format("json").save("file:///Users/zhaoyan/Documents/Atom")
    spark.stop()
  }
}
