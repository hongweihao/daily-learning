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
