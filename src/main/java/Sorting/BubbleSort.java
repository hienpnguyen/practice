package Sorting;

import java.util.List;

public class BubbleSort {

    public List bubbleSort(List<Integer> a){
        int length = a.size();

        boolean swapped = true;
        int temp;

        while(swapped) {
            swapped = false;
            for (int i = 0; i < length - 2; i++) {
                if (a.get(i) > a.get(i + 1)) {
                    temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                    swapped = true;
                }
            }
        }
        return a;
    }

}
