import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> lis = new ArrayList<>();
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            int value = arr[i];
            if(lis.isEmpty() || lis.get(lis.size() - 1) < value) {
                lis.add(value);
                idx[i] = lis.size();
            }
            else{
                int pos = Collections.binarySearch(lis, value);
                if(pos < 0){
                    pos = -pos - 1;
                }
                lis.set(pos, value);
                idx[i] = pos + 1;
            }
        }

        System.out.println(lis.size());
    }
}