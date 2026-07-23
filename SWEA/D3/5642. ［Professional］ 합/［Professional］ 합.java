import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private int N;
	private int[] arr;
	
	public static void main(String args[]) throws Exception{
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
			int answer = 0;
			Solution s = new Solution();
			s.init();
			answer = s.solve();
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	private void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private int solve() {
		int[] dp = new int[N]; // dp[i] : i번째 원소가 마지막이면서 합이 최대인 구간의 합

		dp[0] = arr[0];
		for(int i = 1; i < N; i++) {
			dp[i] = Math.max(arr[i] + dp[i - 1], arr[i]);
		}
		
		int maxValue = -1001;
		for(int i = 0; i < N; i++) {
			maxValue = Math.max(maxValue, dp[i]);
		}
		
		return maxValue;
	}
}