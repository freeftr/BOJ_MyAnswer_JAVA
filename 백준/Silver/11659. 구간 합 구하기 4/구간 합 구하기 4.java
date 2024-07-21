import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M =sc.nextInt();
        int[] arr = new int[N+1];
        int[] result = new int[M];
        for(int i = 1; i <= N; i++){
            arr[i] = sc.nextInt();
        }
        for(int i = 1; i <= N; i++){
            arr[i] = arr[i-1] + arr[i];
        }
        for(int i = 0; i < M; i++){
            int lidx = sc.nextInt(), ridx = sc.nextInt();
            result[i] =  arr[ridx] - arr[lidx-1];
        }
        for(int i = 0; i < M; i++){
            System.out.println(result[i]);
        }
    }
}
