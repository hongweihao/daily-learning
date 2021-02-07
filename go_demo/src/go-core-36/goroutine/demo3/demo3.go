package main

import "fmt"

func main() {
	const num = 10
	wait := make(chan struct{}, num)

	for i := 0; i < num; i++ {
		go func() {
			fmt.Println(i)
			wait <- struct{}{}
		}()
	}

	for i := 0; i < num; i++ {
		<-wait
	}
}
