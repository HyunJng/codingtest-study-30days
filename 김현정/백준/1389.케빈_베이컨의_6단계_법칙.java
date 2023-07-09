package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] rel = new int [n + 1][n + 1];
		
		for(int i = 0; i < m; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			rel[a][b] = rel[b][a] = 1;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i == j || rel[i][k] == 0 || rel[k][j] == 0) continue;
					if(rel[i][j] != 0) 
						rel[i][j] = Math.min(rel[i][j], rel[i][k] + rel[k][j]);
					else rel[i][j] = rel[i][k] + rel[k][j];
				}
			}
		}
		
		int result = 0, min = Integer.MAX_VALUE;
		for(int i = 1; i <= n; i++) {
			int temp = Arrays.stream(rel[i]).sum();
			if(min > temp) {
				result = i;
				min = temp;
			}
		}
		
		System.out.println(result);
	}
}
