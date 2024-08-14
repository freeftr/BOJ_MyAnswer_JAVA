import java.util.*;
import java.io.*;
public class Main {
    static int ans = 0;
    static int sum = 0;
    static int max = 0;
    static int N;
    static long M;
    static long[] province;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        province = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            province[i] = Integer.parseInt(st.nextToken());
        }
        M = Long.parseLong(br.readLine());
        Arrays.sort(province);
        long low = 0;
        long high = province[N-1];
        long mid = 0;
        while(low<=high){
            mid = (low+high)/2;
            long sum = 0;
            for(int i = 0; i < N; i++){
                if(province[i]>mid){
                    sum+=mid;
                }
                else{
                    sum+=province[i];
                }
            }
            if(sum<=M) {
                low = mid + 1;
            }
            else{
                high = mid-1;
            }
        }
        System.out.println(high);
    }
}
