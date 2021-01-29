package main

import (
	"fmt"
	"testing"
)

// 编写高阶函数
// 高阶函数的特性：
// 1. 可以接收函数类型的参数
// 2. 可以返回函数类型的结果
func TestFun(t *testing.T) {
	result, err := compute(1, 2, add)
	if err != nil {
		t.Error(err)
		return
	}
	fmt.Println("result: ", result)
}

// 实现闭包
func TestCloseFun(t *testing.T) {
	compute := genComputeFun(add)
	result, err := compute(4, 5)
	if err != nil {
		t.Error(err)
		return
	}
	fmt.Println("result:", result)
}

// 测试传入的参数值后来怎么样了
func TestParameters(t *testing.T) {
	array := [10]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	slice := array[:]

	fmt.Println("source array: ", array)
	fmt.Println("source slice: ", slice)

	arrayParams(array)
	fmt.Println("source array modified: ", array)

	sliceParams(slice)
	fmt.Println("source slice modified: ", slice)
}
