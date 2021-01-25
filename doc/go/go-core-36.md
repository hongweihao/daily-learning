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

```go
func main() {
	slice := make([]int, 0)
	slice = append(slice, 1)

	value, ok := interface{}(slice).([]int)
	fmt.Println("value", value, "ok", ok)
}
```

> value [1] ok true





































## 二、Go 语言进阶





## 三、Go 语言实战

















