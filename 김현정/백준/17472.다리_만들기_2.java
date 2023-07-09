package Main;
import java.util.*;
import java.io.*;

class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] arr, map;
	static int n, m, sumCnt;
	static int[]  unions;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		map = new int[n][m];
		unions = new int[7];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 지도 만들기
		sumCnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 1) {
					sumCnt++;
					findIsland(i, j);
					unions[sumCnt] = sumCnt;
				}
			}
		}
		
		Queue<Edge> edges = new PriorityQueue<>();
		// edges 탐색: 가로 탐색
		for(int i = 0; i < n; i++) {
			int start = 0,  cnt = 0;
			boolean flag = false;
			for(int j = 0; j < m; j++) {
				if(map[i][j] != 0) {
					if(start == 0) {
						start = map[i][j];
						flag = true;
					}
					else if(start != map[i][j] && cnt >= 2){
						edges.add(new Edge(start, map[i][j], cnt));
						start = map[i][j]; cnt = 0;
					} else if(start == map[i][j] || cnt < 2) {
						start = map[i][j];
						cnt = 0;
					}
				}
				else if(flag) cnt++; 
			}
		}
		
		// edges 탐색: 세로 탐색
		for(int j = 0; j < m; j++) {
			int start = 0,  cnt = 0;
			boolean flag = false;
			for(int i = 0; i < n; i++) {
				if(map[i][j] != 0) {
					if(start == 0) {
						start = map[i][j];
						flag = true;
					}
					else if(start != map[i][j] && cnt >= 2){
						edges.add(new Edge(start, map[i][j], cnt));
						start = map[i][j]; cnt = 0;
					} else if(start == map[i][j] || cnt < 2) {
						start = map[i][j];
						cnt = 0;
					}
				}
				else if(flag) cnt++; 
			}
		}
		
		// union-find 
		int result = 0, totalCnt = 0;
		while(totalCnt < sumCnt - 1 && !edges.isEmpty()) {
			Edge t = edges.poll();
			if(find(t.start) != find(t.end)) {
				union(t.start, t.end);
				result += t.value;
				totalCnt++;
			}
		}
		if(totalCnt == sumCnt - 1)
			System.out.println(result);
		else System.out.println(-1);
	}

	public static void findIsland(int row, int col) {
		if(row < 0 || col < 0 || row >= n || col >= m || arr[row][col] == 0) return;
		
		arr[row][col] = 0;
		map[row][col] = sumCnt;
		for(int i = 0; i < 4; i++) {
			findIsland(row + dy[i], col + dx[i]);
		}
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
