package problem460

import shouldBeEqualTo

// dual-linked-list, O(N) -- TLE...
class LFUCache(private val capacity: Int) {
    private val map = HashMap<Int, Entry>()

    // dual-linked-list with ascending order by Entry.useCount
    private val head = Entry(0, 0, 0)

    fun get(key: Int): Int {
        val entry = map[key]
        if (entry != null) {
            entry.useCount++
            shiftToRight(entry)

            return entry.value
        }

        return -1
    }

    fun put(key: Int, value: Int) {
        val entry = map[key]
        if (entry == null) {
            if (map.size == capacity) {
                drop()
            }
            insert(key, value)
        } else {
            entry.value = value
            entry.useCount++
            shiftToRight(entry)
        }
    }

    private fun insert(key: Int, value: Int) {
        val entry = Entry(key, value, 1)

        // put entry at the head of list
        // head <-> entry <-> n

        val n = head.next
        head.next = entry
        entry.prev = head
        entry.next = n
        n?.prev = entry

        shiftToRight(entry)

        // maintain map
        map[key] = entry
    }

    private fun drop() {
        // remove list head
        val n = head.next
        if (n != null) {
            val nn = n.next
            head.next = n.next
            nn?.prev = head

            // maintain map
            map.remove(n.key)
        }
    }

    private fun shiftToRight(entry: Entry) {
        // useCount++, shift item to the right of list

        // note: when there is a tie (i.e., two or more keys with the same frequency), the least recently used key
        // would be invalidated.
        while (entry.next != null && entry.useCount >= entry.next!!.useCount) {
            swapWithNext(entry)
        }
    }

    private fun swapWithNext(entry: Entry) {
        val p = entry.prev!!
        val n = entry.next!!
        val nn = n.next
        // p <-> n <-> entry <-> nn

        p.next = n

        entry.prev = n
        entry.next = nn

        n.prev = p
        n.next = entry

        nn?.prev = entry
    }

    private data class Entry(val key: Int, var value: Int, var useCount: Int = 0) {
        var prev: Entry? = null
        var next: Entry? = null
    }

    // key, useCount
    fun debugStatus(): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()

        var entry = head.next
        while (entry != null) {
            list.add(entry.key to entry.useCount)
            entry = entry.next
        }

        return list
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * var obj = LFUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
fun main() {
    val lfu = LFUCache(2)
    lfu.debugStatus() shouldBeEqualTo emptyList()

    lfu.get(0) shouldBeEqualTo -1

    lfu.put(0, 0)
    lfu.debugStatus() shouldBeEqualTo listOf(0 to 1)

    lfu.get(0) shouldBeEqualTo 0
    lfu.debugStatus() shouldBeEqualTo listOf(0 to 2)

    lfu.put(1, 1)
    lfu.debugStatus() shouldBeEqualTo listOf(1 to 1, 0 to 2)

    lfu.get(1) shouldBeEqualTo 1
    lfu.debugStatus() shouldBeEqualTo listOf(0 to 2, 1 to 2)

    lfu.put(2, 2)
    lfu.debugStatus() shouldBeEqualTo listOf(2 to 1, 1 to 2)

    lfu.get(0) shouldBeEqualTo -1

    lfu.get(2) shouldBeEqualTo 2
    lfu.debugStatus() shouldBeEqualTo listOf(1 to 2, 2 to 2)

    lfu.put(3, 3)
    lfu.debugStatus() shouldBeEqualTo listOf(3 to 1, 2 to 2)

    lfu.put(4, 4)
    lfu.debugStatus() shouldBeEqualTo listOf(4 to 1, 2 to 2)

    lfu.get(0) shouldBeEqualTo -1
    lfu.get(1) shouldBeEqualTo -1
    lfu.get(2) shouldBeEqualTo 2
    lfu.get(3) shouldBeEqualTo -1
    lfu.get(4) shouldBeEqualTo 4
}
