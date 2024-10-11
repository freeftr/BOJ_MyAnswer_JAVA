import java.io.*;
import java.util.*;
public class Main {

    static StringBuilder sb = new StringBuilder();
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();
        String P = br.readLine();
        KMP(T, P);
        System.out.println(ans);
        System.out.println(sb.toString());
    }

    static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)){
                idx = table[idx-1];
            }

            if(pattern.charAt(i) == pattern.charAt(idx)){
                idx += 1;
                table[i] = idx;
            }
        }

        return table;
    }

    static void KMP(String parent, String pattern){
        int[] table = makeTable(pattern);

        int n1 = parent.length(), n2 = pattern.length();
        int idx = 0;
        for (int i = 0; i < n1; i++) {
            while(idx > 0 && parent.charAt(i) != pattern.charAt(idx)){
                idx = table[idx-1];
            }
            if(parent.charAt(i) == pattern.charAt(idx)){
                if(idx == n2-1){
                    sb.append(i-idx+1 + " ");
                    idx = table[idx];
                    ans++;
                }
                else{
                    idx++;
                }
            }
        }
    }
}