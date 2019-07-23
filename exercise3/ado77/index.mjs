class commit {
    constructor(name, parentCommits) {
        this.name = name;
        this.parents = Array.isArray(parentCommits) ? parentCommits : parentCommits || [];
    }

    get commitName() {
        return this.name;
    }
    set commitName(name) {
        this.name = name;
    }

    get parentCommits() {
        return this.parents;
    }
    set parentCommits(parentCommits) {
        this.parents.push(parentCommits);
    }
};

function findAncestor(commit1, commit2) {
}


let a = new commit('a');
let b = new commit('b', a);
let c = new commit('c', b);
let d = new commit('d', c);
let e = new commit('e', b);
let f = new commit('f', e);
let g = new commit('g', [f, d]);

findAncestor(d, e);

console.log(JSON.stringify(g, null, 2));