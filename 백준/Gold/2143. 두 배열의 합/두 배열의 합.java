import java.io.*;
import java.util.*;

public class Main {
    static int T, n, m;
    static int[] arr1, arr2;
    static ArrayList<Long> sumA = new ArrayList<>();
    static ArrayList<Long> sumB = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr1[j];
                sumA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += arr2[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumB);

        long answer = 0;
        for (long a : sumA) {
            long target = T - a;
            int lower = lowerBound(sumB, target);
            int upper = upperBound(sumB, target);
            answer += (upper - lower);
        }

        System.out.println(answer);
    }

    static int lowerBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int upperBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
