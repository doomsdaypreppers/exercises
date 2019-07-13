// Package exercise2 contains a function to print the top view of a binary tree
package exercise2

import (
	"fmt"
	"sort"

	"github.com/doomsdaypreppers/exercises/exercise1/abhurke/bst"
)

type scoredNode struct {
	node     *bst.Node
	height   int
	distance int
}

// PrintAndGetTopView prints the top view of a binary tree
func PrintAndGetTopView(r *bst.Node) []uint64 {
	var tv []uint64
	m := make(map[int]*scoredNode)
	assignHeightAndDistance(r, m, 0, 0)
	var keys []int
	for k := range m {
		keys = append(keys, k)
	}
	sort.Ints(keys)
	for _, k := range keys {
		fmt.Printf("distance: %d, val: %d\n", k, m[k].node.Key)
		tv = append(tv, m[k].node.Key)
	}
	return tv
}

func assignHeightAndDistance(r *bst.Node, m map[int]*scoredNode, d int, h int) {
	// check if a node exists at that distance
	if n := m[d]; n == nil || n.height > h {
		s := scoredNode{node: r, height: h, distance: d}
		m[d] = &s
	}

	if r.Left != nil {
		assignHeightAndDistance(r.Left, m, d-1, h+1)
	}
	if r.Right != nil {
		assignHeightAndDistance(r.Right, m, d+1, h+1)
	}
}
