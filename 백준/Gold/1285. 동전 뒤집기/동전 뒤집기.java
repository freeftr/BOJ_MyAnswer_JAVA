import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N];

        // T == 1 (뒷면), H == 0 (앞면)
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int row = 0;
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                if (c == 'T') {
                    row |= (1 << j);  // 뒷면(T)을 1로 표시
                }
            }
            map[i] = row;
        }

        dfs(0);

        System.out.println(min);
    }

    static void dfs(int depth) {
        if (depth == N) {
            int cnt = 0;
            // 각 열을 기준으로 뒷면(T)이 더 적은 쪽을 세기
            for (int j = 0; j < N; j++) {
                int tCount = 0;  // 각 열에서 T의 개수
                for (int i = 0; i < N; i++) {
                    if ((map[i] & (1 << j)) != 0) {  // T인 경우
                        tCount++;
                    }
                }
                cnt += Math.min(tCount, N - tCount);  // T가 적은 쪽을 선택
            }
            min = Math.min(min, cnt);
            return;
        }

        // 현재 행을 뒤집지 않고 진행
        dfs(depth + 1);

        // 현재 행을 뒤집고 진행
        flipRow(depth);
        dfs(depth + 1);

        // 원상 복구
        flipRow(depth);
    }

    // 행을 뒤집는 함수
    static void flipRow(int rowNum) {
        map[rowNum] ^= (1 << N) - 1;  // 해당 행의 모든 비트를 뒤집음
    }
}