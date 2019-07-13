package main

import (
	"fmt"

	"github.com/doomsdaypreppers/exercises/exercise1/abhurke/bst"
)

func printLeftSubTree(root *bst.Node) {
	if root != nil && root.Left != nil {
		fmt.Printf("%d\n", root.Key)
		printLeftSubTree(root.Left)
	}
}

func printRightSubTree(root *bst.Node) {
	if root != nil && root.Right != nil {
		printRightSubTree(root.Right)
		fmt.Printf("%d\n", root.Key)
	}
}

func printRightSubTreeWithoutRoot(root *bst.Node) {
	if root != nil && root.Right != nil {
		printRightSubTree(root.Right)
	}
}

func printLeaves(root *bst.Node) {
	if root != nil && root.Left == nil && root.Right == nil {
		fmt.Printf("%d\n", root.Key)
	} else if root != nil {
		printLeaves(root.Left)
		printLeaves(root.Right)
	}
}

func print(root *bst.Node) {
	if root != nil {
		fmt.Printf("%d\n", root.Key)
		print(root.Left)
		print(root.Right)
	}
}

func main() {
	r := bst.Insert(nil, 20)
	bst.Insert(r, 8)
	bst.Insert(r, 22)
	bst.Insert(r, 4)
	bst.Insert(r, 12)
	bst.Insert(r, 10)
	bst.Insert(r, 14)
	bst.Insert(r, 25)
	// print the boundary of the tree
	printLeftSubTree(r)
	printLeaves(r)
	printRightSubTreeWithoutRoot(r)
}
