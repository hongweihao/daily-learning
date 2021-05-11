package main

import "gee-web/day3-router/gee"

func main() {
	engin := gee.New()
	engin.GET("/", indexHandler)
	engin.GET("/hello", helloHandler)
	engin.POST("/post", postHandler)

	engin.Run(":8080")
}

func indexHandler(c *gee.Context) {
	c.Rw.Write([]byte("Welcome"))
}

func helloHandler(c *gee.Context) {
	c.Rw.Write([]byte("Hello world"))
}

func postHandler(c *gee.Context) {
	c.Rw.Write([]byte("This is a POST request"))
}
