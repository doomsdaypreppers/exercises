class node {

    constructor(data) {
        this._value = data;
    }

    get value() {
        return this._value;
    }

    set value(value) {
        this._value = value;
    }

    get leftSide() {
        return this._lhs;
    }

    set leftSide(value) {
        this._lhs = value;
    }

    get rightSide() {
        return this._rhs;
    }

    set rightSide(value) {
        this._rhs = value;
    }
};

const insertNode = (currentNode, value) => {
    if (currentNode == undefined) {
        return new node(value);
    }
    if (value === currentNode.value) {
        return;
    }

    if (value < currentNode.value) {
        if (currentNode.leftSide == undefined) {
            return currentNode.leftSide = new node(value);
        }
        insertNode(currentNode.leftSide, value);
    } else {
        if (currentNode.rightSide == undefined) {
            return currentNode.rightSide = new node(value);
        }

        insertNode(currentNode.rightSide, value);
    }
}

export const buildTree = (data) => {

    let root = new node(data[0]);

    for (let index = 1; index < data.length; index++) {
        insertNode(root, data[index]);
    }

    return root;
}

export const findTreeWidthAndDepth = (currentNode, currentWidth = 0, currentDepth = 0, offset = {
    min: 0,
    max: 0,
    depth: 0
}) => {

    if (currentWidth < offset.min)
        offset.min = currentWidth;

    if (currentWidth > offset.max)
        offset.max = currentWidth;

    if (currentDepth > offset.depth)
        offset.depth = currentDepth;

    if (currentNode.leftSide != undefined) {
        findTreeWidthAndDepth(currentNode.leftSide, currentWidth - 1, currentDepth + 1, offset);
    }

    if (currentNode.rightSide != undefined) {
        findTreeWidthAndDepth(currentNode.rightSide, currentWidth + 1, currentDepth + 1, offset);
    }

    return {
        width: (offset.max + -offset.min + 2),
        depth: offset.depth + 1
    };
}

const _padNode = (arrToFill, start, length, char) => {
    if (arrToFill.length < (start + length)) {
        arrToFill[start + length] = '';
    }
    arrToFill.fill(char, start, start + length);
}

export const buildTreeMap = (map = [], currentNode, x, y, depth) => {

    if (map[y] == undefined) {
        map[y] = [];
    }

    map[y][x] = currentNode.value;

    let padLength = 2 << (depth - y - 1);

    if (currentNode.leftSide != undefined) {
        _padNode(map[y], x - padLength + 1, padLength - 1, '_');
        buildTreeMap(map, currentNode.leftSide, x - padLength, y + 1, depth);
    }

    if (currentNode.rightSide != undefined) {
        _padNode(map[y], x + 1, padLength - 1, '_');
        buildTreeMap(map, currentNode.rightSide, x + padLength, y + 1, depth);
    }
    return map;
}

export const showTreeMap = (map) => {
    console.log(' ');
    for (let y = 0; y < map.length; y++) {
        let line = '';
        for (let x = 0; x < map[y].length; x++) {
            line += map[y][x] || ' ';
        }
        console.log(line);
    }
    console.log(' ');
}

export const findTopNodes = (map) => {
    let topNodes = [];
    for (let y = 0; y < map.length; y++) {
        for (let x = 0; x < map[y].length; x++) {
            if (topNodes[x] == undefined && map[y][x] != undefined && '/\\'.indexOf(map[y][x]) === -1) {
                topNodes[x] = map[y][x];
            }
        }
    }
    return topNodes.filter(e => e != undefined && e !== '_');
};

export const generateRandomTree = (length, useLengthAsRoot = true) => {
    let data = [];
    if (useLengthAsRoot) {
        data.push(length);
    }
    for (let index = 0; index < length; index++) {
        data.push(Math.floor(Math.random() * (2 * length)) + 1);
    }

    console.log(JSON.stringify(data));

    return data;
}