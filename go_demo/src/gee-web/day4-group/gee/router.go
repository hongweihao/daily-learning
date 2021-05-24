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
	tree := router.roots[c.Method]
	n, param := tree.Search(c.Path)
	if n == nil {
		c.Status(404)
		c.Rw.Write([]byte("unknown path:" + c.Path))
		return
	}

	key := strings.Join([]string{c.Method, n.Pattern}, "-")
	c.Param = param
	if handler, ok := router.handlers[key]; ok {
		handler(c)
		return
	}
}
