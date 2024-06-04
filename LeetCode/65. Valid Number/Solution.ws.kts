package problem65

class Solution {
    fun isNumber(s: String): Boolean {
        val str = s.trim()
        val com = str.split('e')
        if (com.size == 2) {
            return (isR(com[0]) && !com[0].endsWith('.')) && isZ(com[1])
        } else if (com.size == 1) {
            return isR(str)
        } else {
            return false
        }
    }

    fun isN(s: String):Boolean{
        if (s.isEmpty()) { return false }
        for (i in 0 until s.length) {
            if (!s[i].isDight) {
                return false
            }
        }
        return true
    }

    fun isZ(s: String):Boolean {
        if (s.isEmpty()) { return false }
        val c = s[0]
        if (c.isDight) {
            return isN(s)
        }

        return (c == '-' || c == '+') && isN(s.substring(1, s.length))
    }

    fun isR(s: String):Boolean {
        if (s.isEmpty()) { return false }
        val com = s.split('.')
        if (com.size == 2) {
            return when(com[0]) {
                "+", "-", "" -> isN(com[1])
                else -> isZ(com[0]) && (isN(com[1]) || com[1].isEmpty())
            }
        } else if (com.size == 1) {
            return isZ(s)
        } else {
            return false
        }
    }

    private val Char.isDight
        get() = this.toInt() in ('0'.toInt() .. '9'.toInt())
}

fun test(str: String) {
    println("$str:${Solution().isNumber(str)}")
}

test("0")
test(" 0.1")
test(".1")
test("0.1")
test("0.1.2.3")
test("abc")
test("1 a")
test("-1")
test("2e10")
test("2e1e0")
