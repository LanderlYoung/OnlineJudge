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
class Solution {
    fun insertIntoMaxTree(root: TreeNode?, `val`: Int): TreeNode? {
        val node = TreeNode(`val`)
        if (root == null) {
            return node
        }

        var currentNode: TreeNode? = root
        var parentNode: TreeNode? = null
        while (currentNode != null && currentNode.`val` > `val`) {
            parentNode = currentNode
            currentNode = currentNode.right
        }

        if (parentNode != null) {
            // non root
            parentNode.right = node
            node.left = currentNode
            return root
        } else {
            // is root
            node.left = root
            return node
        }
    }
}

// test

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

