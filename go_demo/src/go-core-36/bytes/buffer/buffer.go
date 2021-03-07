package main

import (
	"bytes"
	"fmt"
)

func main() {
	var buffer bytes.Buffer
	buffer.WriteString("mkii hong")

	read := make([]byte, 4)
	buffer.Read(read)

	// 返回值是容器内未被读取的内容长度，而不是存储内容长度
	fmt.Println(buffer.Len())
	fmt.Println(buffer.Cap())
}
