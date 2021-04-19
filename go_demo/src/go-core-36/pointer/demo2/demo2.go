package main

import (
	"fmt"
	"unsafe"
)

type Student struct {
	Id   int
	Name string
	Age  int
}

// 使用unsafe.Pointer 操作对象
func main() {

	s := new(Student)
	fmt.Printf("s: %p, %v", s, s)

	// Student对象的指针就是首个field的指针
	idp := (*int)(unsafe.Pointer(uintptr(unsafe.Pointer(s))))
	*idp = 1002

	// 通过偏移量计算第二个field的指针
	namePtr := (*string)(unsafe.Pointer(uintptr(unsafe.Pointer(s)) + unsafe.Offsetof(s.Name)))
	*namePtr = "MKII"

	fmt.Println("s2:", s)
}
