package main

import (
	"sync"
	"testing"
)

func BenchmarkBufferPool(b *testing.B) {
	var pool = sync.Pool{New: func() interface{} {
		return make([]byte, 1024)
	}}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		bytesObj := pool.Get().([]byte)
		_ = bytesObj
		pool.Put(bytesObj)
	}
	b.StopTimer()
}
func BenchmarkBuffer(b *testing.B) {
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		bytesObj := NewByte().([]byte)
		_ = bytesObj
	}
	b.StopTimer()
}
