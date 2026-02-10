import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> memo = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            memo.add(s);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(",");

            for (String a : s) {
                memo.remove(a);
            }

            sb.append(memo.size()).append("\n");
        }

        System.out.println(sb.toString());
    }
}

/*
조건
- 키워드 N개가 존재
- 최대 10개의 키워드
- 글을 쓰면 메모장에서 지워짐

요구
- 글을 다 쓰고 나서 메모장에 있는 키워드 알고 싶음

풀이
- 메모장
 */