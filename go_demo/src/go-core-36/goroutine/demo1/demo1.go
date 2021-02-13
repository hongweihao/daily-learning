package main

import (
	"fmt"
	"time"
)

func main() {

	for i:= 0; i < 10; i++{
		/*go func() {
			fmt.Println(i)
		}()*/
		go print(i)
	}
	time.Sleep(1*time.Second)
}

func print(i int)  {
	fmt.Println(i)
}
