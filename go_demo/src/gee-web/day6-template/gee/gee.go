// gee 框架核心实现
package gee

import (
	"log"
	"net/http"
	"strings"
)

type HandleFunc func(c *Context)

type (
	Engine struct {
		*RouterGroup
		routers *Router
		groups  []*RouterGroup
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
	engine.groups = make([]*RouterGroup, 0)

	group := new(RouterGroup)
	group.engine = engine

	engine.RouterGroup = group
	engine.groups = append(engine.groups, group)
	return engine
}

func (engine *Engine) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	// 将当前请求匹配的middleware加入context对象中
	middlewares := make([]HandleFunc, 0)
	for _, group := range engine.groups {
		if strings.HasPrefix(r.URL.Path, group.prefix) {
			middlewares = append(middlewares, group.middleware...)
		}
	}

	c := NewContext(rw, r, middlewares)
	engine.routers.handle(c)
}
func (engine *Engine) Run(addr string) error {
	log.Println("[Gee Web] Server listening at " + addr)
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

	engine.groups = append(engine.groups, group)
	return group
}

func (routerGroup *RouterGroup) Use(middleware HandleFunc) {
	if routerGroup.middleware == nil {
		routerGroup.middleware = make([]HandleFunc, 0)
	}

	routerGroup.middleware = append(routerGroup.middleware, middleware)
}
