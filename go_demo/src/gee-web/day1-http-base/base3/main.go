// Gee 框架雏形
package main

import (
	"gee-web/day1-http-base/base3/gee"
	"net/http"
)

func main() {
	engin := gee.New()
	engin.GET("/", indexHandler)
	engin.GET("/hello", helloHandler)
	engin.POST("/post", postHandler)

	engin.Run(":8080")
}

func indexHandler(rw http.ResponseWriter, req *http.Request) {
	rw.Write([]byte("Welcome"))
}

func helloHandler(rw http.ResponseWriter, req *http.Request) {
	rw.Write([]byte("Hello world"))
}

func postHandler(rw http.ResponseWriter, req *http.Request) {
	rw.Write([]byte("This is a POST request"))
}
