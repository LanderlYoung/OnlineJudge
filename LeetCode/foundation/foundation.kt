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

/**
 * parse 1 dimension matrix
 * eg: "[855,82,158]"
 */
fun matrix1d(
  input: String,
): IntArray {
  return (input
    .trim('[', ']', ' ')
    .takeIf { it.isNotEmpty() } ?: return intArrayOf())
    .split(',')
    .map { it.toInt() }
    .toIntArray()
}

/**
 * parse 2 dimension matrix
 * eg: "[[855,82,158],[17,719,430]]"
 */
fun matrix2d(
  input: String,
): Array<IntArray> {
  return input.trimStart('[', ' ').trimEnd(']', ' ').split("],[")
    .map { matrix1d(it) }
    .toTypedArray<IntArray>()
}

/**
 * parse 1 dimension matrix
 * eg: ["A","B","C"]
 */
fun smatrix1d(
  input: String,
): Array<String> {
  return (input
    .trim('[', ']', ' ')
    .takeIf { it.isNotEmpty() } ?: return arrayOf())
    .split(',')
    .map { it.trim('"') }
    .toTypedArray()
}

/**
 * parse 2 dimension matrix
 * eg: [["A","B"],["C"],["B","C"],["D"]]
 */
fun smatrix2d(
  input: String,
): Array<Array<String>> {
  return input.trimStart('[', ' ').trimEnd(']', ' ').split("],[")
    .map { smatrix1d(it) }
    .toTypedArray()
}

