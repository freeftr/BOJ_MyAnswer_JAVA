import java.util.*;
class Solution {
    
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static HashSet<Integer> left = new HashSet<>();
    static HashSet<Integer> right = new HashSet<>();
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        left.add(1);
        left.add(4);
        left.add(7);
        right.add(3);
        right.add(6);
        right.add(9);
        int leftPos = 10;
        int rightPos = 12;
        for (int number : numbers) {
            if (left.contains(number)) {
                leftPos = number;
                answer += "L";
            } else if (right.contains(number)) {
                rightPos = number;
                answer += "R";
            } else {
                int num = number;
                if (number == 0) {
                    num = 11;
                }
                int ld = Math.abs(leftPos - num) / 3 + Math.abs(leftPos - num) % 3;
                int rd = Math.abs(rightPos - num) / 3 + Math.abs(rightPos - num) % 3;
                
                if (ld < rd) {
                    leftPos = num;
                    answer += "L";
                } else if (ld == rd) {
                    if (hand.equals("left")) {
                        leftPos = num;
                        answer += "L";
                    } else {
                        rightPos = num;
                        answer += "R";
                    }
                } else {
                    rightPos = num;
                    answer += "R";
                }
            }
        }
        return answer;
    }
}
/*
왼 = * 시작
오른 = # 시작
1 4 7 => 왼
3 6 9 => 오
2 5 8 0 더 가까운 손 동일 시 손잡이에 따라
*/