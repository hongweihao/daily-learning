// 前缀树路由实现
package gee

import (
	"strings"
)

type Trie struct {
	Root *node
}

func NewTrie() *Trie {
	root := &node{
		Children: make([]*node, 0),
	}
	return &Trie{
		Root: root,
	}
}

func (t *Trie) Insert(pattern string) {
	patternTrim := strings.Trim(pattern, "/")
	parts := strings.Split(patternTrim, "/")
	if pattern == "/" {
		t.Root.Pattern = "/"
		return
	}
	t.insert(t.Root, pattern, parts, 0)
}

// todo bug repeated pattern
func (t *Trie) insert(n *node, pattern string, parts []string, index int) {
	// 已经存在，无需插入
	if len(parts) == index {
		n.Pattern = pattern
		return
	}

	part := parts[index]
	var findNode *node
	//
	for _, child := range n.Children {
		// 找到匹配的part
		if child.Part == part || child.IsWild {
			findNode = child
			break
		}

		// 任意匹配， repeatedly insert
		//if part[0] == ':' || part[0] == '*' {
		//	child.Part = part
		//	child.IsWild = true
		//	findNode = child
		//	break
		//}
	}

	// 没找到，创建一个新的节点
	if findNode == nil {
		newNode := new(node)
		newNode.Part = part
		// *filepath/:param，参数可以匹配任意值
		newNode.IsWild = part[0] == '*' || part[0] == ':'

		n.Children = append(n.Children, newNode)
		t.insert(newNode, pattern, parts, index+1)
		return
	}

	// 找到了，继续下一层寻找
	t.insert(findNode, pattern, parts, index+1)
}

func (t Trie) Search(pattern string) (*node, map[string]string) {
	patternTrim := strings.Trim(pattern, "/")
	parts := strings.Split(patternTrim, "/")

	params := make(map[string]string)
	n := t.search(t.Root, pattern, parts, 0, params)
	return n, params
}

func (t Trie) search(n *node, pattern string, parts []string, index int, params map[string]string) *node {
	// 找到中途没找到
	if n == nil {
		return nil
	}

	// 找到了
	if n.Pattern != "" && len(parts)-1 == index {
		return n
	}

	// 找到底了没找到
	if len(parts) == index {
		return nil
	}

	var findNode *node
	for _, child := range n.Children {
		// 如果是参数匹配，把参数取出来
		if child.IsWild {
			params[child.Part[1:]] = parts[index]
			findNode = child
		}

		if child.Part == parts[index] {
			findNode = child
		}
	}

	return t.search(findNode, pattern, parts, index+1, params)
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
