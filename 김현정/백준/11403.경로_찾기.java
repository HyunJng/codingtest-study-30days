package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		
		for(int i =0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 0; k < n; k++) { // 중간노드
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(graph[i][j] == 1) continue;
					if(graph[i][k] == 1 && graph[k][j] == 1)
						graph[i][j] = 1;
				}
			}
		}
		
		for(int i =0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				bw.write(graph[i][j] + " ");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();

	}
}
