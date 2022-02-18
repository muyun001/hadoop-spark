package com.weston.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 创建rdd
 */
object Spark03_rddPartition {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("makrRDD")
    val sc: SparkContext = new SparkContext(sparkConf)

    // 创建rdd,并设置分区的数量
    // textFile可以将文件作为数据处理的数据源，默认也可以设定分区。
    //     minPartitions : 最小分区数量
    //     math.min(defaultParallelism, 2)
    //val rdd = sc.textFile("datas/1.txt")
    // 如果不想使用默认的分区数量，可以通过第二个参数指定分区数
    // Spark读取文件，底层其实使用的就是Hadoop的读取方式
    // 分区数量的计算方式：
    //    totalSize = 7
    //    goalSize =  7 / 2 = 3（byte）

    //    7 / 3 = 2...1 (1.1) + 1 = 3(分区)
    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    rdd1.collect().foreach(println)

    // 保存分区到本地的output文件夹下，方便查看
    rdd1.saveAsTextFile("output")


    sc.stop()
  }
}
