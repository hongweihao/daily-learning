package main

import (
	"context"
	"fmt"
	"sync/atomic"
	"time"
)

func main() {
	//ctx, cancel := context.WithCancel(context.Background())
	ctx, cancel := context.WithDeadline(context.Background(), time.Now().Add(5))
	var num int32
	total := 12
	for i := 0; i < total; i++ {
		go addNum(i, &num, func() {
			if atomic.LoadInt32(&num) == int32(total) {
				fmt.Println(i, "stop.......")
				cancel()
			}
		})
	}
	<-ctx.Done()
	fmt.Println(ctx.Err())
}

func addNum(id int, numP *int32, cancel func()) {
	fmt.Println(id, "start....")
	defer cancel()
	num := atomic.LoadInt32(numP)
	newNum := num + 1
	if atomic.CompareAndSwapInt32(numP, num, newNum) {
		fmt.Println(id, "cas", num, newNum)
	}
	fmt.Println(id, "end....")
}
