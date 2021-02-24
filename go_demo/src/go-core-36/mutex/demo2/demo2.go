package main

import (
	"fmt"
	"math/rand"
	"sync"
)

var count int
var wg sync.WaitGroup

// var rw sync.RWMutex

func main() {
	for i := 0; i < 5; i++ {
		wg.Add(1)
		go write(i)
	}
	for i := 0; i < 5; i++ {
		wg.Add(1)
		go read(i)
	}
	wg.Wait()
}

func read(i int) {
	// rw.RLock()
	// defer rw.RUnlock()
	fmt.Printf("read(%d) start....\n", i)
	v := count
	fmt.Printf("read(%d): %d end.......\n", i, v)
	wg.Done()
}

func write(i int) {
	// rw.Lock()
	// defer rw.Unlock()
	fmt.Printf("write(%d) start....\n", i)
	v := rand.Intn(1000)
	count = v
	fmt.Printf("write(%d): %d end.......\n", i, count)
	wg.Done()
}
