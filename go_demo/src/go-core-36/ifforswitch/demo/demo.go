package main

import (
	"fmt"
)

func main() {
	nums := [...]int{1, 2, 3, 4, 5, 6} // array
	//nums := []int{1, 2, 3, 4, 5, 6} // slice
	maxIndex := len(nums) - 1

	for i, e := range nums {
		if i == maxIndex {
			nums[0] += e
		}else{
			nums[i + 1] +=e
		}
	}
	fmt.Println(nums)
}
