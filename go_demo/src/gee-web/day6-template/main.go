package main

import (
	"gee-web/day6-template/gee"
	"net/http"
)

func main() {
	r := gee.New()
	r.GET("/panic", func(c *gee.Context) {
		names := []string{"mkii"}
		// panic: out of range
		c.String(http.StatusOK, names[4])
	})

	r.Run(":8888")
}
