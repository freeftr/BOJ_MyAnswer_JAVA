import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] mbtis = br.readLine().split(" ");

            if (N > 32) {
                // 비둘기집 원리에 의해 MBTI는 16종류 → 33명이면 반드시 3명 이상 같은 MBTI가 존재
                System.out.println(0);
            } else {
                int minSum = Integer.MAX_VALUE;

                for (int i = 0; i < N - 2; i++) {
                    for (int j = i + 1; j < N - 1; j++) {
                        for (int k = j + 1; k < N; k++) {
                            int dist = calculate(mbtis[i], mbtis[j])
                                     + calculate(mbtis[j], mbtis[k])
                                     + calculate(mbtis[i], mbtis[k]);
                            minSum = Math.min(minSum, dist);
                        }
                    }
                }

                System.out.println(minSum);
            }
        }
    }

    static int calculate(String a, String b) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }
}
