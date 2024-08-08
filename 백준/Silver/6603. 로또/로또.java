import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static int[] arr;
    static boolean[] visited;
    static int[] temp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K==0){
                break;
            }
            arr = new int[K];
            visited = new boolean[K];
            temp = new int[6];
            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            if (K != 0) {
                func(0, 0);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }
    public static void func(int depth, int start){
        if(depth == 6){
            Arrays.sort(temp);
            for(int i = 0; i < 6; i++){
                sb.append(temp[i] +" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i < K; i++){
            if(!visited[i]){
                visited[i] = true;
                temp[depth]=arr[i];
                func(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
