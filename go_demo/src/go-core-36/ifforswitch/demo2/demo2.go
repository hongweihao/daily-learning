package main

import "fmt"

func main() {
	nums := [6]int{1, 2, 3, 4, 5, 6}
	maxIndex := len(nums) - 1
	for i, e := range nums {
		if i == maxIndex {
			nums[0] += e
		} else {
			nums[i+1] += e
		}
	}
	fmt.Println(nums) // [7 3 5 7 9 11]
}
