import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        Collections.sort(words);

        int answer = 0;

        for (int i = 0; i < words.size() - 1; i++) {
            if (!words.get(i + 1).startsWith(words.get(i))) {
                answer++;
            }
        }

        if (N > 0) answer++;

        System.out.println(answer);
    }
}

/*
조건
- 접두사X = 어떤 단어가 다른 단어의 접두사가 되지 않는 집합

요구
- 접두사X 집합인 부분집합의 최대 크기 구하기

풀이
- 정렬하고 다음 거랑 비교해서 이제 아니면 cnt++
- 맞으면 초기화
 */