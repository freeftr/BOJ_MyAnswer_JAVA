import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static long max = 0, result=0;
    static int[] immi;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        immi = new int[N];
        for(int i = 0; i < N; i++){
            immi[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,immi[i]);
        }
        BinarySearch();
    }
    public static void BinarySearch(){
        long low = 1;
        long high = max*M;
        while(low<=high){
            long target = 0;
            long mid = (low+high)/2;
            for(long i : immi){
                target+=mid/i;
                if(target>=M)break;
            }
            if(target>=M){
                high = mid - 1;
                if(result == 0) result = mid;
                else if(result>mid){
                    result = mid;
                }
            }
            else{
                low = mid+1;
            }
        }
        System.out.println(result);
    }
}