import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] students = new long[N];
		for(int i = 0; i < N; i++) {
			students[i] = sc.nextInt();
		}
		long B = sc.nextInt(), C = sc.nextInt();
		
		long answer = 0;
		
		for(int i = 0; i < N; i++) {
			if(students[i] - B <= 0) {
				answer++;
			}
            else{
                if((students[i] - B) % C != 0){
                    answer += (students[i] - B)/C + 2;
                }
                else{
                    answer += (students[i] - B)/C + 1;
                }
            }
		}
		sc.close();
		System.out.println(answer);
	}

}
