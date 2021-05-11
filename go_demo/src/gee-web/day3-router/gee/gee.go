// gee 框架核心实现
package gee

import (
	"net/http"
)

type HandleFunc func(c *Context)

type Engin struct {
	routers *Router
}

func New() *Engin {
	return &Engin{
		routers: NewRouter(),
	}
}

func (engin *Engin) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	c := NewContext(rw, r)
	engin.routers.handle(c)
}

func (engin *Engin) GET(pattern string, handle HandleFunc) {
	engin.routers.addRouter(http.MethodGet, pattern, handle)
}
func (engin *Engin) POST(pattern string, handle HandleFunc) {
	engin.routers.addRouter(http.MethodPost, pattern, handle)
}
func (engin *Engin) Run(addr string) {
	http.ListenAndServe(addr, engin)
}
