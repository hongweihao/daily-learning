package main

import "fmt"

func main() {
	value := []int8{0, 1, 2, 3, 4, 5, 6}
	/*
		//  编译错误
		// 1+3的结果是一个无类型常量，会默认转成int类型，int类型与int8不能坐判断操作
		switch 1 + 3 {
		case value[0], value[1]:
			fmt.Println("0 or 1")
		case value[2], value[3]:
			fmt.Println("2 or 3")
		case value[4], value[5], value[6]:
			fmt.Println("4 or 5 or 6")
		}*/

	// case中的0，1是无类型常量，会转成需要的int8类型
	switch value[2] {
	case 0, 1:
		fmt.Println("0, 1")
	case 2, 3:
		fmt.Println("2, 3")
	case 4, 5, 6:
		fmt.Println("4,5,6")
	}
}
