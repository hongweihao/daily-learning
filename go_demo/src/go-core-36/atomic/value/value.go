package main

import (
	"fmt"
	"sync/atomic"
)

func main() {
	var v atomic.Value

	var a = 12
	v.Store(a)
	fmt.Println(v.Load()) // 12

	// 不该被复制
	copyV := v
	copyV.Store(24)
	fmt.Println(copyV.Load()) // 24
	fmt.Println(v.Load())     // 12 如果Value内存储的指针，这里为什么不是24？

	// v.Store(nil) // panic: sync/atomic: store of nil value into Value
	// v.Store("str") // panic: sync/atomic: store of inconsistently typed value into Value
}
