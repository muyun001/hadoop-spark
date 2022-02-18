package com.weston.bigdata.spark.core.rdd.operator.action

import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_countByKey {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)
    // TODO - 行动算子

    val rdd1 = sc.makeRDD(List(1,1,1,4),2)
    val intToLong: collection.Map[Int, Long] = rdd1.countByValue()
    println(intToLong)  // Map(4 -> 1, 1 -> 3)

    val rdd2 = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("a", 3)
    ))
    val stringToLong: collection.Map[String, Long] = rdd2.countByKey()
    println(stringToLong) // Map(a -> 3)

    sc.stop()

  }
}
