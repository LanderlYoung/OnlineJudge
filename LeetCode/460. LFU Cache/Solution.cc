#include <algorithm>
#include <list>
#include <vector>
#include <unordered_map>

struct Entry;
struct Bucket;

using EntryIt = std::list<Entry *>::iterator;
using BucketIt = std::list<Bucket *>::iterator;

struct Entry {
  int key_;
  int value_;
  Bucket *bueckt_;

  EntryIt position_;
};

struct Bucket {
  int useCount_;
  // most recent used at front
  std::list<Entry *> entries_{};
  BucketIt position_{};

  void addEntryToHead(Entry *entry) {
    entry->position_ = entries_.insert(entries_.begin(), entry);
    entry->bueckt_ = this;
  }

  void deleteEntry(Entry *entry) {
    entries_.erase(entry->position_);
    entry->bueckt_ = nullptr;
    entry->position_ = {};
  }

  Bucket &operator=(const Bucket &) = delete;
  Bucket &operator=(Bucket &&) = delete;
};

class LFUCache {
  std::unordered_map<int, Entry *> map_;
  std::list<Bucket *> buckets_;

  int capacity_;

 public:
  LFUCache(int capacity) : map_{}, buckets_{}, capacity_(capacity) {}

  ~LFUCache() {
    std::for_each(buckets_.begin(), buckets_.end(), [](auto *b) { delete b; });
    buckets_.clear();

    // delete all entry
    std::for_each(map_.begin(), map_.end(), [](auto &pair) { delete pair.second; });
    map_.clear();
  }

  int get(int key) {
    auto it = map_.find(key);
    if (it == map_.end()) {
      return -1;
    }

    auto entry = it->second;
    auto ret = entry->value_;
    increateEntryUseCount(entry);

    return ret;
  }

  void put(int key, int value) {
    auto it = map_.find(key);
    if (it != map_.end()) {
      auto entry = it->second;
      entry->value_ = value;
      increateEntryUseCount(entry);
    } else {
      if (map_.size() == capacity_) {
        dropEntry();
      }
      insertEntry(key, value);
    }
  }

 private:
  void increateEntryUseCount(Entry *entry) {
    auto bucket = entry->bueckt_;

    // remove from bucket
    bucket->deleteEntry(entry);

    auto it = bucket->position_;
    ++it;
    auto &newBucket = getNextBucketUpon(bucket->useCount_ + 1, it);
    newBucket.addEntryToHead(entry);

    // also remove empty old bucket
    deleteEmptyBucket(bucket);
  }

  void insertEntry(int key, int value) {
    auto entry = new Entry{};
    entry->key_ = key;
    entry->value_ = value;

    auto &bucket = getNextBucketUpon(1, buckets_.begin());
    bucket.addEntryToHead(entry);

    // map operation
    map_[key] = entry;
  }

  void deleteEmptyBucket(Bucket *bucket) {
    if (bucket->entries_.empty()) {
      buckets_.erase(bucket->position_);
      delete bucket;
    }
  }

  void dropEntry() {
    if (buckets_.empty()) return;

    auto bucketIt = buckets_.begin();
    auto bucket = *bucketIt;

    // drop last
    auto entry = bucket->entries_.back();
    bucket->deleteEntry(entry);

    // also delete empty bucket
    deleteEmptyBucket(bucket);

    // map operation
    map_.erase(entry->key_);

    delete entry;
  }

  // on `upon` get or create next buffer
  Bucket &getNextBucketUpon(int targetUseCount, BucketIt upon) {
    Bucket *bucket = nullptr;
    if (upon != buckets_.end()) {
      bucket = *upon;
    }
    if (bucket != nullptr && bucket->useCount_ == targetUseCount) {
      return *bucket;
    } else {
      // new bucket
      auto newBucket = new Bucket{};
      auto newBucketIt = buckets_.insert(upon, newBucket);
      newBucket->position_ = newBucketIt;
      newBucket->useCount_ = targetUseCount;

      return *newBucket;
    }
  }

 public:
  // test only
  // vector<key, count>
  std::vector<std::pair<int, int>> debugStatus() const {
    std::vector<std::pair<int, int>> result;

    std::for_each(buckets_.begin(), buckets_.end(), [&result](auto *bucket) {
      std::for_each(
          bucket->entries_.rbegin(), bucket->entries_.rend(),
          [&result, bucket](auto *entry) { result.emplace_back(entry->key_, bucket->useCount_); });
    });

    return result;
  }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */

#include <cassert>

using DebugList = std::vector<std::pair<int, int>>;

void test0() {
  LFUCache c(2);

  assert(c.get(0) == -1);

  c.put(0, 0);
  c.put(1, 1);

  bool testEqual = c.debugStatus() == DebugList{{0, 1}, {1, 1}};
  assert(testEqual);

  assert(c.get(1) == 1);

  testEqual = c.debugStatus() == DebugList{{0, 1}, {1, 2}};
  assert(testEqual);

  c.put(2, 2);

  testEqual = c.debugStatus() == DebugList{{2, 1}, {1, 2}};
  assert(testEqual);

  assert(c.get(0) == -1);
  assert(c.get(2) == 2);

  testEqual = c.debugStatus() == DebugList{{1, 2}, {2, 2}};
  assert(testEqual);

  c.put(3, 3);
  testEqual = c.debugStatus() == DebugList{{3, 1}, {2, 2}};
  assert(testEqual);

  c.put(4, 4);
  testEqual = c.debugStatus() == DebugList{{4, 1}, {2, 2}};
  assert(testEqual);

  assert(c.get(0) == -1);
  assert(c.get(1) == -1);
  assert(c.get(2) == 2);
  assert(c.get(3) == -1);
  assert(c.get(4) == 4);
}

void test1() {
  // [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
  LFUCache c(2);
  c.put(1, 1);
  c.put(2, 2);
  assert(c.get(1) == 1);

  c.put(3, 3);
  assert(c.get(2) == -1);
  assert(c.get(3) == 3);
  c.put(4, 4);
  assert(c.get(1) == -1);
  assert(c.get(3) == 3);
  assert(c.get(4) == 4);
}

void test2() {
  // [[2],[3,1],[2,1],[2,2],[4,4],[2]]
  LFUCache c(2);
  c.put(3, 1);
  c.put(2, 1);
  c.put(2, 2);
  c.put(4, 4);
  c.get(2);
}

int main() {
  test0();
  test1();
  test2();
}
