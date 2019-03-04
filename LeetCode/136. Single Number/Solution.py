class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def singleNumber(self, nums):
        r = int(0)
        for x in nums:
            r ^= x
        return r
            
        