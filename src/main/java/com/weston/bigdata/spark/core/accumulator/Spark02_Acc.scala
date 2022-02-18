package com.weston.bigdata.spark.core.accumulator

import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Acc {

    def main(args: Array[String]): Unit = {

        val sparConf = new SparkConf().setMaster("local").setAppName("Acc")
        val sc = new SparkContext(sparConf)

        val rdd = sc.makeRDD(List(1,2,3,4))

        // 获取系统累加器
        // Spark默认就提供了简单数据聚合的累加器
        /**
         * 累加器用来把 Executor 端变量信息聚合到 Driver 端。在 Driver 程序中定义的变量，在
            Executor 端的每个 Task 都会得到这个变量的一份新的副本，每个 task 更新这些副本的值后，
            传回 Driver 端进行 merge。
         */
        val sumAcc = sc.longAccumulator("sum")

        //sc.doubleAccumulator
        //sc.collectionAccumulator

        rdd.foreach(
            num => {
                // 使用累加器
                sumAcc.add(num)
            }
        )

        // 获取累加器的值
        println(sumAcc.value)

        sc.stop()

    }
}
