package main

import "fmt"

//// 变量重声明
//var block = "package"
//func main()  {
//	var block = "main"
//	{
//		var block = "inner"
//		fmt.Println(block)
//	}
//
//	fmt.Println(block)
//}

func main() {
	slice := make([]int, 0)
	slice = append(slice, 1)

	value, ok := interface{}(slice).([]int)
	fmt.Println("value", value, "ok", ok)
}