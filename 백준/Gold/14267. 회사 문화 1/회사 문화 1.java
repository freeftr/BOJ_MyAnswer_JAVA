import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Integer>> company = new ArrayList<>();
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new int[n+1];
        for(int i = 0; i <= n; i++){
            company.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int a = Integer.parseInt(st.nextToken());
            if(a==-1){
                continue;
            }
            company.get(a).add(i);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ans[a]+=b;
        }
        dfs(1);
        for(int i = 1; i <= n; i++){
            sb.append(ans[i] + " ");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int v){
        for(int nv : company.get(v)){
            ans[nv] += ans[v];
            dfs(nv);
        }
    }
}