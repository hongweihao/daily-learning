package main

import (
	"errors"
	"fmt"
)

// 声明operate的类型为函数类型
type operate func(x, y int) int

func add(x, y int) int {
	return x + y
}

// 实现一个函数，完成对2个数值得操作，具体的操作过程由调用方提供
func compute(x, y int, op operate) (int, error) {
	if op == nil {
		return 0, errors.New("operate is invalid")
	}
	return op(x, y), nil
}

type computeFun func(x, y int) (int, error)

func genComputeFun(op operate) computeFun {
	return func(x, y int) (int, error) {
		if op == nil {
			return 0, errors.New("op is invalid")
		}
		return op(x,y), nil
	}
}

func arrayParams(array [10]int)  {
	fmt.Println("array parameters:", array)
	array[0] = 888
	fmt.Println("after updating:", array)
}

func sliceParams(slice []int) {
	fmt.Println("slice parameters:", slice)
	slice[0] = 999
	fmt.Println("after updating:", slice)
}
