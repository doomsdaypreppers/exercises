package main

import "fmt"

// Node is a type which forms the building block of a binary tree.
type Node struct {
	key   uint64
	left  *Node
	right *Node
}

// Insert inserts a new node creating a BST
func Insert(root *Node, key uint64) *Node {
	n := Node{key: key, left: nil, right: nil}
	if root != nil {
		insertNode(root, &n)
	}
	return &n
}

func insertNode(node, newNode *Node) {
	if newNode.key < node.key {
		if node.left == nil {
			node.left = newNode
		} else {
			insertNode(node.left, newNode)
		}
	} else {
		if node.right == nil {
			node.right = newNode
		} else {
			insertNode(node.right, newNode)
		}
	}
}

func printLeftSubTree(root *Node) {
	if root != nil && root.left != nil {
		fmt.Printf("%d\n", root.key)
		printLeftSubTree(root.left)
	}
}

func printRightSubTree(root *Node) {
	if root != nil && root.right != nil {
		printRightSubTree(root.right)
		fmt.Printf("%d\n", root.key)
	}
}

func printRightSubTreeWithoutRoot(root *Node) {
	if root != nil && root.right != nil {
		printRightSubTree(root.right)
	}
}

func printLeaves(root *Node) {
	if root != nil && root.left == nil && root.right == nil {
		fmt.Printf("%d\n", root.key)
	} else if root != nil {
		printLeaves(root.left)
		printLeaves(root.right)
	}
}

func print(root *Node) {
	if root != nil {
		fmt.Printf("%d\n", root.key)
		print(root.left)
		print(root.right)
	}
}

func main() {
	r := Insert(nil, 20)
	Insert(r, 8)
	Insert(r, 22)
	Insert(r, 4)
	Insert(r, 12)
	Insert(r, 10)
	Insert(r, 14)
	Insert(r, 25)
	// print the boundary of the tree
	printLeftSubTree(r)
	printLeaves(r)
	printRightSubTreeWithoutRoot(r)
}
