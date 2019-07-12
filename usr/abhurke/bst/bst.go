package bst

// Node is a type which forms the building block of a binary tree.
type Node struct {
	Key   uint64
	Left  *Node
	Right *Node
}

// Insert inserts a new node creating a BST
func Insert(root *Node, key uint64) *Node {
	n := Node{Key: key, Left: nil, Right: nil}
	if root != nil {
		insertNode(root, &n)
	}
	return &n
}

func insertNode(node, newNode *Node) {
	if newNode.Key < node.Key {
		if node.Left == nil {
			node.Left = newNode
		} else {
			insertNode(node.Left, newNode)
		}
	} else {
		if node.Right == nil {
			node.Right = newNode
		} else {
			insertNode(node.Right, newNode)
		}
	}
}
