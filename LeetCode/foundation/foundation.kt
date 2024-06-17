import kotlin.time.measureTime

inline fun timedMain(block: () -> Unit) {
  val time =  measureTime(block)

  println()
  println("cost time ${time}ms")
}