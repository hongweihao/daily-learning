package main

import (
	"gee-web/day3-router/gee"
	"net/http"
)

func main() {
	engine := gee.New()
	engine.GET("/", indexHandler)
	engine.GET("/hello", helloHandler)
	engine.POST("/post", postHandler)
	engine.POST("/p/:lang/doc", paramHandler)
	engine.GET("/static/*filepath", filePathHandler)
	engine.Run(":8080")
}

func indexHandler(c *gee.Context) {
	c.String(http.StatusOK, "Welcome")
}

func helloHandler(c *gee.Context) {
	c.String(http.StatusOK, "Hello world")
}

func postHandler(c *gee.Context) {
	c.String(http.StatusOK, "This is a post request")
}

func paramHandler(c *gee.Context) {
	lang, ok := c.ParamGet("lang")
	if !ok {
		c.String(http.StatusOK, "Cannot read lang")
		return
	}
	c.String(http.StatusOK, "Get lang("+lang+") from "+c.Path)
}

func filePathHandler(c *gee.Context) {
	filepath, ok := c.ParamGet("filepath")
	if !ok {
		c.String(http.StatusOK, "Cannot read filepath")
		return
	}

	c.String(http.StatusOK, "Read filepath("+filepath+") from "+c.Path)
}
