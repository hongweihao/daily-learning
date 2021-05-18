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

func (t Trie) parsePattern(pattern string) []string {
	parts := strings.Split(pattern, "/")
	result := make([]string, 0, len(parts))
	for _, part := range parts {
		if part == "" || part == "/" {
			continue
		}
		result = append(result, part)
	}
	return result
}

func (t *Trie) matchChild(n *node, part string) *node {
	for _, child := range n.Children {
		// 找到匹配的part
		if child.Part == part || child.IsWild {
			return child
		}
	}
	return nil
}

func (t *Trie) matchChildren(n *node, part string) []*node {
	nodes := make([]*node, 0)
	for _, child := range n.Children {
		// 找到匹配的part
		if child.Part == part || child.IsWild {
			nodes = append(nodes, child)
		}
	}
	return nodes
}

func (t *Trie) Insert(pattern string) {
	parts := t.parsePattern(pattern)
	t.insert(t.Root, pattern, parts, 0)
}

func (t *Trie) insert(n *node, pattern string, parts []string, index int) {
	// 最后一part，打上pattern
	if len(parts) == index {
		n.Pattern = pattern
		return
	}

	part := parts[index]
	findNode := t.matchChild(n, part)
	// 没找到，创建一个新的节点
	if findNode == nil {
		findNode = new(node)
		findNode.Part = part
		// *filepath/:param，参数可以匹配任意值
		findNode.IsWild = part[0] == '*' || part[0] == ':'
		n.Children = append(n.Children, findNode)
	}

	t.insert(findNode, pattern, parts, index+1)
}

func (t Trie) Search(pattern string) (*node, map[string]string) {
	parts := t.parsePattern(pattern)
	params := make(map[string]string)
	n := t.search(t.Root, pattern, parts, 0, params)
	return n, params
}

func (t Trie) search(n *node, pattern string, parts []string, index int, params map[string]string) *node {
	// 当前节点是最后一个
	if len(parts) == index {
		if n.Pattern == "" {
			return nil
		}
		return n
	}

	part := parts[index]
	// 当前节点不是最后一个，继续匹配子节点
	children := t.matchChildren(n, part)
	for _, child := range children {
		if child.Part[0] == ':' || child.Part[0] == '*' {
			params[child.Part[1:]] = part
		}
		found := t.search(child, pattern, parts, index+1, params)
		if found != nil {
			return found
		}
	}
	// 子节点中没找到匹配的节点
	return nil
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
