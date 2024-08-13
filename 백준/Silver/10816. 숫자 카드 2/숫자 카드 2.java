import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static long[] n, m;
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        n = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            n[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(n);
        M = Integer.parseInt(br.readLine());
        m = new long[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            m[i] = Long.parseLong(st.nextToken());
        }
        for(int i = 0; i < M; i++){
            sb.append((upperbound(m[i],N)-lowerbound(m[i],N)) + " ");
        }
        bw.write(sb.toString());
        bw.close();
    }
    public static int upperbound(long target, int len){
        int low = 0;
        int high = len;
        while(low<high){
            int mid = (low+high)/2;
            if(n[mid]>target){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
    public static int lowerbound(long target, int len){
        int low = 0;
        int high = len;
        while(low<high){
            int mid = (low+high)/2;
            if(n[mid]>=target){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
}