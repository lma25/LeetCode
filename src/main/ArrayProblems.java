package main;

import java.util.HashMap;

public class ArrayProblems {
	// LC-325: Maximum Size Sub-array Sum Equals k
	// Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
	// If there isn't one, return 0 instead.
	// Example 1:
	// Given nums = [1, -1, 5, -2, 3], k = 3,
	// return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
	public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> lookup = new HashMap<>();
        int sum = 0;
        int result = 0;
        lookup.put(0, 0);
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            if(lookup.containsKey(sum - k)){
                result = Math.max(result, i + 1 - lookup.get(sum - k));
            }
            if(!lookup.containsKey(sum)){
                lookup.put(sum, i + 1);
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
