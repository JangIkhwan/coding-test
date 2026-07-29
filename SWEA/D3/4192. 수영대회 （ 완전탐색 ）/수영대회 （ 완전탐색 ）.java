import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	int N;
	int[][] map;
	int startR, startC;
	int endR, endC;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			int answer = 0;
			Solution s = new Solution();
			s.init();
			answer = s.solve();
			System.out.println("#" + test + " " + answer);
		}
	}
	
	void init() throws IOException {
		String line = br.readLine();
		N = Integer.parseInt(line);
		
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		
		line = br.readLine();
		st = new StringTokenizer(line);
		
		endR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
	}
	
	int solve() {
		final int[] dr = { -1, 0, 1, 0 };
		final int[] dc = { 0, 1, 0, -1 };
		Queue<int []> qu = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		qu.offer(new int[] { startR, startC, 0 });
		visited[startR][startC] = true;
		
		while(!qu.isEmpty()) {
			int[] cur = qu.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1) {
					continue;
				}
				if(visited[nr][nc]) {
					continue;
				}
				if(map[nr][nc] == 1) {
					continue;
				}
				
				if(nr == endR && nc == endC) {
					return cur[2] + 1;
				}
				
				qu.offer(new int[] { nr, nc, cur[2] + 1 });
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
}