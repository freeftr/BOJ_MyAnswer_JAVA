import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int answer = 0;
        int diff = Y - X;
        int top = (int) Math.sqrt(diff);
        
        if (diff == 0) {
            System.out.println(0);
            return;
        }

        if (top * top == diff) {
            answer = top + top - 1;
        } else if (diff <= top * top + top) {
            answer = top + top;
        } else {
            answer = top + top + 1;
        }

        System.out.println(answer);
    }
}

/*
조건
- 원숭이가 탈출해서 세상 구경.
- 멍멍이 만남.
- 원숭이가 멍멍이 쓰다듬으려면 키가 같아야 함.
- 쓰다듬기 위해 키를 조절함.
- 하루에 늘릴수 있는 키의 양을 1cm 조절 가능
- ex) 오늘 5cm 늘림 -> 내일은 4, 5, 6 중 선택 가능.
- 첫날과 마지막? 1cm 만큼 늘릴수 있음.

요구
- 원숭이가 매일 키를 늘려서 멍멍이와 키가 같아지는 최소 일수 구하기.

풀이
- 수학
1   2   1                               4
1   2   3   2   1                       9
1   2   3   4   3   2   1               16
 */
