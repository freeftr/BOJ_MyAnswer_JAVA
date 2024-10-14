import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] belt;
    static ArrayDeque<Robot> robots = new ArrayDeque<>();

    static class Robot implements Comparable<Robot> {
        int idx;
        int pos;

        public Robot(int idx, int pos) {
            this.idx = idx;
            this.pos = pos;
        }

        @Override
        public int compareTo(Robot o) {
            return idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2*N+1];
        belt[0] = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2*N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int cnt = 0;
        int turn = 1;
        HashSet<Integer> set = new HashSet<>();

        while (true) {

            cnt = 0;

            //벨트 회전  => 완료
            int temp = belt[1];
            for (int i = N*2; i >= 1; i--) {
                if(i==N*2){
                    belt[1] = belt[2*N];
                }
                else{
                    belt[i+1] = belt[i];
                }
            }
            belt[2] = temp;

            //벨트에 의해 움직이는 로봇
            int size = robots.size();
            for (int i = 0; i < size; i++) {
                Robot cur = robots.poll();
                if(cur.pos+1!=N){
                    robots.addLast(new Robot(cur.idx, cur.pos+1));
                    set.add(cur.pos+1);
                    set.remove(cur.pos);
                }
                else{
                    set.remove(cur.pos);
                }
            }

            //로봇 자체 이동
            size = robots.size();
            for (int i = 0; i < size; i++) {
                Robot cur = robots.poll();
                //앞칸에 로봇
                if(set.contains(cur.pos+1)){
                    robots.addLast(new Robot(cur.idx, cur.pos));
                    continue;
                }
                //앞칸 내구도 확인
                else if(belt[cur.pos+1]==0) {
                    robots.addLast(new Robot(cur.idx, cur.pos));
                    continue;
                }
                else{
                    if(cur.pos+1!=N){
                        set.add(cur.pos+1);
                        set.remove(cur.pos);
                        belt[cur.pos+1]--;
                        robots.addLast(new Robot(cur.idx, cur.pos+1));
                    }
                    else{
                        set.remove(cur.pos);
                        belt[cur.pos+1]--;
                    }
                }
            }

            //로봇 올리기
            if(belt[1]>0){
                idx++;
                robots.add(new Robot(idx,1));
                belt[1]--;
            }

            //내구도 없는 칸 계산
            for(int i : belt){
                if(i==0){
                    cnt++;
                }
            }

            if(cnt>=K){
                break;
            }
            turn++;
        }

        System.out.println(turn);
    }
}