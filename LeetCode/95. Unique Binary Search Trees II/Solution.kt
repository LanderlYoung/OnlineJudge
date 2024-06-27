package problem95

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
    return gen(1, n + 1)
  }

  /**
   * [start, end)
   */
  private fun gen(start: Int, end: Int): List<TreeNode?> {
    if (start >= end) {
      return listOf(null)
    } else if (start + 1 == end) {
      return listOf(TreeNode(start))
    }

    val ret = mutableListOf<TreeNode>()
    for (i in 0..(end - 1 - start)) {
      val part1 = gen(start, start + i)
      val part2 = gen(start + i, end - 1)

      for (p1 in part1) {
        for (p2 in part2) {
          val max = TreeNode(end - 1)

          val pair = clone(p1)
          if (pair != null) {
            val rm: TreeNode = pair.second
            rm.right = max
          }
          max.left = p2

          val newTree = pair?.first ?: max
          ret.add(newTree)
        }
      }
    }

    return ret
  }

  // <root, right most>
  private fun clone(node: TreeNode?): Pair<TreeNode, TreeNode>? {
    if (node == null) {
      return null
    }
    val n = TreeNode(node.`val`)
    n.left = clone(node.left)?.first
    val r = clone(node.right)
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