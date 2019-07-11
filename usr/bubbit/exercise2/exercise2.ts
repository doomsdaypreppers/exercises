interface MyNode {
    value: number;
    left?: MyNode;
    right?: MyNode;
  }
  
  let listOfNodes = [[],[],[],[],[]];
  
  // Tree 1 representation
  const f: MyNode = { value: 7 };
  const e: MyNode = { value: 6 };
  const d: MyNode = { value: 5 };
  const c: MyNode = { value: 4 };
  const b: MyNode = { value: 3, left: e, right: f };
  const a: MyNode = { value: 2, left: c, right: d };
  const root: MyNode = { value: 1, left: a, right: b };

  // Tree 2 representation
  const k: MyNode = { value: 6 };
  const j: MyNode = { value: 5, right: k };
  const i: MyNode = { value: 4, right: j};
  const h: MyNode = { value: 3 };
  const g: MyNode = { value: 2, right: i };
  const root2: MyNode = { value: 1, left: g, right: h};
  
  const traverseTree = async (node: MyNode, distance: number) => {
    return new Promise(async resolve => {
      listOfNodes[distance].push(node.value);
      if(node.left) {
        await traverseTree(node.left, distance - 1);
      }
  
      if(node.right) {
        await traverseTree(node.right, distance + 1);
      }
  
      resolve();
    });
  }
  
  const calculateTopView = async (root: MyNode) => {
    return new Promise(async resolve => {
        await traverseTree(root, 2);
        
        const topViewNodes = [];

        listOfNodes.forEach(nodes => {
            nodes.sort();
            topViewNodes.push(nodes[0]);
        });
    
        resolve(topViewNodes.filter(Boolean));
    });
  }
  
  const runBothTrees = async () => {
    console.log('========================');
    console.log('Find top view of tree 1');
    console.log(await calculateTopView(root));
    console.log('========================');
    listOfNodes = [[],[],[],[],[]];
    console.log('Find top view of tree 2');
    console.log(await calculateTopView(root2));
    console.log('========================');
  }

  runBothTrees();
