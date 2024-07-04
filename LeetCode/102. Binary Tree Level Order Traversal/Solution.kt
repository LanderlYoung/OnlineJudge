package problem102


class Solution {
  fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()
    val result = ArrayList<ArrayList<Int>>()

    // BFS
    val layerNode = ArrayDeque<TreeNode>()
    layerNode.add(root)

    while (layerNode.isNotEmpty()) {
      val layer = ArrayList<Int>()

      for (i in 0..<layerNode.size) {
        val node = layerNode.removeFirst()
        layer.add(node.`val`)
        node.left?.let { layerNode.add(it) }
        node.right?.let { layerNode.add(it) }
      }
      result.add(layer)
    }

    return result
  }
}

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {


}