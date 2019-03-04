import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Author: landerlyoung@gmail.com
 * Date:   2016-07-24
 * Time:   22:48
 * Life with Passion, Code with Creativity.
 */
public class LRUCache {
    private final int mCapacity;
    private HashMap<Integer, Entry> mLruCache;

    private static final class Entry {
        Integer key;
        int value;
        Entry before;
        Entry after;

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", before=" + before +
                    ", after=" + after +
                    '}';
        }
    }

    private Entry mHead;
    private Entry mTail;

    public LRUCache(int capacity) {
        mCapacity = capacity;
        mLruCache = new LinkedHashMap<>(capacity);
    }

    private Entry newEntry(Integer key, int value) {
        Entry e = new Entry();
        e.key = key;
        e.value = value;

        if (mHead == null) {
            mHead = e;
            e.before = null;
        } else {
            e.before = mTail;
            e.after = mTail.after;
            mTail.after = e;
        }
        mTail = e;

        return e;
    }


    public int get(int key) {
        final Integer k = key;
        Entry entry = mLruCache.get(k);
        if (entry != null) {
            makeTail(entry);
            return entry.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        final Integer k = key;
        Entry entry = mLruCache.get(k);
        if (entry != null) {
            entry.value = value;
            makeTail(entry);
        } else {
            mLruCache.put(k, newEntry(k, value));
            if (mLruCache.size() > mCapacity) {
                removeEldest();
            }
        }
    }

    private void makeTail(Entry entry) {
        if (mTail == entry) return;

        if (mHead == entry) {
            mHead = entry.after;
        }

        if (entry.before != null) {
            entry.before.after = entry.after;
        }
        if (entry.after != null) {
            entry.after.before = entry.before;
        }


        entry.before = mTail;
        mTail.after = entry;
        entry.after = null;
        mTail = entry;
    }

    private void removeEldest() {
        final Entry head = mHead;
        mHead = head.after;
        if (mHead != null) {
            mHead.before = null;
        }
        mLruCache.remove(head.key);
    }

    public static void main(String[] args) {

        LRUCache c = new LRUCache(2);
        c.set(2, 1);
        c.set(1, 1);

        System.out.println(c.get(2));


        c.set(4, 1);


        System.out.println(c.get(1));
        System.out.println(c.get(2));

        System.out.println("===");


        c = new LRUCache(2);
        c.set(2, 1);
        c.set(1, 1);


        c.set(2, 3);


        c.set(4, 1);

        System.out.println(c.get(1));
        System.out.println(c.get(2));
    }
}
