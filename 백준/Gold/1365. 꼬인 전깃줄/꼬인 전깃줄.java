import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int value = arr[i];
            if (list.isEmpty() || list.get(list.size() - 1) < value) {
                list.add(value);
            } else {
                int pos = Collections.binarySearch(list, value);
                if (pos < 0) {
                    pos = -pos - 1;
                }
                list.set(pos, value);
            }
        }

        System.out.println(N - list.size());
    }
}