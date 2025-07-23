import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> dices = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            dices.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        /*    A
            B C D E
              F
           - 아랫면 C(2) 옆면 A(0), B(1), D(3), F(5) 윗면 E(4)
           - 아랫면 E(4) 옆면 A(0), B(1), D(3), F(5) 윗면 C(2)
           - 아랫면 A(0) 옆면 C(2), B(1), D(3), E(4) 윗면 F(5)
           - 아랫면 F(5) 옆면 C(2), B(1), D(3), E(4) 윗면 A(0)
           - 아랫면 B(1) 옆면 A(0), C(2), F(5), E(4) 윗면 D(3)
           - 아랫면 D(3) 옆면 A(0), C(2), F(5), E(4) 윗면 B(1)

           - 붙어있는 면에 적혀있는 숫자가 같아야 함.
         */
        for (int i = 0; i < 6; i++) {
            dfs(0, 0, dices.get(0).get(i));
        }

        System.out.println(answer);
    }

    static void dfs(int depth, int sum, int target) {
        if (depth==N) {
            answer = Math.max(sum, answer);
            return;
        }

        ArrayList<Integer> curDice = dices.get(depth);

        //아래 주사위의 윗면 숫자의 인덱스
        int idx = curDice.indexOf(target);

        if (idx == 0) {
            int max = 0;
            int a = Math.max(curDice.get(1), curDice.get(2));
            int b = Math.max(curDice.get(3), curDice.get(4));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(5));
        }

        if (idx == 5) {
            int max = 0;
            int a = Math.max(curDice.get(2), curDice.get(4));
            int b = Math.max(curDice.get(1), curDice.get(3));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(0));
        }

        if (idx == 1) {
            int max = 0;
            int a = Math.max(curDice.get(0), curDice.get(4));
            int b = Math.max(curDice.get(2), curDice.get(5));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(3));
        }

        if (idx == 3) {
            int max = 0;
            int a = Math.max(curDice.get(0), curDice.get(4));
            int b = Math.max(curDice.get(2), curDice.get(5));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(1));
        }

        if (idx == 2) {
            int max = 0;
            int a = Math.max(curDice.get(0), curDice.get(5));
            int b = Math.max(curDice.get(1), curDice.get(3));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(4));
        }

        if (idx == 4) {
            int max = 0;
            int a = Math.max(curDice.get(0), curDice.get(5));
            int b = Math.max(curDice.get(1), curDice.get(3));
            max = Math.max(a, b);
            dfs(depth + 1, sum + max, dices.get(depth).get(2));
        }
    }
}
