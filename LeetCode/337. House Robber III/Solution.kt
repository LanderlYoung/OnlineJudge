package problem337

class Solution {
  fun rob(root: TreeNode?): Int {
    return buildRobTree(root)!!.`val`
  }

  /** return rob tree node */
  private fun buildRobTree(tree: TreeNode?): TreeNode? {
    if (tree == null) return null

    val rob = TreeNode(0)
    rob.left = buildRobTree(tree.left)
    rob.right = buildRobTree(tree.right)

    // 1. we don't take treeNode, take tree's child
    val count1 = sumChild(rob)
    // 2. we take treeNode, so we can't take tree's child, but take tree's grand child
    val count2 = tree.`val` + sumChild(rob.left) + sumChild(rob.right)

    rob.`val` = maxOf(count1, count2)

    return rob
  }

  private fun sumChild(node: TreeNode?): Int {
    return (node?.left?.`val` ?: 0) + (node?.right?.`val` ?: 0)
  }
}

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {

}