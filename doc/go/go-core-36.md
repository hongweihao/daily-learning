# [Go 语言核心36讲](https://time.geekbang.org/column/intro/100013101)

## 一、Go 语言基础

### 1. 工作区和 GOPATH

#### 1.1 设置 GOPATH 有什么意义？

##### 1.1.1 Go语言的源码组织方式

- 以“包”作为基本的单位，包中可以包含多个 `.go` 文件。一个包中的多个 `.go `文件都需要被声明属于一个代码包。

- 这些代码包一般放在GOPATH/src/xxx

- 代码包的名称一般与目录同名。2者可以不同，若不同需以代码包名称为准

##### 1.1.2 构建和安装Go程序的过程

`go build`：在指定目录下生成可执行文件，例如在当前目录 `demo` 执行 `go build`，会在当前目录生成一个名称为 `demo` 的可执行文件

`go install`：在 `GOPATH/bin` 生成可执行文件，将编译后的包文件放到 `GOPATH/pkg` 目录



### 2. 命令源码文件

> 属于 `main` 包，且包含一个无参无返回值的 `main` 函数的源码文件

#### 2.1 命令源码文件怎样接收参数

```go
package main

import (
	"flag"
	"fmt"
)

var name string
func init() {
	flag.StringVar(&name, "name", "everyone", "the usage of mkii")
}
func main() {
	flag.Parse()
	fmt.Println("Hello ", name)
}
```



#### 2.2 运行命令源码文件传入参数，查看参数的使用说明

```shell
# 传入参数
go run main.go -name="karl"
```



```shell
# 查看参数使用说明
go run main.go --help
```

![image-20210123131810921](https://gitee.com/mkii/md-image/raw/master/image-20210123131810921.png)

> build 之后的可执行文件放在一个临时目录



```shell
# build main.go 生成main.exe
go build main.go
# 查看参数使用说明
main.exe --help
```

![image-20210123131955265](https://gitee.com/mkii/md-image/raw/master/image-20210123131955265.png)

#### 2.3 怎样自定义命令源码文件的参数使用说明

使用 `flag.Usage`，这是一个函数类型的对象

```go
package main

import (
	"flag"
	"fmt"
	"os"
)

var foo string

func init() {
	flag.StringVar(&foo, "foo", "everyone", "usage")
	flag.Usage = func() {
		fmt.Fprintf(os.Stderr, "this is a custom usage %s:\n", "question")
		flag.PrintDefaults()
	}
}

func main() {
	flag.Parse()
	fmt.Println("foo: ", foo)
}
```



### 3. 库源码文件

> 不能直接允许的源码文件，用于定义存放程序实体，提供给其他代码调用



> 程序实体：
>
> 变量，常量，结构体，接口，函数



#### 3.1 拆分代码到其他代码包

> 同一个包内程序实体都能访问



#### 3.2 代码包的导入路径与目录名

假设`lib`目录下有一个文件 `lib.go`，内容如下：

```go
package lib3

import "fmt"

func Hello(){
    fmt.Println("hello world")
}
```

那么引用 `Hello` 方法时，应该如何导入

```go
import "xxx/lib"  // 第一种
import "xxx/lib3" // 第二种
```

这里应该选择第二种，在 `Go` 语言中，建议目录名与包名相同。若不同， 应以包名为准。



#### 3.3 程序实体访问权限规则

- 包级私有：程序实体的名称为小写字母开头

- 公开：程序实体的名称为大写字母开头

- 模块级私有（internal）演示如下：

目录结构如下：

![image-20210125111809521](https://gitee.com/mkii/md-image/raw/master/image-20210125111809521.png)

internal.go的内容如下：

```go
package internal

import "fmt"

func Hello(name string) {
   fmt.Println("hello", name)
}
```

internaldemo.go的内容如下：

```go
package lib

// 可正常引用internal的代码
import "go_demo/src/go-core-36/libcodefile/lib/internal"

func test() {
   internal.Hello("karl")
}
```



demo.go的内容如下：（不可引用internal的代码）

![image-20210125112011926](https://gitee.com/mkii/md-image/raw/master/image-20210125112011926.png)

**总结**

包级私有相当于 `Java` 的 `private`，公开相当于 `Java` 的 `public`，模块级私有相当于 `Java` 的 `protected`



### 4. 程序实体

#### 4.1 Go变量声明的方式



#### 4.2 Go类型推断的好处

> 小重构



#### 4.3 变量重声明

```go
var err error
n, err := io.WriteString(os.Stdout, "Hello, everyone!\n")
```



#### 4.4 一个变量与其外层变量重名会发生什么

内层屏蔽外层

```go
package main

import "fmt"

// 变量重声明
var block = "package"
func main()  {
	var block = "main"
	{
		var block = "inner"
		fmt.Println(block)
	}

	fmt.Println(block)
}

```

> inner
>
> main



#### 4.5 如何判断一个变量的类型

使用类型断言表达式

![image-20210126121159505](https://gitee.com/mkii/md-image/raw/master/image-20210126121159505.png)

```go
func main() {
	slice := make([]int, 0)
	slice = append(slice, 1)

	value, ok := interface{}(slice).([]int)
	fmt.Println("value", value, "ok", ok)
}
```

> value [1] ok true



#### 4.6 类型转换过程中需要注意什么

- 精度问题

```go
func main() {
	var srcInt = int16(-255)
	fmt.Println(srcInt) // -255

	dstInt := int8(srcInt)
	fmt.Println(dstInt) // 1
}
```

- 无效代码点

```go
fmt.Println(string(-1)) // �
```

- `string` 与 `[]byte` 和 `[]rune` 之间的转换

`string -> []byte`: 拆分成字节，ASCII

`string -> []rune`： 拆分成 Unicode



#### 4.7 别名类型与潜在类型

```go
// 声明别名类型
type Mystring = string // Mystring是string的别名
```



```go
// 类型再定义
type Mystring string
```



潜在类型：某个类型的本质是什么类型。例如，Mystring的潜在类型是string

![image-20210126125130022](https://gitee.com/mkii/md-image/raw/master/image-20210126125130022.png)



## 二、Go 语言进阶

### 1. 数组与切片

#### 1.1 正确估算切片的长度和容量

当前容量小于1024时，扩容为2倍

当前容量大于1024时，扩容为1.25 倍（递减但一定会保证容量足够）

> 注意：扩容后的容量可能刚好是2/1.25倍也可能不是，因为存在一个内存对齐的步骤



#### 1.2 切片的底层数组什么时候会被替换

切片的底层数组不会被替换。使用append函数扩容时，实际上返回的是一个新的切片对象

> 注意：当拿到切片的指针时，应该避免去修改底层数组的元素，可能有多个切片引用此数组



### 2. container包的容器

`list`：双向链表

`heap`：堆

`ring`：循环链表，环



#### 2.1 能否把自己生成的Element对象传给链表

不能，为了避免链表的结构遭到外界破坏，实际去尝试就会发现：自己生成 `Element` 对象，实际上就是构建一个链表了

**list的用法：**

```go
package container

import (
   "container/list"
   "fmt"
   "testing"
)

func TestList(t *testing.T) {
   // 初始化一个list对象
   l := list.New()

   // 在表头插入元素
   l.PushFront(1)
   
   // 表尾插入元素
   e3 := l.PushBack(3)
   l.PushBack(4)

   // 在e3前插入元素
   e2 := l.InsertBefore(2, e3)

   // 在表头插入一个链表
   l.PushFrontList(l)

   // 将e2移动到e3后面
   l.MoveAfter(e2, e3)

   // 遍历链表元素
   for e := l.Front(); e != nil; e = e.Next(){
      fmt.Println(e.Value)
   }
}
```



#### 2.2 如何做到开箱即用

延迟初始化：在 `PushFrontList`，`PushBackList`，`PushFront`，`PushBack`都调用了 `lazyInit` 方法，这个方法用于延迟初始化



### 3. 字典的操作和约束

> 底层是一个 `hash` 表的特定实现



#### 3.1 key不能是哪些类型

key不能是**函数，字典和切片**

**key的要求有2个：**

- 可hash，由hash函数决定
- 可判等，Go语言中使用==运算符判等

第二是可判等，Golang中使用==运算符判等

> Java 中使用equals方法判等，理论上所以的类型都可作为key



##### 3.1.1 应该优先考虑哪些类型作为key

> 求hash和判等的速度越快，对应的类型就越适合做key

求hash的速度与key的“宽度”有关，宽度指的是某个类型单个值占用的字节数。例如，bool，int8，unit单个值占用1个字节，字符串占用的字节数不定。

优先使用数值类型和指针类型，如果使用字符串类型应该控制字符串的长度

##### 3.1.2 map的值为nil时，可是否可以操作

除了添加key-value之外，其他的操作都可以



### 4. 通道

> 通道是一个并发安全的数据结构，相当于一个先进先出的队列，底层是环形链表



#### 4.1 通道的发送/接收都有哪些基本特性

- 同一个通道，发送操作之间互斥，接收操作之间也互斥
- 发送/接收具有原子性。例如，发送包含2个过程：复制数据，数据副本放入通道，这2个步骤具有原子性
- 发送/接收操作在完成之前会被阻塞



#### 4.2 发送/接收什么时候会被长时间阻塞

- 缓冲通道，当通道数据满时，发送操作会被阻塞；当通道为空时，接收操作会被阻塞
- 非缓冲通道，当发送和接收2个操作其中一个没有就绪时，另一个会被阻塞
- 对一个值为nil的通道进行发送/接收操作时，都会被阻塞



#### 4.3 发送/接收什么时候会引发 panic

- 给一个已经关闭的通道发送数据
- 关闭一个已经关闭的通道

> 注意：
>
> 接收操作可以通过第2个返回值判断通道是否关闭，所以一般应该由发送方关闭通道。
>
> 接收方通过第2个返回值判断通道是否关闭有延迟：通道中存在数据实际上已经被关闭，这时候第2个返回值为true，因为读取到了数据



#### 4.4 单向通道有什么应用价值

约束其他代码的行为。例如，一个通道其中一个线程只允许接收，可以设置为只读通道 `<-chan int`



#### 4.5 select 语句与通道如何联用

```go
ch1 := make(chan int, 1)
ch2 := make(chan int, 1)
ch1 <- 666

select {
    case <-ch1:
    	fmt.Println("get vlaue from ch1")
    case <-ch2:
	    fmt.Println("get value from ch2")
	default:
    	fmt.Println("get nothing")
}
```

**注意点：**

- select语句中不存在default语句，其他的通道为空时，select会阻塞
- select语句中存在default语句时，select不会阻塞
- 可以使用返回的第2个值判断通道是否关闭来避免长时间阻塞
- **与for语句结合使用时，break语句只会跳出select不会跳出for**



#### 4.6 select语句的分支选择规则

```go
package main

import "fmt"

var channels = [3]chan int{
	nil,
	make(chan int),
	nil,
}

var numbers = []int{1, 2, 3}

func main() {
	select {
	case getChan(0) <- getNumber(0):
		fmt.Println("The first candidate case is selected.")
	case getChan(1) <- getNumber(1):
		fmt.Println("The second candidate case is selected.")
	case getChan(2) <- getNumber(2):
		fmt.Println("The third candidate case is selected")
	default:
		fmt.Println("No candidate case is selected!")
	}
}

func getNumber(i int) int {
	fmt.Printf("numbers[%d]\n", i)
	return numbers[i]
}

func getChan(i int) chan int {
	fmt.Printf("channels[%d]\n", i)
	return channels[i]
}

```

> channels[0]
> numbers[0]
> channels[1]
> numbers[1]
> channels[2]
> numbers[2]
> No candidate case is selected!

### 5. 函数

#### 5.1 怎样编写高阶函数

**高阶函数的特性：**

- 可以接收函数类型的参数
- 可以返回函数类型的结果



#### 5.2 如何实现闭包

> 在一个函数中存在对自由变量的引用，这个自由变量参与到该函数的逻辑中来
>
> 自由变量：外部定义的变量，不由函数内部控制，完全由外部控制



#### 5.3 传入的参数值后来怎样了

在 Go 中，向函数传递参数会先把此参数进行复制得到一个副本值，函数中会操作此副本。应该注意的是，复制时如果参数为值类型，那么副本会复制所以的内容。例如，参数为数组时，副本应该复制该数组与该数组的全部值。复制时如果参数为引用类型，那么副本只会复制此引用，底层的数据并不会复制。例如，参数为切片时，副本比复制了切片的引用，底层的数据与原切片公用。

> Go 没有 Java的深拷贝和浅拷贝概念，复制的方式取决于类型



```go
package main

import (
	"fmt"
	"testing"
    "errors"
)

// 编写高阶函数
// 高阶函数的特性：
// 1. 可以接收函数类型的参数
// 2. 可以返回函数类型的结果
func TestFun(t *testing.T) {
	result, err := compute(1, 2, add)
	if err != nil {
		t.Error(err)
		return
	}
	fmt.Println("result: ", result)
}

// 实现闭包
func TestCloseFun(t *testing.T) {
	compute := genComputeFun(add)
	result, err := compute(4, 5)
	if err != nil {
		t.Error(err)
		return
	}
	fmt.Println("result:", result)
}

// 测试传入的参数值后来怎么样了
func TestParameters(t *testing.T) {
	array := [10]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	slice := array[:]

	fmt.Println("source array: ", array)
	fmt.Println("source slice: ", slice)

	arrayParams(array)
	fmt.Println("source array modified: ", array)

	sliceParams(slice)
	fmt.Println("source slice modified: ", slice)
}

// 声明operate的类型为函数类型
type operate func(x, y int) int

func add(x, y int) int {
	return x + y
}

// 实现一个函数，完成对2个数值得操作，具体的操作过程由调用方提供
func compute(x, y int, op operate) (int, error) {
	if op == nil {
		return 0, errors.New("operate is invalid")
	}
	return op(x, y), nil
}

type computeFun func(x, y int) (int, error)

func genComputeFun(op operate) computeFun {
	return func(x, y int) (int, error) {
		if op == nil {
			return 0, errors.New("op is invalid")
		}
		return op(x,y), nil
	}
}

func arrayParams(array [10]int)  {
	fmt.Println("array parameters:", array)
	array[0] = 888
	fmt.Println("after updating:", array)
}

func sliceParams(slice []int) {
	fmt.Println("slice parameters:", slice)
	slice[0] = 999
	fmt.Println("after updating:", slice)
}
```



### 6. 结构体

```go
// AnimalCategory 代表动物分类学中的基本分类法。
type AnimalCategory struct {
  kingdom string // 界。
  phylum string // 门。
  class  string // 纲。
  order  string // 目。
  family string // 科。
  genus  string // 属。
  species string // 种。
}

func (ac AnimalCategory) String() string {
  return fmt.Sprintf("%s%s%s%s%s%s%s",
    ac.kingdom, ac.phylum, ac.class, ac.order,
    ac.family, ac.genus, ac.species)
}


type Animal struct {
  scientificName string // 学名。
  AnimalCategory    // 动物基本分类。
}
```

#### 6.1 Animal类型中的字段声明AnimalCategory代表了什么

代表了嵌入字段，Go语言规定，一个字段的声明中只有字段类型没有字段名称，称这个字段是嵌入字段，也叫匿名字段。嵌入字段可以通过下面这种方式使用：

```go
animal := Animal{...}
animal.AnimalCategory.xxx
```

此外，嵌入字段 `AnimalCategoary` 还有一个作用。嵌入字段的字段和方法集合会被无条件地合入到 `Animal` 中，但如果 Animal 中存在同名的字段或方法，那嵌入字段的字段方法会被“屏蔽”。



#### 6.2 Go语言的嵌入字段实现了继承吗

在Go中没有继承的概念，Go中使用嵌入字段实现类型间的组合。



#### 6.3 值方法和指针方法都是什么意思，有什么区别？

```go
type Cat struct{
    Name string
}

// 值方法
func (cat Cat) SetName(name string){
    cat.Name = name
}

// 指针方法
func (cat *Cat) SetName(name string){
    cat.Name = name
}

```

方法可以理解为一个传了“接收类型对象参数“的函数。像这样：

```go
func SetName(cat Cat, name string){
    cat.name = name
}
func SetName(cat *Cat, name string){
    cat.name = name
}
```

Go中，传给函数的参数会被复制，函数操作的实际上是这个复制出来的副本值。所以值方法中 `SetName` 时，操作的是值对象的一个副本，不会对原址有影响，但在指针方法中 `SetName` 时，操作的是指针对象的副本值，尽管是副本，但底层指向的是同一个地址，所以原址的 `Name` 属性会被覆盖。



#### 6.4 可以在结构体类型中嵌入某个类型的指针类型吗？如果可以，有哪些注意事项？

可以在结构体类型中嵌入指针类型。要注意的是，如果嵌入的是某个类型的值对象，该对象的零值为一个非 nil 的值且能直接使用。

但如果嵌入的是某个类型的指针对象，指针对象的零值是 nil。若没有手动初始化对其操作，会发生nil point panic。



#### 6.5 字面量struct{}代表了什么？又有什么用处？

struct{}不占空间，可以拥有方法



### 7. 接口

![image-20210205092911915](https://gitee.com/mkii/md-image/raw/master/image-20210205092911915.png)

```go
type Pet interface {
	Name() string
	Category() string
}

type Dog struct {
	name string
}

func (dog Dog) Name() string {
	return dog.name
}

func (dog Dog) Category() string {
	return "dog"
}

func (dog *Dog) SetName(name string) {
	dog.name = name
}
```

#### 

#### 7.1 当我们为一个接口变量赋值时会发生什么？

``` go
func main() {
	dog := Dog{"little pig"}
	var pet Pet = dog
    
	dog.SetName("monster")
	fmt.Println("pet name:", pet.Name()) // little pig
}
```

##### 7.1.1 先明确接口类型的3个概念

静态类型：接口本身的类型，不可变。Pet

动态类型：接口的实现类型，可变。Dog

动态值：接口的实现类型的值，可变。dog



#### 7.1.2 上面代码执行完之后为什么不是打印 monster

程序执行`dog.SetName("monster")`时，dog的name值已经被修改为monster。要注意的是，执行 `var pet Pet = doy` 时，pet对象拿到的其实是dog对象的一个副本值，所以后面对dog对象的修改对pet对象完全无影响

>通用规则：
>
>赋值时，对象拿到的总是副本值，是否能对原对象产生影响主要看对象是值类型还是指针类型，值类型会完全复制内容。尽管指针类型也会进行复制，但复制后的指针和原指针指向的内存区域是相同的。



#### 7.2 接口变量在什么时候才真正为nil

- 声明未初始化
- 将nil字面量之间赋给接口变量



注意下面这种情况：

```go
func main() {
	var dog *Dog = nil
	var pet Pet
	fmt.Println("1. pet2 is nil: ", pet == nil)

	pet = dog
	fmt.Println("2. pet2 is nil: ", pet == nil)
}
```

> 1. pet2 is nil:  true
> 2. pet2 is nil:  false

尽管dog的值为nil，但赋给pet之后，pet的值不为nil。原因是接口变量的存储实际上是一层包装（iface），它不止存储值还会存储类型等其他信息，所以当使用dog对象赋给pet时，pet看起来像是拿到了nil，实际上它拿到了类型Dog，值nil



#### 7.3 怎样实现接口之间的组合

```go
type Animal interface {
	Pet
	Skin()
}
```



#### 7.4 如果把一个值为nil的变量赋给接口变量，那变量可以调用该接口的方法吗？注意事项

可以，需要注意接口变量的值为nil，涉及到使用对象的操作都会出现空指针的panic



### 8. 指针（pending）

#### 8.1 Go语言中哪些值是不可寻址的



#### 8.2 可以寻值得值在使用上的限制



#### 8.3 通过unsafe.Pointer操纵可寻址的值



### 9. go语句及其执行规则

#### 9.1 什么是主goroutine，它与我们启用的其他goroutine的区别

Go 程序启动时会默认启用一个goroutine，启用其他goroutine需要使用go语句，启用时会先从groutine queue中查找是否有空闲的goroutine可用，如果有则直接使用，如果没有才去创建一个。

主goroutine执行完成时，如果其他的goroutine未开始执行或者未执行完成，都会退出程序。

```go
for i := 0; i < 10; i++ {
    go func() {
        fmt.Println(i)
    }()
}
```

> 上面的程序会打印什么？
>
> 绝大概率不会打印任何东西。因为主goroutine执行完成之后就退出了程序，go函数没有机会去执行



#### 9.2 如何让主gorountine等待其他的goroutine？

##### 9.2.1 让主goroutine"睡"一会

```go
func main() {
	for i := 0; i < 10; i++ {
		go func() {
			fmt.Println(i)
		}()
	}
	time.Sleep(1 * time.Millisecond)
}
```

> 打印结果每次不同，因为go函数中出现了闭包，i的值取决于外层的代码块执行快慢



##### 9.2.2 channel

```go
func main() {
	const num = 10
	wait := make(chan struct{}, num)

	for i := 0; i < num; i++ {
		go func() {
			fmt.Println(i)
			wait <- struct{}{}
		}()
	}

	for i := 0; i < num; i++ {
		<-wait
	}
}
```

> 这里有个技巧，struct{}类型的值只能是struct{}{}，且不占用空间



##### 9.2.3 sync.WaitGroup

```go
func main() {
	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		wg.Add(1)
		go func() {
			fmt.Println(i)
			wg.Done()
		}()
	}
	wg.Wait()
}
```



#### 9.3 启用的goroutine如何按顺序执行

```go
import (
	"fmt"
	"sync/atomic"
	"time"
)
func main() {
	var count int32 = 0
	var i int32 = 0
	for ; i < 10; i++ {
		fn := func(v int32) {
			fmt.Println(v)
		}
		// 改成参数传入，去除闭包，让每个goroutine能被标识
		go func(no int32, f func(v int32)) {
			for  {
				if count == no {
					f(no)
					atomic.AddInt32(&count, 1)
				}else {
					time.Sleep(1*time.Microsecond)
				}
			}
		}(i, fn)
	}

	time.Sleep(1 * time.Second)
}
```

> 这里使用了atomic包的AddInt32方法，是一个原子操作



### 10. if，for，switch



#### 10.6 switch与case之间的联系

![image-20210209162541407](https://gitee.com/mkii/md-image/raw/master/image-20210209162541407.png)

```go
func main() {
	value := []int8{0, 1, 2, 3, 4, 5, 6}
	/*
		//  编译错误
		// 1+3的结果是一个无类型常量，会默认转成int类型，int类型与int8不能坐判断操作
		switch 1 + 3 {
		case value[0], value[1]:
			fmt.Println("0 or 1")
		case value[2], value[3]:
			fmt.Println("2 or 3")
		case value[4], value[5], value[6]:
			fmt.Println("4 or 5 or 6")
		}*/

	// case中的0，1是无类型常量，会转成需要的int8类型
	switch value[2] {
	case 0, 1:
		fmt.Println("0, 1")
	case 2, 3:
		fmt.Println("2, 3")
	case 4, 5, 6:
		fmt.Println("4,5,6")
	}
}
```



#### 10.7 switch对case有什么约束

```go
func main() {
	slice := []int {0, 1, 1, 2}

	/*
	// case表达式不能相同
	switch slice[0] {
	case 0, 1:
		fmt.Println("01")
	case 0, 2:
		fmt.Println("02")
	}*/

	// 会运行第一个匹配的case
	switch 0{
	case slice[0], slice[1]:
		fmt.Println("01")
	case slice[2], slice[3]:
		fmt.Println("23")
	}
}
```

#### 10.8 在switch语句中，如何对被判断的值做类型转换

使用类型断言

```go
type User struct {
	Name string
	Age int
}

func main() {
	user := &User{
		Name: "mkii",
		Age: 18,
	}

	var iuser interface{} = user
	switch user {
	case iuser.(*User):
		fmt.Println("111")
	}
}
```



#### 10.9 if语句中，初始化子句声明的变量作用域是什么

![image-20210209222211661](https://gitee.com/mkii/md-image/raw/master/image-20210209222211661.png)



### 11. 错误处理

#### 11.1 对于具体错误的判断，Go语言中有哪些惯用法

##### 11.1.1 类型断言表达式或者switch

```go
// underlyingError returns the underlying error for known os error types.
func underlyingError(err error) error {
	switch err := err.(type) {
	case *PathError:
		return err.Err
	case *LinkError:
		return err.Err
	case *SyscallError:
		return err.Err
	}
	return err
}
```

##### 11.1.2 判等

##### 11.1.3 字符串识别



#### 11.2 怎样根据实际情况给予错误值

##### 11.2.1 立体的错误类型体系

以error接口为根，一层层实现。

此外，还有err字段可以链式查找最深层的错误



##### 11.2.2 扁平的错误值列表

预先创建一些代表已知的错误。需要注意防止篡改，可以定义为常量或者定义为私有属性并提供公有方法访问。



### 12. panic，recover，defer

#### 12.1 从panic被引发到程序终止运行的大致过程

从引发panic的语句开始，开始初始化panic详情，控制权逐级交由上层直到main，再由go运行时系统收回。最后在程序崩溃退出前打印panic详情。



#### 12.2 怎样让panic包含一个值

```go
func main() {
   fmt.Println("main enter........")
   testPanic("my err msg")
   fmt.Println("testPanic exit........")
}

func testPanic(errMsg string)  {
   fmt.Println("testPanic enter........")
   panic(errMsg)
   fmt.Println("testPanic exit........")
}
```



#### 12.3 如何施加应对panic的保护措施，避免程序崩溃

使用defer+recover

```go
func main() {
   defer func() {
      err:= recover()
      if err != nil {
         fmt.Println("catch panic")
      }
   }()
   
   panic("my panic msg")
}
```

#### 12.4 如果一个函数中有多个defer语句，他们的执行顺序是怎样的

像栈一样，先进后出。底层是一个与函数对应的链表

```go
func main() {
   /*defer func() {
      fmt.Println("defer 1")
   }()

   defer func() {
      fmt.Println("defer 2")
   }()

   defer func() {
      fmt.Println("defer 3")
   }()*/
   loopDefer()
   fmt.Println("main end")
}

func loopDefer() {
   for i := 0; i < 10; i++ {
      defer func(no int) {
         fmt.Println("defer", no)
      }(i)
   }
   fmt.Println("loopDefer end")
}
```

## 三、Go 语言实战

### 1. 测试（pending）

#### 1.1 Go语言对测试函数的名称和签名有什么要求



#### 1.2 `go test` 命令的主要测试流程是什么



#### 1.3 怎样解释功能测试的测试结果



### 2. Mutex与RWMutex

- 竞态条件：多个线程共享一个数据，这时候很可能出现多个线程同时争用这份数据导致冲突和数据不一致
- 临界区：一段代码，这段代码要求被串行执行。这段代码通常是一些资源的使用
- 同步工具：用来保持临界区串行执行的工具。例如互斥锁mutex

#### 2.1 使用互斥锁时有哪些注意事项

- 不可重复锁定
- 不要忘记解锁，推荐使用defer
- 不可对未加锁或已解锁的互斥锁解锁
- 不要在多个函数中传递互斥锁
- sync.Mutex对象是一个值类型，要注意副本的产生

#### 2.2 读写锁和互斥锁的异同

|           | Mutex                                             | RWMutex                                                      |
| --------- | ------------------------------------------------- | ------------------------------------------------------------ |
| 概念      | 互斥锁                                            | 读写锁，分为读锁和写锁。多个写操作不能同时进行，写操作和读操作也不能同时进行，但多个读操作却可以同时进行。 |
| 加锁/解锁 | m.Lock()/m.Unlock()                               | rw.Lock()/rw.Unlock()<br/> rw.RLock()/rw.RUnlock()           |
| 规则      | 锁已被锁定的情况下再试图拿锁，会阻塞当前goroutine | 在写锁已被锁定的情况下再试图锁定写锁，会阻塞当前的 goroutine <br/>在写锁已被锁定的情况下试图锁定读锁，也会阻塞当前的 goroutine<br/>在读锁已被锁定的情况下试图锁定写锁，同样会阻塞当前的 goroutine<br/> 在读锁已被锁定的情况下再试图锁定读锁，并不会阻塞当前的 goroutine |
|           | 重复加锁或重复解锁都会panic                       | 重复加锁或重复解锁都会panic                                  |



##### 2.2.1 sync.Mutex的使用

```go
func main() {
	//var lock sync.Mutex
	count := 0
	for i := 0; i < 1000; i++ {
		go func() {
			//lock.Lock()
			//defer lock.Unlock()
			count++
		}()
	}
	time.Sleep(1 * time.Second)
	fmt.Println("count", count)
}
```

> 结果是一个小于1000的值

##### 2.2.2 sync.RWMutex的使用

```go
var count int
var wg sync.WaitGroup
// var rw sync.RWMutex

func main() {
   for i := 0; i < 5; i++ {
      wg.Add(1)
      go write(i)
   }
   for i := 0; i < 5; i++ {
      wg.Add(1)
      go read(i)
   }
   wg.Wait()
}

func read(i int) {
   // rw.RLock()
   // defer rw.RUnlock()
   fmt.Printf("read(%d) start....\n", i)
   v := count
   fmt.Printf("read(%d): %d end.......\n", i, v)
   wg.Done()
}

func write(i int) {
   // rw.Lock()
   // defer rw.Unlock()
   fmt.Printf("write(%d) start....\n", i)
   v := rand.Intn(1000)
   count = v
   fmt.Printf("write(%d): %d end.......\n", i, count)
   wg.Done()
}
```



### 3. 条件变量Cond

条件变量并不是用来保护临界区和共享资源的，它用于协调想要访问共享资源的那些线程。

当共享资源的状态发生变化时，可以用来通知被互斥锁阻塞的线程。

#### 3.1 条件变量怎么与互斥锁配合使用

```go
// 条件变量与互斥锁配合使用
var (
	wg       sync.WaitGroup
	data     uint8
	lock     sync.RWMutex
	sendCond = sync.NewCond(&lock)
	recvCond = sync.NewCond(lock.RLocker())
)

func main() {
	MutexAndCond()
}

func MutexAndCond() {
	wg.Add(2)
	go send(0)
	// 仅适用于一个接收者的情况，因为接收者拿了读锁却修改了数据
	go recv(1)
	wg.Wait()
}

// 从信箱里收情报
func recv(i int) {
	fmt.Printf("receiver(%d) start\n", i)

	lock.RLock()
	for 0 == data {
		recvCond.Wait()
		fmt.Printf("receiver(%d) wait\n", i)
	}

	data = 0
	lock.RUnlock()
	sendCond.Broadcast()

	fmt.Printf("receiver(%d) end\n", i)
	wg.Done()
}

// 放情报进信箱
func send(i int) {
	time.Sleep(100 * time.Millisecond)
	fmt.Printf("sender(%d) start\n", i)

	lock.Lock()
	// 如果信箱里面有情报就等待通知
	for 1 == data {
		// 放情报的人在等情报被取走
		sendCond.Wait()
		fmt.Printf("sender(%d) wait\n", i)
	}

	// 放好情报了，通知给别人
	data = 1
	lock.Unlock()
	recvCond.Broadcast()

	fmt.Printf("sender(%d) end\n", i)
	wg.Done()
}
```

> **为什么用for不用if？**
>
> 首先要明确for是持续的，if是一次性的。
>
> 1. 若A被唤醒，但此时的状态不是A要的，那么使用if就会直接执行后面的代码了，使用for相当于A被唤醒之后还会持续检查状态是不是自己要的。
> 2. A在等待期间不一定是通过条件变量唤醒的，可能由其他的唤醒（例如操作系统）



#### 3.2 条件变量的Wait方法做了什么

- 把当前goroutine加入当前条件变量的通知队列中
- 解锁当前条件变量的Locker对象
- 让当前goroutine处于等待状态（阻塞在调用wait的那一行）
- 被唤醒之后重新锁定当前条件变量的Locker对象



#### 3.3 Singnal和Brodcast的异同

同：都用来发送通知

异：Signal通知的是等待队列的队首goroutine，而Brodcast通知的是等待队列的所以goroutine

一个goroutine调用Wait方法会被放到等待队列的队尾，Signal会通知队首的goroutine，一般为等待时长最长的。



### 4. 原子操作atomic

go语言中，goroutine在执行临界区中的代码时，可以不被其他的goroutine打扰，但却可以被中断（interruption）。也就是说互斥锁可以保证临界区代码的串行执行，但不能保证这些代码执行的原子性。

#### 4.1 sync/atomic包中提供了几种原子操作？可操作的数据类型又有哪些？

- add
- cas
- load
- store
- swap
- Value类型（结构体）

------

- int32/int64
- uint32/uint64
- uintptr
- unsafe.Pointer（不支持add）



#### 4.2 CAS和Swap的区别

CAS：先比较原子值是否和预期的值相等，若相等则将新值赋给当前值并返回true，否则就忽略交换操作并返回false

Swap：将新值赋给原子值



#### 4.3 在其他的写操作原子的情况下，读操作有必要使用原子操作吗

有必要，如果读操作不具备原子性，有可能读到不完整的值，即读到一半被其他操作修改了。



#### 4.4 atomic.Value

相当于一个容器，可以用来“原子地”存储/加载值。

它是开箱即用的，有2个方法Store和Load

**注意点**

- 一旦atomic.Value被使用，就不该再被复制
- 不能存储nil
- 存储的第一个值的类型决定了以后这个Value只能存储此类型的值

```go
func main() {
	var v atomic.Value

	var a = 12
	v.Store(a)
	fmt.Println(v.Load()) // 12

	// 不该被复制
	copyV := v
	copyV.Store(24)
	fmt.Println(copyV.Load()) // 24
	fmt.Println(v.Load())     // 12 如果Value内存储的指针，这里为什么不是24？

	// v.Store(nil) // panic: sync/atomic: store of nil value into Value
	// v.Store("str") // panic: sync/atomic: store of inconsistently typed value into Value
}
```



### 5. WaitGroup和Once

#### 5.1 WaitGroup中的值可以小于0吗

不可以。

在一个计数周期结束时，会通知调用了Wait方法的那个goroutine。

要开始下一个计数周期，需要上一个计数周期的Wait方法执行完毕。

> 计数周期：WaitGroup的值从0 -> 正整数 -> 0的过程



#### 5.2 Once的Do方法如何保证执行参数函数一次

sync.Once用来保证代码只允许一遍。Once的内部有个变量done（默认值为0）保存着执行状态一旦参数函数执行完成就会将done的值设置为1。

```go
// Do方法源码
// 这里和单例模式的双重检查相似
func (o *Once) Do(f func()) {
    // 先查看done的值，快失败
	if atomic.LoadUint32(&o.done) == 0 {
		o.doSlow(f)
	}
}

func (o *Once) doSlow(f func()) {
    // 其他的goroutine会被阻塞在此
	o.m.Lock()
	defer o.m.Unlock()
	if o.done == 0 {
		defer atomic.StoreUint32(&o.done, 1)
		f()
	}
}
```



#### 5.3 Do方法的特点

- 如果参数函数运行慢，或者压根不会退出。那么其他调用此Do方法的goroutine会被阻塞
- 函数参数在运行结束时总会将done的值设置为1，无论函数参数运行结果如何。也就是说done标记的是函数参数是否运行？至于结果它并不关心。



### 6. Context

#### 6.1 使用context实现一对多的goroutine协作流程

```go
func main() {
	//ctx, cancel := context.WithCancel(context.Background())
	ctx, cancel := context.WithDeadline(context.Background(), time.Now().Add(5))
	var num int32
	total := 12
	for i := 0; i < total; i++ {
		go addNum(i, &num, func() {
			if atomic.LoadInt32(&num) == int32(total) {
				fmt.Println(i, "stop.......")
				cancel()
			}
		})
	}
	<-ctx.Done()
	fmt.Println(ctx.Err())
}

func addNum(id int, numP *int32, cancel func())  {
	fmt.Println(id, "start....")
	defer cancel()
	num := atomic.LoadInt32(numP)
	newNum := num + 1
	if atomic.CompareAndSwapInt32(numP, num, newNum){
		fmt.Println(id, "cas", num, newNum)
	}
	fmt.Println(id, "end....")
}
```



#### 6.2 可撤销的context代表什么？撤销一个context会发生什么

撤销即一个gotoutine发送撤销信号，另一个goroutine（主goroutine）接收信号。

撤销有2种：

- 手动撤销，即手动调用cancel方法
- 到时自动撤销，当前时间等于设置的deadline时撤销

撤销一个context时，ctx.Done()返回的通道会被关闭，即接收操作不再阻塞。



#### 6.3 撤销信号如何在上下文树之间传播

撤销函数被调用后：

- 先关闭内部接收通道
- 向所有子节点传送撤销信号（递归）
- 这个context断开与复制的联系

> context.WithValue产生的值不可撤销，被跳过但会尝试通知它的子节点



#### 6.4 如何通过context携带数据？如何获取数据

```go
func main() {
	ctx := context.WithValue(context.Background(), "name", "mkii")
	ctx2, cancel := context.WithCancel(ctx)

	go func() {
		fmt.Println("start...")
		time.Sleep(time.Second)
		cancel()
		fmt.Println("end....")
	}()

	<-ctx2.Done()
	fmt.Println("name", ctx2.Value("name"))
}
```

### 7. sync.Pool

用来保存一组可独立访问的临时对象，可以被称为临时对象池

#### 7.1 特点

- 线程安全
- 使用后不可复制



#### 7.2 实现原理

sync.Pool的数据结构如下：(from: https://time.geekbang.org/column/article/301716)

![image-20210415092038136](https://gitee.com/mkii/md-image/raw/master/image-20210415092038136.png)



Pool 中用来存储空闲对象的属性主要是 local 和 victim，GC之前 Pool（sync初始化时会注册一个用于清除的函数） 会先把 victim 中的对象移除，然后在把 local 中的对象转移到 victim 中。

Get 获取对象的路径： local.PoolLocalInternel.private（P级私有） -> local.PoolLocalInternel.share -> getSlow -> New

getSlow获取对象：遍历 其他 local 的 share -> victim 的 private -> victim 的 share

Put对象的路径：local 的 private -> other local 的 private



#### 7.3 使用方法

> Pool struct 中有一个属性New 类型是 `func() interface{}`，在初始化一个 sync.Pool 对象时需要指定。如果从Pool中获取不到对象时，会通过 New 对应的方法生成一个对象并返回。如果没指定 New 的值则获取对象时返回nil

1. 从Pool中取走一个对象，（注意该对象会被从pool移除）
2. do something
3. 将对象的数据清除
4. 将对象返还给Pool

```go
func NewByte() interface{} {
	return make([]int, 0, 2)
}

func main() {
	pool := &sync.Pool{New: NewByte}

	bytes := pool.Get().([]int)
	fmt.Println(len(bytes), cap(bytes))

	pool.Put(bytes)
}
```



#### 7.4 使用场景

- buffer缓冲池
- server端解析大量的request对象
- fmt打印

>  buffer缓冲池为例：（from: https://geektutu.com/post/hpg-sync-pool.html）

```go
package main

import (
	"bytes"
	"sync"
	"testing"
)

var data = make([]byte, 10000)

func BenchmarkBufferPool(b *testing.B) {
	var pool = sync.Pool{
		New: func() interface{} {
			return &bytes.Buffer{}
		},
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		buf := pool.Get().(*bytes.Buffer)
		buf.Write(data)

		buf.Reset()
		pool.Put(buf)
	}
	b.StopTimer()
}
func BenchmarkBuffer(b *testing.B) {
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		var buf = &bytes.Buffer{}
		buf.Write(data)
	}
	b.StopTimer()
}

```

```
BenchmarkBufferPool-6   10619402	       119 ns/op	       0 B/op	       0 allocs/op
BenchmarkBuffer-6       750046	           1648 ns/op	   10240 B/op	       1 allocs/op
```



#### 7.5 注意内存泄漏和内存浪费问题

从 Pool 中获取 slice 对象，做一些操作之后 Put 到 Pool中，可能会有扩容的情况发生，这会导致 Pool 中的对象的 cap 只增不减。



### 8. sync.Map

#### 8.1 syncMap对key的类型有要求吗

有要求，不能是函数、字典和切片类型



#### 8.2 如何保证syncMap中key-value类型的正确性

##### 8.2.1 固定key-value类型（例如这里固定key为int类型，value为string类型）

```go
type IntStrSyncMap struct {
   m sync.Map
}

func NewIntStrSyncMap() *IntStrSyncMap {
   return &IntStrSyncMap{}
}

func (intStrSyncMap *IntStrSyncMap) Store(key int, value string)  {
   intStrSyncMap.m.Store(key, value)
}

func (intStrSyncMap *IntStrSyncMap) Load(key int) (string, bool) {
   value, ok := intStrSyncMap.m.Load(key)
   return value.(string), ok
}
```



##### 8.2.2 指定key-value类型

```go
type ConcurrentMap struct {
   m         sync.Map
   keyType   reflect.Type
   valueType reflect.Type
}

func badType(t reflect.Type) bool {
   return t == nil || t.Kind() == reflect.Slice || t.Kind() == reflect.Map || t.Kind() == reflect.Func
}

func NewConcurrentMap(keyType, valueType reflect.Type) *ConcurrentMap {
   if badType(keyType) {
      panic("Invalid type for key")
   }
   if badType(valueType) {
      panic("Invalid type for value")
   }

   return &ConcurrentMap{
      keyType:   keyType,
      valueType: valueType,
   }
}

func (concurrentMap *ConcurrentMap) Store(key, value interface{}) {
   if reflect.TypeOf(key) != concurrentMap.keyType {
      panic("no support type for key")
   }
   if reflect.TypeOf(value) != concurrentMap.valueType {
      panic("no support type for value")
   }
   concurrentMap.m.Store(key, value)
}

func (concurrentMap *ConcurrentMap) Load(key interface{}) (interface{}, bool) {
   if reflect.TypeOf(key) != concurrentMap.keyType {
      panic("no support type for key")
   }
   return concurrentMap.m.Load(key)
}
```



- 测试代码

```go
func main() {

   intStrSyncMap := NewIntStrSyncMap()
   intStrSyncMap.Store(1, "111")
   intStrSyncMap.Store(2, "222")

   v1, _ := intStrSyncMap.Load(1)
   v2, _ := intStrSyncMap.Load(2)

   fmt.Println("1", v1)
   fmt.Println("2", v2)

   concurrentMap := NewConcurrentMap(reflect.TypeOf(0), reflect.TypeOf(""))
   concurrentMap.Store(3, "333")
   concurrentMap.Store(4, "444")

   v3, _ := concurrentMap.Load(3)
   v4, _ := concurrentMap.Load(4)

   fmt.Println("3", v3)
   fmt.Println("4", v4)
}
```



#### 8.3 syncMap如何做到尽量避免使用锁

##### 8.3.1 结构

![image-20210305001101781](https://gitee.com/mkii/md-image/raw/master/image-20210305001101781.png)

（图片来源：https://www.jianshu.com/p/43e66dab535b）

##### 8.3.2 原理

- 将数据分别存在read和dirty2个map中
- read用来读和原子写（这里有一点，value的值底层使用的是unsafe.Pointer，可以使用atomic提供的CAS），但不会新增key
- dirty可以理解为read的超集，dirty的访问都需要加锁。添加一个key-value时，此key不存在，需要写入到dirty
- 当从read中load值失败次数超过dirty的长度时，dirty会给read且dirty会被置为nil



> read和dirty中的value都指向entry，entry中的unsafe.Pointer变化可以实现read和dirty同时变化



##### 8.3.3 lock-free实现的sync.Map与segment分段锁实现的[ConcurrentMap](https://github.com/halfrost/Halfrost-Field/tree/master/contents/Go/go_map_bench_test/concurrent-map)的比较

|            | mutexMap        | sync.Map                          | ConcurrentMap                              |
| ---------- | --------------- | --------------------------------- | ------------------------------------------ |
| 原理       | mutex互斥锁+map | read+dirty+cas+mutex（lock-free） | 分段锁，每操作一个区块需要先获取此区块的锁 |
| Store性能  | 中              | 低                                | 高                                         |
| Load性能   | 低              | 高                                | 中                                         |
| Delete性能 | 低              | 高                                | 中                                         |



### 9. unicode与字符编码

http://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html



#### 9.1 一个string类型的值在底层如何被表达

一个string类型的值是由一系列相对应的 Unicode 代码点的 UTF-8 编码值来表达的。

```go
str := "Go爱好者"
fmt.Printf("The string: %q\n", str)
fmt.Printf("  => runes(char): %q\n", []rune(str))
fmt.Printf("  => runes(hex): %x\n", []rune(str))
fmt.Printf("  => bytes(hex): [% x]\n", []byte(str))
```

> The string: "Go爱好者"
>   => runes(char): ['G' 'o' '爱' '好' '者']
>   => runes(hex): [47 6f 7231 597d 8005]
>   => bytes(hex): [47 6f e7 88 b1 e5 a5 bd e8 80 85]



#### 9.2 使用range遍历字符串时的注意点

```go
str1 := "Go爱好者"
for i, c := range str1 {
    fmt.Printf("%d: %q [% x]\n", i, c, []byte(string(c)))
}
```

> 注意index：=========================
> 0: 'G' [47]
> 1: 'o' [6f]
> 2: '爱' [e7 88 b1]
> 5: '好' [e5 a5 bd]
> 8: '者' [e8 80 85]



### 10. strings包与字符串操作

#### 10.1 与string相比，strings.Builder有什么优势

- 已存在的内容不可变
- 减少了内存分配和内容拷贝次数
- 可重置重用值
- 字符串拼接（主要优势）

|      | string                                                 | strings .Builder                                             |
| ---- | ------------------------------------------------------ | ------------------------------------------------------------ |
| 底层 | 通过unsafe.Pointer指向一个不可变的字节数组             | 通过unsafe.Pointer指向一个字节切片。已存在的值不可变，可添加内容到字节切片的尾部。可重置 |
| 操作 | 切片表达式，+等                                        | Write，WriteByte，WriteString，WriteRune                     |
| copy | 共用一个底层的字节数组，因为不可变所以不用担心冲突问题 | 一旦使用不可被复制，需要使用方自行解决冲突                   |

```go
func main() {
	s := "mkii"
	b := []byte("hong")
	r := []rune("works")

	var builder strings.Builder

	builder.WriteString(s)
	builder.Write(b)

	for _, v := range b {
		builder.WriteByte(v)
	}

	for _, v := range r {
		builder.WriteRune(v)
	}
	fmt.Println(builder.String())

	builder.Reset()
	fmt.Println(builder.String())
}
```



#### 10.2 为什么string.Reader类型值可以更高效地读取字符串

已读计数记录着下次读取的起始索引位置



### 11. bytes包与字符串操作

> 不了解使用场景。pending...
>
> // todo 源码

#### 11.1 bytes.Buffer已读计数的作用

#### 11.2 bytes.Buffer的扩容策略

#### 11.3 bytes.Buffer中的哪些方法可能会造成内容的泄露？



### 12. io包



### 13. bufio包



### 14. os包



### 15. 网络服务



### 16. 性能分析

















































