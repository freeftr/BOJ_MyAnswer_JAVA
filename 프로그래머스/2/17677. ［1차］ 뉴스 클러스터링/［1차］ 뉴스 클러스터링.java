import java.io.*;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            char a = str1.charAt(i);
            char b = str1.charAt(i + 1);
            if (a >= 'A' && a <= 'Z' && b >= 'A' && b <= 'Z') {
                list1.add(a + "" + b);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            char a = str2.charAt(i);
            char b = str2.charAt(i + 1);
            if (a >= 'A' && a <= 'Z' && b >= 'A' && b <= 'Z') {
                list2.add(a + "" + b);
            }
        }

        int A = list1.size();
        int B = list2.size();
        if (A == 0 && B == 0) return 65536;

        int same = 0;
        ArrayList<String> list2Copy = new ArrayList<>(list2);
        for (String s : list1) {
            int idx = list2Copy.indexOf(s);
            if (idx != -1) {
                same++;
                list2Copy.remove(idx);
            }
        }

        int union = A + B - same;
        double score = (double) same * 65536.0 / union;
        return (int) Math.floor(score);
    }
}
