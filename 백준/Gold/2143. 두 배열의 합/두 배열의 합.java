import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long T = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken()) + A[i-1];
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = Integer.parseInt(st.nextToken()) + B[i-1];
        }

        ArrayList<Long> aSum = new ArrayList<>();
        ArrayList<Long> bSum = new ArrayList<>();

        // A의 모든 부분 합 구하기
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                aSum.add((long) (A[j] - A[i-1]));
            }
        }

        // B의 모든 부분 합 구하기
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                bSum.add((long) (B[j] - B[i-1]));
            }
        }

        // A의 부분합 리스트는 그대로 두고, B는 역순으로 정렬 (이분 탐색 준비)
        Collections.sort(aSum);
        Collections.sort(bSum, Collections.reverseOrder());

        int left = 0;
        int right = 0;
        long ans = 0;

        // 투 포인터를 이용하여 두 리스트에서 합이 T가 되는 경우 찾기
        while (left < aSum.size() && right < bSum.size()) {
            long sumA = aSum.get(left);
            long sumB = bSum.get(right);
            long sum = sumA + sumB;

            if (sum == T) {
                long aCount = 0;
                long bCount = 0;

                while (left < aSum.size() && aSum.get(left) == sumA) {
                    left++;
                    aCount++;
                }

                while (right < bSum.size() && bSum.get(right) == sumB) {
                    right++;
                    bCount++;
                }

                ans += aCount * bCount;
            } else if (sum < T) {
                left++;
            } else {
                right++;
            }
        }

        System.out.println(ans);
    }
}