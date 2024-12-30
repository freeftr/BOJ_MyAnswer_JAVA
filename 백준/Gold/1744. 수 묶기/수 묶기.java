import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer> plus = new ArrayList<>();
    static ArrayList<Integer> minus = new ArrayList<>();
    static long sum = 0;
    static boolean zero = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x > 0) {
                plus.add(x);
            }
            else if (x < 0) {
                minus.add(-x);
            }
            else if (x==0){
                zero = true;
            }
        }

        plus.sort(Collections.reverseOrder());
        minus.sort(Collections.reverseOrder());

        for (int i = 0; i < plus.size(); i++) {
            if (i<plus.size()-1) {
                //하나가 1인 경우 더하는게 낫다
                if(plus.get(i)==1 || plus.get(i+1)==1) {
                    sum+=plus.get(i) + plus.get(i+1);
                    i++;
                    continue;
                }
                sum += plus.get(i) * plus.get(i+1);
                i++;
            }
        }
        if (plus.size()%2 != 0){
            sum += plus.get(plus.size()-1);
        }

        for (int i = 0; i < minus.size(); i++) {
            if (i<minus.size()-1) {
                sum += minus.get(i) * minus.get(i+1);
                i++;
            }
        }

        if (minus.size()%2 != 0 && !zero){
            sum -= minus.get(minus.size()-1);
        }

        System.out.println(sum);
    }
}