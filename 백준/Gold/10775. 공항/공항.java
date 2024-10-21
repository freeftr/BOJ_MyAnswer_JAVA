import java.io.*;
import java.util.*;

public class Main {
    static int G, P;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G+1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 1; i <= P; i++) {
            int a = Integer.parseInt(br.readLine());
            if(find(a)==0){
                break;
            }
            ans++;
            union(find(a), find(a)-1);
        }
        System.out.println(ans);
    }

    static int find(int v){
        if(parent[v]==v){
            return v;
        }
        return parent[v]=find(parent[v]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        parent[a] = b;
    }
}