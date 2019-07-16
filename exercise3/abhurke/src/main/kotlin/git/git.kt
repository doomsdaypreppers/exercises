package git

import java.util.*

data class Commit(val commitHash: String, val parents: MutableList<String>, val children: MutableList<Commit>) {
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
 * buildGitCommitTree builds a @CommitTree from 2 same sized arrays of a commit hashes and commit parents.
 */
fun buildGitCommitTree(commitHashes: Array<String>, commitParents: Array<Array<String>>): CommitTree? {
    if (commitHashes.size != commitParents.size)
        throw IllegalArgumentException("size of commits and parents array should be same")

    var commitTree: CommitTree? = null
    var commit: Commit? = null
    val stack: Stack<Commit> = Stack()
    for (i in commitHashes.size - 1 downTo 0) {
        if( commit == null ) {
            commit = Commit(commitHashes[i], mutableListOf<String>(), mutableListOf<Commit>())
            commitTree = CommitTree(commit)

        } else {
            commit = Commit(commitHashes[i], commitParents[i].toMutableList(), mutableListOf<Commit>())
            val parent = stack.peek()
            when(commitParents[i].contains(parent.commitHash)){
                true    -> parent.children.add(commit)
                false   -> stack.pop()
            }
        }
        stack.push(commit)
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
    println(commitTree)
}