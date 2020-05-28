package com.example.demo.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PointCreateUtils {

    public static void main(String[] args) {
        Map<String, BigDecimal> jw = randomLonLat(39, 40, 120, 121);
        System.out.println(jw.get("J") + "," + jw.get("W"));
    }

    /**
     * @Description: 在矩形内随机生成经纬度
     * @param MinLon：最小经度
     * 		  MaxLon： 最大经度
     *  	  MinLat：最小纬度
     * 		  MaxLat：最大纬度
     * @return @throws
     */
    public static Map<String, BigDecimal> randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        BigDecimal lon = db.setScale(6, BigDecimal.ROUND_HALF_UP);// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        BigDecimal lat = db.setScale(6, BigDecimal.ROUND_HALF_UP);
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("longitude", lon);
        map.put("latitude", lat);
        return map;
    }

    /**
     * 获取一个随机速度
     * @param MinLon
     * @param MaxLon
     * @return
     */
    public static BigDecimal randomSpeed(double MinLon, double MaxLon) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        BigDecimal number = db.setScale(2, BigDecimal.ROUND_HALF_UP);// 小数后6位

        return number;
    }
}
