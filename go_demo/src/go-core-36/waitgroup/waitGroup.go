package main

import (
	"fmt"
	"sync"
)

func main() {
	// useChannel()
	useWaitGroup()
}

func useChannel() {
	c := make(chan struct{})

	go func() {
		fmt.Println("func 1")
		c <- struct{}{}
	}()

	go func() {
		fmt.Println("func 2")
		c <- struct{}{}
	}()

	<-c
	<-c
}

func useWaitGroup() {
	var wg sync.WaitGroup
	wg.Add(2)

	go func() {
		fmt.Println("func 1")
		wg.Done()
	}()

	go func() {
		fmt.Println("func 2")
		wg.Done()
	}()

	wg.Wait()
}
