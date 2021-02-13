package main

import "fmt"

func main() {
	/*defer func() {
		fmt.Println("defer 1")
	}()

	defer func() {
		fmt.Println("defer 2")
	}()

	defer func() {
		fmt.Println("defer 3")
	}()*/
	loopDefer()
	fmt.Println("main end")
}

func loopDefer() {
	for i := 0; i < 10; i++ {
		defer func(no int) {
			fmt.Println("defer", no)
		}(i)
	}
	fmt.Println("loopDefer end")
}
