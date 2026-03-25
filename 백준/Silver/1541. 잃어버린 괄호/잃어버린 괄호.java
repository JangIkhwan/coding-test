import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        int headOfNum = 0;
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) == '-' || exp.charAt(i) == '+'){
                nums.add(Integer.parseInt(exp.substring(headOfNum, i)));
                ops.add(exp.charAt(i));
                headOfNum = i + 1;
            }
        }
        nums.add(Integer.parseInt(exp.substring(headOfNum, exp.length())));

//        for(int i = 0; i < nums.size(); i++){
//            System.out.println(nums.get(i) + " ");
//        }

        int[][] dp = new int[nums.size()][nums.size()];
        final int INF = 999999;
        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j < nums.size(); j++){
                dp[i][j] = INF;
            }
        }
        for(int i = 0; i < nums.size(); i++){
            dp[i][i] = nums.get(i);
        }
        for(int length = 2; length <= nums.size(); length++){
            for(int i = 0; i + length <= nums.size(); i++){
                int j = i + (length - 1);
                for(int k = i; k + 1 <= j; k++){
                    if(ops.get(i) == '-'){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] - dp[k + 1][j]);
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        System.out.println(dp[0][nums.size() - 1]);
    }
}