package main

import (
	"context"
	"fmt"
	"time"
)

func main() {
	ctx := context.WithValue(context.Background(), "name", "mkii")
	ctx2, cancel := context.WithCancel(ctx)

	go func() {
		fmt.Println("start...")
		time.Sleep(time.Second)
		cancel()
		fmt.Println("end....")
	}()

	<-ctx2.Done()
	fmt.Println("name", ctx2.Value("name"))
}
