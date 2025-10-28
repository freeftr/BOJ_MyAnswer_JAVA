import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur > 0) plus.add(cur);
            if (cur < 0) minus.add(cur * (-1));
        }

        Collections.sort(plus);
        Collections.sort(minus);

        int answer = 0;
        // -39  -37 -29 -28 -6  2   11
        //  6   28  29  37  39
        //  2   11

        int max = 0;
        for (int i = plus.size() - 1; i >= 0; i -= M) {
            answer += plus.get(i) * 2;
            max = Math.max(max, plus.get(i));
        }

//        System.out.println(answer);

        for (int i = minus.size() - 1; i >= 0; i -= M) {
            answer += minus.get(i) * 2;
            max = Math.max(max, minus.get(i));
        }

        System.out.println(answer - max);
    }
}

/*
조건
- 도서관에서 책을 제자리에.
- 세준이 현재 0에 있음.
- 책들도 0에 있음.

요구
- 책들의 위치가 주어질 때 책을 모두 제자리에 놔둘 때 드는 최소 걸음 수 계산.

풀이
- 그리디
- 가까운 것부터 처리.
- 다 더해서 제일 멀리있는 거 하나만 뺌.
 */
