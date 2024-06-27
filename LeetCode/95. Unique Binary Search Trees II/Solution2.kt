package problem95_2

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

  fun generateTrees(n: Int): List<TreeNode?> {
    val dp = arrayOfNulls<List<TreeNode?>?>(n + 1)
    dp[0] = listOf(null)
    dp[1] = listOf(TreeNode(1))

    for (i in 1..n) {
      val list = mutableListOf<TreeNode?>()
      for (j in 0..i - 1) {
        // part1 -> f(1 .. j)
        // part2 -> (j + 1, i-1)
        val part1 = dp[j]!!
        val part2 = dp[i - 1 - j]!! // -> offset j

        for (p1 in part1) {
          for (p2 in part2) {
            val max = TreeNode(i)

            val pair = clone(p1, 0)
            if (pair != null) {
              val rm: TreeNode = pair.second
              rm.right = max
            }
            max.left = clone(p2, j)?.first

            val newTree = pair?.first ?: max
            list.add(newTree)
          }
        }
      }

      dp[i] = list
    }

    return dp[n]!!
  }

  // <root, right most>
  private fun clone(node: TreeNode?, offset: Int): Pair<TreeNode, TreeNode>? {
    if (node == null) return null

    val n = TreeNode(node.`val` + offset)
    n.left = clone(node.left, offset)?.first
    val r = clone(node.right, offset)
    n.right = r?.first

    return n to (r?.second ?: n)
  }
}

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {
  val f2 = Solution().generateTrees(2)
  val f3 = Solution().generateTrees(3)
  val f4 = Solution().generateTrees(4)
  f4.forEachIndexed { i, t ->
    val valid = isValidTree(t)
    if (!valid) {
      error("$i")
    }
  }

  val trees = (1..5).map { Solution().generateTrees(it) }

  trees.forEach { tree ->
    tree.forEach { isValidTree(it) }
  }

  trees.size
}

fun isValidTree(node: TreeNode?): Boolean {
  if (node != null) {
    if (node.left != null) {
      if (!(node.`val` > node.left!!.`val` && isValidTree(node.left))) {
        return false
      }
    }

    if (node.right != null) {
      if (!(node.`val` < node.right!!.`val` && isValidTree(node.right))) {
        return false
      }
    }
  }

  return true
}