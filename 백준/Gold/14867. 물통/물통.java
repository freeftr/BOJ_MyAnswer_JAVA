import java.io.*;
import java.util.*;

public class Main {

    static int A, B, a, b;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        visited = new boolean[A+1][B+1];

        System.out.println(bfs());
    }


    static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int nowA = arr[0], nowB = arr[1];
            int dist = arr[2];

            if(nowA == a && nowB == b){
                return dist;
            }

            if(!visited[A][nowB]){
                visited[A][nowB] = true;
                q.add(new int[]{A, nowB, dist+1});
            }

            if(!visited[nowA][B]){
                visited[nowA][B] = true;
                q.add(new int[]{nowA, B, dist+1});
            }

            if(!visited[0][nowB]){
                visited[0][nowB] = true;
                q.add(new int[]{0, nowB, dist+1});
            }

            if(!visited[nowA][0]){
                visited[nowA][0] = true;
                q.add(new int[]{nowA, 0, dist+1});
            }

            //B -> A 인데 A가 넘치는 경우
            if(nowA+nowB>A){
                if(!visited[A][nowB-(A-nowA)]){
                    visited[A][nowB-(A-nowA)] = true;
                    q.add(new int[]{A, nowB-(A-nowA), dist+1});
                }
            }
            //B -> A 인데 A가 안 넘치는 경우
            if(nowA+nowB<=A){
                if(!visited[nowA+nowB][0]){
                    visited[nowA+nowB][0] = true;
                    q.add(new int[]{nowA+nowB, 0, dist+1});
                }
            }

            if(nowA+nowB>B){
                if(!visited[nowA-(B-nowB)][B]){
                    visited[nowA-(B-nowB)][B] = true;
                    q.add(new int[]{nowA - (B-nowB), B, dist+1});
                }
            }

            if(nowA+nowB<=B){
                if(!visited[0][nowA+nowB]){
                    visited[0][nowA+nowB] = true;
                    q.add(new int[]{0, nowA+nowB, dist+1});
                }
            }
        }

        return -1;
    }
}