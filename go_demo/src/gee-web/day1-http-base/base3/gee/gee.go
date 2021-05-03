// gee 框架核心实现
package gee

import (
	"net/http"
	"strings"
)

type HandleFunc func(rw http.ResponseWriter, req *http.Request)

type Engin struct {
	routers map[string]HandleFunc
}

func New() *Engin {
	return &Engin{
		routers: make(map[string]HandleFunc, 0),
	}
}

func (engin *Engin) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	key := strings.Join([]string{r.Method, r.URL.Path}, "-")
	if handler, ok := engin.routers[key]; ok {
		handler(rw, r)
		return
	}
	rw.Write([]byte("unknown path:" + r.URL.Path))
}

func (engin *Engin) addRouter(method, pattern string, handle HandleFunc) {
	key := strings.Join([]string{method, pattern}, "-")
	engin.routers[key] = handle
}

func (engin *Engin) GET(pattern string, handle HandleFunc) {
	engin.addRouter(http.MethodGet, pattern, handle)
}
func (engin *Engin) POST(pattern string, handle HandleFunc) {
	engin.addRouter(http.MethodPost, pattern, handle)
}
func (engin *Engin) Run(addr string) {
	http.ListenAndServe(addr, engin)
}
