import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        LinkedList<Integer> dq = new LinkedList<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];
        int cnt = 0;
        //입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= N; i++){
            dq.add(i);
        }
        int idx = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < dq.size(); j++){
                if(dq.get(j) == arr[i]){
                    idx=j;
                    break;
                }
            }
            if(idx <= dq.size()/2){
                while(dq.peekFirst() != arr[i]){
                    dq.addLast(dq.pollFirst());
                    cnt++;
                }
            }
            else{
                while(dq.peekFirst() != arr[i]){
                    dq.addFirst(dq.pollLast());
                    cnt++;
                }
            }
            dq.pollFirst();
        }
        System.out.println(cnt);
    }
}
