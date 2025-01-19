import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static class TrieNode {
        Map<String, TrieNode> childNode = new HashMap<>();

        TrieNode() {

        }

        public void insert(String strs) {
            TrieNode trieNode = this;
            String[] str = strs.split(",");
            for (String s : str) {
                trieNode.childNode.putIfAbsent(s, new TrieNode());
                trieNode = trieNode.childNode.get(s);
            }
        }
        
        public void print(TrieNode cur, int depth) {
            TrieNode trieNode = cur;
            if(trieNode.childNode != null) {
                List<String> list = new ArrayList<>(trieNode.childNode.keySet());
                Collections.sort(list);
                for(String s : list) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print("--");
                    }
                    System.out.println(s);
                    print(trieNode.childNode.get(s), depth + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        TrieNode root = new TrieNode();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < k+1; j++) {
                sb.append(input[j] + ",");
            }

            root.insert(sb.toString());
        }
        root.print(root, 0);
    }

}
