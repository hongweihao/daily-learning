##### 遍历原理
> 普通`for`循环在遍历集合时使用下标来定位集合中的元素。  

> `foreach`使用`Iterator`接口实现对集合的遍历。

##### for
```
for(int i = 0; i < list.size(); i++){

    Object object = list.get(i);
    //...
}
```

##### foreach
```
for(Object object : list){
    //...
}
```

##### 结果(来源：[https://blog.csdn.net/qq_26545305/article/details/78485224](https://blog.csdn.net/qq_26545305/article/details/78485224))
> 用`for`循环`arrayList` 10万次花费时间：5毫秒  
用`foreach`循环`arrayList` 10万次花费时间：7毫秒  
用`for`循环`linkList` 10万次花费时间：4481毫秒  
用`foreach`循环`linkList` 10万次花费时间：5毫秒

##### 结论
> 需要循环数组结构的数据时，建议使用普通`for`循环，因为`for`循环采用下标访问，对于数组结构的数据来说，采用下标访问比较好。
  
> 需要循环链表结构的数据时，一定不要使用普通`for`循环，这种做法很糟糕，数据量大的时候有可能会导致系统崩溃。
