package com.zy.log

import com.ggstar.util.ip.IpHelper

object IpUtils {
  def getCity(ip: String) = {
    IpHelper.findRegionByIp(ip)
  }

}
