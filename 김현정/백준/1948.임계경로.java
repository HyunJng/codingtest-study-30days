package Main;
import java.util.*;
import java.io.*;
/**
 * 위상정렬과 BFS가 다른 점
 * - 다음 경로가 나올 때마다 Queue에 넣어도 해결은 되지만
 * 위상을 체크하면서 넣으면 그 노드의 value 최대가 될 때를 고려하여 한 번만 Queue에 넣어 탐색할 수 있어서
 * 시간이 대폭 깎이고, 메모리도 낭비되지 않는다.
 */
class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<Node>[] right_graph = new ArrayList[n + 1];
		List<Node>[] left_graph = new ArrayList[n + 1];

		for(int i = 0; i <= n; i++) {
			right_graph[i] = new ArrayList<>();
			left_graph[i] = new ArrayList<>();
		}
		
		int[] indegree = new int[n + 1];
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			right_graph[a].add(new Node(b, c));
			left_graph[b].add(new Node(a, c));
			indegree[b]++;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		
		Queue<Integer> q = new LinkedList<>();
		int[] maxValue = new int[n + 1];

		q.add(start);
		while(!q.isEmpty()) {
			int target = q.poll();		
			for(Node x : right_graph[target]) {
				maxValue[x.num] = Math.max(maxValue[x.num], maxValue[target] + x.value);
				if(--indegree[x.num] == 0)
					q.add(x.num);
			}
		}

		int cnt = 0;
		boolean visited[] = new boolean[n + 1];
		
		q.add(end);
		while(!q.isEmpty()) {
			int target = q.poll();
			for(Node x : left_graph[target]) {
				if(maxValue[x.num] + x.value == maxValue[target]) {
					cnt++;
					if(!visited[x.num]) {
						visited[x.num] = true;
						q.add(x.num);
					}
				}
			}
		}

		System.out.println(maxValue[end] );
		System.out.println(cnt);
	}
}

class Node {
	int num;
	int value;
	
	Node(int num, int value){
		this.num = num;
		this.value = value;
	}
}