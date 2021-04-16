package test

import "testing"

func TestUnitTestFuncName(t *testing.T) {
	t.Log("log")
	t.Logf("t.Name() = %v", t.Name())
	t.Error("error") // 表示失败，但依然会继续执行程序
	t.Fatal("Fatal") // 表示失败，终止程序
	t.Logf("After Fatal")
}

func BenchmarkBenchmarkTestFuncName(b *testing.B) {
	// 耗时操作
	b.ResetTimer() // 重置计时器，如果前面做了耗时地操作
	for i := 0; i < b.N; i++ {
		b.Log(i)
	}
	b.StopTimer()
}
