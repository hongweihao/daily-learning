package gee

import (
	"strings"
)

type Router struct {
	roots    map[string]*Trie
	handlers map[string]HandleFunc
}

func NewRouter() *Router {
	return &Router{
		roots:    make(map[string]*Trie),
		handlers: make(map[string]HandleFunc),
	}
}

func (router *Router) addRouter(method, pattern string, handle HandleFunc) {
	tree := router.roots[method]
	if tree == nil {
		root := NewTrie()
		router.roots[method] = root
		tree = root
	}
	tree.Insert(pattern)

	key := strings.Join([]string{method, pattern}, "-")
	router.handlers[key] = handle
}

func (router *Router) handle(c *Context) {
	key := strings.Join([]string{c.Method, c.Path}, "-")
	if handler, ok := router.routers[key]; ok {
		handler(c)
		return
	}
	c.Rw.Write([]byte("unknown path:" + c.Path))
}
