import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashMap<Character, Integer> map = new HashMap<>();
        char[] letters = {'A', 'C', 'G', 'T'};

        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String s = br.readLine();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            map.put(letters[i], Integer.parseInt(st.nextToken()));
        }

        int left = 0;
        int right = P - 1;
        int A = 0;
        int C = 0;
        int G = 0;
        int T = 0;

        for (int i = 0; i < P; i++) {
            if (s.charAt(i) == 'A') {
                A++;
            } else if (s.charAt(i) == 'C') {
                C++;
            } else if (s.charAt(i) == 'G') {
                G++;
            } else if (s.charAt(i) == 'T') {
                T++;
            }
        }

        int answer = 0;

        while (right <= S) {

            if (check(A, C, G, T, map)) {
                answer++;
            }

            if (s.charAt(left) == 'A') {
                A--;
            }

            if (s.charAt(left) == 'C') {
                C--;
            }

            if (s.charAt(left) == 'G') {
                G--;
            }

            if (s.charAt(left) == 'T') {
                T--;
            }

            left++;
            right++;

            if (right == S) break;

            if (s.charAt(right) == 'A') {
                A++;
            }

            if (s.charAt(right) == 'C') {
                C++;
            }

            if (s.charAt(right) == 'G') {
                G++;
            }

            if (s.charAt(right) == 'T') {
                T++;
            }
        }

        System.out.println(answer);
    }

    static boolean check(int A, int C, int G, int T, HashMap<Character, Integer> map) {
        if (A < map.get('A')) {
            return false;
        }

        if (C < map.get('C')) {
            return false;
        }

        if (G < map.get('G')) {
            return false;
        }

        if (T < map.get('T')) {
            return false;
        }

        return true;
    }
}
