import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0, sum = 0;
        int lidx = 0, ridx = 0;
        if(N == 1){
            System.out.println(1);
        }
        else {
            while (lidx <= N && ridx <= N) {
                ridx++;
                lidx = ridx;
                sum = 0;
                while (lidx >= 0) {
                    lidx--;
                    sum = (lidx + ridx) * (ridx - lidx + 1) / 2;
                    if (sum > N) {
                        break;
                    }
                    if (sum == N) {
                        result++;
                        break;
                    }
                }
            }
            System.out.println(result + 1);
        }
    }
}