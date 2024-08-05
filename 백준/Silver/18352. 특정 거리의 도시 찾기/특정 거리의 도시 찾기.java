import java.io.*;
import java.util.*;
public class Main {
	static int N, M ,K, X;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static ArrayList<Integer> ans = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); 
		X = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			graph.get(a).add(b);
		}
		bfs(X);
		if(ans.size()==0) {
			System.out.println(-1);
			System.exit(0);
		}
		StringBuilder sb = new StringBuilder();
		Collections.sort(ans);
		for(int i : ans) {
			sb.append(i+"\n");
		}
		bw.write(sb.toString());
		bw.close();
	}
	
	public static void bfs(int start) {
		Queue<int[]> q = new LinkedList<>();
		visited[start] = true;
		q.offer(new int[] {start, 0});
		while(!q.isEmpty()) {
			for(int i = 0; i < q.size(); i++) {
				int[] arr = q.poll();
				int v = arr[0];
				int d = arr[1];
				if(d==K) {
					ans.add(v);
					continue;
				}
				for(int nv : graph.get(v)) {
					if(!visited[nv]) {
						visited[nv]=true;
						q.add(new int[] {nv,d+1});
					}
				}
			}
		}
	}
}
