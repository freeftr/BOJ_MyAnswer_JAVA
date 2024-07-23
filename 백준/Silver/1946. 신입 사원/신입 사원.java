import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        String s;
        StringBuilder result = new StringBuilder();
        for(int t = 0; t < T; t++){
            int cnt = 0;
            int N = Integer.parseInt(br.readLine());
            int[] grade = new int[N+1];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                grade[a] = b;
            }
            int k = grade[1];
            for(int i = 1; i <= N; i++){
                if(grade[i] > k){
                    cnt++;
                }
                if(grade[i] < k){
                    k = grade[i];
                }
            }
            result.append((N-cnt)+"\n");
        }
        bw.write(result.toString());
        bw.close();
        br.close();
    }
}

//1   4
//2   5     O
//3   6     O
//4   2
//5   7     O
//6   1
//7   3     O