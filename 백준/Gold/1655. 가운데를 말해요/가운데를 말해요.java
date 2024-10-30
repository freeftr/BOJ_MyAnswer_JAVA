import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(((o1, o2) -> o1-o2));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2-o1));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if(i==0){
                maxHeap.add(a);
            }
            else if(i==1){
                if(a>=maxHeap.peek()){
                    minHeap.add(a);
                }
                else{
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(a);
                }
            }
            else{
                maxHeap.add(a);
                if(maxHeap.size()-minHeap.size()==1){
                    if(maxHeap.peek()>minHeap.peek()){
                        maxHeap.add(minHeap.poll());
                        minHeap.add(maxHeap.poll());
                    }
                }
                else{
                    minHeap.add(maxHeap.poll());
                }
            }
            sb.append(maxHeap.peek()+"\n");
        }
        System.out.println(sb);
    }
}