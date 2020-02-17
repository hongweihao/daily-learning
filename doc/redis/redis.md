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



#### 7. 



