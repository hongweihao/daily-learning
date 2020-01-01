##### 1/下载rabbitmq的rpm包 [rpm下载地址](http://www.rabbitmq.com/install-rpm.html#downloads "rpm下载地址")

> 我这里下载的包是 `rabbitmq-server-3.6.6-1.el7.noarch.rpm`

##### 2/安装erlang环境
> 因为rabbitMQ依赖于erlang，这里必须安装erlang的环境

`yum install erlang  #通过yum安装erlang`

> 执行上面这条语句如果报错，则先运行`yum install epel-release` 再运行上面的语句

##### 3/安装rabbitMQ

> 通过rpm安装`rpm -ivh rabbitmq-server-3.6.6-1.el7.noarch.rpm` （后面的包换为下载的包）

>若提示缺少socat，则先运行`yum install socat` 

>若安装socat报错，先运行 `yum makecache`

>再运行上面的语句

##### 4/运行rabbitMQ
> 运行语句`rabbitmq-server -detached`

> 若出现`ERROR: epmd error for host "77": badarg (unknown POSIX error)`

> 运行 `vim /etc/rabbitmq/rabbitmq-env.conf` 增加一行内容（不存在这个文件则创建）

> `NODENAME=rabbit@localhost`

> 运行语句`rabbitmq-server -detached`启动rabbitMQ

> 提示 `Warning: PID file not written; -detached was passed.`（程序员根本不管warning，[狗头]）

##### 查看rabbitMQ状态
> `rabbitmqctl status` 

> 注：之前通过压缩包方式一直安装不成功，卡在`ERROR: epmd error for host "77": badarg (unknown POSIX error)`，纯属手打，有错误的地方请指正，另外压缩包方式如果有解决上面错误的方法也请告知我，感谢！

##### 参考文章
> [https://blog.csdn.net/fuck487/article/details/77869039](https://blog.csdn.net/fuck487/article/details/77869039 "https://blog.csdn.net/fuck487/article/details/77869039")

> [https://blog.csdn.net/dw_java08/article/details/77856038](https://blog.csdn.net/dw_java08/article/details/77856038 "https://blog.csdn.net/dw_java08/article/details/77856038")