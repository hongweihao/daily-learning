package container

import (
	"container/list"
	"fmt"
	"testing"
)

func TestList(t *testing.T) {
	// 初始化一个list对象
	//l := list.New()

	// 开箱即用，延迟初始化
	var l1 list.List
	l := &l1

	// 在表头插入元素
	l.PushFront(1)

	// 表尾插入元素
	e3 := l.PushBack(3)
	l.PushBack(4)

	// 在e3前插入元素
	e2 := l.InsertBefore(2, e3)

	// 在表头插入一个链表
	l.PushFrontList(l)

	// 将e2移动到e3后面
	l.MoveAfter(e2, e3)

	// 遍历链表元素
	for e := l.Front(); e != nil; e = e.Next(){
		fmt.Println(e.Value)
	}
}

