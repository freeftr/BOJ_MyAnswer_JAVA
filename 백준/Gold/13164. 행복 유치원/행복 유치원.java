import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] heights = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<int[]> diffs = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            diffs.add(new int[]{i, heights[i + 1] - heights[i]});
        }

        Collections.sort(diffs, (a, b) -> b[1] - a[1]);

        ArrayList<int[]> divides = new ArrayList<>();

        for (int i = 0; i < K - 1; i++) {
            int[] cur = diffs.get(i);

            divides.add(cur);
        }

        Collections.sort(divides, (a, b) -> a[0] - b[0]);

        int prev = 0;
        int answer = 0;

        for (int[] d : divides) {
            int divide = d[0];
            int min = Integer.MAX_VALUE;
            int max = 0;

            for (int i = prev; i <= divide; i++) {
                min = Math.min(min, heights[i]);
                max = Math.max(max, heights[i]);
            }

//            System.out.println(min + " " + max + " " + prev + " " + divide);

            answer += (max - min);

            prev = divide + 1;
        }

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = prev; i < N; i++) {
            min = Math.min(min, heights[i]);
            max = Math.max(max, heights[i]);
        }

        answer += (max - min);

        System.out.println(answer);
    }
}

/*
조건
- N명 줄세우고, K개의 조로 나눈다.
- 각 조당 한명씩은 있어야 함.
- 같은 조 원생은 인접.
- 각 조는 반티를 맞춘다.
- 비용 = 가장 큰 키 - 가장 작은 키.

요구
- K개의 조에 대해 티셔츠 비용 최소로.

풀이
- 정렬되어 있음
- 가장 큰 간격들을 빼면 되는거 아닌가.

1   (2)    3   (2)    5    (1)    6    (4)    10
10 5,6,3 1
 */
