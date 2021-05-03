package main

import "net/http"

func main() {
	http.HandleFunc("/", indexHandler)
	http.HandleFunc("/hello", helloHandler)

	http.ListenAndServe("127.0.0.1:8080", nil)

}

func indexHandler(rw http.ResponseWriter, r *http.Request) {
	rw.Write([]byte("Welcome"))
}

func helloHandler(rw http.ResponseWriter, r *http.Request) {
	rw.Write([]byte("Hello world"))
}
