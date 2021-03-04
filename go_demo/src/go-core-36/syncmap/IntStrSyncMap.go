package main

import "sync"

type IntStrSyncMap struct {
	m sync.Map
}

func NewIntStrSyncMap() *IntStrSyncMap {
	return &IntStrSyncMap{}
}

func (intStrSyncMap *IntStrSyncMap) Store(key int, value string) {
	intStrSyncMap.m.Store(key, value)
}

func (intStrSyncMap *IntStrSyncMap) Load(key int) (string, bool) {
	value, ok := intStrSyncMap.m.Load(key)
	return value.(string), ok
}
