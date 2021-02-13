package main

import "fmt"

type User struct {
	Name string
	Age int
}

func main() {
	user := &User{
		Name: "mkii",
		Age: 18,
	}

	var iuser interface{} = user
	switch user {
	case iuser.(*User):
		fmt.Println("111")
	}
}
