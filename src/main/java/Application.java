import LeetCode.Solution;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args)
    {


    }

    static public String reverseString(String s){
        StringBuilder newString = new StringBuilder();

        int length = s.length();
        int j = length;

        for(int i = length-1; i >= 0; i--){
            if(s.charAt(i) == '.'){
                newString.append(s.substring(i+1, j));
                j = i+1;
            }
        }

        /*if(j != 0){
            newString.append(s.substring(0,j));
        }*/

        return newString.toString();
    }

    static public void recursiveCall(int i,int m, int n){
        if(i < m) {
            System.out.print(m + " ");
            recursiveCall(i+1,m,n);
        }

        if(m == n){
            return;
        }

        if(i == m) {
            System.out.println();
            recursiveCall(0,m+1,n);
        }
    }
}