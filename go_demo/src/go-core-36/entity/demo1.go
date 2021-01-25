package main

import (
	"fmt"
	"io"
	"os"
)

func mai1n()  {
	var err error
	n, err := io.WriteString(os.Stdout, "Hello everyone!\n")

	fmt.Println(n, err)
}
