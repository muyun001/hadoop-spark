package com.weston.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_WordCount {
  def main(args: Array[String]): Unit = {
    // 配置
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)

    //读取文件数据
    val lines: RDD[String] = sc.textFile("datas/word1.txt")
    //    val words:RDD[String] = lines.flatMap(line => line.split(" "))
    val words: RDD[String] = lines.flatMap(_.split(" "))
    //    words.foreach(println)
    val wordOne: RDD[(String, Int)] = words.map(word => (word, 1))
    //    wordOne.collect().foreach(println)
    val wordCount: RDD[(String, Int)] = wordOne.reduceByKey((x, y) => (x + y))
    wordCount.foreach(println)

    sc.stop()
  }

}
