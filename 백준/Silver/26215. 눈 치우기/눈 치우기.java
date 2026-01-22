import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> houses = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            houses.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(houses);

        long answer = 0;
        while (houses.size() > 1) {
            Collections.sort(houses);
            int min = houses.get(0);
            int max = houses.get(houses.size() - 1);

            max--;
            min--;

            if (max == 0 && min == 0) {
                houses.remove(houses.size() - 1);
                houses.remove(0);
            } else if (min == 0 && max != 0) {
                houses.set(houses.size() - 1, max);
                houses.remove(0);
            } else {
                houses.set(0, min);
                houses.set(houses.size() - 1, max);
            }

            answer++;

            if (answer > 1440) {
                break;
            }
        }

        int leftOver = !houses.isEmpty() ? houses.get(0) : 0;

        answer += leftOver;

        System.out.println(answer > 1440 ? -1 : answer);
    }
}

/*
조건
- 눈을 치운다
- 1분에 한번씩 두 집 1씩 치우기 vs 한집 1 치우기

요구
- 모든 집 앞 눈치우는데 얼마나 걸리는지

풀이
1   2   5
0   1   4
3
3   3   3
2   2   3
1   2   2
0   1   2
0   0   1
- 일단 두 개씩 지워나가면 되는데 마지막에 한개만 남을 때가 있음. -> 이거 어떻게 처리해야 하는지 고민해야 함.
- 제일 높은 + 제일 낮은거 순대로 해치우기.
 */