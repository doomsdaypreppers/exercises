# To print the top view of a binary tree

For each node in the tree, there is - 
1. height of the node from the root, a positive integer.
2. relative size to the root.

For e.g. 

* root is height 0 and distance = 0
* first node on the left of the root is heigh = 1 and distance = -1

For the top view of the tree, the node (0, 0) while hide node(0, 1), (0, 2) ... (0, n)
 

* Given two nodes of same distance, the one with a lowest height will be visible.
* the output will be printed from the lowest distance to the highest, only for those nodes which have the lowest heights.