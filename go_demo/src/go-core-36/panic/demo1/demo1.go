package main

import "fmt"

func main() {
	defer func() {
		err:= recover()
		if err != nil {
			fmt.Println("catch panic")
		}
	}()

	panic("my panic msg")
}
