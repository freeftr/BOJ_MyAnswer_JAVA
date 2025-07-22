import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] map;
    static ArrayList<Fireball> fireballs = new ArrayList<>();
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(r,c,m,d,s));
        }

        /*
        1. 파이어볼 d로 s만큼(같은 칸 존재 가능)
        2. 겹쳐 있으면 하나로 합침.
        3. 이걸 4개로 나눔.
        4. 질량은 합/5
        5. 속력은 합/개수
        6. 방향 모두 홀수나 짝수 -> 0,2,4,6  아니면 1,3,5,7
         */

        System.out.println(process());
    }

    static int process() {
        for (int i = 0; i < K; i++) {
            moveFireball();
            unionAndDivide();
        }
        return calculate();
    }

    static void moveFireball() {
        for (Fireball fireball : fireballs) {
            int x = fireball.x + dx[fireball.d] * fireball.s;
            int y = fireball.y + dy[fireball.d] * fireball.s;

            x = (x % N + N) % N;
            y = (y % N + N) % N;

            fireball.x = x;
            fireball.y = y;
        }
    }

    static void unionAndDivide() {
        Map<Point, ArrayList<Fireball>> map = new HashMap<>();
        ArrayList<Fireball> newFireballs = new ArrayList<>();

        for (Fireball fireball : fireballs) {
            Point p = new Point(fireball.x, fireball.y);
            map.computeIfAbsent(p, k -> new ArrayList<>()).add(fireball);
        }

        for (Point point : map.keySet()) {
            ArrayList<Fireball> list = map.get(point);

            if (list.size() == 1) {
                newFireballs.add(list.get(0));
            } else {
                int sumM = 0;
                int sumS = 0;
                boolean even = false;
                boolean odd = false;

                for (Fireball fireball : list) {
                    sumM += fireball.m;
                    sumS += fireball.s;

                    if (fireball.d % 2 ==0) {
                        even = true;
                    }

                    if (fireball.d % 2 !=0) {
                        odd = true;
                    }
                }

                int newM = sumM / 5;

                if (newM == 0) continue;

                int newS = sumS / list.size();
                boolean dir = false;

                // 1, 3, 5, 7
                if (even && odd) {
                    dir = true;
                }

                if (dir) {
                    newFireballs.add(new Fireball(point.x, point.y, newM, 1, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 3, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 5, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 7, newS));
                } else {
                    newFireballs.add(new Fireball(point.x, point.y, newM, 0, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 2, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 4, newS));
                    newFireballs.add(new Fireball(point.x, point.y, newM, 6, newS));
                }
            }
        }

        fireballs.clear();
        fireballs.addAll(newFireballs);
    }

    static int calculate() {
        int sum = 0;
        for (Fireball fireball : fireballs) {
            sum += fireball.m;
        }
        return sum;
    }

    static class Fireball{
        int x;
        int y;
        int m;
        int d;
        int s;

        public Fireball(int x, int y, int m, int d, int s) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
