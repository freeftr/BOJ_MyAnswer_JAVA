import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static boolean[] visited;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        answer = new int[N+1];
        permutation(1);
        System.out.println(sb.toString());
    }
    public static void permutation(int depth) throws IOException{
        if(depth==N+1){
            for(int i = 1; i <= N; i++){
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 1; i<=N; i++){
            if(visited[i])continue;
            visited[i]=true;
            answer[depth] = i;
            permutation(depth+1);
            visited[i]=false;
        }
    }
}