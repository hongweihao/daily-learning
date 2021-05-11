// 前缀树路由实现
package gee

import (
	"strings"
)

type Trie struct {
	Root *node
}

func NewTrie() *Trie {
	return &Trie{
		Root: new(node),
	}
}

func (t *Trie) Insert(pattern string) {
	patternTrim := strings.Trim(pattern, "/")
	parts := strings.Split(patternTrim, "/")
	t.insert(t.Root, pattern, parts, 0)
}

func (t *Trie) insert(n *node, pattern string, parts []string, index int) {
	// 已经存在，无需插入
	if len(parts) == index {
		return
	}

	// 
	for _, child := range n.Children {
		if child.Part ==  
		
	}

}






type node struct {
	// 匹配的url(注册时提供的url，例如：/p/:lang/doc)
	Pattern string
	// url中的一段，例如：p  :lang  doc
	Part string
	// 子节点
	Children []*node
	// 任意节点都能匹配，例如当前节点的part是:lang或者*filepath，则IsWild为true
	IsWild bool
}

// Insert 启动服务时，将注册的url添加到前缀树中
func (n *node) Insert(pattern string) {
	patternTrim := strings.Trim(pattern, "/")
	parts := strings.Split(patternTrim, "/")

	n.insert(pattern, parts, 0)
}

func (n *node) insert(pattern string, parts []string, partIndex int) {

	for _, v := range n.Children {
		if v.Part == parts[partIndex] || n.IsWild {

		} else {

		}

	}

}

// search 访问时，从前缀树中查找是否有对应的url


