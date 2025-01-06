import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[4];

        for (int i = 0; i < 4; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int target = clockNum(numbers);
        int cnt = 0;

        for (int i = 1111; i <= target; i++) {
            if (String.valueOf(i).contains("0")) continue;
            int[] temp = toArr(i);
            if (clockNum(temp) == i) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static int clockNum(int[] numbers) {
        int min = toNum(numbers);

        for (int i = 0; i < 3; i++) {
            rotate(numbers);
            min = Math.min(min, toNum(numbers));
        }

        return min;
    }

    private static void rotate(int[] numbers) {
        int first = numbers[0];
        System.arraycopy(numbers, 1, numbers, 0, 3);
        numbers[3] = first;
    }

    private static int toNum(int[] numbers) {
        return numbers[0] * 1000 + numbers[1] * 100 + numbers[2] * 10 + numbers[3];
    }

    private static int[] toArr(int num) {
        return new int[]{num / 1000, (num / 100) % 10, (num / 10) % 10, num % 10};
    }
}