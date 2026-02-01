import java.util.*;

class Solution {
    static class Name{
        int idx;
        String head;
        String number;
        
        Name (int idx, String head, String number) {
            this.idx = idx;
            this.head = head;
            this.number = number;
        }
    }
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        ArrayList<Name> result = new ArrayList<>();
        
        int idx = 0;
        for (String file : files) {
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            for (int i = 0; i < file.length(); i++) {
                char cur = file.charAt(i);
                
                if (cur == '.') break;
                if (number.length() == 5) break;
                
                if (Character.isDigit(cur)) number.append(cur);
                else {
                    if (number.length() == 0) {  
                        head.append(cur);
                    }
                }
                
            }
            
            String h = head.toString().toLowerCase();
            String n = number.toString();
            
            result.add(new Name(idx++, h, n));
        }
        
        Collections.sort(result, (a, b) -> {
            if (a.head.equals(b.head)) {
                if (Integer.parseInt(a.number) == Integer.parseInt(b.number)) {
                    return a.idx - b.idx;
                }
                return Integer.parseInt(a.number) - Integer.parseInt(b.number);
            }
           return a.head.compareTo(b.head); 
        });
        
        for (int i = 0; i < result.size(); i++) {
            Name n = result.get(i);
            answer[i] = files[n.idx];
        }
        return answer;
    }
}

/*
조건
- 파일을 이름순으로 정렬한다.
- HEAD, NUMBER, TAIL로 구성
- HEAD: 문자로 구성
- NUMBER: 0 ~ 99999 0101도 가능
- TAIL: 빈칸일 수 있고, 아무렇게나 가능

정렬 기준
- HEAD를 기준으로 사전순 정렬 (대소문자 구분 안함)
- 같으면 NUMBER의 숫자 순으로 정렬
- HEAD, NUMBER가 같을 경우 인덱스 기준으로 정렬
*/