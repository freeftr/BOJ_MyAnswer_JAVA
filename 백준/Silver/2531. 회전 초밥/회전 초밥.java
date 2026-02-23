import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] table = new int[N * 2];
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());

            table[i] = a;
            table[i + N] = a;
        }

        int left = 0;
        int right = k - 1;
        int answer = 0;

        int[] sushi = new int[d + 1];

        for (int i = left; i <= right; i++) {
            if (sushi[table[i]] == 0) answer++;
            sushi[table[i]]++;
        }

        int cur = answer;


        while (right < N * 2) {
            sushi[table[left]]--;

            if (sushi[table[left]] == 0) {
                cur--;
            }

            left++;

            right++;

            if (right == N * 2) break;

            if (sushi[table[right]] == 0) cur++;

            sushi[table[right]]++;

            if (sushi[c] == 0) {
                answer = Math.max(answer, cur + 1);
            } else {
                answer = Math.max(answer, cur);
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- k개 접시를 연속으로 먹을 경우 정액 가격으로 제공
- 쿠폰을 하나 주는데 정액제 사용 시 스시를 하나 더 줌

요구
- 먹을 수 있는 최대 초밥 가짓수

풀이
- 슬라이딩 윈도우
- 쿠폰 종류가 있는 경우: 그냥 최댓값 갱신
- 쿠폰 종류가 없는 경우: 현재 상태에서 + 1
 */