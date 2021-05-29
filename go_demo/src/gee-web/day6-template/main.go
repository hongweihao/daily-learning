package main

import (
	"fmt"
	"gee-web/day6-template/gee"
	"html/template"
	"log"
	"net/http"
	"time"
)

type student struct {
	Name string
	Age  int8
}

func FormatAsDate(t time.Time) string {
	year, month, day := t.Date()
	return fmt.Sprintf("%d-%02d-%02d", year, month, day)
}

func main() {
	r := gee.New()
	r.Use(Logger())

	r.SetFuncMap(template.FuncMap{
		"FormatAsDate": FormatAsDate,
	})

	// 加载模板文件
	r.LoadHTMLGlob("templates/*")
	// 加载静态文件
	r.Static("/assets", "./static")

	r.GET("/", func(c *gee.Context) {
		c.HTML(http.StatusOK, "index.tmpl", nil)
	})

	//stu1 := &student{Name: "Geektutu", Age: 20}
	//stu2 := &student{Name: "Jack", Age: 22}
	//r.GET("/students", func(c *gee.Context) {
	//	c.HTML(http.StatusOK, "arr.tmpl", gee.H{
	//		"title":  "gee",
	//		"stuArr": [2]*student{stu1, stu2},
	//	})
	//})
	//
	//r.GET("/date", func(c *gee.Context) {
	//	c.HTML(http.StatusOK, "custom_func.tmpl", gee.H{
	//		"title": "gee",
	//		"now":   time.Date(2019, 8, 17, 0, 0, 0, 0, time.UTC),
	//	})
	//})

	r.Run(":8888")
}

func Logger() gee.HandleFunc {
	return func(c *gee.Context) {
		// Start timer
		t := time.Now()
		// Process request
		c.Next()
		// Calculate resolution time
		log.Printf("[Gee Web] Logger for all: [%d] %s in %v", c.StatusCode, c.Req.RequestURI, time.Since(t))
	}
}
