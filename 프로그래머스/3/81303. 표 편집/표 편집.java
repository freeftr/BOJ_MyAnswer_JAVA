import java.util.*;

class Solution {
    static Deque<Node> stack = new ArrayDeque<>();

    public String solution(int n, int k, String[] cmd) {
        ArrayList<Node> nodes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) nodes.add(new Node(i));
        for (int i = 0; i < n; i++) {
            if (i > 0) nodes.get(i).prev = nodes.get(i - 1);
            if (i < n - 1) nodes.get(i).next = nodes.get(i + 1);
        }

        Node now = nodes.get(k);
        for (String c : cmd) {
            char op = c.charAt(0);
            if (op == 'U' || op == 'D') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) now = (op == 'U') ? now.prev : now.next;
            } else if (op == 'C') {
                stack.push(now);
                now.deleted = true;
                if (now.prev != null) now.prev.next = now.next;
                if (now.next != null) now.next.prev = now.prev;
                now = (now.next != null) ? now.next : now.prev;
            } else {
                Node r = stack.pop();
                r.deleted = false;
                if (r.prev != null) r.prev.next = r;
                if (r.next != null) r.next.prev = r;
            }
        }

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(nodes.get(i).deleted ? 'X' : 'O');
        return sb.toString();
    }

    static class Node {
        int idx;
        Node prev, next;
        boolean deleted;
        Node(int idx) { this.idx = idx; }
    }
}
