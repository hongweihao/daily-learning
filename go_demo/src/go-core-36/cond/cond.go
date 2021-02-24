package main

import (
	"fmt"
	"sync"
	"time"
)

// 条件变量与互斥锁配合使用
var (
	wg       sync.WaitGroup
	data     uint8
	lock     sync.RWMutex
	sendCond = sync.NewCond(&lock)
	recvCond = sync.NewCond(lock.RLocker())
)

func main() {
	MutexAndCond()
}

func MutexAndCond() {
	wg.Add(2)
	go send(0)
	// 仅适用于一个接收者的情况，因为接收者拿了读锁却修改了数据
	go recv(1)
	wg.Wait()
}

// 从信箱里收情报
func recv(i int) {
	fmt.Printf("receiver(%d) start\n", i)

	lock.RLock()
	for 0 == data {
		recvCond.Wait()
		fmt.Printf("receiver(%d) wait\n", i)
	}

	data = 0
	lock.RUnlock()
	sendCond.Broadcast()

	fmt.Printf("receiver(%d) end\n", i)
	wg.Done()
}

// 放情报进信箱
func send(i int) {
	time.Sleep(100 * time.Millisecond)
	fmt.Printf("sender(%d) start\n", i)

	lock.Lock()
	// 如果信箱里面有情报就等待通知
	for 1 == data {
		// 放情报的人在等情报被取走
		sendCond.Wait()
		fmt.Printf("sender(%d) wait\n", i)
	}

	// 放好情报了，通知给别人
	data = 1
	lock.Unlock()
	recvCond.Broadcast()

	fmt.Printf("sender(%d) end\n", i)
	wg.Done()
}
