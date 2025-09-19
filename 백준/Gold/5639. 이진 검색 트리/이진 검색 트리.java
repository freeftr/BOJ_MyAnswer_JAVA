import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node root = new Node(n);
        Node r = root;

        String input;
        while ((input = br.readLine()) != null) {
            if (input.isEmpty()) break;
            int value = Integer.parseInt(input);
            Node node = new Node(value);

            Node cur = r;
            while (true) {
                if (value < cur.value) {
                    if (cur.left == null) {
                        cur.left = node;
                        node.parent = cur;
                        break;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = node;
                        node.parent = cur;
                        break;
                    }
                    cur = cur.right;
                }
            }
        }

        post(r);
    }

    static void post(Node node) {
        if (node == null) return;
        if (node.left != null) post(node.left);
        if (node.right != null) post(node.right);
        System.out.println(node.value);
    }

    static class Node {
        Node left;
        Node parent;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
        }
    }
}
