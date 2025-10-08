import java.util.*;

class Solution {
    
    static int[] parent = new int[50 * 50];
    static String[] values = new String[50 * 50];
    
    public String[] solution(String[] commands) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < 50 * 50; i++) {
            parent[i] = i;
            values[i] = "";
        }
        
        for (String cmd : commands) {
            String[] sp = cmd.split(" ");
            String type = sp[0];
            
            if (type.equals("UPDATE") && sp.length == 4) {
                int r = Integer.parseInt(sp[1]);
                int c = Integer.parseInt(sp[2]);
                String v = sp[3];
                
                int idx = getIdx(r, c);
                int root = find(idx);

                String prev = values[root];
                if (!prev.isEmpty()) {
                    removeIndexFromMap(map, prev, root);
                }
                values[root] = v;
                addIndexToMap(map, v, root);
            }
            
            if (type.equals("UPDATE") && sp.length == 3) {
                String v1 = sp[1];
                String v2 = sp[2];

                if (v1.equals(v2)) continue;

                ArrayList<Integer> members = map.get(v1);
                if (members == null || members.isEmpty()) continue;

                ArrayList<Integer> snapshot = new ArrayList<>(members);
                for (int idx : snapshot) {
                    int root = find(idx);
                    if (!values[root].equals(v1)) continue;

                    removeIndexFromMap(map, v1, root);
                    values[root] = v2;
                    addIndexToMap(map, v2, root);
                }

                members = map.get(v1);
                if (members == null || members.isEmpty()) {
                    map.remove(v1);
                }
            }
            
            if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(sp[1]);
                int c1 = Integer.parseInt(sp[2]);
                int r2 = Integer.parseInt(sp[3]);
                int c2 = Integer.parseInt(sp[4]);

                int idx1 = getIdx(r1, c1);
                int idx2 = getIdx(r2, c2);

                int a = find(idx1);
                int b = find(idx2);
                if (a == b) continue;

                String keep = !values[a].isEmpty() ? values[a] : values[b];

                if (!values[b].isEmpty()) {
                    removeIndexFromMap(map, values[b], b);
                }
                if (!values[a].isEmpty() && !values[a].equals(keep)) {
                    removeIndexFromMap(map, values[a], a);
                }

                union(a, b);

                values[a] = keep;
                values[b] = "";

                if (!keep.isEmpty()) {
                    addIndexToMap(map, keep, a);
                }
            }
            
            if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(sp[1]);
                int c = Integer.parseInt(sp[2]);
                int cell = getIdx(r, c);
                int root = find(cell);
                String v = values[root];

                ArrayList<Integer> children = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        int idx = getIdx(i, j);
                        if (find(idx) == root) children.add(idx);
                    }
                }

                if (!v.isEmpty()) removeIndexFromMap(map, v, root);

                for (int child : children) {
                    parent[child] = child;
                    values[child] = "";
                }

                values[cell] = v;
                if (!v.isEmpty()) addIndexToMap(map, v, cell);
            }
            
            if (type.equals("PRINT")) {
                int r = Integer.parseInt(sp[1]);
                int c = Integer.parseInt(sp[2]);
                int idx = getIdx(r, c);
                int root = find(idx);
                String v = values[root];
                result.add(v == null || v.isEmpty() ? "EMPTY" : v);
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    static int getIdx(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
    
    static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[b] = a;
    }

    static void addIndexToMap(HashMap<String, ArrayList<Integer>> map, String key, int idx) {
        ArrayList<Integer> list = map.computeIfAbsent(key, k -> new ArrayList<>());
        if (!list.contains(idx)) list.add(idx);
    }

    static void removeIndexFromMap(HashMap<String, ArrayList<Integer>> map, String key, int idx) {
        ArrayList<Integer> list = map.get(key);
        if (list == null) return;
        list.remove(Integer.valueOf(idx));
        if (list.isEmpty()) map.remove(key);
    }
}
