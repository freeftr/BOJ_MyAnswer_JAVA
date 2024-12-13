import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int R, G;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        int GCD = 0;

        if(R>G){
            GCD = gcd(R,G);
        }
        else{
            GCD = gcd(G,R);
        }

//        System.out.println(GCD);
        for (int i = 1; i <= GCD; i++) {
            if(GCD%i==0){
                System.out.println(i + " " + R/i + " " + G/i);
            }
        }
    }

    static int gcd(int a, int b){
        if(b==0){
            return a;
        }
        return gcd(b, a%b);
    }
}