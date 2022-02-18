package com.weston.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 创建rdd
 */
object Spark04_mapPartition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("makrRDD")
    val sc: SparkContext = new SparkContext(sparkConf)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    val res = rdd1.mapPartitions {
      datas => {
        datas.filter(_ / 2 == 0)
      }
    }

    res.collect().foreach(println)

    sc.stop()
  }
}
