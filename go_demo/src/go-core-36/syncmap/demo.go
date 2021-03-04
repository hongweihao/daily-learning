package main

import (
	"fmt"
	"reflect"
)

func main() {

	intStrSyncMap := NewIntStrSyncMap()
	intStrSyncMap.Store(1, "111")
	intStrSyncMap.Store(2, "222")

	v1, _ := intStrSyncMap.Load(1)
	v2, _ := intStrSyncMap.Load(2)

	fmt.Println("1", v1)
	fmt.Println("2", v2)

	concurrentMap := NewConcurrentMap(reflect.TypeOf(0), reflect.TypeOf(""))
	concurrentMap.Store(3, "333")
	concurrentMap.Store(4, "444")

	v3, _ := concurrentMap.Load(3)
	v4, _ := concurrentMap.Load(4)

	fmt.Println("3", v3)
	fmt.Println("4", v4)
}
