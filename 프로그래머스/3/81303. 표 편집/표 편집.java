import java.util.*;

class Solution {
    
    static Stack<Node> trashCan = new Stack<>();
    
    static class Node {
        Node prev, next;
        boolean deleted = false;
        
        public Node d(int x) {
            Node node = this;
            for (int i = 0; i < x; i++) {
                if (node.next != null) node = node.next;
            }
            return node;
        }
        
        public Node u(int x) {
            Node node = this;
            for (int i = 0; i < x; i++) {
                if (node.prev != null) node = node.prev;
            }
            return node;
        }
        
        public Node c() {
            this.deleted = true;
            trashCan.push(this);

            if (this.prev != null) this.prev.next = this.next;
            if (this.next != null) this.next.prev = this.prev;
            
            if (this.next!=null){
                return this.next;
            }
            return this.prev;
        }
        
        public void z() {
            if(!trashCan.isEmpty()){
                Node node = trashCan.pop();
                node.deleted = false;
                Node prev = node.prev;
                Node next = node.next;
            
               if (prev != null) prev.next = node;
               if (next != null) next.prev = node;
            }
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
                nodes[i - 1].next = nodes[i];
            }
        }
        
        Node now = nodes[k];

        for (String command : cmd) {
            char head = command.charAt(0);
            // System.out.println(command);
            if (head == 'U') {
                now = now.u(Integer.parseInt(command.substring(2)));
            } else if (head == 'D') {
                now = now.d(Integer.parseInt(command.substring(2)));
            } else if (head == 'C') {
                now = now.c();
            } else if (head == 'Z') {
                if (!trashCan.isEmpty()) {
                    now.z();
                }
            }
            // for (int i = 0; i < n; i++){
            //     if(nodes[i].deleted){
            //         System.out.print("X ");
            //     } else {
            //         System.out.print("O ");
            //     }
            // }
            // System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            if (nodes[i].deleted){
                sb.append("X");
            } else {
                sb.append("O");
            }
        }

        return sb.toString();
    }
}
