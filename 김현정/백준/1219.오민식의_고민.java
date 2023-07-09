package Main;
import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n, m, scity, ecity;
		Edge[] edges;
		long distance[], cityMoney[];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		scity = Integer.parseInt(st.nextToken());
		ecity = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edges = new Edge[m];
		distance = new long[n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(start, end, price);
		}
		
		cityMoney = Arrays.stream(br.readLine().split(" "))
		.mapToLong(Long::parseLong)
		.toArray();
		
		// 벨만-포드 변형
		Arrays.fill(distance, Long.MIN_VALUE);
		distance[scity] = cityMoney[scity];
		
		for(int i = 0; i <= n + 100; i++) {
			for(int j = 0; j < m; j++) {
				int start = edges[j].start;
				int end = edges[j].end;
				int price = edges[j].price;
				
				if(distance[start] == Long.MIN_VALUE) continue;
				else if(distance[start] == Long.MAX_VALUE) {
					distance[end] = Long.MAX_VALUE;
				} else if(distance[end] < distance[start] + cityMoney[end] - price) {
					distance[end] = distance[start] + cityMoney[end] - price;
					if(i >= n - 1) distance[end] = Long.MAX_VALUE; // n - 1 이후에 변경됐다면 사이클에 포함된거라서
				}
			}
		}
		
		if(distance[ecity] == Long.MIN_VALUE) bw.write("gg");
		else if(distance[ecity] == Long.MAX_VALUE) bw.write("Gee");
		else bw.write(distance[ecity] + "");
		bw.close();
	}
}

class Edge {
	int start, end, price;
	
	public Edge(int start, int end, int price) {
		this.start = start;
		this.end = end;
		this.price = price;
	}
}