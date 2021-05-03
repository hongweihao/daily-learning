package main

import (
	"context"
	"io"
	"log"
	"net/http"
	"os"
	"os/signal"
	"time"

	"github.com/gin-gonic/gin"
)

func main() {
	log.Println("server is starting")
	initLogger()
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		log.Println("ping -> pong")
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	srv := &http.Server{
		Addr:    ":8080",
		Handler: r,
	}

	go func() {
		// 服务连接
		if err := srv.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Fatalf("listen: %s\n", err)
		}
	}()

	// 等待中断信号以优雅地关闭服务器（设置 5 秒的超时时间）
	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt)
	<-quit
	log.Println("Shutdown Server ...")

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := srv.Shutdown(ctx); err != nil {
		log.Fatal("Server Shutdown:", err)
	}
	log.Println("Server exiting")
}

func initLogger() {
	// 禁用控制台颜色，将日志写入文件时不需要控制台颜色。
	gin.DisableConsoleColor()

	logFile := "gin-demo.log"
	f, err := os.Create(logFile)
	if err != nil {
		log.Fatalln("Failed to open log file")
		os.Exit(-1)
	}

	gin.DefaultWriter = io.MultiWriter(f, os.Stdout)
}
