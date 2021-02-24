package main

import (
	"fmt"
	"time"
)

func main() {
	//var lock sync.Mutex
	count := 0
	for i := 0; i < 1000; i++ {
		go func() {
			//lock.Lock()
			//defer lock.Unlock()
			count++
		}()
	}
	time.Sleep(1 * time.Second)
	fmt.Println("count", count)
}
