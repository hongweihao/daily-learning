
#### 1. 使用shell操作hdfs
```shell
# 查看根目录的内容
$ hadoop fs -ls /  

# win中，将d:\aio.txt文件上传到hdfs的/haoop_shell目录中
# Linux中，目录的表示方式不一样
$ hadoop fs -put D:\aio.txt /hadoop_shell

# 拉取hdfs上的文件到本地
$ hadoop fs -get /hadoop_shell/aio.txt
$ hadoop fs -get /hadoop_shell/aio.txt D:\xml\

# 显示内容
$ hadoop fs -cat /hadoop_shell/aio.txt

# 删除文件，
$ hadoop fs -rm /hadoop_shell/aio.txt

# 递归删除文件和文件夹
$ hadoop fs -rm -r /hadoop_shell

...

```

#### 使用JavaAPI操作hdfs
->[code](https://github.com/hongweihao/daily-study/tree/master/src/main/java/mkii/hadoop/hdfs_api/)



