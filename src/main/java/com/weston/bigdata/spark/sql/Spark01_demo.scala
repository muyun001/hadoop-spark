package com.weston.bigdata.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Spark01_demo {
  def main(args: Array[String]): Unit = {
    // 创建上下文配置对象
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark01_demo")
    // 创建sparkSession对象
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._

    // 读取json文件，创建dataframe
//    val df: DataFrame = spark.read.json("datas/user.json")
    // 展示数据
//    df.show()

    //sql语法
//    df.createOrReplaceTempView("user")
//    spark.sql("select * from user").show()

    // dsl风格语法
//    df.select("username", "age").show()

    // rdd=>dataframe
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 20), (1, "lisi", 40), (1, "wangwui", 30), (1, "chenliu", 60)))
    val df: DataFrame = rdd.toDF("id", "username", "age")
    //    df.show()
    // dataframe=>rdd
//    val rdd1 =  df.rdd
//    println(rdd1.collect().mkString(","))


    // dateframe=>dataset
    val ds=df.as[User]
//    ds.show()
    // dataset=>dataframe
    val df2 =ds.toDF()
    df2.show()

    // rdd=>dataset
    val ds2=rdd.map {
      case (id, username, age) => {
        User(id, username, age)
      }
    }.toDS()
//    ds2.show()
    // dataset=>rdd
    val rdd3=ds2.rdd
//    println(rdd3.collect().mkString(","))


    // 关闭环境
    spark.close()
  }
  case class User(id:Int, username:String, age:Int)
}
