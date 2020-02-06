import LeetCode.ListNode;
import LeetCode.Solution;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    /*public static void main(String[] args) {

        Solution s = new Solution();

        final String testCase = "long array input";
        final int[] expected = new int[] {120, 480, 240, 320, 960, 192};
        final int[] actual = s.getProductsOfAllIntsExceptAtIndex(
                new int[] {8, 2, 4, 3, 1, 5});

        for(int i : actual){
            System.out.println(i);
        }

    }*/

    static long countWays(int S[], int m, int n) {
        //Time complexity of this function: O(mn)
        //Space Complexity of this function: O(n)

        // table[i] will be storing the number of solutions
        // for value i. We need n+1 rows as the table is
        // constructed in bottom up manner using the base
        // case (n = 0)
        long[] table = new long[n + 1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);   //O(n)

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i = 0; i < m; i++)
            for (int j = S[i]; j <= n; j++) {
                System.out.println("Coin Index: " + i + " j: " + j + " si: " + S[i] + " tablejsi: " + table[j - S[i]]);
                table[j] += table[j - S[i]];
            }

        return table[n];
    }

    public static void heapPractice() {
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });//Min Heap, Ascending Order

        PriorityQueue<Integer> maxPirorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });//Max Heap, Descending Order

        Random r = new Random();
        int low = 10;
        int high = 100;

        for (int i = 0; i < 5; i++) {
            int result = r.nextInt(high - low) + low;
            System.out.println("Inserting: " + result);

            minPriorityQueue.add(result);
            maxPirorityQueue.add(result);
        }

        while (!minPriorityQueue.isEmpty()) {
            System.out.println("Min: " + minPriorityQueue.remove());
        }

        while (!maxPirorityQueue.isEmpty()) {
            System.out.println("Max: " + maxPirorityQueue.remove());
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> solutionList = new ArrayList<>();

        binaryTreePathsHelper(root, "", solutionList);

        return solutionList;
    }

    public void binaryTreePathsHelper(TreeNode node, String currentPath, List<String> solutionList){
        if(node == null){
            return;
        }

        if(node.left == null && node.right == null){
            solutionList.add(currentPath + node.val);
        }

        if(node.left != null){
            binaryTreePathsHelper(node.left, currentPath+ node.val + "->", solutionList);
        }

        if(node.right != null){
            binaryTreePathsHelper(node.right, currentPath+ node.val + "->", solutionList);
        }
    }
    // Driver Function to test above function
    public static void main(String args[]) {

        Map<String, List<String>> hashMap = new HashMap<>();

        List<String> healthEnrollments = new ArrayList<>();
        healthEnrollments.add("123456");

        List<String> dentalEnrollments = new ArrayList<>();
        dentalEnrollments.add("654321");
        dentalEnrollments.add("654321");

        hashMap.put("health", healthEnrollments);
        hashMap.put("dental", dentalEnrollments);

        List<String> newList = hashMap.entrySet().stream().map(Map.Entry::getValue).flatMap(List::stream).collect(Collectors.toList());

        for(String stringList : newList){
            System.out.println(stringList);
        }
    }

    public static void buildBalancedBST() {

    }

    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, Integer.toString(root.val));

        /*
        Input: [4,9,0,5,1]
            4
           / \
          9   0
         / \
        5   1
        Output: 1026
        Explanation:
        The root-to-leaf path 4->9->5 represents the number 495.
        The root-to-leaf path 4->9->1 represents the number 491.
        The root-to-leaf path 4->0 represents the number 40.
        Therefore, sum = 495 + 491 + 40 = 1026.
        */
    }

    public int sumNumbersHelper(TreeNode root, String currentNumber) {
        int sum = 0;

        if (root == null)
            return 0;

        if (root.left == null && root.right == null) {
            currentNumber = currentNumber + Integer.toString(root.val);
            return Integer.parseInt(currentNumber);
        }

        if (root.left != null) {
            currentNumber = currentNumber + Integer.toString(root.val);
            sum += sumNumbersHelper(root.left, currentNumber);
        }

        if (root.right != null) {
            currentNumber = currentNumber + Integer.toString(root.val);
            sum += sumNumbersHelper(root.right, currentNumber);
        }

        return sum;
    }

    public List<Interval> merge(List<Interval> intervals) {
        //Cases
        //No overlap
        //End time is greater than next start time
        //Keep previous start time, Keep current end time
        //End time is greater than next end time
        //Delete this interval

        if (intervals.size() == 0 || intervals.size() == 1) {
            return intervals;
        }

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {

                return i1.start - i2.start;
            }
        });

        List<Interval> solutionList = new ArrayList<>();

        int previousStartTime = intervals.get(0).start;
        int previousEndTime = intervals.get(0).end;

        if (previousEndTime < intervals.get(1).start) {
            solutionList.add(intervals.get(0));
        }


        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);
            if (previousEndTime >= currentInterval.start) {
                if (previousEndTime > currentInterval.end) {
                    continue;
                } else {
                    currentInterval.start = previousStartTime;
                    solutionList.add(currentInterval);
                    previousEndTime = currentInterval.end;
                }
            } else {
                solutionList.add(intervals.get(i));
                previousStartTime = intervals.get(i - 1).start;
                previousEndTime = intervals.get(i - 1).end;
            }
        }

        return solutionList;
    }

    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();

        List<Integer> solutionList = new ArrayList<>();

        if (root == null) {
            return solutionList;
        }

        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            int currentQueueSize = nodeQueue.size();
            int currentMax = Integer.MIN_VALUE;

            for (int i = 0; i < currentQueueSize; i++) {
                TreeNode currentNode = nodeQueue.poll();
                currentMax = (currentNode.val > currentMax) ? currentNode.val : currentMax;

                if (currentNode.left != null) {
                    nodeQueue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeQueue.add(currentNode.right);
                }
            }

            solutionList.add(currentMax);
        }

        return solutionList;
    }
    /*
    Input: amount = 5, coins = [1, 2, 5]

    1
    1

    2
    1 1
    2

    3
    1 1 1
    2 1

    4
    1 1 1 1
    1 1 2
    2 2

    5
    5
    1 1 1 1 1
    1 1 2 1
    2 2 1

    Input: amount = 5, coins = [2, 5]

    1

    2
    2

    3

    4
    2 2

    5
    5

    6
    2 2 2

    7
    2 5



     */

    public int change(int amount, int[] coins) {
        int[] solution = new int[amount + 1];

        solution[0] = 1;

        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (coin <= i) {
                    solution[i] += solution[coin - i];
                }
            }
        }

        return solution[amount];
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> solutionList = new ArrayList<>();

        Queue<TreeNode> bfsQueue = new LinkedList<>();

        bfsQueue.add(root);

        boolean forwardDirection = true;

        while (!bfsQueue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int sizeOfQueue = bfsQueue.size();

            for (int i = 0; i < sizeOfQueue; i++) {
                TreeNode currentNode = bfsQueue.poll();

                if (currentNode.left != null) {
                    bfsQueue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    bfsQueue.add(currentNode.right);
                }
                if (forwardDirection) {
                    currentLevel.add(currentNode.val);
                } else {
                    if (currentLevel.isEmpty()) {
                        currentLevel.add(currentNode.val);
                    } else {
                        currentLevel.add(0, currentNode.val);
                    }
                }
            }

            forwardDirection = !forwardDirection;

            solutionList.add(currentLevel);
        }

        return solutionList;
    }

    public Map<Character, List<Integer>> getGradeMap(List<Integer> scores) {
        Map<Character, List<Integer>> sMap = new HashMap<>();

        return sMap;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
