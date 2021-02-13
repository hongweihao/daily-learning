package main

import "fmt"

func main() {
	if i := 1 + 2; i == 3 {
		fmt.Println("i= ", i)
	}

	// 编译错误
	//fmt.Println("outside i=", i)
}
