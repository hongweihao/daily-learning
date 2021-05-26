package main

import (
	"gee-web/day5-middleware/gee"
	"log"
	"net/http"
	"time"
)

func main() {
	r := gee.New()

	r.Use(Logger())

	r.GET("/index", func(c *gee.Context) {
		c.HTML(http.StatusOK, "<h1>Index Page</h1>")
	})
	v1 := r.Group("/v1")
	{
		v1.GET("/", func(c *gee.Context) {
			c.HTML(http.StatusOK, "<h1>Hello Gee</h1>")
		})

		v1.GET("/hello", func(c *gee.Context) {
			c.String(http.StatusOK, "hello "+c.Query("name")+"  "+c.Path)
		})
	}
	v1.Use(LoggerV1())
	v2 := r.Group("/v2")
	{
		v2.GET("/hello/:name", func(c *gee.Context) {
			c.String(http.StatusOK, "hello "+c.Param["name"]+", you're at "+c.Path)
		})
		v2.POST("/login", func(c *gee.Context) {
			c.JSON(http.StatusOK, gee.H{
				"username": c.PostForm("username"),
				"password": c.PostForm("password"),
			})
		})

	}
	r.Run(":8888")
}

func Logger() gee.HandleFunc {
	return func(c *gee.Context) {
		// Start timer
		t := time.Now()
		// Process request
		c.Next()
		// Calculate resolution time
		log.Printf("Logger for all: [%d] %s in %v", c.StatusCode, c.Req.RequestURI, time.Since(t))
	}
}

func LoggerV1() gee.HandleFunc {
	return func(c *gee.Context) {
		// Start timer
		t := time.Now()
		// Process request
		c.Next()
		// Calculate resolution time
		log.Printf("Logger for v1 group: [%d] %s in %v", c.StatusCode, c.Req.RequestURI, time.Since(t))
	}
}
