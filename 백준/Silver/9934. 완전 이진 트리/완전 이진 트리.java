import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static int[] arr;
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        arr = new int[(int) Math.pow(2, k) - 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(new ArrayList<>());
        }

        dfs(0, 0,arr.length - 1);

        for (int i = 0; i < k; i++) {
            for (int j : list.get(i)) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    static void dfs(int depth, int start, int end) {
        if(depth == k) {
            return;
        }

        int mid = (start + end) / 2;

        list.get(depth).add(arr[mid]);

        dfs(depth+1, start, mid - 1);
        dfs(depth+1,mid + 1, end);
    }
}