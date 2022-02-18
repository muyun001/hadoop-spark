package com.weston.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 创建rdd
 */
object Spark02_makeRDD {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("makrRDD")
    val sc: SparkContext = new SparkContext(sparkConf)

    // 创建rdd
    // 1.从内存中创建rdd
    // 1.1 使用parallelize()创建
    val rdd1: RDD[Int] = sc.parallelize(List(1, 2, 3, 4))
    // 使用makeRDD()创建-- 从底层代码实现来讲，makeRDD 方法其实就是 parallelize 方法
    val rdd2: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    rdd1.collect().foreach(println)
    rdd2.collect().foreach(println)

    // 2.从外部存储（文件）创建 RDD，如本地文件，hdfs，hbase等
    // 2.1 从本地文件创建rdd
    val rdd3: RDD[String] = sc.textFile("datas/word1.txt")
    rdd3.collect().foreach(println)

    // 2.2从hdfs创建：略
    // 2.3从hbase创建：略
    // 3. 从其他 RDD 创建: 主要是通过一个 RDD 运算完后，再产生新的 RDD。详情请参考后续章节
    // 4。直接创建 RDD（new） ：使用 new 的方式直接构造 RDD，一般由 Spark 框架自身使用。
    sc.stop()
  }

}
