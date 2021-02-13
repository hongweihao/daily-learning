package main

import "fmt"

func main() {
	fmt.Println("main enter........")
	testPanic("my err msg")
	fmt.Println("testPanic exit........")
}

func testPanic(errMsg string)  {
	fmt.Println("testPanic enter........")
	panic(errMsg)
	fmt.Println("testPanic exit........")
}
