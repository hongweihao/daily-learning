# go-kit-demo

## 分层架构

### 1. transport

### 2. endpoint

### 3. service 



## 构建步骤

### 1. 创建 project 目录

创建一个目录 `go-kit-demo`



### 2. init project

使用 go mod 初始化一个 project

```shell
cd ...../go-kit-demo
go mod init
```

初始化成功会生成一个文件：`go.mod`

```
module go-kit-demo

go 1.15
```



### 3. import 依赖

```shell
# go-kit 
go get github.com/go-kit/kit

# 一个名叫 mux 的路由组件
go get github.com/gorilla/mux
```



### 4. 创建分层的包目录

1. transport

2. endpoint

3. service 
4. common：定义通用对象

### 5. 写 service 层代码

```go
// service 包的职责
// 1. 业务模块
package service

import (
	"context"
	"errors"
)

var (
	ErrorLogin    = errors.New("failed to login")
	ErrorRegister = errors.New("failed to register")
)

type UserInfo struct {
	Id       uint64
	UserName string
	Password string
}

type IUserService interface {
	Register(ctx context.Context, service *UserInfo) error
	Login(ctx context.Context, userName, password string) error
}

type UserService struct {
	//UserDao *dao.UserDao
}

func NewUserService( /*userDao *dao.UserDao*/ ) *UserService {
	return &UserService{ /*UserDao: userDao*/ }
}

func (userService *UserService) Register(ctx context.Context, service *UserInfo) error {
	if service != nil {
		return nil
	}
	return ErrorRegister
}

func (userService *UserService) Login(ctx context.Context, userName, password string) error {
	if userName == "mkii" && password == "1234" {
		return nil
	}
	return ErrorLogin
}
```



### 6. 写 endpoint 层代码

```go
// endpoint 包的职责
// 1. 定义方法级的 endpoint入口。 例如注册和登录是2个endpoint
// 2. 定义 request 的数据结构
// 3. 定义 response 的数据结构
// 4. 利用装饰器做其他的功能。例如，限流rate limit
package endpoint

import (
   "context"
   "github.com/go-kit/kit/endpoint"
   "go-kit-demo/common"
   "go-kit-demo/service"
)

type UserEndpoints struct {
   RegisterEndPoint endpoint.Endpoint
   LoginEndPoint    endpoint.Endpoint
}

type UserReq struct {
   UserName string `json:"user_name"`
   Password string `json:"password"`
}

type UserRsp struct {
   common.Rsp
}

// 如果使用这种方式会有 copy value
/*func NewUserEndpoints(userService service.IUserService) UserEndpoints{
   return UserEndpoints{
      RegisterEndPoint: MakeLoginEndpoint(userService),
      LoginEndPoint:    MakeLoginEndpoint(userService),
   }
}*/

func MakeRegisterEndpoint(userService service.IUserService) endpoint.Endpoint {
   return func(ctx context.Context, request interface{}) (interface{}, error) {
      req := request.(*UserReq)

      userInfo := &service.UserInfo{
         UserName: req.UserName,
         Password: req.Password,
      }

      if err := userService.Register(ctx, userInfo); err != nil {
         return nil, err
      }
      return common.NewRspOk(), nil
   }
}

func MakeLoginEndpoint(userService service.IUserService) endpoint.Endpoint {
   return func(ctx context.Context, request interface{}) (interface{}, error) {
      req := request.(*UserReq)
      if err := userService.Login(ctx, req.UserName, req.Password); err != nil {
         return nil, err
      }
      return common.NewRspOk(), nil
   }
}
```



### 7. 写 transport 层代码

```go
// transport 包的职责：
// 1. 对外选择一种协议暴露接口，例如：http或者rpc
// 2. 定义接口 request 的反序列化方法
// 3. 定义接口的 response 的序列化方法
package transport

import (
	"context"
	"encoding/json"
	"errors"
	"github.com/gorilla/mux"
	"go-kit-demo/common"
	"go-kit-demo/endpoint"
	"net/http"

	kitHttp "github.com/go-kit/kit/transport/http"
)

var (
	//ErrorInvalidParams = errors.New("invalid parameters")
	ErrorUserName = errors.New("invalid user_name")
	ErrorPassword = errors.New("invalid password")
)

func MakeHttpHandler(ctx context.Context, endpoint endpoint.UserEndpoints) http.Handler {
	r := mux.NewRouter()

	r.Methods("POST").Path("/register").Handler(kitHttp.NewServer(
		endpoint.RegisterEndPoint,
		decodeUserReq,
		encodeRsp))

	r.Methods("POST").Path("/login").Handler(kitHttp.NewServer(
		endpoint.LoginEndPoint,
		decodeUserReq,
		encodeRsp))

	return r
}

func decodeUserReq(ctx context.Context, req *http.Request) (interface{}, error) {
	if err := req.ParseForm(); err != nil {
		return nil, err
	}
	userName := req.FormValue("user_name")
	password := req.FormValue("password")

	if userName == "" {
		return nil, ErrorUserName
	}
	if password == "" {
		return nil, ErrorPassword
	}

	userReq := &endpoint.UserReq{
		UserName: userName,
		Password: password,
	}
	return userReq, nil
}

func encodeRsp(ctx context.Context, rw http.ResponseWriter, rsp interface{}) error {
	userRsp := rsp.(*common.Rsp)

	/*bytes, err := userRsp.RspToJson()
	if err != nil {
		return err
	}

	rw.WriteHeader(200)
	rw.Write(bytes)

	return nil*/

	return json.NewEncoder(rw).Encode(userRsp.M)
}
```



### 8. 在main 入口启动服务

```go
package main

import (
   "context"
   "fmt"
   "go-kit-demo/endpoint"
   "go-kit-demo/service"
   "go-kit-demo/transport"
   "log"
   "net/http"
   "os"
   "os/signal"
   "syscall"
)

func main() {
   ctx := context.Background()

   errChan := make(chan error)

   userService := service.NewUserService()

   userEndpoints := endpoint.UserEndpoints{
      RegisterEndPoint: endpoint.MakeRegisterEndpoint(userService),
      LoginEndPoint:    endpoint.MakeLoginEndpoint(userService),
   }

   handler := transport.MakeHttpHandler(ctx, userEndpoints)

   go func() {
      errChan <- http.ListenAndServe(":8099", handler)
   }()
   go func() {
      // 监控系统信号，等待 ctrl + c 系统信号通知服务关闭
      c := make(chan os.Signal, 1)
      signal.Notify(c, syscall.SIGINT, syscall.SIGTERM)
      errChan <- fmt.Errorf("%s", <-c)
   }()
   error := <-errChan
   log.Println(error)
}
```



### 9. 启动服务并测试

启动服务：`go run main.go`

注意是表单提交：

![image-20210402174135046](https://gitee.com/mkii/md-image/raw/master/image-20210402174135046.png)