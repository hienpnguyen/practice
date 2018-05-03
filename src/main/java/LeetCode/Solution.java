package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    /*
    https://leetcode.com/problems/reverse-integer/description/
    Given a 32-bit signed integer, reverse digits of an integer.

    Example 1:

    Input: 123
    Output: 321
    Example 2:

    Input: -123
    Output: -321
    Example 3:

    Input: 120
    Output: 21
     */
    public int reverse(int x) {
        try {
            String s = Integer.toString(x);
            if (x < 0) {
                s = s.substring(1, s.length());
            }
            s = new StringBuilder(s).reverse().toString();

            if (Character.getNumericValue(s.charAt(0)) % 10 == 0) {
                s = s.substring(1, s.length());
            }

            if (x < 0) {
                s = "-" + s;
            }

            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isPalindrome(int x) {
        boolean isPalindrome = false;

        try {

            String s = Integer.toString(x);

            String reverse = new StringBuilder(Integer.toString(x)).reverse().toString();

            if(s.equalsIgnoreCase(reverse)){
                isPalindrome = true;
            }
        }
        catch(NumberFormatException e){
            return false;
        }
        return isPalindrome;
    }

    /*
    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:

    The solution set must not contain duplicate triplets.

    Example:

    Given array nums = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> solutionList = new ArrayList<List<Integer>>();

        Arrays.sort(nums);

        int sum;

        if(nums.length <= 2){
            return solutionList;
        }

        if(nums.length == 3){
            sum = nums[0] + nums[1] + nums[2];
            if(sum == 0){
                List<Integer> solution = new ArrayList<Integer>();

                solution.add(nums[0]);
                solution.add(nums[1]);
                solution.add(nums[2]);

                solutionList.add(solution);
            }
            return solutionList;
        }

        for(int i = 1; i < nums.length-2; i++){
            int left = i - 1;
            int right = i + 1;
            sum = nums[left] + nums[i] + nums[right];

            if (sum == 0) {
                List<Integer> solution = new ArrayList<Integer>();

                solution.add(nums[left]);
                solution.add(nums[i]);
                solution.add(nums[right]);

                solutionList.add(solution);
            }

            while(left != 0 && right != nums.length) {
                if (sum < 0 && right < nums.length - 1) {
                    right++;
                }
                if (sum > 0 && left > 0) {
                    left--;
                }
                sum = nums[left] + nums[i] + nums[right];

                if (sum == 0) {
                    List<Integer> solution = new ArrayList<Integer>();

                    solution.add(nums[left]);
                    solution.add(nums[i]);
                    solution.add(nums[right]);

                    solutionList.add(solution);

                    break;
                }
            }
        }

        return solutionList;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null){
            return null;
        }

        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode mergedList = new ListNode(-1);
        ListNode head = mergedList;

        if(l1.val < l2.val){
            mergedList.val = l1.val;
            l1 = l1.next;
        }
        else{
            mergedList.val = l2.val;
            l2 = l2.next;
        }

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                mergedList.next = l1;
                l1 = l1.next;
            }
            else{
                mergedList.next = l2;
                l2 = l2.next;
            }
            mergedList = mergedList.next;
        }
        if(l1 == null && l2 != null){
            mergedList.next = l2;
        }
        if(l1 != null && l2 == null){
            mergedList.next = l1;
        }

        return head;
    }

    public String intToRoman(int num) {
        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        return M[(num/1000)%10] + C[(num/100)%10] + X[(num/10)%10] + I[(num)%10];
    }

    public List<List<Integer>> permute(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int n : num) {
            int size = res.size();
            for (; size > 0; size--) {
                List<Integer> r = res.pollFirst();
                System.out.println("Size is: " + size+" r is: " + r);
                for (int i = 0; i <= r.size(); i++) {
                    List<Integer> t = new ArrayList<Integer>(r);
                    System.out.println("i is: "+i+" n is: "+n);
                    t.add(i, n);
                    System.out.println("t is: "+t);
                    res.add(t);
                    System.out.println("res is: " + res);
                }
            }
        }
        return res;
    }
}
