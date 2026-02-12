import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    static class Node{
        int key;
        Node parent;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }

        public void setParent(Node node) {
            this.parent = node;
        }

        public void setLeft(Node node) {
            this.left = node;
        }

        public void setRight(Node node) {
            this.right = node;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> preorder = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) break;
            preorder.add(Integer.parseInt(line));
        }


        Node root = new Node(preorder.get(0));

        for (int i = 1; i < preorder.size(); i++) {
            int key = preorder.get(i);

            Node cur = root;

            while (true) {
                if (cur.key > key) {
                    if (cur.left == null) {
                        Node l = new Node(key);
                        cur.left = l;
                        l.setParent(cur);
                        break;
                    } else {
                        cur = cur.left;
                    }
                } else {
                    if (cur.right == null) {
                        Node r = new Node(key);
                        cur.right = r;
                        r.setParent(cur);
                        break;
                    } else {
                        cur = cur.right;
                    }
                }
            }
        }

        postOrder(root);
    }

    static void postOrder(Node v) {
        if (v.left != null) {
            postOrder(v.left);
        }

        if (v.right != null) {
            postOrder(v.right);
        }

        System.out.println(v.key);
    }
}
