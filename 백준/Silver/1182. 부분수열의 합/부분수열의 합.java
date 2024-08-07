import java.io.*;
import java.util.*;
public class Main {
    static int N , S;
    static int[] arr;
    static int cnt = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        func(0, 0);
        if(S==0)cnt--;
        System.out.println(cnt);
    }
    public static void func(int depth, int sum){
        if(depth==N){
            if(sum==S)cnt++;
            return;
        }
        func(depth+1,sum);
        func(depth+1,sum+arr[depth]);
    }
}
