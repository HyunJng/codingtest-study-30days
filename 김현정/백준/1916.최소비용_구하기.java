package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Node>[] graph = new List[n + 1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
			graph[temp[0]].add(new Node(temp[1], temp[2]));
		}
		
		int[] result = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		
		int[] start_end = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		Arrays.fill(result, Integer.MAX_VALUE);
		result[start_end[0]] = 0;

		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start_end[0], 0));

		while(!q.isEmpty()) {
			Node t = q.poll();
			if(!visited[t.num]) {
				visited[t.num] = true; // 방문체크를 여기서 해야함
				for(Node next: graph[t.num]) {
					if(result[t.num] + next.value < result[next.num]) { // 방문 체크를 여기서 하면 오버헤드가 커짐 
						result[next.num] = result[t.num] + next.value;
						q.add(new Node(next.num, result[next.num]));
					}
				}
			}
		}
		
		bw.write(result[start_end[1]] + "");
		bw.close();
	}
}

class Node implements Comparable<Node> {
	int num;
	int value;
	
	Node(int num, int value){
		this.num = num;
		this.value = value;
	}
	
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}

