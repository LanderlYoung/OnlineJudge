package problem2115

import equalsWithoutOrder
import shouldBeEqualToWithoutOrder
import smatrix1d
import smatrix2d

class Solution {
  fun findAllRecipes(recipes: Array<String>, ingredients: List<List<String>>, supplies: Array<String>): List<String> {
    val inDegree = hashMapOf<String, Int>()

    // use KEY to make VALUE
    val graph = hashMapOf<String, MutableSet<String>>()
    for (i in recipes.indices) {
      val recipe = recipes[i]
      for (ingredient in ingredients[i]) {
        graph.getOrPut(ingredient) { mutableSetOf() }
          .add(recipe)

        inDegree[recipe] = (inDegree[recipe] ?: 0) + 1
        if (!inDegree.contains(ingredient)) {
          inDegree[ingredient] = 0
        }
      }
    }

    val queue = ArrayDeque<String>()
    // start search from given nodes
    // different from normal topological sort, we don't search from 0-in-degree nodes
    supplies.forEach { queue.add(it) }

    val madeOut = mutableSetOf<String>()

    while (queue.isNotEmpty()) {
      val made = queue.removeFirst()
      madeOut.add(made)

      for (next in graph[made] ?: emptyList()) {
        val degree = inDegree[next]!! - 1
        inDegree[next] = degree
        if (degree == 0) {
          // we have supplies, some of them may have -1 in degree
          queue.add(next)
        }
      }
    }

    return recipes.filter { madeOut.contains(it) }
  }
}

fun main() {
  fun test(
    recipes: String, ingredients: String, supplies: String,
    expected: String,
  ) {
    val exp = smatrix1d(expected).toList()
    val result =
      Solution().findAllRecipes(smatrix1d(recipes), smatrix2d(ingredients).map { it.toList() }, smatrix1d(supplies))
    println("$ -> $result == $exp -> ${result equalsWithoutOrder exp}")
    result shouldBeEqualToWithoutOrder exp
  }

  test(recipes = """["bread"]""",
    ingredients = """[["yeast","flour"]]""",
    supplies = """["yeast"]""",
    expected = "[]")

  test(recipes = """["bread"]""",
    ingredients = """[["yeast","flour"]]""",
    supplies = """["yeast","flour","corn"]""",
    expected = """["bread"]""")

  test(recipes = """["bread","sandwich"]""",
    ingredients = """[["yeast","flour"],["bread","meat"]]""",
    supplies = """["yeast","flour","meat"]""",
    expected = """["bread","sandwich"]""")

  test(recipes = """["bread","sandwich","burger"]""",
    ingredients = """[["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]]""",
    supplies = """["yeast","flour","meat"]""",
    expected = """["bread","sandwich","burger"]""")

}