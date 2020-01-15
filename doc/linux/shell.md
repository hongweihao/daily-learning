### Shell

```shell
chmod +x test.sh # 设置可执行权限
```



#### 变量

```shell
# 声明变量并使用
your_name="qinjx"
echo $your_name
echo ${your_name}

# 删除变量
unset variable_name

# 字符串：
# 单引号里的任何字符都会原样输出
# 双引号里可以有变量和转义字符
string="abcd"
echo ${#string} # 长度
echo ${string:1:4} # 子字符串
```



#### 数组

```shell
# 用括号来表示数组，数组元素用空格隔开。定义数组的一般形式为
array_name=(value0 value1 value2 value3)

# 赋值
array_name[0]=value0 

# 读取数组元素值
${数组名[下标]} 

# 使用 @ 符号可以获取数组中的所有元素
echo ${array_name[@]} 

# 数组中的全部元素
${my_array[*]}/${my_array[@]} 

# 取得数组元素的个数
length=${#array_name[@]}/length=${#array_name[*]} 
```



#### 传递参数

```shell
$# # 传递到脚本的参数个数
$0 # 单前执行的脚本名称
$1 # 第一个参数
$2 # 第二个参数
$* # 将参数合成一个字符串 "1 2 3"
$@ # 都是引用所有参数 "1" "2" "3"
```

#### 基本运算符

> expr 是一款表达式计算工具，使用它能完成表达式的求值操作

```shell
val=`expr 2 + 2`   # 实际上是执行Linux命令，再把结果给到val
echo "sum: $val"
```

[基本运算符](https://www.runoob.com/linux/linux-shell-basic-operators.html)

#### [ test 命令](https://www.runoob.com/linux/linux-shell-test.html)



#### 流程命令
```shell
# 中括号条件判断
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi

# test条件判断
num1=$[2*3]
num2=$[1+5]
if test $[num1] -eq $[num2]
then
    echo '两个数字相等!'
else
    echo '两个数字不相等!'
fi

# 第一种for方式
for item in 1 2 3 4 5
do
    echo "The value is: $item"
done
# 第二种for方式
for str in 'This is a string'
do
    echo $str
done

# while
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done
```



#### awk和sort命令

```shell
$ cat test.txt 
NAME            JOB             AGE     BIRTH_DATE
Sheldon         physicist       27      1.1
Penny           actress         26      2.2
Leonard         physicist       28      3.3
Howard          engineer        24      1.31
Rajesh          physicist       26      4.4
Bernadette      scientist       26      2.28
Amy             biologist       28      12.31

# 只读取前5行
$ head -n 5 test.txt 

# 只读取最后两行
$ tail -n 2 test.txt 
Bernadette  scientist   26  2.28
Amy         biologist   28  12.31

# 只读第3列
$ awk '{print $3}' test.txt
AGE
27
26
28
24
26
26
28

# 删除第一行
$ sed -i '2d' test.txt

# awk 打印第1，3行；sort根据第3行排序
# sort中，-n表示采用数字排序（默认是字符排序，10<2）;-k表示指定列数，这里指定第二列
$ awk '{print $1,$3}' test.txt | sort -n -k 2
Howard 24
Bernadette 26
Penny 26
Rajesh 26
Sheldon 27
Amy 28
Leonard 28
```

[Linux awk命令](https://www.runoob.com/linux/linux-comm-awk.html)

[Linux sort命令](https://www.cnblogs.com/51linux/archive/2012/05/23/2515299.html)

[linux之sed用法](https://www.cnblogs.com/dong008259/archive/2011/12/07/2279897.html)

