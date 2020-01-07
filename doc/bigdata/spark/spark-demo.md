## 第一个spark程序wordcount（idea）

#### 1. 环境

`hadoop hdfs`  & `spark` 

我下载的包是`hadoop-2.7.7` &  `spark-2.4.4-bin-hadoop2.7`

> 注意：在win中使用`hadoop`需要一个`winutils.exe`



#### 2. 构建项目

新建一个`maven project`, `archetype`选择`···quickstart`

`pom.xml`文件中导入依赖：

```xml
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-core_2.12</artifactId>
    <version>${spark.version}</version>
</dependency>
```



写`wordcount`程序：

```scala
package com.mkii.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * object 表示一个单例对象类
 *
 */
object WordCount {
  def main(args: Array[String]): Unit = {

    // 1.获取配置类对象
    val sparkConf: SparkConf = new SparkConf()
    // local[2]指的是本地的spark
    sparkConf.setAppName("WordCount").setMaster("local[2]")

    // 2.获取spark上下文对象
    val sparkContext: SparkContext = new SparkContext(sparkConf)

    // 3.读取文件
    val data: RDD[String] = sparkContext.textFile("./data/text.txt")
    //val data: RDD[String] = sparkContext.textFile("D:\\Common\\zimbatch\\mapin\\FLO_315_8401_1603652443.EDI")

    // 4.split操作
    val words: RDD[String] = data.flatMap(x => x.split(" "))

    // 5.将split之后的单词映射为<key, value>格式.这里是(word, 1)
    val wordAndOne: RDD[(String, Int)] = words.map(x => (x, 1))

    // 6.相同key累加，猜测：由于方法是reduceByKey，应该是按照key分组了，然后x,y就是两个<key, value>的value因为key一样
    val result: RDD[(String, Int)] = wordAndOne.reduceByKey((x, y) => x+y)

    // 7. 打印
    result.foreach(println(_))

    sparkContext.stop()
  }
}

```



#### 3.编译运行

先在`idea`中设置`scala`版本(主要要与`spark`中的`scala`版本对应，不然会报错)：

>  File ->  Project Structure -> global Libraries, 添加`Scala SDK (${SPARK_HOME}\jars)`， 选择完会自动识别版本(如果之前已经有了要清掉)

接下来启动运行的环境：（本地测试可以不用启动`hdfs`和`spark`）

准备input数据

启动`hdfs`  -> 启动`spark-shell`  -> 编译运行`WordCount`就行了



