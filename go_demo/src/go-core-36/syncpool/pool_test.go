package main

import (
	"bytes"
	"sync"
	"testing"
)

var data = make([]byte, 10000)

func BenchmarkBufferPool(b *testing.B) {
	var pool = sync.Pool{
		New: func() interface{} {
			return &bytes.Buffer{}
		},
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		buf := pool.Get().(*bytes.Buffer)
		buf.Write(data)

		buf.Reset()
		pool.Put(buf)
	}
	b.StopTimer()
}
func BenchmarkBuffer(b *testing.B) {
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		var buf = &bytes.Buffer{}
		buf.Write(data)
	}
	b.StopTimer()
}
