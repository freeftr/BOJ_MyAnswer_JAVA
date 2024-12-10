import java.io.*;
import java.util.*;

public class Main {
    static long G;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        G = Integer.parseInt(br.readLine());

        //예전 몸무게
        int left = 1;
        //현재 몸무게
        int right = 2;

        boolean flag = false;
        while(right<100000){
            //현재와 예전의 차이
            long g = right*right - left*left;
            if(g==G){
                System.out.println(right);
                flag=true;
            }
            if(g>G){
                left++;
            }
            else {
                right++;
            }
        }
        if(!flag){
            System.out.println(-1);
        }
    }
}