class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        int w = park[0].length();
        int h = park.length;
        
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (park[i].charAt(j) == 'S') {
                    x = i;
                    y = j;
                }
            }
        }
        
        for (String route : routes) {
            String dir = route.split(" ")[0];
            int dist = Integer.parseInt(route.split(" ")[1]);
            
            if (dir.equals("N")) {
                int nx = x - dist;
                int ny = y;
                
                if (nx < 0) continue;
                boolean flag = false;
                for (int i = x; i >= nx; i--) {
                    if (park[i].charAt(ny) == 'X') {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                
                x = nx;
                y = ny;
            }
            
            if (dir.equals("S")) {
                int nx = x + dist;
                int ny = y;
                
                if (nx >= h) continue;
                boolean flag = false;
                for (int i = x; i <= nx; i++) {
                    if (park[i].charAt(ny) == 'X') {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                
                x = nx;
                y = ny;
            }
            
            if (dir.equals("W")) {
                int nx = x;
                int ny = y - dist;
                
                if (ny < 0) continue;
                boolean flag = false;
                for (int i = y; i >= ny; i--) {
                    if (park[nx].charAt(i) == 'X') {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                
                x = nx;
                y = ny;
            }
            
            if (dir.equals("E")) {
                int nx = x;
                int ny = y + dist;
                
                if (ny >= w) continue;
                boolean flag = false;
                for (int i = y; i <= ny; i++) {
                    if (park[nx].charAt(i) == 'X') {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                
                x = nx;
                y = ny;
            }
        }
        
        return new int[]{x, y};
    }
}

/*
조건
- 방향 거리 로 쿼리 주어짐.
- 주어진 방향으로 공원 벗어나는지 확인.
- 장애물 만나는지 확인.
- 하나라도 해당시 다음 쿼리

요구
- 마지막 강아지 위치 반환
*/