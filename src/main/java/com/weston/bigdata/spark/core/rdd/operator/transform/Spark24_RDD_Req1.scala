package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 获取agent.log文件原始数据：时间戳，省份，城市，用户，广告
 * 1516609143867 6 7 64 16
 * 统计出每一个省份每个广告被点击数量排行的 Top3
 */
object Spark24_RDD_Req1 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // 1. 获取原始数据：时间戳，省份，城市，用户，广告
    val dataRDD = sc.textFile("datas/agent.log")
    // 2. 省份分组
    // 3. 广告分组
    // (省份，广告, 1)
    val mapRDD: RDD[(String, String, Int)] = dataRDD.map(
      lines => {
        val lineInfo = lines.split(" ")
        val province = lineInfo(1)
        val ad = lineInfo(4)
        (province, ad, 1)
      }
    )

    //    mapRDD.collect().foreach(println)
    //    ((city, ad), 1)
    val mapRDD2: RDD[((String, String), Int)] = mapRDD.map(info => ((info._1, info._2), info._3))

    // ((city, ad), sum)
    val reduceRes: RDD[((String, String), Int)] = mapRDD2.reduceByKey(_ + _)

    // (city, (ad, sum))
    val rdd2: RDD[(String, (String, Int))] = reduceRes.map {
      case ((city, ad), sum) => (city, (ad, sum))
    }

    val groupRDD: RDD[(String, Iterable[(String, Int)])] = rdd2.groupByKey()
    val result: RDD[(String, List[(String, Int)])] = groupRDD.mapValues {
      // 需要把buffer转成list，排序，并取前三名
      iter => {
        iter.toList.sortBy(_._2).take(3)
      }
    }

    result.collect().foreach(println)
    sc.stop()

  }
}
