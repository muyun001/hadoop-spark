package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_RDD_Operator_glom1 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - glom
        // 将同一个分区的数据转为数组,类型不变
        val rdd : RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)
        val glomRDD: RDD[Array[Int]] = rdd.glom()
        glomRDD.collect().foreach(data=>println(data.mkString(",")))

        sc.stop()

    }
}
