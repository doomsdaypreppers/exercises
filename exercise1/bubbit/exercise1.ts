interface MyNode {
  value: number;
  left?: MyNode;
  right?: MyNode;
}

const listOfNodes = [[],[],[],[]];
const maxLevel = 3;

// Tree representation
const g: MyNode = { value: 14 };
const f: MyNode = { value: 10 };
const e: MyNode = { value: 25 };
const d: MyNode = { value: 12, left: f, right: g };
const c: MyNode = { value: 4 };
const b: MyNode = { value: 22, right: e };
const a: MyNode = { value: 8, left: c, right: d };
const root: MyNode = { value: 20, left: a, right: b };

const traverseTree = async function(node: MyNode, level: number) {
  return new Promise(async resolve => {
    listOfNodes[level].push(node.value);
    if(node.left) {
      await traverseTree(node.left, level + 1);
    }

    if(node.right) {
      await traverseTree(node.right, level + 1);
    }

    resolve();
  });
}

const calculateBoundingNodes = async function(root: MyNode, direction: String) {
  await traverseTree(root, 0);
  
  const boundingNodes = [];
  
  // Add root
  boundingNodes.push(root.value);

  for(let i = 1; i <= listOfNodes.length - 1; ++i) {
    boundingNodes.push(listOfNodes[i][direction === 'anti' ? 0 : listOfNodes[i].length - 1]);
  }

  for(let i = listOfNodes.length - 1; i > 0; --i) {
    boundingNodes.push(listOfNodes[i][direction === 'anti' ? listOfNodes[i].length - 1 : 0]);
  }

  console.log(boundingNodes);
}

calculateBoundingNodes(root, 'anti');
