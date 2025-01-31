package com.weston.bigdata.spark.core.accumulator

import org.apache.spark.{SparkConf, SparkContext}

object Spark01_Acc {

    def main(args: Array[String]): Unit = {

        val sparConf = new SparkConf().setMaster("local").setAppName("Acc")
        val sc = new SparkContext(sparConf)

        val rdd = sc.makeRDD(List(1,2,3,4))

        // reduce : 分区内计算，分区间计算
        //val i: Int = rdd.reduce(_+_)
        //println(i)
        var sum = 0
        rdd.foreach(
            num => {
                sum += num
            }
        )
        println("sum = " + sum)  // 结果：0

        sc.stop()

    }
}
