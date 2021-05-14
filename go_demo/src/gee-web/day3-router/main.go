package main

import "gee-web/day3-router/gee"

func main() {
	engine := gee.New()
	engine.GET("/", indexHandler)
	engine.GET("/hello", helloHandler)
	engine.POST("/post", postHandler)
	engine.POST("/p/:lang/doc", paramHandler)
	//engine.GET("*", allHandler)

	engine.Run(":8080")
}

func allHandler(c *gee.Context) {
	c.Rw.Write([]byte("All Get here"))
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

func paramHandler(c *gee.Context) {
	lang, ok := c.ParamGet("lang")
	if !ok {
		c.Rw.Write([]byte("cannot read lang"))
		return
	}

	c.Rw.Write([]byte("read lang(" + lang + ") from " + c.Path))
}
