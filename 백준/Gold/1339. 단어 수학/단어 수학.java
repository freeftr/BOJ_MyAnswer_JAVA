import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();

    static String[] words;
    static int N;
    static int max;
    static int[] arr;
    static Set<Character> letter;
    static boolean[] visited = new boolean[10];
    static ArrayList<Character> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        words = new String[N+1];
        letter  = new HashSet<>();
        max = 0;

        for(int i = 1; i <= N; i++) {
            words[i] = br.readLine();
            for(int j = 0; j < words[i].length(); j++) {
                letter.add(words[i].charAt(j));
            }
        }
        list = new ArrayList<>();

        for(char l : letter) {
            list.add(l);
        }

        arr = new int[letter.size()];
        dfs(0,arr);
        System.out.println(max);
    }

    public static void dfs(int depth, int[] arr) {
        if(depth==letter.size()) {
            int sum = 0;
            for(int i = 1; i <= N; i++) {
                int ten = 1;
                for(int j = words[i].length()-1; j>=0; j--) {
                    for(int a = 0; a < list.size(); a++) {
                        if(words[i].charAt(j)==list.get(a)) {
                            sum+=arr[a]*ten;
                            ten*=10;
                        }
                    }
                }
            }
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i <= 9; i++) {
            if(visited[i])continue;
            visited[i] = true;
            arr[depth] = i;
            dfs(depth+1, arr);
            visited[i] = false;
        }
    }
}