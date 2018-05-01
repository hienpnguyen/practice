import Sorting.BubbleSort;
import Sorting.MergeSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyClassTest {
    @Test
    public void bubbleSortTest() {
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> a = Arrays.asList(5, 1, 4, 2, 8);

        System.out.println(a);

        List<Integer> b = bubbleSort.bubbleSort(a);

        System.out.println(b);
    }

    @Test
    public void mergeSortTest(){
        int arr[] = {12, 11, 13, 5, 6, 7};

        MergeSort mergeSort = new MergeSort();

        System.out.println(Arrays.toString(arr));

        mergeSort.sort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}