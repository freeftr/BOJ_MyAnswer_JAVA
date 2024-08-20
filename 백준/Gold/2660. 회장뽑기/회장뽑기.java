import java.util.*;
import java.io.*;
public class Main {
    static final int INF = 999999999;
    static int N;
    static int[][] graph;
    static int[] score;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        score = new int[N + 1];
        graph = new int[N + 1][N + 1];

        //그래프 초기화
        //간선이 0개일때 최단 거리 0 이므로 i!=j
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i!=j) graph[i][j] = INF;
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        //플로이드 워셜
        //노드를 1부터 N개 까지 거치기
        for (int k = 1; k <= N; k++){
            //i에서 j로 가기
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    //최단 거리 갱신
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        //반장의 점수
        int idx = INF;
        //학생 별 점수 고르기
        for(int i = 1; i <= N; i++){
            int a = 0;
            for(int j = 1; j <= N; j++){
                if(graph[i][j]!=INF){
                    a = Math.max(graph[i][j], a);
                }
            }
            score[i] = a;
            idx = Math.min(a,idx);
        }
        //후보군 검색
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            if(idx == score[i]){
                cnt++;
            }
        }
        sb.append(idx + " " + cnt + "\n");
        for(int i = 1; i <= N; i++){
            if(idx == score[i]){
                sb.append(i + " ");
            }
        }
        bw.write(sb.toString());
        bw.close();
    }
}