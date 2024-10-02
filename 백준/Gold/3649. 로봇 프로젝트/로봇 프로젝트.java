import java.io.*;
import java.util.*;
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            try {
                String input = br.readLine();
                if (input == null || input.isEmpty()) {
                    break; // EOF가 오거나 빈 줄일 때 종료
                }

                int T = Integer.parseInt(input); // 구멍의 너비 T
                N = Integer.parseInt(br.readLine()); // 조각의 개수 N

                long[] blocks = new long[N];
                for (int i = 0; i < N; i++) {
                    blocks[i] = Integer.parseInt(br.readLine()); // 각 조각의 크기
                }

                Arrays.sort(blocks); // 조각들을 크기 순으로 정렬

                int left = 0;
                int right = N - 1;
                long sum = 0;
                boolean check = false;

                while (left < right) {
                    sum = blocks[left] + blocks[right] - (T * 10000000L);
                    if (sum > 0) {
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        sb.append("yes ").append(blocks[left]).append(" ").append(blocks[right]).append("\n");
                        check = true;
                        break;
                    }
                }

                if (!check) {
                    sb.append("danger\n");
                }

            } catch (Exception e) {
                break; // 예외가 발생하면 종료 (EOF 처리)
            }
        }

        System.out.print(sb);
    }
}