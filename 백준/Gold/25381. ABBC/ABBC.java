import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        ArrayDeque<Integer> cQ = new ArrayDeque<>();
        ArrayDeque<Integer> bQ = new ArrayDeque<>();
        ArrayDeque<Integer> aQ = new ArrayDeque<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'C') cQ.addLast(i);
            else if (s[i] == 'B') bQ.addLast(i);
            else if (s[i] == 'A') aQ.addLast(i);
        }

        int answer = 0;
        ArrayDeque<Integer> bLeft = new ArrayDeque<>();

        while (!bQ.isEmpty()) {
            int b = bQ.pollFirst();
            while (!cQ.isEmpty() && cQ.peekFirst() < b) cQ.pollFirst();
            if (!cQ.isEmpty()) {
                answer++;
                cQ.pollFirst();
            } else {
                bLeft.addLast(b);
            }
        }

        while (!bLeft.isEmpty()) {
            int b = bLeft.pollLast();
            while (!aQ.isEmpty() && aQ.peekLast() > b) aQ.pollLast();
            if (!aQ.isEmpty()) {
                answer++;
                aQ.pollLast();
            }
        }

        System.out.println(answer);
    }
}
