package Main;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		long[][] result = new long[n + 1][n + 1];

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) result[i][j] = 0;
				else result[i][j] = Long.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			result[start][end] = Math.min(result[start][end], price);
		}
		
		for(int k = 1; k <= n; k++) { // 중간노드
			for(int i = 1; i <= n; i++) { // 시작노드
				for(int j = 1; j <= n; j++) { // 끝 노드
					if(result[i][k] == Long.MAX_VALUE || result[k][j] == Long.MAX_VALUE) continue;
					result[i][j] = Math.min(result[i][j], result[i][k] + result[k][j]);
				}
			}
		}
		
		for(int i = 1; i <= n ; i++) {
			for(int j = 1; j <= n; j++) {
				if(result[i][j] == Long.MAX_VALUE) bw.write("0 ");
				else bw.write(result[i][j] + " ");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}
}
