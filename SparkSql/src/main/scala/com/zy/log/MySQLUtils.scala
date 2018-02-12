package com.zy.log

import java.sql.{Connection, DriverManager, PreparedStatement}

object MySQLUtils {
  def getConnection() = {
    DriverManager.getConnection("jdbc:mysql://localhost:3306/access_log", "root", "idanlu")
  }

  def release(con: Connection, ps: PreparedStatement) = {
    try {
      if (ps != null) {
        ps.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (con != null) {
        con.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val con = getConnection()
    println(con)
  }
}
