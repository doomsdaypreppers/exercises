var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
let listOfNodes = [[], [], [], [], []];
// Tree 1 representation
const f = { value: 7 };
const e = { value: 6 };
const d = { value: 5 };
const c = { value: 4 };
const b = { value: 3, left: e, right: f };
const a = { value: 2, left: c, right: d };
const root = { value: 1, left: a, right: b };
// Tree 2 representation
const k = { value: 6 };
const j = { value: 5, right: k };
const i = { value: 4, right: j };
const h = { value: 3 };
const g = { value: 2, right: i };
const root2 = { value: 1, left: g, right: h };
const traverseTree = (node, distance) => __awaiter(this, void 0, void 0, function* () {
    return new Promise((resolve) => __awaiter(this, void 0, void 0, function* () {
        listOfNodes[distance].push(node.value);
        if (node.left) {
            yield traverseTree(node.left, distance - 1);
        }
        if (node.right) {
            yield traverseTree(node.right, distance + 1);
        }
        resolve();
    }));
});
const calculateTopView = (root) => __awaiter(this, void 0, void 0, function* () {
    return new Promise((resolve) => __awaiter(this, void 0, void 0, function* () {
        yield traverseTree(root, 2);
        const topViewNodes = [];
        listOfNodes.forEach(nodes => {
            nodes.sort();
            topViewNodes.push(nodes[0]);
        });
        resolve(topViewNodes.filter(Boolean));
    }));
});
const runBothTrees = () => __awaiter(this, void 0, void 0, function* () {
    console.log('========================');
    console.log('Find top view of tree 1');
    console.log(yield calculateTopView(root));
    console.log('========================');
    listOfNodes = [[], [], [], [], []];
    console.log('========================');
    console.log('Find top view of tree 2');
    console.log(yield calculateTopView(root2));
    console.log('========================');
});
runBothTrees();
