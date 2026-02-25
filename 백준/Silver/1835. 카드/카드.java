import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = N; i >= 1; i--) {

            dq.addFirst(i);

            if (!dq.isEmpty()) {
                for (int j = 0; j < i; j++) {
                    dq.addFirst(dq.pollLast());
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) sb.append(dq.pollFirst() + " ");
        System.out.println(sb);
    }
}

/*
조건
1. 첫 카드를 가장 뒤로 옮김 첫 카드를 책상으로 -> 1
2. 남은 카드 중 첫 카드를 가장 뒤로 옮김 반복 첫 카드를 책상으로 -> 2
3. 남은 카드 중 첫 카드를 가장 뒤로 3번 -> 3
4. 반복
 */