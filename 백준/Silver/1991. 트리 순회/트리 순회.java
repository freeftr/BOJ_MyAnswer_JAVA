import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static HashMap<String, Node> nodes = new HashMap<>();
    static class Node{
        String name;
        Node parent;
        Node left;
        Node right;

        Node (String name) {
            this.name = name;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            String p = s[0];
            String l = s[1];
            String r = s[2];

            Node P = nodes.get(p) == null ? new Node(p) : nodes.get(p);
            nodes.put(p, P);
            Node L;
            Node R;

            if (!l.equals(".")) {
                L = nodes.get(l) == null ? new Node(l) : nodes.get(l);
                nodes.put(l, L);

                P.left = L;
                L.parent = P;
            }

            if (!r.equals(".")) {
                R = nodes.get(r) == null ? new Node(r) : nodes.get(r);
                nodes.put(r, R);

                P.right = R;
                R.parent = P;
            }
        }

        Node root = nodes.get("A");
        preOrder(root);
        sb.append("\n");
        midOrder(root);
        sb.append("\n");
        postOrder(root);

        System.out.println(sb);
    }

    static void preOrder(Node v) {
        sb.append(v.name);
        if (v.left != null) {
            preOrder(v.left);
        }

        if (v.right != null) {
            preOrder(v.right);
        }
    }

    static void midOrder(Node v) {
        if (v.left != null) {
            midOrder(v.left);
        }

        sb.append(v.name);

        if (v.right != null) {
            midOrder(v.right);
        }
    }

    static void postOrder(Node v) {
        if (v.left != null) {
            postOrder(v.left);
        }

        if (v.right != null) {
            postOrder(v.right);
        }

        sb.append(v.name);
    }
}
