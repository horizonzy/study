package com.zy.log

import java.sql.{Connection, PreparedStatement}

import scala.collection.mutable.ListBuffer


object StatDAO {
  def insertVideoAccessTopN(list: ListBuffer[DayVideoAccessStat]) = {
    var con: Connection = null
    var ps: PreparedStatement = null
    try {
      con = MySQLUtils.getConnection()
      con.setAutoCommit(false) //设置手动提交
      val sql = "insert into day_video_access_topn_stat(day,cms_id,times) values (?,?,?)"
      ps = con.prepareStatement(sql)
      for (ele <- list) {
        ps.setString(1, ele.day)
        ps.setLong(2, ele.cmsId)
        ps.setLong(3, ele.times)
        ps.addBatch()
      }
      ps.executeBatch() //执行批量处理
      con.commit() //手动提交
    } catch {
      case e: Exception => {
        e.printStackTrace()
        con.rollback()
      }
    } finally {
      MySQLUtils.release(con, ps)
    }
  }

  def insertArticleAccessTopN(list: ListBuffer[DayArticleAccessStat]) = {
    var con: Connection = null
    var ps: PreparedStatement = null
    try {
      con = MySQLUtils.getConnection()
      con.setAutoCommit(false) //设置手动提交
      val sql = "insert into day_article_access_topn_stat(day,cms_id,times) values (?,?,?)"
      ps = con.prepareStatement(sql)
      for (ele <- list) {
        ps.setString(1, ele.day)
        ps.setLong(2, ele.cmsId)
        ps.setLong(3, ele.times)
        ps.addBatch()
      }
      ps.executeBatch()
      con.commit()
    } catch {
      case e: Exception => {
        e.printStackTrace()
        con.rollback()
      }
    } finally {
      MySQLUtils.release(con, ps)
    }
  }

  def insertVideoCityAccessTopN(list: ListBuffer[DayVideoCityAccessStat]) = {
    var con: Connection = null
    var ps: PreparedStatement = null
    try {
      con = MySQLUtils.getConnection()
      con.setAutoCommit(false)
      val sql = "insert into day_video_city_access_topn_stat(day,city,cms_id,times,times_rank) values (?,?,?,?,?)"
      ps = con.prepareStatement(sql)
      if (list.size > 0) {
        for (ele <- list) {
          ps.setString(1, ele.day.toString)
          ps.setString(2, ele.city)
          ps.setLong(3, ele.cmsId)
          ps.setLong(4, ele.times)
          ps.setInt(5, ele.timesRank)
          ps.addBatch()
        }
        ps.executeBatch()
        con.commit()
      }
    } catch {
      case e: Exception => {
        e.printStackTrace()
        con.rollback()
      }
    } finally {
      MySQLUtils.release(con, ps)
    }
  }

  def insertVideoTrafficAccessTopN(list: ListBuffer[DayVideoTrafficStat]) = {
    var con: Connection = null
    var ps: PreparedStatement = null
    try {
      con = MySQLUtils.getConnection()
      con.setAutoCommit(false)
      val sql = "insert into day_video_traffic_access_topn_stat(day,cms_id,traffics) values (?,?,?)"
      ps = con.prepareStatement(sql)
      for (ele <- list) {
        ps.setString(1, ele.day)
        ps.setLong(2, ele.cmsId)
        ps.setLong(3, ele.traffics)
        ps.addBatch()
      }
      ps.execute()
      con.commit()
    } catch {
      case e: Exception => {
        e.printStackTrace()
        con.rollback()
      }
    } finally {
      MySQLUtils.release(con, ps)
    }
  }

  def deleteData(day: String) = {
    val tables = Array("day_video_access_topn_stat", "day_video_city_access_topn_stat", "day_video_traffic_access_topn_stat")
    var con: Connection = null
    var ps: PreparedStatement = null
    try {
      con = MySQLUtils.getConnection()
      for (table <- tables) {
        val deleteSQL = s"delete from $table where day = ?"
        ps = con.prepareStatement(deleteSQL)
        ps.setString(1, day)
        ps.executeUpdate()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(con, ps)
    }
  }

}
