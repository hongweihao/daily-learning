package gee

import "strings"

type Router struct {
	routers map[string]HandleFunc
}

func NewRouter() *Router {
	return &Router{
		routers: make(map[string]HandleFunc),
	}
}

func (router *Router) addRouter(method, pattern string, handle HandleFunc) {
	key := strings.Join([]string{method, pattern}, "-")
	router.routers[key] = handle
}

func (router *Router) handle(c *Context) {
	key := strings.Join([]string{c.Method, c.Path}, "-")
	if handler, ok := router.routers[key]; ok {
		handler(c)
		return
	}
	c.Rw.Write([]byte("unknown path:" + c.Path))
}
