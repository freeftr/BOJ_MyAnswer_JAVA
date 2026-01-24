import java.util.*;

class Solution {

    static boolean[] deleted;
    static ArrayList<Node> nodes;
    static Deque<Node> stack;

    static class Node {
        int idx;
        Node prev;
        Node next;

        Node(int idx) {
            this.idx = idx;
        }

        Node up(int x) {
            Node cur = this;
            while (x-- > 0 && cur.prev != null) cur = cur.prev;
            return cur;
        }

        Node down(int x) {
            Node cur = this;
            while (x-- > 0 && cur.next != null) cur = cur.next;
            return cur;
        }

        void delete() {
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            stack.push(this);
        }

        void restore() {
            if (prev != null) prev.next = this;
            if (next != null) next.prev = this;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        deleted = new boolean[n];
        nodes = new ArrayList<>(n);
        stack = new ArrayDeque<>();

        // 노드 생성 + 리스트에 저장
        for (int i = 0; i < n; i++) nodes.add(new Node(i));

        // 양방향 연결
        for (int i = 0; i < n; i++) {
            if (i > 0) nodes.get(i).prev = nodes.get(i - 1);
            if (i < n - 1) nodes.get(i).next = nodes.get(i + 1);
        }

        Node now = nodes.get(k);

        for (String c : cmd) {
            char type = c.charAt(0);

            if (type == 'U') {
                int x = Integer.parseInt(c.substring(2));
                now = now.up(x);

            } else if (type == 'D') {
                int x = Integer.parseInt(c.substring(2));
                now = now.down(x);

            } else if (type == 'C') {
                deleted[now.idx] = true;

                Node nextNode = (now.next != null) ? now.next : now.prev; // 이동할 위치 먼저 확보
                now.delete();
                now = nextNode;

            } else { // 'Z'
                Node top = stack.pop();
                deleted[top.idx] = false;
                top.restore();
            }
        }

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(deleted[i] ? 'X' : 'O');
        return sb.toString();
    }
}
