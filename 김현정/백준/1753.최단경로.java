package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] temp = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		int n = temp[0];
		int m = temp[1];
		int start = Integer.parseInt(br.readLine());
		List<Node>[] graph = new ArrayList[n + 1];
		
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			temp = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			graph[temp[0]].add(new Node(temp[1], temp[2]));
		}
		
		boolean[] check  = new boolean[n + 1];
		int[] values = new int[n + 1];
		
		Arrays.fill(values, Integer.MAX_VALUE);
		values[start] = 0;
		check[start] = true;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node t = pq.poll();
			
			for(Node next: graph[t.num]) {
				if(check[next.num]) continue;
				if(values[next.num] > values[t.num] + next.value) {
					values[next.num] = values[t.num] + next.value;
					pq.add(new Node(next.num, values[next.num]));
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			if(values[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(values[i]);
		}
	}
}

class Node implements Comparable<Node>{
	int num;
	int value;
	
	Node(int num, int value) {
		this.num = num;
		this.value = value;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}