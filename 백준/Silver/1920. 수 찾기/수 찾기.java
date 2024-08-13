import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[] n, m;
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        n = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            n[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        m = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            m[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(n);
        for(int i = 0; i < M; i++){
            sb.append(binarySearch(m[i])+"\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
    public static int binarySearch(int target){
        int low = 0;
        int high = N-1;
        int mid=0;
        while(low<=high){
            mid = (low+high)/2;
            if(n[mid]>target){
                high = mid-1;
            }
            else if(n[mid]<target){
                low = mid+1;
            }
            else{
                return 1;
            }
        }
        return 0;
    }
}