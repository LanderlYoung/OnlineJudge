import kotlin.time.measureTime

inline fun timedTest(block: () -> Unit) {
  val time = measureTime(block)

  println()
  println("cost time $time")
}

infix fun <T> T.shouldBeEqualTo(value: T) {
  check(this == value) {
    "[$this] != [$value]"
  }
}