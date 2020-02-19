## `Redis`面试题

#### 1.为什么使用`Redis`？

> 因为传统的数据库已经不能适用于所有场景了，比如秒杀扣除，首页访问流量高峰等。都很容易导致数据库崩溃，所以引入了缓存中间件。目前比较热门的缓存中间件有`Redis`和`memcached`，综合考虑比较之后选择了`Redis`。



#### 2. `Redis` 和`memcached`的区别？

- 都将数据存放在内存中，但`memcached`还可以缓存图片视频等其他数据；
- `memcached`只支持简单的key-value，`redis`还支持list/set/set/hash等数据结构；
- 当`redis`的内存满了，会利用虚拟内存将一些过期的key交换到磁盘中；
- `memcached`的过期时间在set的时候就设定，`redis`还可以使用expire设定
- `memcached`的数据只保存在内存中机器故障不可恢复，`redis`数据会定期flush到磁盘，可通过rdb和aof恢复
- `memcached`不支持复制，`redis`支持master-slave的主从复制
- `memcached`的读写速度高于`redis`，因为`memcached`是多线程，`redis`是单线程



#### 3.Redis的数据结构

> 常用的数据结构有：`String`，`List`，`Set`，`ZSet (sorted set)`，`Hash`
>
> 中级：`HyperLogLog`，`Geo`
>
> 高级：`Reids Module`，`Bloom Filter`，`Redis Search`，`Redis-ML`
>
> [布隆过滤器(Bloom Filter)](./bloom_filter.md)



#### 4. Redis的数据淘汰策略

> redis检查内存使用情况，如果已使用的内存大于max memory的值，则根据用户选择的内存淘汰策略来淘汰key。淘汰策略如下：

- `no-eviction`:：当内存达到阈值的时候，所有申请内存的命令会报错。（禁止驱逐数据）
- `allkeys-lru` ：从数据集 `server.db[i].dict` 中挑选最近最少使用的数据淘汰
- `allkeys-random`：从数据集中随机挑选数据淘汰
- `volatile-lru`：从已设置过期的数据集 `server.db[i].expires` 中挑选最近最少使用的数据淘汰
- `volatile-random`：从已设置过期的数据集中任意挑选数据淘汰
- `volatile-ttl`：从已设置过期的数据集中挑选将要过期的数据淘汰



#### 5. Redis集群方案什么情况下会导致集群不可用

> 主要在于是否有从节点。
>
> 假如集群中有三个节点A B C，如果B节点没有从节点，且B节点故障，会导致B节点分管的范围的槽不可用。



#### 6. Hash槽？

> Redis 集群 `redis cluster` 没有使用一致性hash，而是使用hash槽的概念。Redis集群有16385个hash槽，每个key通过CRC16校验对16384取模来决定防止的槽，集群的每个节点负责一部分hash槽。



#### 7. Redis集群会有写操作丢失吗？

> Redis 满足可用性(A)和分区容错性(P)，不能满足强一致性，有可能会发生写操作丢失的情况。redis是单线程，每个请求都是事务执行的，但是多个请求之间不能保证强一致性。

> C：consistency 强一致性：更新操作成功并返回客户端完成后，所有节点在同一时间的数据完全一致，所以，一致性，说的就是数据一致性
>
> A：availability 可用性：服务一直可用，而且是正常响应时间
>
> P：partition tolerance 分区容错性：分布式系统在遇到某节点或网络分区故障的时候，仍然能够对外提供满足一致性和可用性的服务。
>
> [分布式系统CAP理论](http://www.hollischuang.com/archives/666)

![CAP](http://ww1.sinaimg.cn/large/006fJlVugy1gc1g23egumj30m80m8djd.jpg)



#### 8. Redis集群之间是如何复制的？

> 异步复制，复制过程中 master/slaver 节点都是非阻塞的。在从节点同步数据时，采用的是旧数据集的数据。

[Redis--主从复制](https://blog.csdn.net/zhengzhaoyang122/article/details/99695747)

1. 当从节点连接到主节点时，会向主节点发送`psync`命令，附带参数`runID：主节点ID`和`offset数据位置偏移`
2. 如果主节点回复`FULLRESYNC`，那么触发全量复制
3. 如果主节点回复`CONTINUE`，那么触发部分复制
4. 如果主节点回复`ERR`，表示主节点不支持此命令

**主从全量复制：**复制过程中，主节点使用`bgsave`命令生成 RDB 文件并发送给从节点

**主从部分复制：**主节点与从节点断开连接，主节点只需要将缓冲区的部分数据同步到从节点就能够保证数据的一致性。主节点会将接收到的request数据写入“复制积压缓冲区”，默认1M，可通过`repl-backlog-size`配置



#### 9. Redis中的管道（pipeline）有什么作用？



