package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class StringProblems {

	// LC-3: return the longest substring without repeating characters.
	public int longestSubstring(String s){
		int left = 0;
		int right = 0;
		int longest = 0;
		HashSet<Character> lookup = new HashSet<>();
		while(right < s.length()){
			if(!lookup.contains(s.charAt(right))){
				lookup.add(s.charAt(right));
				longest = Math.max(longest, right - left + 1);
				++right;
			}else{
				while(left < right && s.charAt(left) != s.charAt(right)){
					lookup.remove(s.charAt(left++));
				}
				++left;
				++right;
			}
		}
		longest = Math.max(longest, left - right);
		return longest;
	}
	public void test_longestSubstring(){
		System.out.println(longestSubstring("aaaabcaaaaaabcd"));
	}

	// LC-5: find the longest palindromic substring.
	public String longestPalindrome(String s){
		boolean[][] M = new boolean[s.length()][s.length()];
		for(int i = 0; i < s.length(); ++i){
			M[i][i] = true;
		}
		int left = 0;
		int right = 0;
		for(int i = 0; i < s.length() - 1; ++i){
			if(s.charAt(i) == s.charAt(i + 1)){
				M[i][i + 1] = true;
				left = i;
				right = i + 1;
			}
		}
		for(int j = 2; j < s.length(); ++j){
			for(int i = 0; i < s.length() - j; ++i){
				if(M[i + 1][i + j - 1] && s.charAt(i) == s.charAt(i + j)){
					M[i][i + j] = true;
					if(right - left < j){
						left = i;
						right = i + j;
					}
				}
			}
		}
		return s.substring(left, right + 1);
	}

	// LC-13: Roman to Integer.
	public int romanToInt(String s){
		HashMap<Character, Integer> lookup = new HashMap<>();
		lookup.put('I', 1);
		lookup.put('V', 5);
        lookup.put('X', 10);
        lookup.put('L', 50);
        lookup.put('C', 100);
        lookup.put('D', 500);
        lookup.put('M', 1000);
        int result = 0;
        for(int i = 0; i < s.length() - 1; ++i){
        	result += lookup.get(s.charAt(i)) < lookup.get(s.charAt(i + 1)) ? 
        			-lookup.get(s.charAt(i)) : lookup.get(s.charAt(i));
        }
        return result + lookup.get(s.charAt(s.length() - 1));
	}

	// LC-14: fin the longest common prefix.
	public String longestCommonPrefix(String[] array){
		int index = 0;
		boolean flag = true;
		while(flag){
			if(index >= array[0].length()){
				++index;
				break;
			}
			char c = array[0].charAt(index);
			for(int i = 1; i < array.length; ++i){
				if(index >= array[i].length() || array[i].charAt(index) != c){
					flag = false;
					break;
				}
			}
			++index;
		}
		return array[0].substring(0, index - 1);
	}

	// LC-17: find all the letter combinations of a phone number.
	public void letterCombinations(int index, String digits, StringBuilder sb, List<String> result, String[] lookup){
		if(index == digits.length()){
			result.add(sb.toString());
			return;
		}
		String cur = lookup[digits.charAt(index) - '0'];
		for(int i = 0; i < cur.length(); ++i){
			sb.append(cur.charAt(i));
			letterCombinations(index + 1, digits, sb, result, lookup);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	public List<String> letterCombinations(String digits){
		List<String> result = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		String[] lookup = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//		if(digits == null || digits.length() == 0) return result;
		letterCombinations(0, digits, sb, result, lookup);
		return result;
	}

	// LC-20: check if the string of parentheses is valid.
	public boolean isValid(String s){
		LinkedList<Character> stack = new LinkedList<>();
		for(int i  = 0; i < s.length(); ++i){
			if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) =='{'){
				stack.offerLast(s.charAt(i));
			}else if(stack.isEmpty()){
				return false;
			}else if(s.charAt(i) == ')' && stack.peekLast() == '('
					|| s.charAt(i) == ']' && stack.peekLast() == '['
					|| s.charAt(i) == '}' && stack.peekLast() == '{'){
				stack.pollLast();
			}else{
				return false;
			}
		}
		return stack.isEmpty();
	}

	// LC-22: find all valid parentheses permutations.
	public void generateParentheses(int left, int right, int n, StringBuilder sb, List<String> result){
		if(left == n && right == n){
			result.add(sb.toString());
			return;
		}
		if(left < n){
			sb.append('(');
			generateParentheses(left + 1, right, n, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
		if(right < left){
			sb.append(')');
			generateParentheses(left, right + 1, n, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	public List<String> generateParentheses(int n){
		List<String> result = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		generateParentheses(0, 0, n, sb, result);
		return result;
	}

	// LC-38: count and say. Example: 1, 11, 21, 1211, 111221, ....
	public String countAndSay(int n){
		String result = "1";
		for(; n > 1; --n){
			StringBuilder temp = new StringBuilder();
			int left = 0;
			int right = 0;
			int count = 0;
			while(right < result.length()){
				if(result.charAt(left) == result.charAt(right)){
					++count;
					++right;
				}else{
					temp.append(count);
					temp.append(result.charAt(left));
					left = right;
					count = 0;
				}
			}
			temp.append(count);
			temp.append(result.charAt(left));
			result = temp.toString();
		}
		return result;
	}
	public void test_countAndSay(){
		System.out.println(countAndSay(5));
	}

	public String multiply(String a, String b){
		StringBuilder sb = new StringBuilder();
		for(int i = a.length() - 1; i >= 0; --i){
			int sum = 0;
			int carry = 0;
			for(int j = b.length() - 1; j >= 0; --j){
				sum = carry;
				int location = a.length() + b.length() - i - j - 2;
				if(location < sb.length()){
					sum += sb.charAt(location) - '0';
				}
				sum += (a.charAt(i) - '0') * (b.charAt(j) - '0');
				carry = sum / 10;
				sum = sum % 10;
				if(location < sb.length()){
					sb.setCharAt(location, (char)('0' + sum));
				}else{
					sb.append(sum);
				}
			}
			if(carry > 0){
				sb.append(carry);
			}
		}
		while(sb.length() > 0 && sb.charAt(sb.length() - 1) == '0'){
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.length() == 0 ? "0" : sb.reverse().toString();
	}
	public void test_multiply(){
		System.out.println(multiply("112345", "0"));
	}

	// LC-49: group anagrams.
	public class CharArrayComparator implements Comparator<Character>{
		@Override
		public int compare(Character c1, Character c2){
			if(c1 == c2){
				return 0;
			}
			return c1 < c2 ? -1 : 1;
		}
	}
	public List<List<String>> groupAnagrams(String[] strs){
		HashMap<String, LinkedList<String>> table = new HashMap<>();
		for(int i = 0; i < strs.length; ++i){
			char[] temp = strs[i].toCharArray();
			Arrays.sort(temp);
			StringBuilder sb = new StringBuilder();
			sb.append(temp);
			String key = sb.toString();
			if(table.containsKey(key)){
				table.get(key).add(strs[i]);
			}else{
				LinkedList<String> l = new LinkedList<>();
				l.add(strs[i]);
				table.put(key, l);
			}
		}
		List<List<String>> result = new LinkedList<>();
		for(String key : table.keySet()){
			Collections.sort(table.get(key));
			result.add((List)table.get(key));
		}
		return result;
	}
	public void test_groupAnagrams(){
		String[] strs = {"ab", "ba"};
		groupAnagrams(strs);
	}
	
	// LC-58:
	
	// LC-293: flip game.
	public List<String> generatePossibleNextMoves(String s) {
        StringBuilder sb = new StringBuilder(s);
        List<String> result = new LinkedList<>();
        int index = 0;
        while(index < s.length() - 1){
            if(sb.charAt(index) == sb.charAt(index + 1) && sb.charAt(index) == '+'){
                sb.setCharAt(index, '-');
                sb.setCharAt(index + 1, '-');
                result.add(sb.toString());
                sb.setCharAt(index, '+');
                sb.setCharAt(index + 1, '+');
            }
            ++index;
        }
        return result;
    }
	
	public static void main(String[] args) {
		StringProblems solution = new StringProblems();
//		solution.test_longestSubstring();
//		solution.test_countAndSay();
//		solution.test_multiply();
		solution.test_groupAnagrams();
	}

}
