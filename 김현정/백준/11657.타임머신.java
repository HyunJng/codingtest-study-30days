package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] result = new long[n + 1];
		Edge[] edges = new Edge[m];
		
		Arrays.fill(result, Integer.MAX_VALUE);
		for(int i = 0; i < m; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());		
			int c = Integer.parseInt(st.nextToken());		
				
			edges[i] = new Edge(a, b, c);
		}
		
		result[1] = 0;
		for(int i = 0; i < n - 1 ; i++) { // 각 노드가 최소 가중치를 갖기 위한 취대 에지 수는 N-1 이라서
			for(int j = 0; j < m; j++) { // 각 노드 n - 1번씩 모두 탐색
				Edge t = edges[j];
				if(result[t.start] != Integer.MAX_VALUE
						&& result[t.end] > result[t.start] + t.value) {
					result[t.end] = result[t.start] + t.value;
				}
			}
		}
		
		boolean mCycle = false; // 사이클 여부 탐색
		for(int i = 0; i < m; i ++) { // 모든 에지를 한번 탐색하고 바뀐 것이 있다면 음수사이클이 있는 것
			Edge t = edges[i]; 
			if(result[t.start] != Integer.MAX_VALUE
					&& result[t.end] > result[t.start] + t.value) {
				mCycle = true; break;
			}
		}
		
		if(mCycle) {
			System.out.println(-1);
		} else {
			for(int i = 2; i <= n; i++) {
				if(result[i] == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else System.out.println(result[i]);
			}
		}
	}
}
class Edge {
	int start;
	int end;
	int value;
	
	Edge(int start, int end, int value){
		this.start = start;
		this.end = end;
		this.value = value;
	}
}