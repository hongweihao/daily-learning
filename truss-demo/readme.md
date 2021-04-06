# 使用 `truss` 工具生成 `go-kit` 框架代码

## 1. 安装 `protobuf` 工具

https://github.com/protocolbuffers/protobuf/releases

下载解压并将解压后的 bin 目录加入 path 环境变量



## 2. 安装 truss 工具

Github：https://github.com/metaverse/truss

1. 下载包

``` powershell
go get -u -d github.com/metaverse/truss
```

>  -u 和 -d表示什么可以看：https://go-zh.org/cmd/go/
>
> ![image-20210406132447126](https://gitee.com/mkii/md-image/raw/master/image-20210406132447126.png)



2. 编译出二进制文件

```powershell
# 我使用的是 windows
cd %GOPATH%/src/github.com/metaverse/truss
wininstall.bat
```



3. 设置环境变量

执行 wininstall.bat 之后会在 %GOPATH%下创建一个 bin 目录。需要将此目录加入 PATH 环境变量

![image-20210406133700525](https://gitee.com/mkii/md-image/raw/master/image-20210406133700525.png)



## 3. 使用 truss 工具生成一个 project

1. 新一个目录 truss-demo 作为 project 的目录

2. 新建目录 proto，并在上一步下载的 truss 目录中找到示例用的 proto 文件

![image-20210406133243975](https://gitee.com/mkii/md-image/raw/master/image-20210406133243975.png)

3. 使用 `gomod` 初始化 project

```powershell
go mod init truss-demo
```



4. 使用 truss生成代码，并放入到 truss-demo 目录下

```powershell
cd proto
truss echo.proto --svcout=..
```



5. 生成模板代码之后，修改 `handlers.go`

![image-20210406134024192](https://gitee.com/mkii/md-image/raw/master/image-20210406134024192.png)



6. 启动服务

```powershell
cd truss-demo/cmd/echo
go run main.go
```

![image-20210406134216930](https://gitee.com/mkii/md-image/raw/master/image-20210406134216930.png)



7. 测试接口

![image-20210406134259922](https://gitee.com/mkii/md-image/raw/master/image-20210406134259922.png)

