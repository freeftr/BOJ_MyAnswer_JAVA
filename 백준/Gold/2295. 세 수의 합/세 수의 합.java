import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long[] n;
    static ArrayList<Long> twosome = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        n = new long[N];
        for (int i = 0; i < N; i++) {
            n[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(n);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twosome.add(n[i] + n[j]);
            }
        }
        Collections.sort(twosome);
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bs(n[i] - n[j]) == 1) {
                    System.out.println(n[i]);
                    System.exit(0);
                }
            }
        }
    }
    public static int bs(long target) {
        int low = 0;
        int high = twosome.size()-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (twosome.get(mid) > target) {
                high = mid - 1;
            }
            else if (twosome.get(mid) < target) {
                low = mid + 1;
            }
            else {
                return 1;
            }
        }
        return 0;
    }
}