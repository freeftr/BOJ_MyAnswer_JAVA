import java.io.*;
import java.util.*;
public class Main {
    static int[] field = new int[3];
    static int N;
    static int[][] player;
    static int max = 0;
    static boolean[] visited ;
    static int[] lineup;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 0:1루 1:2루 2:3루

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        player = new int[10][N];
        //1:안타 2:2루타 3:3루타 4:홈런 0:아웃
        //각이닝마다 아웃한명씩잇음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                player[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        lineup = new int[10];
        visited = new boolean[10];
        lineup[4] = 1;
        visited[4] = true;
        seq(2);
        System.out.println(max);
    }
    public static int play(){
        int taasoon = 1;
        int inning = 0;
        int outcnt = 0;
        int score = 0;
        while(inning<N){
            if(player[lineup[taasoon]][inning]==0){
                outcnt++;
            }
            else{
                score+=hit(player[lineup[taasoon]][inning]);
            }
            taasoon++;
            if(outcnt==3){
                inning++;
                outcnt = 0;
                field=new int[]{0,0,0};
            }
            if(taasoon>9){
                taasoon = 1;
            }
        }
        return score;
    }

    //순열 검증 완료
    public static void seq(int num){
        if(num==10){
            int score = 0;
            score = play();
            max = Math.max(score, max);
        }
        //9까지 돌면서 방문안되어있으면 라인업에 추가
        for(int i =1; i<=9; i++){
            if(!visited[i]){
                visited[i] = true;
                lineup[i] = num;
                seq(num+1);
                visited[i] = false;
            }
        }
    }
    //주자와 타격에 따른 점수 획득 기능
    public static int hit(int num){
        int score = 0;
        for(int i = 2; i >=0; i--){
            if(field[i] == 1){
                if(i+num>=3){
                    score++;
                    field[i]=0;
                }
                else{
                    field[i] = 0;
                    field[i+num] = 1;
                }
            }
        }
        if(num<4) {
            field[num - 1] = 1;
        }
        if(num==4){
            score++;
        }
        return score;
    }
}
