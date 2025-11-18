import java.io.*;
import java.util.*;

public class Main {

    static int score = 0, ans = 0;

    static int N, M, D;
    static int[][] map;
    static int[][] mapCopy;

    static boolean end = false;

    public static class Enemy implements Comparable<Enemy> {

        int x;
        int y;
        int dist;

        Enemy(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Enemy o) {
            if (this.dist == o.dist) {
                return this.y - o.y; // 거리 같으면 y 좌표(열 번호) 기준으로 정렬
            }
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(map[N], 5);

        for (int a = 0; a < M; a++) {
            for (int b = a + 1; b < M; b++) {
                for (int c = b + 1; c < M; c++) {

//                    System.out.printf("a:%d b:%d c:%d\n",a,b,c);

                    score = 0;
                    end = false;

                    // 원본 맵 복사본 생성
                    mapCopy = new int[N + 1][M];
                    for (int i = 0; i < N + 1; i++) {
                        System.arraycopy(map[i], 0, mapCopy[i], 0, M);
                    }

                    while (!end) {
                        Attack(a, b, c);
                    }
//                    System.out.println(score);
                    ans = Math.max(ans, score);

//                    System.out.println("----------------------------------------------------------");
                }
            }
        }
        System.out.println(ans);
    }

    public static void Attack(int a, int b, int c) {
        PriorityQueue<Enemy> aq = new PriorityQueue<>();
        PriorityQueue<Enemy> bq = new PriorityQueue<>();
        PriorityQueue<Enemy> cq = new PriorityQueue<>();
        Set<String> targets = new HashSet<>(); // 공격할 적들의 좌표를 저장할 Set
        int enemy = 0;

        // 각 적에 대해
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mapCopy[i][j] == 1) {
                    enemy++;
                    // 각 궁수와의 거리 계산 및 큐에 추가
                    if (Math.abs(i - N) + Math.abs(j - a) <= D) {
                        aq.add(new Enemy(i, j, Math.abs(i - N) + Math.abs(j - a)));
                    }
                    if (Math.abs(i - N) + Math.abs(j - b) <= D) {
                        bq.add(new Enemy(i, j, Math.abs(i - N) + Math.abs(j - b)));
                    }
                    if (Math.abs(i - N) + Math.abs(j - c) <= D) {
                        cq.add(new Enemy(i, j, Math.abs(i - N) + Math.abs(j - c)));
                    }
                }
            }
        }

        // 각 궁수가 선택한 적의 좌표를 Set에 저장
        if (!aq.isEmpty()) {
            Enemy ae = aq.poll();
            targets.add(ae.x + "," + ae.y);
        }
        if (!bq.isEmpty()) {
            Enemy be = bq.poll();
            targets.add(be.x + "," + be.y);
        }
        if (!cq.isEmpty()) {
            Enemy ce = cq.poll();
            targets.add(ce.x + "," + ce.y);
        }

        // Set에 저장된 좌표에 있는 적들을 한꺼번에 제거
        for (String target : targets) {
            String[] pos = target.split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            mapCopy[x][y] = 0;
        }

        // 남은 적의 수를 세어 게임 종료 여부 확인
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mapCopy[i][j] == 1) {
                    cnt++;
                }
            }
        }



        // 이번 턴에 제거된 적의 수만큼 점수 추가
        score += enemy - cnt;

        if (cnt == 0) {
            end = true;
            return;
        }

//        System.out.println("Map after attack:");
//        printMap(mapCopy);
        if (!end) {
            move(); // 적 이동
        }
    }

    public static void move() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (mapCopy[i][j] == 1) {
                    if (i + 1 < N) {
                        if (mapCopy[i + 1][j] == 0) {
                            mapCopy[i + 1][j] = 1;
                            mapCopy[i][j] = 0;
                        }
                    } else {
                        // 적이 맵을 벗어나면 제거
                        mapCopy[i][j] = 0;
                    }
                }
            }
        }
//        System.out.println("Map after move:");
//        printMap(mapCopy);
    }

    public static void printMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}