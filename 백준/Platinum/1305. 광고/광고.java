import java.io.*;
public class Main {

    static int L;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        L = Integer.parseInt(br.readLine());
        String s = br.readLine();
        n = s.length();
        int[] table = makeTable(s);
        int ans = L - table[n-1];
        System.out.println(ans);
    }

    static int[] makeTable(String pattern){
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)){
                idx = table[idx-1];
            }

            if(pattern.charAt(i) == pattern.charAt(idx)){
                idx++;
                table[i] = idx;
            }
        }

        return table;
    }
}