package main

import (
	"fmt"
	"strings"
)

func main() {
	s := "mkii"
	b := []byte("hong")
	r := []rune("works")

	var builder strings.Builder

	builder.WriteString(s)
	builder.Write(b)

	for _, v := range b {
		builder.WriteByte(v)
	}

	for _, v := range r {
		builder.WriteRune(v)
	}
	fmt.Println(builder.String())

	builder.Reset()
	fmt.Println(builder.String())
}
