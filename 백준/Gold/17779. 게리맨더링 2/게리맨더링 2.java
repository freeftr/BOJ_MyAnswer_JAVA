import java.util.*;
import java.io.*;

public class Main {
    //      x,y의 값을 정해야 한다.
    //      d1,d2 값을 정해야 한다.
    //      => 완탐으로 해본다
    static int N;
    static int[][] map;
    static int[][] union;
    static int sum = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //  입력
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                sum+=map[i][j];
            }
        }
        //  x, y, d1, d2 값 구하기
        int ans = sum;
        for(int x = 1; x <= N; x++){
            for(int y = 1; y <= N; y++){
                for(int d1 = 1; d1 <= N-1; d1++){
                    for(int d2 = 1; d2 <= N-1; d2++){
                       if(1<=x+d1+d2 && x+d1+d2<=N && 1<=y-d1 && y+d2<=N){
                           union = new int[N+1][N+1];
                           make_section(x,y,d1,d2);
                           ans=Math.min(ans,calculate(x,y,d1,d2));
                       }
                    }
                }
            }
        }
        System.out.println(ans);
//        union = new int[N+1][N+1];
//        make_section(3,5,2,1);
//        int a = calculate(3,5,2,1);
//        System.out.println(a);
    }


    // union에 경계정보 설정하는 메소드
    public static void make_section(int x, int y, int d1, int d2){
       //   경계 설정

        //  왼쪽 윗변, 아랫변
        for(int i = 0; i <= d1; i++){
            union[x+i][y-i] = 5;
            union[x+d2+i][y+d2-i] = 5;
        }
        //  오른쪽 윗변, 아랫변
        for(int i = 0; i <= d2; i++){
            union[x+i][y+i] = 5;
            union[x+d1+i][y-d1+i] = 5;
        }
    }

    public static int calculate(int x, int y, int d1, int d2){
        int sec1=0, sec2=0, sec3=0, sec4=0, sec5=0;
        int max = 0;
        int min = sum;
        //  1번 구역 측정
        for(int r = 1; r < x + d1; r++){
            for(int c = 1; c <= y; c++){
                if(union[r][c]==5)break;
                sec1+=map[r][c];
            }
        }
        max = Math.max(max,sec1);
        min = Math.min(min,sec1);


        //  2번 구역 측정
        for(int r = x + d2; r>=1; r--){
            for(int c = N; c > y; c--){
                if(union[r][c]==5)break;
                sec2+=map[r][c];
            }
        }
        max = Math.max(max,sec2);
        min = Math.min(min,sec2);


        //  3번 구역 측정
        for(int r = x + d1; r <= N; r++){
            for(int c = 1; c < y-d1+d2; c++){
                if(union[r][c]==5)break;
                sec3+=map[r][c];
            }
        }
        max = Math.max(max,sec3);
        min = Math.min(min,sec3);


        //  4번 구역 측정
        for(int r = x + d2 + 1; r <= N; r++){
            for(int c = N; c >= y-d1+d2; c--){
                if(union[r][c]==5)break;
                sec4+=map[r][c];
            }
        }
        max = Math.max(max,sec4);
        min = Math.min(min,sec4);



        sec5 = sum - sec1 - sec2 - sec3 - sec4;
        max = Math.max(max,sec5);
        min = Math.min(min,sec5);
//        System.out.printf("1:%d\n2:%d\n3:%d\n4:%d\n5:%d\n",sec1,sec2,sec3,sec4,sec5);
        return max-min;
    }
}