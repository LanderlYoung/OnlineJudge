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
    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        if (nums.isEmpty()) {
            return null
        }
        var root = TreeNode(nums[0])

        for (i in 1 until nums.size) {
            val value = nums[i]

            var currentNode: TreeNode? = root
            var parentNode: TreeNode? = null
            while (currentNode != null && currentNode.`val` > value) {
                parentNode = currentNode
                currentNode = currentNode.right
            }

            val node = TreeNode(value)
            if (parentNode != null) {
                // non root
                parentNode.right = node
                node.left = currentNode
            } else {
                // is root
                node.left = root
                root = node
            }
        }
        return root
    }
}

// test
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

