import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem>{
        int index;
        int difficulty;

        public Problem(int index, int difficulty) {
            this.index = index;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem o){
            if(difficulty != o.difficulty){
                return Integer.compare(difficulty, o.difficulty);
            }
            else{
                return Integer.compare(index, o.index);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TreeSet<Problem> ts = new TreeSet<>();
        HashMap<Integer,Integer> hm = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            ts.add(new Problem(P,L));
            hm.put(P,L);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if(s.startsWith("recommend")) {
                String[] temp = s.split(" ");
                int x = Integer.parseInt(temp[1]);
                if(x == 1){
                    System.out.println(ts.last().index);
                } else {
                    System.out.println(ts.first().index);
                }
            }

            else if(s.startsWith("add")){
                String[] temp = s.split(" ");
                int P = Integer.parseInt(temp[1]);
                int L = Integer.parseInt(temp[2]);
                ts.add(new Problem(P, L));
                hm.put(P, L); 
            }

            else if(s.charAt(0)=='s'){
                String[] temp = s.split(" ");
                int P = Integer.parseInt(temp[1]);
                int L = hm.get(P);
                ts.remove(new Problem(P,L));
                hm.remove(P);
            }
        }
    }
}