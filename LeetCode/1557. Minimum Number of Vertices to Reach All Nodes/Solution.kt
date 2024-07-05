package problem1557

import equalsWithoutOrder
import matrix1d
import matrix2d
import shouldBeEqualToWithoutOrder

// tips 1: A node that does not have any incoming edge can only be reached by itself.

class Solution {
  fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
    val result = (0..<n).toMutableSet()
    edges.forEach { (_, to)->
      result.remove(to)
    }
    return result.toList()
  }
}

fun main() {
  fun test(
    n: Int, edges: /*List<List<Int>>*/ String,
    expected: /*List<Int>*/ String,
  ) {
    val result = Solution().findSmallestSetOfVertices(n, matrix2d(edges).map { it.toList() })
    val exp = matrix1d(expected).toList()
    println("$n -> $result == $expected -> ${result equalsWithoutOrder exp}")
    result shouldBeEqualToWithoutOrder exp
  }

  test(3, "[[1,2],[1,0],[0,2]]", "[1]")
  test(6, "[[0,1],[0,2],[2,5],[3,4],[4,2]]", "[0,3]")
  test(5, "[[0,1],[2,1],[3,1],[1,4],[2,4]]", "[0,2,3]")
}