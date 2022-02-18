package com.weston.bigdata.spark.core.rdd.operator.transform

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.immutable.Nil.equals

object Spark03_RDD_Operator_mapPartitionsWithIndex {

    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitionsWithIndex
        val rdd = sc.makeRDD(List(1,2,3,4), 2)
        // 【1，2】，【3，4】
        var mpiRDD = rdd.mapPartitionsWithIndex(
            // 只保留第2个分区的内容
            (index, iter) =>{
                if (index == 1){
                    iter
                }else{
                    Nil.iterator
                }
            }
        )
        mpiRDD.collect().foreach(println)


        sc.stop()

    }
}
