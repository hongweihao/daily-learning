#### 实例

```java
for (int i = 0; i < 150; i++) {
            Integer a = i;
            Integer b = i;
            System.out.println(i + ": " + (a == b));
            // 一直都是true
            //System.out.println(a.equals(b));
        }
```

#### 运行结果

```
0: true
1: true
...
127: true
128: false
129: false
...
149: false
```



#### 原因分析

> 在-128至127范围内的赋值，Integer对象是在`IntegerCache.cache`产生，会复用已有对象，这个区间内的`Integer`值可以直接使用==进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象



##### 自动装箱源码分析

> `Integer a = i`中`a`是`Integer`类型，而`i`是`int`类型。Java会自动将`i`装箱为`Integer`->`Integer.valueOf(i)`

```java
public static Integer valueOf(int i) {
    // IntegerCache.low = -128  IntegerCache.high = 127
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```



```java
// 静态内部类
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {}
}
```



> -128~127之间的值在类加载的时候已经被放入cache数组中。如果声明对象值落在这个区间内，就会返回cache中对应的对象；如果没落在这个区间就会new一个Integer对象返回。



#### 备注

不仅int，Java中的另外7中基本类型都可以自动装箱和自动拆箱，其中也有用到缓存。见下表：

| 基本类型 | 装箱类型  | 取值范围           | 是否缓存 | 缓存范围        |
| :------- | :-------- | :----------------- | :------- | :-------------- |
| byte     | Byte      | -128 ~ 127         | 是       | -128 ~ 127      |
| short    | Short     | -2^15 ~ (2^15 - 1) | 是       | -128 ~ 127      |
| int      | Integer   | -2^31 ~ (2^31 - 1) | 是       | -128 ~ 127      |
| long     | Long      | -2^63 ~ (2^63 - 1) | 是       | -128～127       |
| float    | Float     | --                 | 否       | --              |
| double   | Double    | --                 | 否       | --              |
| boolean  | Boolean   | true, false        | 是       | true, false     |
| char     | Character | \u0000 ~ \uffff    | 是       | \u0000 ~ \u007f |

[Java: Integer用==比较时127相等128不相等的原因](https://www.polarxiong.com/archives/Java-Integer用-比较时127相等128不相等的原因.html)