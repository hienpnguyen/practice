import LeetCode.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        Solution s = new Solution();

        int[] nums = {1, 2, 3};

        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        List<List<Integer>> list = s.permute(nums);

        System.out.println(list);

    }
}
