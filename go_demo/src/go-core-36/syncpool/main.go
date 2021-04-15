package main

import (
	"fmt"
	"sync"
)

func NewByte() interface{} {
	return make([]byte, 1024)
}

func main() {
	pool := &sync.Pool{New: NewByte}

	bytes := pool.Get().([]byte)
	fmt.Println(len(bytes), cap(bytes))

	pool.Put(bytes)
}
