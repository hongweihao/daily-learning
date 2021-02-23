package main

import (
	"fmt"
	"sync/atomic"
)

func main() {
	//atomicInt32Functions()
	atomicUint32Functions()
}

func atomicInt32Functions() {
	var data int32
	atomic.AddInt32(&data, 1)
	fmt.Println("add", data)

	atomic.CompareAndSwapInt32(&data, 1, 5)
	fmt.Println("cas", data)

	load32 := atomic.LoadInt32(&data)
	fmt.Println("load", load32)

	atomic.StoreInt32(&data, 8)
	fmt.Println("store", data)

	atomic.SwapInt32(&data, 9)
	fmt.Println("swap", data)
}

func atomicUint32Functions() {
	var data uint32 = 5

	// 编译错误，constant -3 overflows uint32
	//var delta = uint32(int32(-3))

	// 绕过编译检查
	var delta = int32(-3)
	atomic.AddUint32(&data, uint32(delta))
	fmt.Println("add1", data)

	// 表示data - 2
	var delta2 int32 = 2 - 1
	// ^便是当前值delta2(1)与int32的最大值(2^32)做异或操作
	// 例如^unit8(1) => 00000001 ^ 11111111 = 11111110
	// 补码相同：
	atomic.AddUint32(&data, ^uint32(delta2))
	fmt.Println("add2", data)

	atomic.CompareAndSwapUint32(&data, 1, 5)
	fmt.Println("cas", data)

	load32 := atomic.LoadUint32(&data)
	fmt.Println("load", load32)

	atomic.StoreUint32(&data, 8)
	fmt.Println("store", data)

	atomic.SwapUint32(&data, 9)
	fmt.Println("swap", data)
}
