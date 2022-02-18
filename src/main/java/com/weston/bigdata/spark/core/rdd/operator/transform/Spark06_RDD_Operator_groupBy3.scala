package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 从服务器日志数据 apache.log 中获取每个时间段访问量。
 * 按小时分组，忽略不同的日期
 */
object Spark06_RDD_Operator_groupBy3 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.textFile("datas/apache.log")
    val groupRes: RDD[(String, Iterable[String])] = rdd.groupBy {
      line => {
        // 83.149.9.216 - - 17/05/2015:10:05:03 +0000 GET xxxxxxxx  =》 17/05/2015:10:05:03
        val lineDatas = line.split(" ")(3)
        // 获取小时：17/05/2015:10:05:03 =》 10:05:03 => 10
        val hour = lineDatas.split(":")(1)
        hour
      }
    }
    val res: RDD[(String, Int)] = groupRes.map(
      t => {
        (t._1, t._2.size)
      }
    )
    res.collect().foreach(println)


    sc.stop()

  }
}
