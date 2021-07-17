package problem173
import java.util.*

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class BSTIterator(root: TreeNode?) {
    companion object {
        const val LEFT_VISITED = 0
        const val MIDDLE_VISITED = 1
        const val RIGHT_VISITED = 2
    }

    // Node, right visited
    private val stack = Stack<Pair<TreeNode, Int>>()

    init {
        if (root != null) {
            stack.push(root to LEFT_VISITED)
            visitLeft(root.left)
        }
    }

    private fun visitLeft(root: TreeNode?) {
        var current = root
        while (current != null) {
            stack.push(current to LEFT_VISITED)
            current = current.left
        }
    }

    private fun moveForward() {
        while (stack.isNotEmpty()) {
            var (node, visit) = stack.peek()
            when (visit) {
                LEFT_VISITED -> return
                MIDDLE_VISITED -> {
                    stack.pop()
                    stack.push(node to RIGHT_VISITED)
                    visitLeft(node.right)
                }
                RIGHT_VISITED -> {
                    stack.pop()
                }
            }
        }
    }

    /** @return the next smallest number */
    fun next(): Int {
        moveForward()
        val (node, _) = stack.pop()
        stack.push(node to MIDDLE_VISITED)
        return node.`val`
    }

    /** @return whether we have a next smallest number */
    fun hasNext(): Boolean {
        moveForward()
        return stack.isNotEmpty()
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */

// test
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}