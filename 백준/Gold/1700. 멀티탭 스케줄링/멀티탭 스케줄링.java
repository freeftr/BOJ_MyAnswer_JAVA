import java.io.*;
import java.util.*;
public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int ans = 0;
        Queue<Integer> order = new ArrayDeque<>();
        ArrayList<Integer> consent = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int a = Integer.parseInt(st.nextToken());
            order.add(a);
        }

        // 그리디 알고리즘
        for(int i = 0; i < K; i++){
            int cur = order.poll();

            // 콘센트에 이미 꽂혀 있는 경우
            if (consent.contains(cur)) continue;

            // 콘센트에 빈 공간이 있는 경우
            if (consent.size() < N) {
                consent.add(cur);
                continue;
            }

            // 콘센트가 가득 차 있는 경우
            if(consent.size() == N) {
                boolean xused = false;
                int size = consent.size();

                // 콘센트에 꽂힌 기기 중 뒤에서 더 이상 사용하지 않는 기기를 찾음
                for(int j = 0; j < size; j++){
                    if(!order.contains(consent.get(j))){
                        consent.set(j, cur);
                        ans++;
                        xused = true;
                        break;
                    }
                }
                if(xused) continue;

                // 모든 기기가 뒤에서 사용될 경우, 가장 늦게 사용하는 기기를 찾음
                int max = -1;
                int idx = -1;

                for(int j = 0; j < size; j++){
                    int nextUse = 0;
                    for(int next : order){
                        if(consent.get(j) == next){
                            break;
                        }
                        nextUse++;
                    }
                    if(nextUse > max) {
                        max = nextUse;
                        idx = j;
                    }
                }

                consent.set(idx, cur);
                ans++;
            }
        }
        System.out.println(ans);
    }
}