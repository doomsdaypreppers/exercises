import {
    buildTree,
    buildTreeMap,
    showTreeMap,
    findTreeWidthAndDepth,
    findTopNodes,
    generateRandomTree,
} from './common/treeHelpers.mjs';

let root = buildTree([50, 36, 25, 1, 66, 55, 54, 53,75, 100]);

root = buildTree(generateRandomTree(10));

let { width, depth } = findTreeWidthAndDepth(root);
console.log(`Width: ${width}, depth: ${depth}`);

let map = buildTreeMap(undefined, root, 2 << depth - 1, 0, depth - 1);
showTreeMap(map);

console.log('Top Nodes:', findTopNodes(map));