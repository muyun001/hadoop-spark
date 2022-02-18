package com.weston.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_WordCount {
  def main(args: Array[String]): Unit = {
    // 配置信息
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")
    // 第一个参数：配置信息，第二个参数：批处理周期（采集周期）
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // 逻辑处理
    // 获取端口数据
    val lines = ssc.socketTextStream("localhost", 9999)
    // 写wordCount
    val words = lines.flatMap(_.split("_"))
    val wordToOne = words.map((_, 1))
    val wordCount = wordToOne.reduceByKey(_ + _)
    wordCount.print()

    // 由于SparkStreaming采集器是长期执行的任务，所以不能直接关闭
    // 如果main防止执行完毕，应用程序也会自动结束，所以不能让main执行完毕
//    ssc.stop()
    // 1.启动采集器
    ssc.start()
    // 2.等待采集器的关闭
    ssc.awaitTermination()
  }
}
