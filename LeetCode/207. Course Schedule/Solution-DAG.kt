package problem207_2

import matrix2d
import shouldBeEqualTo

class Solution {
  fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    // check if there is circle in a graph
    // -> check if it's a DAG (or DAGs)
    // -> if G is a DAG, G has a topological order, and vice versa
    val inDegree = HashMap<Int, Int>()
    (0..<numCourses).forEach { inDegree[it] = 0 }

    val graph = Array(numCourses) { HashSet<Int>() }
    prerequisites.forEach { (a, b) ->
      inDegree[a] = inDegree[a]!! + 1
      graph[b].add(a) // b -> a
    }

    var nextZero = inDegree.entries.find { it.value == 0 }?.key ?: return false
    while (nextZero != -1) {
      inDegree.remove(nextZero)

      var newNode = -1
      for (next in graph[nextZero]) {
        val new = inDegree[next]!! - 1
        inDegree[next] = new
        if (new == 0) {
          newNode = next
        }
      }

      // if graph is a single DAG, newNode must not be -1 if inDegree is not empty
      // if graph is a DAG forest, newNode may be -1, which means we need to find a new tree
      // if graph is not a DAG (has circle), newNode is -1, and cannot find a new start point
      nextZero =
        if (newNode == -1) {
          inDegree.entries.find { it.value == 0 }?.key ?: -1
        } else {
          newNode
        }
    }

    return inDegree.isEmpty()
  }
}

fun main() {
  fun test(
    numCourses: Int, prerequisites: /*Array<IntArray>*/ String,
    expected: Boolean,
  ) {
    val result = Solution().canFinish(numCourses, matrix2d(prerequisites))
    println("$numCourses -> $result == $expected -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(2, "[[0,1]]", true)
  test(3, "[[1,0],[1,2],[0,1]]", false)
  test(2, "[[1,0]]", true)
  test(2, "[[1,0],[0,1]]", false)
  test(5, "[[1,4],[2,4],[3,1],[3,2]]", true)
  test(20, "[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]", false)

  test(100,
    "[[1,0],[2,0],[2,1],[3,1],[3,2],[4,2],[4,3],[5,3],[5,4],[6,4],[6,5],[7,5],[7,6],[8,6],[8,7],[9,7],[9,8]," +
        "[10,8],[10,9],[11,9],[11,10],[12,10],[12,11],[13,11],[13,12],[14,12],[14,13],[15,13],[15,14],[16,14],[16,15]," +
        "[17,15],[17,16],[18,16],[18,17],[19,17],[19,18],[20,18],[20,19],[21,19],[21,20],[22,20],[22,21],[23,21],[23," +
        "22],[24,22],[24,23],[25,23],[25,24],[26,24],[26,25],[27,25],[27,26],[28,26],[28,27],[29,27],[29,28],[30,28]," +
        "[30,29],[31,29],[31,30],[32,30],[32,31],[33,31],[33,32],[34,32],[34,33],[35,33],[35,34],[36,34],[36,35],[37," +
        "35],[37,36],[38,36],[38,37],[39,37],[39,38],[40,38],[40,39],[41,39],[41,40],[42,40],[42,41],[43,41],[43,42]," +
        "[44,42],[44,43],[45,43],[45,44],[46,44],[46,45],[47,45],[47,46],[48,46],[48,47],[49,47],[49,48],[50,48],[50," +
        "49],[51,49],[51,50],[52,50],[52,51],[53,51],[53,52],[54,52],[54,53],[55,53],[55,54],[56,54],[56,55],[57,55],[57,56],[58,56],[58,57],[59,57],[59,58],[60,58],[60,59],[61,59],[61,60],[62,60],[62,61],[63,61],[63,62],[64,62],[64,63],[65,63],[65,64],[66,64],[66,65],[67,65],[67,66],[68,66],[68,67],[69,67],[69,68],[70,68],[70,69],[71,69],[71,70],[72,70],[72,71],[73,71],[73,72],[74,72],[74,73],[75,73],[75,74],[76,74],[76,75],[77,75],[77,76],[78,76],[78,77],[79,77],[79,78],[80,78],[80,79],[81,79],[81,80],[82,80],[82,81],[83,81],[83,82],[84,82],[84,83],[85,83],[85,84],[86,84],[86,85],[87,85],[87,86],[88,86],[88,87],[89,87],[89,88],[90,88],[90,89],[91,89],[91,90],[92,90],[92,91],[93,91],[93,92],[94,92],[94,93],[95,93],[95,94],[96,94],[96,95],[97,95],[97,96],[98,96],[98,97],[99,97]]",
    true)
}