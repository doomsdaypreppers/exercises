import git.Commit
import git.buildGitCommitTree
import git.findCommonAncestor
import git.treeSearch
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec
import java.util.*

class gittest : FunSpec() {
    init {
        test("treeSearch returns true for an available node ") {
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
            treeSearch(commitTree?.root, "F", Stack()) shouldBe true
        }

        test("treeSearch returns true for an available node ") {
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
            treeSearch(commitTree?.root, "Z", Stack()) shouldBe false
        }

        test("treeSearch populates correct branch for an available node ") {
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
            val branch = Stack<Commit>()
            treeSearch(commitTree?.root, "F", branch) shouldBe true
            val arr = arrayOfNulls<Commit>(branch.size)
            branch.toArray(arr)
            val expected = arrayOf(Commit("A"),
                Commit("B"),
                Commit("E"),
                Commit("F")
            )
            Arrays.equals(arr, expected) shouldBe true
        }

        test("findCommonAncestor should return merge base for available commits") {
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
            commonAncestor?.equals("B") shouldBe true
        }

        test("findCommonAncestor should return merge base for random commits") {
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
            val commonAncestor: String? = findCommonAncestor(commitTree?.root, "X", "D")
            commonAncestor shouldBe null
        }
    }
}