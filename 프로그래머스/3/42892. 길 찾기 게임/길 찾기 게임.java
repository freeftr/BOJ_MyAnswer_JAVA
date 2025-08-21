import java.util.*;

class Solution {
    
    static ArrayList<Node> nodes = new ArrayList<>();
    static ArrayList<Integer> preorder = new ArrayList<>();
    static ArrayList<Integer> postorder = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        Collections.sort(nodes, (a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;
            }
            return b.y - a.y;
        });
        
        Node root = nodes.get(0);
        
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            
            makeTree(root, node);
        }
        
        proceedPreorder(root);
        proceedPostorder(root);
        
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = preorder.get(i);
            answer[1][i] = postorder.get(i);
        }
        return answer;
    }
    
    static void proceedPreorder(Node v) {
        preorder.add(v.id);
        
        if (v.left != null) {
            proceedPreorder(v.left);   
        }
        if (v.right != null) {
            proceedPreorder(v.right);
        }
    }
    
    static void proceedPostorder(Node v) {
        if (v.left != null) {
            proceedPostorder(v.left);   
        }
        if (v.right != null) {
            proceedPostorder(v.right);
        }
        postorder.add(v.id);
    }
    
    static void makeTree(Node root, Node child) {
        if (root.x > child.x) {
            if (root.left == null) {
                root.left = child;
            } else {
                makeTree(root.left, child);
            }
        } else {
            if (root.x < child.x) {
                if (root.right == null) {
                    root.right = child;
                } else {
                    makeTree(root.right, child);
                }
            }
        }
    }
    
    static class Node {
        Node left;
        Node right;
        Node parent;
        int id;
        int x;
        int y;
        
        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
        
        public void makeParent(Node parent) {
            this.parent = parent;
        }
        public void makeLeft(Node left) {
            this.left = left;
        }
        public void makeRight(Node right) {
            this.right = right;
        }
    }
}

// 모든 노드는 서로 다른 x 값
// y가 같으면 같은 레벨
// 