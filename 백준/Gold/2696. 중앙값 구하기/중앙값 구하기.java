import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int M;

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = sc.nextInt();

        for(int t = 1; t <= T; t++){

            M = sc.nextInt();

            int length = (M + 1)/2;

            sb.append(length+"\n");

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            PriorityQueue<Integer> temp = new PriorityQueue<>();

            for(int i = 1; i <= M; i++){

                int b = sc.nextInt();

                pq.add(b);

                if(i%2==1) {

                    for (int j = 1; j <= (i + 1) / 2; j++) {

                        int a = pq.poll();

                        if(j==(i+1)/2) {
                            sb.append(a + " ");
//                            System.out.println(a);
                        }

                        temp.add(a);
                    }

                    pq.addAll(temp);
                    temp.clear();

                }
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}