import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // 문자별 등장 인덱스 리스트
            ArrayList<Integer>[] pos = new ArrayList[26];
            for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();

            for (int i = 0; i < W.length(); i++) {
                pos[W.charAt(i) - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            for (int c = 0; c < 26; c++) {
                if (pos[c].size() < K) continue;

                ArrayList<Integer> list = pos[c];

                // K개씩 슬라이딩
                for (int i = 0; i <= list.size() - K; i++) {
                    int start = list.get(i);
                    int end = list.get(i + K - 1);

                    int length = end - start + 1;

                    minLen = Math.min(minLen, length);
                    maxLen = Math.max(maxLen, length);
                }
            }

            if (maxLen == -1) System.out.println(-1);
            else System.out.println(minLen + " " + maxLen);
        }
    }
}
