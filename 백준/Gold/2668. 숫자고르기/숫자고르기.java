import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static HashSet<Integer> resultSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            dfs(i, i);
        }

        List<Integer> resultList = new ArrayList<>(resultSet);
        Collections.sort(resultList);

        System.out.println(resultList.size());
        for (int num : resultList) {
            System.out.println(num);
        }
    }

    static void dfs(int start, int current) {
        if (!visited[current]) {
            visited[current] = true;
            dfs(start, arr[current]);
        } else if (current == start) {
            resultSet.add(start);
        }
    }
}