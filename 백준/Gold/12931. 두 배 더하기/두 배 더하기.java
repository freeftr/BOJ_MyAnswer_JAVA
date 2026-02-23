import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] B = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        while (true) {
            int result = canDivide(B, N);

            if (result == -1) {
                cnt++;
                for (int i = 0; i < N; i++) {
                    B[i] = B[i] / 2;
                }
            } else if (result == -2) {
                break;
            } else {
                cnt++;
                B[result]--;
            }
        }

        System.out.println(cnt);
    }

    static int canDivide(int[] B, int N) {
        boolean zero = true;

        for (int i = 0; i < N; i++) {
            if (B[i] % 2 != 0) return i;
            if (B[i] != 0) zero = false;
        }

        if (zero) return -2;

        return -1;
    }
}

/*
조건
- 연산 두개 수행 가능
    - 배열에 있는 값 하나 ++
    - 배열에 있는 모든 값 두배

요구
- A를 B로 만들기 위한 연산의 최소 횟수 구하기

풀이
- 그리디
- B를 2로 나눌수 있으면 일단 나눔
 */