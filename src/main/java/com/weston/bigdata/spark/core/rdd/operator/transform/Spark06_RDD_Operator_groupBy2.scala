package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 将 List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组。
 */
object Spark06_RDD_Operator_groupBy2 {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - groupBy
        val rdd : RDD[String] = sc.makeRDD( List("Hello", "hive", "hbase", "Hadoop"))
//        val gbRDD: RDD[(String, Iterable[String])] = rdd.groupBy(
//            word => {
//                word.substring(0, 1)
//            }
//        )
        // 简写
        val gbRDD: RDD[(String, Iterable[String])] = rdd.groupBy(_.substring(0, 1))
        // 另一种方式：
//        val groupRDD = rdd.groupBy(_.charAt(0))

        gbRDD.collect().foreach(println)

        sc.stop()

    }
}
