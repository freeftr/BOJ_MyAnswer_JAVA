import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] A;
    static int[][] map;

    static PriorityQueue<Tree> pq = new PriorityQueue<>();
    static Queue<Tree> grave = new LinkedList<>();

    static class Tree implements Comparable<Tree>{
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
        @Override
        public int compareTo(Tree o){
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        A = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                map[i][j] +=  5;
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            pq.add(new Tree(x,y,age));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(pq.size());
    }

    static void spring(){
        PriorityQueue<Tree> temp = new PriorityQueue<>();
        int size = pq.size();

        for (int i = 0; i < size; i++) {
            Tree tree = pq.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if(map[x][y]>=age){
                map[x][y]-=age;
                age++;
                temp.add(new Tree(x,y,age));
            }
            else{
                grave.add(tree);
            }
        }

        pq.addAll(temp);
    }

    static void summer(){
        while(!grave.isEmpty()){
            Tree tree = grave.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            map[x][y] += age/2;
        }
    }

    static int[] dx = {1,-1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};

    static void autumn(){
        List<Tree> temp = new ArrayList<>(pq);
        for (Tree tree : temp) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if(age % 5 == 0){
                for (int j = 0; j < 8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 1 || ny < 1 || nx > N || ny > N) continue;

                    pq.add(new Tree(nx, ny, 1));
                }
            }
        }
    }


    static void winter(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j]+=A[i][j];
            }
        }
    }
}