import java.util.*;

class Solution {

    static final int W = 50;
    static final int SIZE = W * W + 1;
    static int[] parent = new int[SIZE];
    static String[] value = new String[SIZE];

    static int getIdx(int r, int c) {
        return (r - 1) * W + c;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;
        String keep = value[ra].isEmpty() ? value[rb] : value[ra];
        parent[rb] = ra;
        value[ra] = keep;
        value[rb] = "";
    }

    public String[] solution(String[] commands) {
        for (int i = 1; i < SIZE; i++) {
            parent[i] = i;
            value[i] = "";
        }

        List<String> result = new ArrayList<>();

        for (String cmd : commands) {
            String[] s = cmd.split(" ");
            String type = s[0];

            if (type.equals("UPDATE") && s.length == 4) {
                int r = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                String v = s[3];
                int root = find(getIdx(r, c));
                value[root] = v;
            } else if (type.equals("UPDATE") && s.length == 3) {
                String v1 = s[1];
                String v2 = s[2];
                for (int i = 1; i < SIZE; i++) {
                    if (parent[i] == i && value[i].equals(v1)) {
                        value[i] = v2;
                    }
                }
            } else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(s[1]);
                int c1 = Integer.parseInt(s[2]);
                int r2 = Integer.parseInt(s[3]);
                int c2 = Integer.parseInt(s[4]);
                int a = getIdx(r1, c1);
                int b = getIdx(r2, c2);
                if (find(a) != find(b)) union(a, b);
            } else if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                int x = getIdx(r, c);
                int root = find(x);
                String keep = value[root];
                List<Integer> members = new ArrayList<>();
                for (int i = 1; i <= W; i++) {
                    for (int j = 1; j <= W; j++) {
                        int idx = getIdx(i, j);
                        if (find(idx) == root) {
                            members.add(idx);
                        }
                    }
                }
                for (int m : members) {
                    parent[m] = m;
                    value[m] = "";
                }
                value[x] = keep;
            } else if (type.equals("PRINT")) {
                int r = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                String v = value[find(getIdx(r, c))];
                result.add(v.isEmpty() ? "EMPTY" : v);
            }
        }

        return result.toArray(new String[0]);
    }
}
