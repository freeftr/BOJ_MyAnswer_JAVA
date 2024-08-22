import java.util.*;
import java.io.*;
public class Main {
    static int M,N;
    static long L;
    static long[] gun;
    static long[][] target;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        gun = new long[M];
        target = new long[N][2];


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            gun[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(gun);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            target[i][0] = Long.parseLong(st.nextToken());
            target[i][1] = Long.parseLong(st.nextToken());
        }

        int low;
        int high;
        long dist;
        int ans = 0;
        for(int i = 0; i < N; i++) {
            low = 0;
            high = M-1;
            while (low <= high){
                int mid = (low+high)/2;
                dist = Math.abs(gun[mid]-target[i][0])+target[i][1];
                if(dist<=L){
                    ans++;
                    break;
                }
                else{
                    if(gun[mid]<target[i][0]){
                        low = mid + 1;
                    }
                    else{
                        high = mid - 1;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}