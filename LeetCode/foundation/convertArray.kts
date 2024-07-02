import java.io.InputStreamReader

/* [[54,95,4],[99,46,3],[29,21,3],[96,72,8],[49,43,3],[11,20,3],[2,57,1],[69,51,7],[97,1,10],[85,45,2],[38,47,1],[83, 75,3],[65,59,3],[33,4,1],[32,10,2],[20,97,8],[35,37,3]]
 */

val transformed =
  InputStreamReader(System.`in`).readText()
    .trim(' ', '[').trim(' ', ']').split("],[")
    .map { it.split(',').joinToString(prefix = "intArrayOf(", postfix = ")", separator = ", ") }
    .joinToString(prefix = "arrayOf(", postfix = ")", separator = ", ")

println(transformed)