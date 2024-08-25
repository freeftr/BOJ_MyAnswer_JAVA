import java.io.*;
import java.util.*;

public class Solution {

    static int M, a, ans=0, total = 0;
    static int[] A, B;
    static ArrayList<bc> BC;

    static StringBuilder sb = new StringBuilder();
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};

    static public class bc implements Comparable<bc>{
        int x;
        int y;
        int range;
        int power;
        bc(int x, int y, int range, int power){
            this.x = x;
            this.y = y;
            this.range = range;
            this.power = power;
        }
        @Override
        public int compareTo(bc other) {
            return other.power - this.power;
        }
    }

    public static void main(String[] args) throws Exception, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t ++) {
            total = 0;

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            A = new int[M+1];
            B = new int[M+1];
            BC = new ArrayList<>();


            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=M;i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=M;i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<a;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                BC.add(new bc(x, y, range, power));
            }

            move();
            sb.append("#" + t + " " + total + "\n");
        }

        System.out.println(sb.toString());
    }

    //움직임 구현
    public static void move() {

        int A_x=1, A_y=1;
        int B_x=10, B_y=10;
        total+=recharge(A_x, A_y, B_x, B_y);

        for(int i=1;i<=M;i++) {
            A_x += dx[A[i]];
            A_y += dy[A[i]];
            B_x += dx[B[i]];
            B_y += dy[B[i]];
            total+=recharge(A_x, A_y, B_x, B_y);
        }
    }

    //충전량 계산
    static int recharge(int A_x, int A_y, int B_x, int B_y) {
        int total=0;

        for(int a = BC.size()-1; a >= 0; a--) {
            int A = isRange(a, A_x, A_y);
            for(int b = BC.size()-1; b >= 0; b--) {
                int sum=0;
                int B = isRange(b, B_x, B_y);

                if(a != b) {
                    sum += A + B;
                }else {
                    sum = Math.max(A, B);
                }
                total = Math.max(total, sum);
            }
        }

        return total;
    }

    static int isRange(int idx, int x, int y) {
        int dist = Math.abs(BC.get(idx).x-x) + Math.abs(BC.get(idx).y-y);
        if(dist <= BC.get(idx).range){
            return BC.get(idx).power;
        }
        return  0;
    }


}

