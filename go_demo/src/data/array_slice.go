package data

import "fmt"

func arraySlice() {
	// 声明数组
	var arr [2]int
	fmt.Println("arr -> len: ", len(arr), "cap: ", cap(arr), "values: ", arr)

	// 声明数组并赋值
	arr1 := [2]int{1, 2}
	fmt.Println("arr1 -> len: ", len(arr1), "cap: ", cap(arr1), "values: ", arr1)
	var arr2 = [2]int{3, 4}
	fmt.Println("arr2 -> len: ", len(arr2), "cap: ", cap(arr2), "values: ", arr2)

	// 查看元素
	fmt.Println("arr2[1] -> ", arr2[1])

	// 赋值
	arr2[1] = 5
	fmt.Println("arr2[1] -> ", arr2[1])

	// 遍历
	for index, value := range arr2 {
		fmt.Println("arr2 range -> index: ", index, "value: ", value)
	}

	// 切片声明，切片与数组声明方式非常相似，切片无需指定长度
	var slice []int
	fmt.Println("slice -> len: ", len(slice), "cap", cap(slice), "value： ", slice)

	// 切片初始化
	slice1 := make([]int, 0, 10)
	fmt.Println("slice1 -> len: ", len(slice1), "cap", cap(slice1), "value: ", slice1)
	// 从数组初始化，获取数组arr1索引从1开始后的元素
	slice2 := arr1[1:]
	fmt.Println("slice2 -> len: ", len(slice2), "cap", cap(slice2), "value: ", slice2)

	// 添加元素
	slice2 = append(slice2, 3)
	fmt.Println("slice2 -> len: ", len(slice2), "cap", cap(slice2), "value: ", slice2)

	// 修改元素
	slice2[0] = 3
	fmt.Println("slice2 -> len: ", len(slice2), "cap", cap(slice2), "value: ", slice2)

	// 遍历
	for index, value := range slice2 {
		fmt.Println("slice2 range -> index: ", index, "value: ", value)
	}

	// 删除元素，切片没有提供删除元素的方法，可以使用拼接的方式。删除下标为1的元素
	slice3 := append(slice2[:1], slice2[2:]...)
	fmt.Println("slice3 -> len: ", len(slice3), "cap", cap(slice3), "value: ", slice3)

	// 重置切片
	slice4 := slice3[0:0]
	fmt.Println("slice4 -> len: ", len(slice4), "cap", cap(slice4), "value: ", slice4)

	// 复制切片
	slice5 := make([]int, 3)
	copy(slice5, []int{1, 3, 5})
	fmt.Println("slice5 -> len: ", len(slice5), "cap", cap(slice5), "value: ", slice5)
}
