package problem460_1

import shouldBeEqualTo
import timedTest
import java.io.File

// two layers of dual-linked-list, succeed in O(1)
class LFUCache(private val capacity: Int) {
  private val map = HashMap<Int, Entry>()

  /**
   * dual-linked-list with ascending order by Bucket.useCount
   */
  private val head = Bucket(0)

  val size: Int
    get() = map.size

  fun get(key: Int): Int {
    val entry = map[key]
    if (entry != null) {
      increaseEntryUseCount(entry)
      return entry.value
    }

    return -1
  }

  fun put(key: Int, value: Int) {
    val entry = map[key]
    if (entry == null) {
      if (map.size == capacity) {
        dropEntry()
      }
      insert(key, value)
    } else {
      entry.value = value
      increaseEntryUseCount(entry)
    }
  }

  private fun increaseEntryUseCount(entry: Entry) {
    val bucket = entry.bucket!!
    // remove from old bucket
    bucket.deleteEntry(entry)
    // obtain new bucket with useCount++, put to the head of it (most recent used)
    obtainNextBucket(bucket).appendEntryToHead(entry)

    if (bucket.isEmpty) {
      deleteBucket(bucket)
    }
  }

  private fun insert(key: Int, value: Int) {
    val entry = Entry(key, value)
    // obtain bucket, whose useCount == 1
    obtainNextBucket(head).appendEntryToHead(entry)

    // maintain map
    map[key] = entry
  }

  private fun dropEntry() {
    // drop:
    // 1. the least frequently used (leftmost bucket)
    // 2. the lease used (tail inside a bucket)
    val leastUsedBucket = head.next ?: return
    val leastRecentUsed = leastUsedBucket.tail

    leastUsedBucket.deleteEntry(leastRecentUsed)
    if (leastUsedBucket.isEmpty) {
      deleteBucket(leastUsedBucket)
    }

    // maintain map
    map.remove(leastRecentUsed.key)
  }

  private fun obtainNextBucket(bucket: Bucket): Bucket {
    val useCount = bucket.useCount + 1
    val n = bucket.next

    if (n != null && n.useCount == useCount) {
      // already have it
      return n
    }

    // didn't have it, add a new one
    val newBucket = Bucket(useCount)
    // bucket <-> newBucket <-> n

    bucket.next = newBucket
    newBucket.prev = bucket
    newBucket.next = n
    n?.prev = newBucket

    return newBucket
  }

  private fun deleteBucket(bucket: Bucket) {
    // p <-> bucket <-> n
    val p = bucket.prev!!
    val n = bucket.next

    p.next = n
    n?.prev = p
  }

  private data class Bucket(val useCount: Int) {
    // most recent used at head
    val head: Entry = Entry(-1, -1)
    var tail: Entry = head

    var prev: Bucket? = null
    var next: Bucket? = null

    val isEmpty: Boolean
      get() = head == tail

    fun deleteEntry(e: Entry) {
      // simple dual-linked operation
      check(e != head) { "wtf" }

      e.bucket = null

      // p <-> e <-> n
      val p = e.prev!!
      val n = e.next

      if (tail === e) {
        tail = p
      }

      p.next = n
      n?.prev = p

      e.prev = null
      e.next = null
    }

    fun appendEntryToHead(e: Entry) {
      // simple dual-linked operation
      e.bucket = this

      // head <-> e <-> n
      val n = head.next

      head.next = e
      e.prev = head
      e.next = n
      if (n != null) {
        n.prev = e
      } else {
        tail = e
      }
    }
  }

  private data class Entry(val key: Int, var value: Int) {
    var prev: Entry? = null
    var next: Entry? = null
    var bucket: Bucket? = null
  }

  // key, useCount
  fun debugStatus(): List<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()

    var bucket = head.next
    while (bucket != null) {
      // reverse iterate bucket
      var entry = bucket.tail
      while (entry != bucket.head) {
        list.add(entry.key to bucket.useCount)
        entry = entry.prev!!
      }
      bucket = bucket.next
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
fun test0() {
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

// failed test-case 25
fun main() = timedTest {
  test0()

  val line = File("LeetCode/460. LFU Cache/testcase-25.txt").readLines()
  val commands = line[0].subSequence(1, line[0].length - 1).split(",").map { it.trim('"') }

  val data = line[1].subSequence(1, line[1].length - 1).split("],[").map {
    it.trim('[', ']').split(',').map { it.toInt() }
  }

  lateinit var lfs: LFUCache
  lateinit var lfsBaseLine: problem460.LFUCache
  repeat(commands.size) { i ->
    // println("${i}. ${commands[i]} ${data[i]}")

    when (commands[i]) {
      "LFUCache" -> {
        lfs = LFUCache(data[i][0])
        lfsBaseLine = problem460.LFUCache(data[i][0])
      }

      "put" -> {
        if (i == 7528) {
          println()
        }

        lfs.put(data[i][0], data[i][1])
        lfsBaseLine.put(data[i][0], data[i][1])

        val one = lfs.debugStatus()
        val two = lfsBaseLine.debugStatus()
        // println("put ${one.size}, ${two.size}")
      }

      "get" -> {
        val r = lfs.get(data[i][0])
        val rr = lfsBaseLine.get(data[i][0])

        val one = lfs.debugStatus()
        val two = lfsBaseLine.debugStatus()
        // println("get ${one.size}, ${two.size}")
        r shouldBeEqualTo rr
        one shouldBeEqualTo two
      }
    }
  }
}