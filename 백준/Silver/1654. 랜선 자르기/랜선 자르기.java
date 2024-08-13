import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    static long max = 0;
    static long[] lan;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lan = new long[K];
        for(int i = 0; i < K; i++){
            lan[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lan[i]);
        }
        BinarySearch();
    }

    public static void BinarySearch(){
        long low = 1;
        long high = max;
        while(low<high){
            long mid = (low+high+1)/2;
            long k = 0;
            for(int i = 0; i < K; i++){
                k+=lan[i]/mid;
            }
            if(k>=N){
                low = mid;
            }
            else{
                high = mid - 1;
            }
        }
        System.out.println(low);
    }
}