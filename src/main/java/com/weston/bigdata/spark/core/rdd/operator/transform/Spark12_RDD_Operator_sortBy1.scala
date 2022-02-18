package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark12_RDD_Operator_sortBy1 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - sortBy
        val rdd = sc.makeRDD(List(6,2,4,5,3,1), 2)

//        val newRDD: RDD[Int] = rdd.sortBy(num=>num)  // 默认升序
        val newRDD: RDD[Int] = rdd.sortBy(num=>num, false)  // 降序

//        newRDD.saveAsTextFile("output")
        newRDD.collect().foreach(println)



        sc.stop()

    }
}
