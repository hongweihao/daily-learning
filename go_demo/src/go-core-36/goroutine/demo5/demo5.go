package main

import (
	"fmt"
	"sync/atomic"
	"time"
)

func main() {
	var count int32 = 0
	var i int32 = 0
	for ; i < 10; i++ {
		fn := func(v int32) {
			fmt.Println(v)
		}
		// 改成参数传入，去除闭包，让每个goroutine能被标识
		go func(no int32, f func(v int32)) {
			for {
				if count == no {
					f(no)
					atomic.AddInt32(&count, 1)
				} else {
					time.Sleep(1 * time.Microsecond)
				}
			}
		}(i, fn)
	}

	time.Sleep(1 * time.Second)
}
