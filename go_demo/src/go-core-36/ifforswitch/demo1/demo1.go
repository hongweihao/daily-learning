package main

import "fmt"

func main() {
	nums := []int{1, 2, 3, 4, 5, 6}
	for i := range nums {
		if i == 3 {
			nums[i] |= i // 4 | 3  ==> 100 | 011 = 111 = 7
		}
	}
	fmt.Println(nums) // [1 2 3 7 5 6]
}
