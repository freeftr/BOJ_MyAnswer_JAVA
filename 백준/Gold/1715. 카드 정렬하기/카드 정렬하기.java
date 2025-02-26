import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int ans = 0;

        while(!pq.isEmpty()) {

            if(pq.size()==1){
                break;
            }
            int a = pq.poll();
            int b = pq.poll();

            ans += a + b;
//            System.out.println("a: " + a + " b: " + b + " ans: " + ans);
            pq.add(a+b);
        }

        System.out.println(ans);
    }
}