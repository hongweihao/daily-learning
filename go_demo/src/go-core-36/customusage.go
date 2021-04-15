package main

import (
	"flag"
	"fmt"
	"os"
)

var foo string

func init() {
	flag.StringVar(&foo, "foo", "everyone", "usage")
	flag.Usage = func() {
		fmt.Fprintf(os.Stderr, "this is a custom usage %s:\n", "question")
		flag.PrintDefaults()
	}
}

// func main() {
// 	flag.Parse()
// 	fmt.Println("foo: ", foo)
// }
