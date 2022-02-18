package com.weston.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark02_udafDemo {
  def main(args: Array[String]): Unit = {
    // 创建上下文配置对象
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark01_demo")
    // 创建sparkSession对象
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._

    // 读取json文件，创建dataframe
    val df: DataFrame = spark.read.json("datas/user.json")


    // 关闭环境
    spark.close()
  }
  case class User(id:Int, username:String, age:Int)
}
