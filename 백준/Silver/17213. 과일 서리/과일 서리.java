import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        long answer = 1;
        for (int i = 1; i <= N - 1; i++) {
            answer = answer * (M - N + i) / i;
        }

        System.out.println(answer);
    }
}

/*
조건
- N가지 종류 과일
- 민건이네 과일 농장에서 과일 훔침
- 처음 생각한 개수만큼만 훔침
- 모든 종류 과일을 적어도 1개씩은 훔친다.

요구
- 훔칠 수 있는 경우의 수 구하기

풀이
- 중복 조합
 */