package LeetCode;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public List<List<Integer>> permute(int[] nums) {
        return permute(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    private List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.size() == 0) {
            return permutations;
        }
        if (nums.size() == 1) {
            List<Integer> permutation = new ArrayList<>();
            permutation.add(nums.get(0));
            permutations.add(permutation);
            return permutations;
        }

        List<List<Integer>> smallPermutations = permute(nums.subList(1, nums.size()));
        int first = nums.get(0);
        for(List<Integer> permutation : smallPermutations) {
            for (int i = 0; i <= permutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<>(permutation);
                newPermutation.add(i, first);
                permutations.add(newPermutation);
            }
        }
        return permutations;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;

        int[]nums = IntStream.concat(IntStream.of(nums1), IntStream.of(nums2)).toArray();

        if(nums.length == 0){
            return median;
        }

        if(nums.length == 1){
            return nums[0];
        }

        if(nums.length == 2){
            return (double) (nums[0] + nums[1])/2;
        }

        Arrays.sort(nums);

        if(nums.length%2 == 1){
            median = nums[nums.length/2];
        }
        else{
            median = (double) (nums[nums.length/2] + nums[(nums.length/2)-1])/2;
        }

        return median;
    }

    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null){
            return null;
        }

        ListNode dummy = new ListNode(0);

        dummy.next = head;

        ListNode l = head;
        ListNode t = head;

        for(int i = 0; i < n; i++){
            l = l.next;
        }

        while(l.next != null){
            l = l.next;
            t = t.next;
        }

        t.next = t.next.next;

        return dummy.next;
    }

    public String convert(String s, int numRows) {
        String convertedS = "";

        //char[] sArray = s.toCharArray();

        String[] sArray = s.split("");

        String[] builtStrings = new String[numRows];
        Arrays.fill(builtStrings, "");

        int count = 0;
        boolean up = true;

        for(int i = 0; i < sArray.length; i++){
            builtStrings[count].concat(sArray[i]);
            if(count < numRows && up == true){
                count++;
                if(count == numRows){
                    up = false;
                    count = numRows - 2;
                }
            }
            else if(count > 0 && up == false){
                count--;
                if(count == -1){
                    up = true;
                    count = 1;
                }
            }
        }

        for(int i = 1; i < numRows; i++){
            builtStrings[0].concat(builtStrings[i]);
        }

        convertedS = builtStrings[0];

        return convertedS;
    }

    //Rotate an array of n elements to the right by k steps.
    //For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. How many different ways do you know to solve this problem?

    public int[] rotate(int[] n, int k){
        int[] rotatedN = new int[n.length];

        for(int i = 0; i < n.length; i++){
            rotatedN[k] = n[i];
            k++;
            if(k == n.length){
                k = 0;
            }
        }
        return rotatedN;
    }

    //Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /. Each operand may be an integer or another expression. For example:

    //["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
    //["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

    public int reversePolishNotation(String[] equation){
        int answer = 0;

        if(equation == null){
            return 0;
        }

        Stack s = new Stack();

        for(int i = 0; i < equation.length; i++){
            if(Character.isDigit(equation[i].charAt(0))){
                s.push(equation[i]);
            }
            if(equation[i].equalsIgnoreCase("+")){
                int firstOperand = Integer.parseInt(s.pop().toString());
                int secondOperand = Integer.parseInt(s.pop().toString());
                s.push(firstOperand+secondOperand);
            }
            if(equation[i].equalsIgnoreCase("-")){
                int firstOperand = Integer.parseInt(s.pop().toString());
                int secondOperand = Integer.parseInt(s.pop().toString());
                s.push(firstOperand-secondOperand);
            }
            if(equation[i].equalsIgnoreCase("*")){
                int firstOperand = Integer.parseInt(s.pop().toString());
                int secondOperand = Integer.parseInt(s.pop().toString());
                s.push(firstOperand*secondOperand);
            }
            if(equation[i].equalsIgnoreCase("/")){
                int firstOperand = Integer.parseInt(s.pop().toString());
                int secondOperand = Integer.parseInt(s.pop().toString());
                s.push(firstOperand/secondOperand);
            }
        }

        return answer;
    }

    public int fibonacci(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public int tripleStep(int n){
        if ( n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 1;
        }
        return 0;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        /* DFS Solution
        boolean[] visited = new boolean[rooms.size()];
        Stack<Integer> s = new Stack<>();

        visited[0] = true;
        s.push(0);

        while(!s.isEmpty()){
            int currentRoom = s.pop();

            for(int room : rooms.get(currentRoom)){
                if(!visited[room]){
                    visited[room] = true;
                    s.push(room);
                }
            }
        }

        for(boolean b : visited){
            if(!b == true)
                return false;
        }

        return true;*/

        //BFS

        boolean[] visited = new boolean[rooms.size()];

        Queue<Integer> q = new LinkedList<>();

        q.add(0);

        while(!q.isEmpty()){
            int currentRoom = q.poll();

            visited[currentRoom] = true;

            for(int room : rooms.get(currentRoom)){
                if(!visited[room] == true){
                    q.add(room);
                }
            }

        }

        for(boolean b : visited){
            if(!b == true)
                return false;
        }

        return true;
    }

    public static int getClosingParen(String sentence, int openingParenIndex) {

        Stack<Integer> s = new Stack();

        for(int i = openingParenIndex; i < sentence.length(); i++){
            if(sentence.charAt(i) == '('){
                s.push(i);
            }
            if(sentence.charAt(i) == ')'){
                //int currentInt = 0;
                //currentInt = s.pop();
                s.pop();
                if(s.isEmpty()){
                    return i;
                }
            }
        }

        throw new IllegalArgumentException("No matching index");
    }
    
}
