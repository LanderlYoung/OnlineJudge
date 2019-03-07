import kotlin.math.sqrt

/**
 * Definition for a point.
 * class Point(
 *     var x: Int = 0,
 *     var y: Int = 0
 * )
 */
class Solution {
    fun maxPoints(points: Array<Point>): Int {
        if (points.isEmpty()) return 0
        if (points.size == 1) return 1
        val points = points.map {
            MyPoint(it.x, it.y)
        }
        val ks = HashMap<Pair<Double, Double>, Pair<Int, MutableCollection<MyPoint>>>()
        for (i in 1 until points.size) {
            for (j in 0 until i) {
                val k = k(points[i], points[j])
                var value = ks[k] ?: 0 to HashSet<MyPoint>()
                value.second.add(points[i])
                value.second.add(points[j])
                ks[k] = (value.first + 1) to value.second
            }
        }

        val p = ks.values.maxBy { it.first } ?: return 0
        val count = points.count { it in p.second }

        println(ks)
        println()
        println(p)
        println(count)

        return count
    }

    private fun k(p1: MyPoint, p2: MyPoint): Pair<Double, Double> {
        var k = (p1.y - p2.y) / (p1.x - p2.x).toDouble()
        var b = p1.y - p1.x * k
        if (k.isInfinite()) {
            k = Double.POSITIVE_INFINITY
            b = p1.x.toDouble()
        }
        return k to b
    }
}

data class MyPoint(var x: Int = 0, var y: Int = 0)

// test

typealias Point = MyPoint


Solution().maxPoints(
        arrayOf(
                MyPoint(-240, -657), MyPoint(-27, -188), MyPoint(-616, -247), MyPoint(-264, -311), MyPoint(-352, -393), MyPoint(-270, -748), MyPoint(3, 4), MyPoint(-308, -87), MyPoint(150, 526), MyPoint(0, -13), MyPoint(-7, -40), MyPoint(-3, -10), MyPoint(-531, -892), MyPoint(-88, -147), MyPoint(4, -3), MyPoint(-873, -555), MyPoint(-582, -360), MyPoint(-539, -207), MyPoint(-118, -206), MyPoint(970, 680), MyPoint(-231, -47), MyPoint(352, 263), MyPoint(510, 143), MyPoint(295, 480), MyPoint(-590, -990), MyPoint(-236, -402), MyPoint(308, 233), MyPoint(-60, -111), MyPoint(462, 313), MyPoint(-270, -748), MyPoint(-352, -393), MyPoint(-35, -148), MyPoint(-7, -40), MyPoint(440, 345), MyPoint(388, 290), MyPoint(270, 890), MyPoint(10, -7), MyPoint(60, 253), MyPoint(-531, -892), MyPoint(388, 290), MyPoint(-388, -230), MyPoint(340, 85), MyPoint(0, -13), MyPoint(770, 473), MyPoint(0, 73), MyPoint(873, 615), MyPoint(-42, -175), MyPoint(-6, -8), MyPoint(49, 176), MyPoint(308, 222), MyPoint(170, 27), MyPoint(-485, -295), MyPoint(170, 27), MyPoint(510, 143), MyPoint(-18, -156), MyPoint(-63, -316), MyPoint(-28, -121), MyPoint(396, 304), MyPoint(472, 774), MyPoint(-14, -67), MyPoint(-5, 7), MyPoint(-485, -295), MyPoint(118, 186), MyPoint(-154, -7), MyPoint(-7, -40), MyPoint(-97, -35), MyPoint(4, -9), MyPoint(-18, -156), MyPoint(0, -31), MyPoint(-9, -124), MyPoint(-300, -839), MyPoint(-308, -352), MyPoint(-425, -176), MyPoint(-194, -100), MyPoint(873, 615), MyPoint(413, 676), MyPoint(-90, -202), MyPoint(220, 140), MyPoint(77, 113), MyPoint(-236, -402), MyPoint(-9, -124), MyPoint(63, 230), MyPoint(-255, -118), MyPoint(472, 774), MyPoint(-56, -229), MyPoint(90, 228), MyPoint(3, -8), MyPoint(81, 196), MyPoint(970, 680), MyPoint(485, 355), MyPoint(-354, -598), MyPoint(-385, -127), MyPoint(-2, 7), MyPoint(531, 872), MyPoint(-680, -263), MyPoint(-21, -94), MyPoint(-118, -206), MyPoint(616, 393), MyPoint(291, 225), MyPoint(-240, -657), MyPoint(-5, -4), MyPoint(1, -2), MyPoint(485, 355), MyPoint(231, 193), MyPoint(-88, -147), MyPoint(-291, -165), MyPoint(-176, -229), MyPoint(154, 153), MyPoint(-970, -620), MyPoint(-77, 33), MyPoint(-60, -111), MyPoint(30, 162), MyPoint(-18, -156), MyPoint(425, 114), MyPoint(-177, -304), MyPoint(-21, -94), MyPoint(-10, 9), MyPoint(-352, -393), MyPoint(154, 153), MyPoint(-220, -270), MyPoint(44, -24), MyPoint(-291, -165), MyPoint(0, -31), MyPoint(240, 799), MyPoint(-5, -9), MyPoint(-70, -283), MyPoint(-176, -229), MyPoint(3, 8), MyPoint(-679, -425), MyPoint(-385, -127), MyPoint(396, 304), MyPoint(-308, -352), MyPoint(-595, -234), MyPoint(42, 149), MyPoint(-220, -270), MyPoint(385, 273), MyPoint(-308, -87), MyPoint(-54, -284), MyPoint(680, 201), MyPoint(-154, -7), MyPoint(-440, -475), MyPoint(-531, -892), MyPoint(-42, -175), MyPoint(770, 473), MyPoint(118, 186), MyPoint(-385, -127), MyPoint(154, 153), MyPoint(56, 203), MyPoint(-616, -247)
        )
)
