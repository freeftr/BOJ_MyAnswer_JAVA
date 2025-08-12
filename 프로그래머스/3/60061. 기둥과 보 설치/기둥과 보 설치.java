import java.util.*;

class Solution {
    // ver[x][y][y+1] : (x, y)~(x, y+1) 기둥 존재
    // hor[y][x][x+1] : (x, y)~(x+1, y) 보 존재
    boolean[][][] ver;
    boolean[][][] hor;
    int N;

    public int[][] solution(int n, int[][] build_frame) {
        this.N = n;
        // 인덱스 여유를 약간 줘서 경계 체크 단순화
        ver = new boolean[N + 2][N + 2][N + 2];
        hor = new boolean[N + 2][N + 2][N + 2];

        for (int[] cmd : build_frame) {
            int x = cmd[0];
            int y = cmd[1];
            int a = cmd[2]; // 0: 기둥, 1: 보
            int b = cmd[3]; // 0: 삭제, 1: 설치

            if (a == 0) {
                if (b == 1) {
                    if (canPlaceVer(x, y)) {
                        ver[x][y][y + 1] = true;
                    }
                } else {
                    ver[x][y][y + 1] = false;
                    if (!isAllValid()) ver[x][y][y + 1] = true;
                }
            } else {
                if (b == 1) {
                    if (canPlaceHor(x, y)) {
                        hor[y][x][x + 1] = true;
                    }
                } else {
                    hor[y][x][x + 1] = false;
                    if (!isAllValid()) hor[y][x][x + 1] = true;
                }
            }
        }

        List<int[]> list = new ArrayList<>();
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (y + 1 <= N && ver[x][y][y + 1]) list.add(new int[]{x, y, 0});
                if (x + 1 <= N && hor[y][x][x + 1]) list.add(new int[]{x, y, 1});
            }
        }
        list.sort((p, q) -> {
            if (p[0] != q[0]) return p[0] - q[0];
            if (p[1] != q[1]) return p[1] - q[1];
            return p[2] - q[2];
        });

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        return answer;
    }

    private boolean isAllValid() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (y + 1 <= N && ver[x][y][y + 1]) {
                    if (!canPlaceVer(x, y)) return false;
                }
                if (x + 1 <= N && hor[y][x][x + 1]) {
                    if (!canPlaceHor(x, y)) return false;
                }
            }
        }
        return true;
    }

    private boolean canPlaceVer(int x, int y) {
        if (y == 0) return true;
        boolean onPillar = (y - 1 >= 0) && ver[x][y - 1][y];
        boolean onLeftBeamEnd = (x - 1 >= 0) && hor[y][x - 1][x];
        boolean onRightBeamEnd = hor[y][x][x + 1];
        return onPillar || onLeftBeamEnd || onRightBeamEnd;
    }

    private boolean canPlaceHor(int x, int y) {
        boolean leftPillar  = (y - 1 >= 0) && ver[x][y - 1][y];
        boolean rightPillar = (y - 1 >= 0) && (x + 1 <= N) && ver[x + 1][y - 1][y];
        boolean bothBeams   = (x - 1 >= 0) && (x + 2 <= N) && hor[y][x - 1][x] && hor[y][x + 1][x + 2];
        return leftPillar || rightPillar || bothBeams;
    }
}
