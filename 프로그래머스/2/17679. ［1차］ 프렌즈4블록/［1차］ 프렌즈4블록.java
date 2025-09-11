import java.util.*;

class Solution {
    
    static int N, M, answer = 0;
    static char[][] map;
    static HashSet<String> set = new HashSet<>();
    
    public int solution(int m, int n, String[] board) {
        M = m;
        N = n;
        map = new char[M][N];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        // print();
        // erase();
        // print();
        // down();
        // print();
        // erase();
        // print();
        
        boolean flag = true;
        while (flag) {
            flag = erase();
            down();
        }
        return answer;
    }
    
    static boolean erase() {
        boolean flag = false;
        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == ' ') continue;
                if (map[i][j] == map[i][j + 1] 
                    && map[i][j] == map[i + 1][j + 1]
                    && map[i][j] == map[i + 1][j]) {
                    String s = i + " " + j;
                    set.add(s);
                    s = (i + 1) + " " + j;
                    set.add(s);
                    s = (i + 1) + " " + (j + 1);
                    set.add(s);
                    s = i + " " + (j + 1);
                    set.add(s);
                }
            }
        }
        
        for (String s : set) {
            int x = Integer.parseInt(s.split(" ")[0]);
            int y = Integer.parseInt(s.split(" ")[1]);
            map[x][y] = ' ';
            answer++;
            flag = true;
        }
        set.clear();
        
        return flag;
    }
    
    static void down() {
        for (int i = 0; i < N; i++) {
            for (int j = M - 2; j >= 0; j--) {
                if (map[j][i] != ' ') {
                    char base = map[j][i];
                    int x = j;
                    while (x < M - 1) {
                        int nx = x + 1;
                        if (map[nx][i] == ' ') {
                            map[nx][i] = base;
                            map[x][i] = ' ';
                            x = nx;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    static void print() {
        for (int i = 0; i < M; i++) {
            System.out.println();
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
        }
        System.out.println();
    }
}

/*
같은 모양 4개 정사각형 => 제거
빈 캄 메꾸려고 내려감

지워지는 거 개수 세기

1. 있는거 지우기
2. 내리기
3. 1 반복
*/