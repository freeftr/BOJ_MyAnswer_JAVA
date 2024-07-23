import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> S = new ArrayList<>();
        ArrayList<String> P = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            S.add(s);
        }
        for(int i = 0; i < M; i++){
            String p = br.readLine();
            if(S.contains(p)){
                cnt++;
            }
        }
        bw.write(cnt + "");
        br.close();
        bw.close();
    }
}
