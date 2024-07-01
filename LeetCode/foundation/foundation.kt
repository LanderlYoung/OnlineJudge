import kotlin.time.measureTime

inline fun timedTest(name: String = "", block: () -> Unit) {
  val time = measureTime(block)

  println()
  if (name.isEmpty()) {
    println("cost time $time")
  } else {
    println("$name cost time $time")
  }
}

infix fun <T> T.shouldBeEqualTo(value: T) {
  check(this == value) {
    "[$this] != [$value]"
  }
}

infix fun <T> Collection<T>.shouldBeEqualToWithoutOrder(value: Collection<T>) {
  check(this equalsWithoutOrder value) {
    "[$this] != [$value]"
  }
}

infix fun <T> Collection<T>.equalsWithoutOrder(value: Collection<T>): Boolean {
  return this.size == value.size && HashSet(this) == HashSet(value)
}