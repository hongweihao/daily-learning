// 实现 http.Handler 接口
// type Handler interface {
// 		ServeHTTP(ResponseWriter, *Request)
// }
package main

import (
	"net/http"
)

type MyHandler struct {
}

func (myhandler *MyHandler) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	switch r.URL.Path {
	case "/":
		rw.Write([]byte("Welcome"))
	case "/hello":
		rw.Write([]byte("Hello world"))
	default:
		rw.Write([]byte("unknown path:" + r.URL.Path))
	}
}

func main() {

	myHandler := new(MyHandler)
	http.ListenAndServe(":8080", myHandler)
}
