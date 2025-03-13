import java.io.*;
import java.util.*;

public class Main {
    static long hATK;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        hATK = Integer.parseInt(st.nextToken());

        map = new int[N][3];

        long left = 1, right = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }


        while (left < right) {
            long mid = (left + right) / 2;
            if (check(mid, hATK)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean check(long maxHP, long atk) {
        long curHP = maxHP, curAtk = atk;

        for (int i = 0; i < N; i++) {
            int t = map[i][0], a = map[i][1], h = map[i][2];

            if (t == 1) {
                if (!meetMonster(curHP, curAtk, a, h)) {
                    return false;
                }
                curHP -= ((h + curAtk - 1) / curAtk - 1) * a;
            }
            else {
                curAtk += a;
                curHP = Math.min(maxHP, curHP + h);
            }
        }
        return true;
    }

    static boolean meetMonster(long maxHP, long heroATK, int monsterATK, int monsterHP) {
        long heroTurn = (monsterHP + heroATK - 1) / heroATK;
        long monsterTurn = (maxHP + monsterATK - 1) / monsterATK;
        return heroTurn <= monsterTurn;
    }
}
