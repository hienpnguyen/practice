import Sorting.BubbleSort;
import Sorting.MergeSort;
import LeetCode.Solution;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyClassTest {
    @Test
    public void bubbleSortTest() {
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> a = Arrays.asList(5, 1, 4, 2, 8);
        List<Integer> expectedResult = Arrays.asList(1, 2, 4, 5, 8);

        List<Integer> b = bubbleSort.bubbleSort(a);

        Assert.assertEquals(expectedResult, b);
    }

    @Test
    public void mergeSortTest(){
        int arr[] = {12, 11, 13, 5, 6, 7};
        String expectedResult = "[5, 6, 7, 11, 12, 13]";

        MergeSort mergeSort = new MergeSort();

        mergeSort.sort(arr, 0, arr.length - 1);

        Assert.assertEquals(expectedResult, Arrays.toString(arr));
    }

    @Test
    public void reverse(){
        int x = 123;
        int expectedResult = 321;

        Solution s = new Solution();

        Assert.assertEquals(expectedResult, s.reverse(x));
    }

    @Test
    public void isPalindrome(){
        int x = 121;
        boolean expectedResult = true;

        Solution s = new Solution();

        Assert.assertEquals(expectedResult, s.isPalindrome(x));

    }
}