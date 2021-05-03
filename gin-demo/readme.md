# gin-demo

## 1. 初始化 project

- 先使用 `go env` 查看是否启用了 go mod

![image-20210423232831519](https://gitee.com/mkii/md-image/raw/master/image-20210423232831519.png)

- 开启 go mod 可以将

```powershell
# 可以将值设置为on或者auto，auto会自动切换go mod和gopath的project
go env -w GO111MODULE=auto
```

![image-20210423233204220](https://gitee.com/mkii/md-image/raw/master/image-20210423233204220.png)

- 使用以下命令初始化 project，会自动生成一个 go.mod 文件

```powershell
go mod init gin-demo
```

- 创建目录结果和 main 入口文件

![image-20210423233949789](https://gitee.com/mkii/md-image/raw/master/image-20210423233949789.png)



## 2. 集成 gin

- 安装 gin 依赖

```powershell
go get -u github.com/gin-gonic/gin
```

- 集成

```go
package main

import (
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	r.Run()
}
```

- 测试

![image-20210423235145488](https://gitee.com/mkii/md-image/raw/master/image-20210423235145488.png)

## 3. 配置文件







## 4. 日志配置

- 加上标准库的日志，要求能输出到控制台和文件

























