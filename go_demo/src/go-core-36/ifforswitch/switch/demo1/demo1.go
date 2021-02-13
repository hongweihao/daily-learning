package main

import "fmt"

func main() {
	slice := []int {0, 1, 1, 2}

	/*
	// case表达式不能相同
	switch slice[0] {
	case 0, 1:
		fmt.Println("01")
	case 0, 2:
		fmt.Println("02")
	}*/

	// 会运行第一个匹配的case
	switch 0{
	case slice[0], slice[1]:
		fmt.Println("01")
	case slice[2], slice[3]:
		fmt.Println("23")
	}
}
