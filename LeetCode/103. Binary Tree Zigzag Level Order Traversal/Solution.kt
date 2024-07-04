package problem103

class Solution {
  fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()
    val result = ArrayList<ArrayList<Int>>()

    // BFS
    val layerNode = ArrayDeque<TreeNode>()
    layerNode.add(root)

    var reverse = false
    while (layerNode.isNotEmpty()) {
      val size = layerNode.size
      val layer = ArrayList<Int>(size)
      for (i in (0..<size).reversed()) {
        val node = layerNode[i]
        layer.add(node.`val`)

        if (!reverse) {
          node.left?.let { layerNode.add(it) }
          node.right?.let { layerNode.add(it) }
        } else {
          node.right?.let { layerNode.add(it) }
          node.left?.let { layerNode.add(it) }
        }
      }

      result.add(layer)
      repeat(size) { layerNode.removeFirst() }
      reverse = !reverse
    }

    return result
  }
}

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {}