import java.util.*;
import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] students = new int[N];
        int[] grades = new int[N];
        long answer = 0;

        for(int i = 0; i < N; i++){
            students[i] = sc.nextInt();
        }
        Arrays.sort(students);
        for(int i = 0; i < N; i++){
            answer += Math.abs(i + 1 - students[i]);
        }
        System.out.println(answer);
        sc.close();
    }
}
