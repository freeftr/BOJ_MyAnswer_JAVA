import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> same = new ArrayList<>();
        ArrayList<String> all = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            String s = str1.substring(i, i + 2).toLowerCase();
            if ('a' <= s.charAt(0) && s.charAt(0) <= 'z' && 'a' <= s.charAt(1) && s.charAt(1) <= 'z') {
                list1.add(s);
            }
        }
        
        for (int i = 0; i < str2.length() - 1; i++) {
            String s = str2.substring(i, i + 2).toLowerCase();
            if ('a' <= s.charAt(0) && s.charAt(0) <= 'z' && 'a' <= s.charAt(1) && s.charAt(1) <= 'z') {
                list2.add(s);
            }
        }
        
        if (list1.isEmpty() && list2.isEmpty()) {
            return 65536;
        }

        Collections.sort(list1);
        Collections.sort(list2);
        
        int one = 0;
        int two = 0;
        
        int gyo = 0;
        
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int cmp = list1.get(i).compareTo(list2.get(j));
            if (cmp == 0) {
                gyo++;
                i++;
                j++;
            } else if (cmp < 0) {
                i++;
            } else {
                j++;
            }
        }
        
        int hap = list1.size() + list2.size() - gyo;
    
        if (hap == 0) {
            return 65536;
        }
        
        answer = (int) gyo * 65536 / hap;
        return answer;
    }
}

/*
자카드 유사도 = 교집합 크기 / 합집합 크기
둘다 공집합 이면 1

1 1 2 2 3
1 2 2 4 5
1 1 2 3 4 5
aa aa
aa aa aa
*/