package main

import (
	"fmt"
	"strings"
)

func main() {
	reader := strings.NewReader("mkii hong")
	b := make([]byte, 4)

	read, err := reader.Read(b)
	fmt.Println(read, err, string(b))
}
