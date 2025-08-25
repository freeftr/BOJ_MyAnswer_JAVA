import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node> nodes = new ArrayList<>();
    static int rowNum = 1, maxLevel = 0;
    static HashMap<Integer, Integer> maxMap = new HashMap<>();
    static HashMap<Integer, Integer> minMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parentIdx = Integer.parseInt(st.nextToken()) - 1;
            int leftIdx = Integer.parseInt(st.nextToken()) - 1;
            int rightIdx = Integer.parseInt(st.nextToken()) - 1;

            Node parent = nodes.get(parentIdx);

            if (leftIdx != -2) {
                Node left = nodes.get(leftIdx);
                parent.makeLeft(left);
                left.makeParent(parent);
            }

            if (rightIdx != -2) {
                Node right = nodes.get(rightIdx);
                parent.makeRight(right);
                right.makeParent(parent);
            }
        }

        Node root = null;
        for (Node n : nodes) {
            if (n.parent == null) {
                root = n;
                break;
            }
        }

        dfs(root, 1);

        int ansLevel = 1;
        int ansWidth = 0;

        for (int i = 1; i <= maxLevel; i++) {
            int min = minMap.get(i);
            int max = maxMap.get(i);
            int width = max - min + 1;

            if (width > ansWidth || (width == ansWidth && i < ansLevel)) {
                ansLevel = i;
                ansWidth = width;
            }
        }

        System.out.println(ansLevel + " " + ansWidth);
    }

    static void dfs(Node node, int level) {
        Node left = node.left;
        Node right = node.right;

        node.level = level;
        maxLevel = Math.max(level, maxLevel);

        if (left != null) {
            dfs(left, level + 1);
        }

        if (node.row == 0) {
            node.row = rowNum;

            if (maxMap.get(level) == null) {
                maxMap.put(level, rowNum);
            } else {
                maxMap.put(level, Math.max(maxMap.get(level), rowNum));
            }

            if (minMap.get(level) == null) {
                minMap.put(level, rowNum);
            } else {
                minMap.put(level, Math.min(minMap.get(level), rowNum));
            }

            rowNum++;
        }

        if (right != null) {
            dfs(right, level + 1);
        }
    }

    static class Node {
        int idx;
        int level;
        int row;
        Node parent;
        Node left;
        Node right;

        public Node (int idx) {
            this.idx = idx;
            this.row = 0;
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

        public void setLevel(int level) {
            this.level = level;
        }

        public void setRow(int row) {
            this.row = row;
        }
    }
}
