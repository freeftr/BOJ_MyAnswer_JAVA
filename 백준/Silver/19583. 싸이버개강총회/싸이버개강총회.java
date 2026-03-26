import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        int S = strToInt(info[0]);
        int E = strToInt(info[1]);
        int Q = strToInt(info[2]);

        int answer = 0;

        HashSet<String> in = new HashSet<>();

        while (true) {
            String t = br.readLine();
            if (t == null || t.equals("")) break;

            String[] s = t.split(" ");

            String name = s[1];
            int time = strToInt(s[0]);

            if (time <= S) in.add(name);
            if (E <= time && time <= Q) {
                if (in.contains(name)) {
                    in.remove(name);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static int strToInt(String s) {
        String[] t = s.split(":");

        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}

/*
조건
- 시작하기 전 입장 확인 여부 확인.
- 한시간 이전 채팅 기록
- 스트리밍이 끝나기 전까지 채팅

요구
- 입장부터 퇴장까지 인원
 */