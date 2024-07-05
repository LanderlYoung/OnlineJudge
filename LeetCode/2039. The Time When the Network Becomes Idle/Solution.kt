package problem2039

import matrix1d
import matrix2d
import shouldBeEqualTo

class Solution {
  fun networkBecomesIdle(edges: Array<IntArray>, patience: IntArray): Int {
    // reverse bfs, get distance of each data server to master server
    val graph = Array(patience.size) { HashSet<Int>() }
    edges.forEach { (from, to) ->
      graph[from].add(to)
      graph[to].add(from)
    }

    val delay = IntArray(patience.size) { -1 }
    val queue = ArrayDeque<Int>()
    queue.addLast(0)
    delay[0] = 0
    while (queue.isNotEmpty()) {
      val node = queue.removeFirst()
      for (next in graph[node]) {
        if (delay[next] == -1) {
          delay[next] = delay[node] + 1
          queue.addLast(next)
        }
      }
    }

    // delay, patient
    // round-trip = delay * 2
    // retry start-at patient, if (patient > round-trip), patient + round-trip
    var maxIdle = 0
    for (i in 1..<patience.size) {
      // eg: delay = 2, p = 1
      //     roundTrip = 4
      // second-0: send             ;
      // second-1: send going       ; out of patient, resend-0
      // second-2: master-received  ; resend-1
      // second-3: reply going      ; resend-2
      // second-4: received         ; stop resend
      // second-5:                  ; received resend-0
      // second-6:                  ; received resend-1
      // second-7:                  ; received resend-2
      // second-8:         --------IDLE-------

      val d = delay[i]
      val p = patience[i]
      val roundTrip = d * 2

      val idel = (roundTrip - 1) / p * p + roundTrip + 1
      maxIdle = maxOf(maxIdle, idel)
    }

    return maxIdle
  }

  /*
val idel = if (roundTrip > p) {
  // the maxValue multiple of p and < roundTrip
  val lastResend = (roundTrip - 1) / p * p
  lastResend + roundTrip
} else {
  // (roundTrip - 1) / p -> 0
  roundTrip
} + 1
 */

}

fun main() {
  fun test(
    edges: String, // Array<IntArray>
    patience: String, // IntArray
    expected: Int,
  ) {
    val result = Solution().networkBecomesIdle(matrix2d(edges), matrix1d(patience))
    println("$ -> $result == $expected -> ${result == expected}")
    result shouldBeEqualTo expected
  }

  test(edges = "[[0,1],[1,2]]", patience = "[0,2,1]", 8)
  test(edges = "[[0,1],[0,2],[1,2]]", patience = "[0,10,10]", 3)
  test(edges = "[[5,7],[15,18],[12,6],[5,1],[11,17],[3,9],[6,11],[14,7],[19,13],[13,3],[4,12],[9,15],[2,10],[18,4]," +
      "[5,14],[17,5],[16,2],[7,1],[0,16],[10,19],[1,8]]",
    patience = "[0,2,1,1,1,2,2,2,2,1,1,1,2,1,1,1,1,2,1,1]",
    67)
}