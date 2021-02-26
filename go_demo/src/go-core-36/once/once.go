package main

import (
	"fmt"
	"sync"
)

func main() {
	var wg sync.WaitGroup
	var once sync.Once

	wg.Add(10)
	for i := 0; i < 10; i++ {
		go func(n int) {
			fmt.Println("goroutine", n)
			once.Do(func() {
				fmt.Println("once...")
			})
			wg.Done()
		}(i)
	}

	wg.Wait()
}
