import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static HashMap<String, String[]> promis_nine = new HashMap<>();
    static HashMap<String, String> baekjiheon = new HashMap<>();
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            String group = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] temp = new String[n];
            for(int j = 0; j < n; j++){
                String name = br.readLine();
                temp[j] = name;
                baekjiheon.put(name,group);
            }
            Arrays.sort(temp);
            promis_nine.put(group,temp);
        }
        for(int i = 0; i < M; i++){
            String s = br.readLine();
            int quiz = Integer.parseInt(br.readLine());
            if(quiz==0){
                for(String group : promis_nine.get(s)){
                    sb.append(group+"\n");
                }
            }
            else{
                sb.append(baekjiheon.get(s)+"\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
    }
}