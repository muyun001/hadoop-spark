package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_RDD_Operator_flatMap2 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - flatMap
        val rdd: RDD[List[Int]] = sc.makeRDD(List(
            List(1, 2), List(3, 4)
        ))
        // 第一个list是List（1,2），第二个list是返回值，做的封装
        val flatRDD: RDD[Int] = rdd.flatMap(list => list)
        flatRDD.collect().foreach(println)


        sc.stop()

    }
}
