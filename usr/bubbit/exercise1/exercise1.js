var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const listOfNodes = [[], [], [], []];
const maxLevel = 3;
// Tree representation
const g = { value: 14 };
const f = { value: 10 };
const e = { value: 25 };
const d = { value: 12, left: f, right: g };
const c = { value: 4 };
const b = { value: 22, right: e };
const a = { value: 8, left: c, right: d };
const root = { value: 20, left: a, right: b };
const traverseTree = function (node, level) {
    return __awaiter(this, void 0, void 0, function* () {
        return new Promise((resolve) => __awaiter(this, void 0, void 0, function* () {
            listOfNodes[level].push(node.value);
            if (node.left) {
                yield traverseTree(node.left, level + 1);
            }
            if (node.right) {
                yield traverseTree(node.right, level + 1);
            }
            resolve();
        }));
    });
};
const calculateBoundingNodes = function (root, direction) {
    return __awaiter(this, void 0, void 0, function* () {
        yield traverseTree(root, 0);
        const boundingNodes = [];
        // Add root
        boundingNodes.push(root.value);
        for (let i = 1; i <= listOfNodes.length - 1; ++i) {
            boundingNodes.push(listOfNodes[i][direction === 'anti' ? 0 : listOfNodes[i].length - 1]);
        }
        for (let i = listOfNodes.length - 1; i > 0; --i) {
            boundingNodes.push(listOfNodes[i][direction === 'anti' ? listOfNodes[i].length - 1 : 0]);
        }
        console.log(boundingNodes);
    });
};
calculateBoundingNodes(root, 'anti');
