import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Double> drinks = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            drinks.add(Double.parseDouble(st.nextToken()));
        }

        Collections.sort(drinks);

        for (int i = 0; i < N - 1; i++) {
            drinks.set(N - 1, drinks.get(i) / 2 + drinks.get(N - 1));
        }

        System.out.println(drinks.get(N - 1));
    }
}

/*
조건
- 에너지 드링크를 합친다.
- 서로 다른 두 에너지 드링크 고름.
- 하나를 다른 하나에 절반만 붓는다.
- 에너지 드링크 하나 남을 때까지 반복

요구
- 에너지 드링크 양을 최대로 만들기

풀이
- 버리는게 제일 적은 것을 그 다음 작은거에 계속 더해야 함.
 */