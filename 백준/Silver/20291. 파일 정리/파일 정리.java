import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String file = br.readLine();
            map.merge(file.split("\\.")[1], 1, (a, b) -> a + b);
        }

        ArrayList<String> result = new ArrayList<>(map.keySet());

        StringBuilder sb = new StringBuilder();

        Collections.sort(result);
        for (String key : result) {
            sb.append(key + " " + map.get(key) + "\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 바탕화면에 파일들이 많음.
- 그중에 보물이 있음. 확장자 참고.
- 확장자별로 몇 개씩 있는지 알려줘
- 확장자들을 사전 순 정렬해줘
 */