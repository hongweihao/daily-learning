package main

import (
	"reflect"
	"sync"
)

type ConcurrentMap struct {
	m         sync.Map
	keyType   reflect.Type
	valueType reflect.Type
}

func badType(t reflect.Type) bool {
	return t == nil || t.Kind() == reflect.Slice || t.Kind() == reflect.Map || t.Kind() == reflect.Func
}

func NewConcurrentMap(keyType, valueType reflect.Type) *ConcurrentMap {
	if badType(keyType) {
		panic("Invalid type for key")
	}
	if badType(valueType) {
		panic("Invalid type for value")
	}

	return &ConcurrentMap{
		keyType:   keyType,
		valueType: valueType,
	}
}

func (concurrentMap *ConcurrentMap) Store(key, value interface{}) {
	if reflect.TypeOf(key) != concurrentMap.keyType {
		panic("no support type for key")
	}
	if reflect.TypeOf(value) != concurrentMap.valueType {
		panic("no support type for value")
	}
	concurrentMap.m.Store(key, value)
}

func (concurrentMap *ConcurrentMap) Load(key interface{}) (interface{}, bool) {
	if reflect.TypeOf(key) != concurrentMap.keyType {
		panic("no support type for key")
	}
	return concurrentMap.m.Load(key)
}
