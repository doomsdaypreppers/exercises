package git

import java.util.*
import kotlin.collections.ArrayList

data class Commit(val commitHash: String, val children: MutableList<Commit>) {
    override fun equals(other: Any?) = when (other) {
            is Commit -> this.commitHash.equals(other.commitHash)
            else -> false
        }

    override fun hashCode(): Int {
        return this.commitHash.hashCode()
    }
}

data class CommitTree(val root: Commit) {

    override fun toString(): String {
        val sb = StringBuilder()
        toString(sb, root)
        return sb.toString()
    }

    private fun toString(sb: StringBuilder, top: Commit) {
        sb.append(top.commitHash)
        sb.append(" -> ")
        top.children.forEach { toString(sb, it) }
    }
}

/**
 * findCommonAncestor returns the common ancestor (merge base) of two commit hashes
 * in a git tree.
 */
fun findCommonAncestor(node: Commit?, hash1: String, hash2: String): String? {
    if (node == null) return null
    val branch1 = Stack<Commit>()
    val branch2 = Stack<Commit>()
    treeSearch(node, "F", branch1)
    treeSearch(node, "D", branch2)
    var commonAncestor: String? = null
    for (i in 0 until branch1.size-1) {
        if (branch1[i] == branch2[i]) {
            commonAncestor = branch1[i].commitHash
        } else {
            break
        }
    }
    return commonAncestor
}

/**
 * treeSearch searches the git commit tree for the hash. The branch of commits
 * to the commit hash can be found pushed on the stack.
 */
fun treeSearch(node: Commit?, hash: String, branch: Stack<Commit>): Boolean {
    if (node == null) return false
    if (!node.commitHash.equals(hash)) {
        branch.push(node)
        node.children.forEach {
            if( treeSearch(it, hash, branch) ) return true
        }
        branch.pop()
        return false
    } else {
        branch.push(node)
        return true
    }
}

/**
 * buildGitCommitTree builds a @CommitTree from 2 same sized arrays of a commit hashes and commit parents.
 */
fun buildGitCommitTree(commitHashes: Array<String>, commitParents: Array<Array<String>>): CommitTree? {
    if (commitHashes.size != commitParents.size)
        throw IllegalArgumentException("size of commits and parents array should be same")

    var commitTree: CommitTree? = null
    var commit: Commit? = null
    val parents: MutableList<Commit> = ArrayList()
    for (i in commitHashes.size - 1 downTo 0) {
        if( commit == null ) {
            commit = Commit(commitHashes[i], mutableListOf<Commit>())
            commitTree = CommitTree(commit)

        } else {
            commit = Commit(commitHashes[i], mutableListOf<Commit>())
            parents.forEach {
                if (commitParents[i].contains(it.commitHash)){
                    it.children.add(commit)
                }
            }
        }
        parents.add(commit)
    }
    return commitTree
}

fun main() {
    val commits = arrayOf("G", "F", "E", "D", "C", "B", "A")
    val parents = arrayOf(
        arrayOf("F", "D"),
        arrayOf("E"),
        arrayOf("B"),
        arrayOf("C"),
        arrayOf("B"),
        arrayOf("A"),
        arrayOf()
        )
    val commitTree = buildGitCommitTree(commits, parents)
    val commonAncestor: String? = findCommonAncestor(commitTree?.root, "F", "D")
    commonAncestor?.let { println(it) }
}