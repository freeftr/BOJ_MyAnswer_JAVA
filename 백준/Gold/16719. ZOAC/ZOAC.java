import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        boolean[] visited = new boolean[s.length()];

        find(0, s.length() - 1, s, visited);
    }

    public static void find(int start, int end, String s, boolean[] visited) {
        if (start > end) return;

        int min = -1;
        for (int i = start; i <= end; i++) {
            if (!visited[i] && (min == -1 || s.charAt(i) < s.charAt(min))) {
                min = i;
            }
        }

        visited[min] = true;
        print(s, visited);

        find(min + 1, end, s, visited);
        find(start, min - 1, s, visited);
    }

    public static void print(String s, boolean[] visited) {
        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) {
                System.out.print(s.charAt(i));
            }
        }
        System.out.println();
    }
}