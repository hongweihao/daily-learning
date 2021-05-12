package gee

import "testing"

func TestInsert(t *testing.T) {
	tree := NewTrie()
	tree.Insert("/hello/world")

	t.Log(tree.Root.Part)
	t.Log(tree.Root.Children[0].Part)
	t.Log(tree.Root.Children[0].Children[0].Part)
}

// todo bug repeated pattern
func TestInsertRepeatedly(t *testing.T) {
	tree := NewTrie()
	tree.Insert("/hello/world")
	tree.Insert("/:hello/world")

	t.Log(tree.Root.Part)
	t.Log(tree.Root.Children[0].Part)
	t.Log(tree.Root.Children[0].Children[0].Part)
}

func TestAnyUrl(t *testing.T) {
	tree := NewTrie()
	tree.Insert("/p/:lang/doc")

	t.Log(tree.Root.Part)
	t.Log(tree.Root.Children[0].Part)
	t.Log(tree.Root.Children[0].Children[0].Part)
}

func TestSearch(t *testing.T) {
	tree := NewTrie()
	tree.Insert("/:hello/world/")

	searchNode, params := tree.Search("/hello/world")
	t.Log(searchNode.Pattern)
	t.Log(params)
}
