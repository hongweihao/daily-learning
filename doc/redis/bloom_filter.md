### Bloom Filter

[Redis-避免缓存穿透的利器之BloomFilter](https://juejin.im/post/5db69365518825645656c0de#heading-0)

#### 1. 什么是布隆过滤器

> Bloom Filter是一个很长的二进制矢量和一系列随机映射函数。可以用于判断一个元素是否存在某个集合中。
>
> 优点是空间效率和所用时间都远好于一般的算法
>
> 缺点是有一定的机率误判和删除困难



#### 2. 原理

![原理](http://ww1.sinaimg.cn/large/006fJlVugy1gca6fry5muj30oi0d8aae.jpg)

- 先创建一个长度为 n 的 `int` 类型数组，初始化值为0
- 准备 n 个散列函数
- 判断一个值时会产生 n 个散列结果，这 n 个结果需要对 n 取模
- 将取模后的结果对应索引的位置设置为1
- 判断下个值时就查看对应的位置是否为1（会误判）





