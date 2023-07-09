package Main;
import java.util.*;
import java.io.*;

class Main {
	static int[] unions;
	static Queue<Edge> edges;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		unions = new int[v + 1];
		edges = new PriorityQueue<>();
		
		for(int i =0; i <= v; i++) {
			unions[i] = i;
		}
		
		for(int i= 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());		
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(a, b, c));
		}

		int cnt = 0; // 횟수 세기
		int result = 0; // 결과
		while(cnt < v - 1) {
			Edge t = edges.poll();
			if(find(t.start) == find(t.end)) continue;
			
			union(t.start, t.end);
			result += t.value;
			cnt++; 
		}
		
		System.out.println(result);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) unions[a] = b;
	}
	
	public static int find(int v) {
		if(v == unions[v]) return v;
		return unions[v] = find(unions[v]);
	}
}
 
class Edge implements Comparable<Edge>{
	int start, end, value;
	
	Edge(int start, int end, int value){
		this.start = start;
		this.end = end;
		this.value = value;
	}
	
	public int compareTo(Edge o) {
		return this.value - o.value;
	}
}