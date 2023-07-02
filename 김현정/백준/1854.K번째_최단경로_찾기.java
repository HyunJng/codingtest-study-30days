package Main;
import java.util.*;
import java.io.*;
import java.util.stream.*;

/**
 * pq가 채워지지고 난 후에 더이상 다익스트라의 전체 pq에 값을 push하지 않아도 되는 이유
 * - 현재 노드보다 더 큰 값을 갖더라도 그 이후의 경로에서는 작은 값을 가진다면 
 * 다른 노드의 K번째 값은 바뀔 수도 있찌 않나? 라고 생각했는데
 * 이미 해당 노드에서 K째 탐색했다면 거기서 나아갈 수 있는 노드 또한 K번 이상 탐색됐을 거라서
 * 그런 경우가 존재할 수 없다. 
 */
class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer>[] result = IntStream.rangeClosed(0, n)
				.mapToObj(i -> new PriorityQueue<Integer>((a, b) -> b - a))
				.toArray(PriorityQueue[]::new);
		
		List<Node>[] graph = IntStream.rangeClosed(0, n)
				.mapToObj(ArrayList<Node>::new)
				.toArray(List[]::new);
		
		for(int i = 0; i < m; i ++) {
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, c));
		}
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(1, 0));
		result[1].add(0);
		
		while(!q.isEmpty()) {
			Node t = q.poll();
			for(Node next : graph[t.num]) {
				if(result[next.num].size() < k) { // 저장경로가 K개 안될때는 그냥 추가
					q.add(new Node(next.num, t.value + next.value));
					result[next.num].add(t.value + next.value);
				} else if(result[next.num].peek() > t.value + next.value){ // 저장겨올가 K이고 가장 큰 값보다 작을때 추가
					result[next.num].poll();
					result[next.num].add(t.value + next.value);
					q.add(new Node(next.num, t.value + next.value));
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			if(result[i].size() < k) System.out.println(-1);  
			else System.out.println(result[i].peek());
		}
	}
}

class Node implements Comparable<Node>{
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