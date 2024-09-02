import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> answer = new ArrayList<>();


        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            inDegree[B]++;
        }

        for(int i = 1; i <= N; i++){
            if(inDegree[i]==0){
                pq.add(i);
            }
        }
        while(!pq.isEmpty()){
            int v = pq.poll();
            answer.add(v);

            for(int nv : graph.get(v)){
                inDegree[nv]--;
                if(inDegree[nv]==0){
                    pq.add(nv);
                }
            }
        }

        for(int i : answer){
            System.out.printf("%d ", i);
        }
    }
}