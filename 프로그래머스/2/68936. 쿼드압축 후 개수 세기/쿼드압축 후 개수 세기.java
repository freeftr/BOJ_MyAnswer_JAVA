class Solution {
    public int[] solution(int[][] arr) {
        return dfs(0, 0, arr.length, arr[0].length, arr);
    }

    static int[] dfs(int sx, int sy, int ex, int ey, int[][] arr) {
        if (sx + 1 == ex && sy + 1 == ey) {
            return arr[sx][sy] == 0 ? new int[]{1, 0} : new int[]{0, 1};
        }

        int cnt0 = 0, cnt1 = 0;
        for (int i = sx; i < ex; i++) {
            for (int j = sy; j < ey; j++) {
                if (arr[i][j] == 0) cnt0++;
                else cnt1++;
            }
        }

        int total = (ex - sx) * (ey - sy);
        if (cnt0 == total) return new int[]{1, 0};
        if (cnt1 == total) return new int[]{0, 1};

        int[] ret = new int[2];
        int mx = (sx + ex) / 2;
        int my = (sy + ey) / 2;

        int[] temp = dfs(sx, sy, mx, my, arr);
        ret[0] += temp[0]; ret[1] += temp[1];

        temp = dfs(sx, my, mx, ey, arr);
        ret[0] += temp[0]; ret[1] += temp[1];

        temp = dfs(mx, sy, ex, my, arr);
        ret[0] += temp[0]; ret[1] += temp[1];

        temp = dfs(mx, my, ex, ey, arr);
        ret[0] += temp[0]; ret[1] += temp[1];

        return ret;
    }
}
