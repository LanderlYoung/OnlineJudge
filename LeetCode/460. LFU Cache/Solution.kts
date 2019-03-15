class LFUCache(capacity: Int) {
    val age = 0;
    val map = TreeMap<Entry, Int>()

    fun get(key: Int): Int {

    }

    fun put(key: Int, value: Int) {

    }

    private class Entry(var f: Int = 0, key: Int) : Comparable<Entry> {
        override fun compareTo(other: Entry): Int {
            return f - other.f
        }
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * var obj = LFUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */