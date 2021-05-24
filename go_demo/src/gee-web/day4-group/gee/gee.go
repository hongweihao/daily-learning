// gee 框架核心实现
package gee

import (
	"net/http"
)

type HandleFunc func(c *Context)

type Engine struct {
	routers *Router
}

func New() *Engine {
	return &Engine{
		routers: NewRouter(),
	}
}

func (engine *Engine) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	c := NewContext(rw, r)
	engine.routers.handle(c)
}

func (engine *Engine) GET(pattern string, handle HandleFunc) {
	engine.routers.addRouter(http.MethodGet, pattern, handle)
}
func (engine *Engine) POST(pattern string, handle HandleFunc) {
	engine.routers.addRouter(http.MethodPost, pattern, handle)
}
func (engine *Engine) Run(addr string) {
	http.ListenAndServe(addr, engine)
}

func (engine *Engine) Group(prefix string) *RouterGroup {
	group := new(RouterGroup)
	group.prefix = prefix
	group.engine = engine
	return group
}

type RouterGroup struct {
	prefix     string
	middleware []HandleFunc
	//parent *RouterGroup
	engine *Engine
}

func (rg *RouterGroup) GET(pattern string, handle HandleFunc) {
	rg.engine.routers.addRouter(http.MethodGet, rg.prefix+pattern, handle)
}
func (rg *RouterGroup) POST(pattern string, handle HandleFunc) {
	rg.engine.routers.addRouter(http.MethodPost, rg.prefix+pattern, handle)
}
