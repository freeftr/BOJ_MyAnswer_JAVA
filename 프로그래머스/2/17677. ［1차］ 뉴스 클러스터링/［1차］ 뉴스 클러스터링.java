import java.io.*;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        // 유효한 두 글자만 리스트에 저장
        for (int i = 0; i < str1.length() - 1; i++) {
            String s = str1.substring(i, i + 2);
            if (s.matches("[a-z]{2}")) {
                list1.add(s);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String s = str2.substring(i, i + 2);
            if (s.matches("[a-z]{2}")) {
                list2.add(s);
            }
        }

        int same = 0;
        List<String> temp = new ArrayList<>(list2);
        for (String s : list1) {
            if (temp.contains(s)) {
                same++;
                temp.remove(s); // 다중집합: 하나만 제거
            }
        }

        int union = list1.size() + list2.size() - same;
        int answer = (int)((union == 0 ? 1.0 : (double) same / union) * 65536);
        return answer;
    }
}
