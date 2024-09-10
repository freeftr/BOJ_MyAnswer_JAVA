import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            String[] numbers = new String[N];

            for(int i = 0; i < N; i++){
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers);

            String ans = "YES";

            for(int i = 0; i < N-1; i++){
                if(numbers[i+1].startsWith(numbers[i])){
                    ans = "NO";
                    break;
                }
            }

            sb.append(ans+"\n");
        }
        System.out.println(sb);
    }
}