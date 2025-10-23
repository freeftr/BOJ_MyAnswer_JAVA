import java.util.*;

class Solution {
    
    static Stack<Node> deleted = new Stack<>();
    static boolean[] check;
    static ArrayList<Node> nodes = new ArrayList<>();
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        check = new boolean[n];
        // 현재 위치 설정.
        int cur = k;
        
        // 노드 초기화
        for (int i = 0; i < n; i++) {
            Node now = new Node(i, null, null);
            nodes.add(now);
            if (i != 0) {
                now.prev = nodes.get(i - 1);
                nodes.get(i - 1).next = now;
            }
        }
        
        for (String c : cmd) {
            String type = c.split(" ")[0];
            Node now = nodes.get(cur);
            
            if (type.equals("U")) {
                int X = Integer.parseInt(c.split(" ")[1]);
                cur = now.up(X);
            }
            
            if (type.equals("D")) {
                int X = Integer.parseInt(c.split(" ")[1]);
                cur = now.down(X);
            }
            
            if (type.equals("C")) {
                cur = now.delete();
            }
            
            if (type.equals("Z")) {
                Node last = deleted.pop();
                check[last.idx] = false;
                last.restore();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            sb.append(check[nodes.get(i).idx] ? "X" : "O");
        }
        
        return sb.toString();
    }
    
    static class Node {
        int idx;
        Node prev;
        Node next;
        
        Node (int idx, Node prev, Node next) {
            this.idx = idx;
            this.prev = prev;
            this.next = next;
        }
        
        public int up (int X) {
            if (X == 0) {
                return this.idx;
            }
            
            return this.prev.up(X - 1);
        }
        
        public int down (int X) {
            if (X == 0) {
                return this.idx;
            }
            
            return this.next.down(X - 1);
        }
        
        public int delete () {
            int idx = this.idx;
            
            if (this.next == null) {
                idx = this.prev.idx;
                this.prev.next = null;
            } else if (this.prev == null) {
                idx = this.next.idx;
                this.next.prev = null;
            } else {
                idx = this.next.idx;
                Node temp = this.prev;
                this.prev.next = this.next;
                this.next.prev = temp;
            }
            
            deleted.add(this);
            check[this.idx] = true;
            return idx;
        }
        
        public void restore() {
            if (this.next != null) {
                this.next.prev = this;
            }
            
            if (this.prev != null) {
                this.prev.next = this;
            }
        }
    }
}

/*
조건
- 행을 선택, 삭제, 복구
- U X: X 칸 위에 있는 행 선택
- D X: X 칸 아래 있는 행 선택
- C: 현재 행 삭제 후 아래 행 선택. (만약 마지막행인 경우 바로 윗 행 선택)
- Z: 가장 마지막에 삭제된 행 복구, 선택 행은 안바뀜

요구
- 삭제된 행은 X 삭제되지 않은 행은 O로 표시해서 반환.

풀이
- 연결리스트
*/