import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        List<Integer> lis = new ArrayList<>();
        int[] idx = new int[n];

        for (int i = 0; i < n; i++) {
            int value = arr[i][1];

            if (lis.isEmpty() || lis.get(lis.size() - 1) < value) {
                lis.add(value);
                idx[i] = lis.size();
            } else {
                int pos = Collections.binarySearch(lis, value);
                if (pos < 0) {
                    pos = -(pos + 1);
                }
                lis.set(pos, value);
                idx[i] = pos + 1;
            }
        }

        int size = lis.size();
        List<Integer> toRemove = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            if (idx[i] == size) {
                size--;
            } else {
                toRemove.add(arr[i][0]);
            }
        }

        Collections.sort(toRemove);

        System.out.println(toRemove.size());
        for (int a : toRemove) {
            System.out.println(a);
        }
    }
}