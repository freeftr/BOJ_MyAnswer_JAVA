class Solution {
    public int[] solution(String[] wallpaper) {
        int N = wallpaper.length;
        int M = wallpaper[0].length();
        int lux = N, luy = M, rdx = 0, rdy = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    lux = Math.min(i, lux);
                    luy = Math.min(j, luy);
                    rdx = Math.max(i + 1, rdx);
                    rdy = Math.max(j + 1, rdy);
                }
            } 
        }
        return new int[]{lux, luy, rdx, rdy};
    }
}

/*
조건
- 바탕화면에 아무데나 저장함.
- # = 파일.
- 드래그로 선택.
- 최소한의 이동거리를 갖는 드래그로 모든 파일 선택하기.

풀이
- 제일 높은 거를 이제 lux
- 제일 낮은 거를 이제 rdx
- 제일 왼쪽을 이제 luy
- 제일 오른쪽을 이제 rdy
*/