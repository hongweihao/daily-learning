package main

import "fmt"

func main() {
	var srcInt = int16(-255)
	fmt.Println(srcInt)

	dstInt := int8(srcInt)
	fmt.Println(dstInt)

	fmt.Println(string(-1))
}
