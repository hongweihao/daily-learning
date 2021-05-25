// gee 框架核心实现
package gee

import (
	"net/http"
)

type HandleFunc func(c *Context)

type (
	Engine struct {
		*RouterGroup
		routers *Router
	}
	RouterGroup struct {
		prefix     string
		middleware []HandleFunc
		engine     *Engine
	}
)

func New() *Engine {
	// 循环指针: Engine <-> RouterGroup
	engine := new(Engine)
	engine.routers = NewRouter()

	group := new(RouterGroup)
	group.engine = engine

	engine.RouterGroup = group
	return engine
}

func (engine *Engine) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	c := NewContext(rw, r)
	engine.routers.handle(c)
}
func (engine *Engine) Run(addr string) error {
	return http.ListenAndServe(addr, engine)
}

func (routerGroup *RouterGroup) GET(pattern string, handle HandleFunc) {
	routerGroup.addRouter(http.MethodGet, pattern, handle)
}
func (routerGroup *RouterGroup) POST(pattern string, handle HandleFunc) {
	routerGroup.addRouter(http.MethodPost, pattern, handle)
}
func (routerGroup *RouterGroup) addRouter(method string, pattern string, handle HandleFunc) {
	routerGroup.engine.routers.addRouter(method, routerGroup.prefix+pattern, handle)
}

func (routerGroup *RouterGroup) Group(prefix string) *RouterGroup {
	engine := routerGroup.engine
	group := new(RouterGroup)
	group.prefix += prefix
	group.engine = engine
	return group
}
