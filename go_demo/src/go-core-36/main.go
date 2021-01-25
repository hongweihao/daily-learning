package main

import (
	"flag"
	"fmt"
)

var name string
func init() {
	flag.StringVar(&name, "foo", "everyone", "the usage of mkii")
}
func main() {
	flag.Parse()
	fmt.Println("Hello ", name)
}
