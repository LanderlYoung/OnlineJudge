package problem124

import shouldBeEqualTo

class Solution {
  private var max = Int.MIN_VALUE

  fun maxPathSum(root: TreeNode?): Int {
    // 1. define sub-problem
    // f(node) -> use node as edge, the MPS
    // result = max(f(allNodes...))
    //
    // 2. recursion formula
    // - leftTree + node;
    // - node + rightTree;
    // f(node) -> node.value + max(f(node.left), 0) + max(f(node.right), 0)
    // max = maxOf(max, leftTree + node + rightTree)

    f(root)
    return max
  }

  fun f(node: TreeNode?): Int {
    if (node == null) return 0

    val left = f(node.left).coerceAtLeast(0)
    val right = f(node.right).coerceAtLeast(0)

    max = maxOf(node.`val` + left + right, max)

    return maxOf(node.`val` + left, node.`val` + right)
  }
}

data class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {
  val root = TreeNode(5).apply {
    left = TreeNode(4).apply {
      left = TreeNode(11).apply {
        left = TreeNode(7)
        right = TreeNode(2)
      }
    }
    right = TreeNode(8).apply {
      left = TreeNode(13)
      right = TreeNode(4).apply {
        right = TreeNode(1)
      }
    }
  }

  Solution().maxPathSum(root) shouldBeEqualTo 48


}