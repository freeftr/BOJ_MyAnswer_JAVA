import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        int len = files.length;
        
        String[] answer = new String[len];
        PriorityQueue<File> words = new PriorityQueue<>((a, b) -> {
            if (a.head.equals(b.head)) {
                if (a.num == b.num) return a.idx - b.idx;
                return a.num - b.num;
            }
            return a.head.compareTo(b.head);
        });
        
        for (int i = 0; i < len; i++) {
            String word = files[i];
            StringBuilder sb = new StringBuilder();
            
            for (int j = 0; j < word.length(); j++) {
                if (!Character.isDigit(word.charAt(j))) {
                    sb.append(word.charAt(j));
                } else {
                    break;
                }
            }
            
            String head = sb.toString();
            sb = new StringBuilder();
            head = head.toUpperCase();
            String temp = word.substring(head.length());
            int limit = Math.min(5, temp.length());
            
            for (int j = 0; j < limit; j++) {
                if (Character.isDigit(temp.charAt(j))) {
                    sb.append(temp.charAt(j));
                } else {
                    break;
                }
            }
            
            int number = Integer.parseInt(sb.toString());
            
            words.add(new File(i, head, number));
            
            // System.out.println(head + " " + number);
        }
        
        int idx = 0;
        while (!words.isEmpty()) {
            answer[idx++] = files[words.poll().idx];
        }
        
        return answer;
    }
    
    static class File {
        int idx;
        String head;
        int num;
        
        File(int idx, String head, int num) {
            this.idx = idx;
            this.head = head;
            this.num = num;
        }
    }
}

/*
조건
- 파일명에 포함된 숫자를 반영한 정렬 기능 구현.
- HEAD: 문자로 구성.
- NUMBER: 1~5길이의 숫자. 앞쪽에 0 올 수 있음.
- TAIL: 나머지 부분.
- HEAD를 기준으로 사전순 정렬. 대소문자 구문 x
- HEAD같을 경우: NUMBER의 숫자로 정렬.
- HEAD와 NUMBER같을 경우 원래 입력대로.

풀이
- 클래스 만들어서 정렬 그냥.
*/