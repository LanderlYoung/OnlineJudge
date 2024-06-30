package problem841

class Solution {
  fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    val visited = BooleanArray(rooms.size)

    fun dfs(room: Int) {
      if (visited[room]) return

      visited[room] = true

      for (key in rooms[room]) {
        dfs(key)
      }
    }

    dfs(0)

    return visited.all { it }
  }
}

fun main() {

}