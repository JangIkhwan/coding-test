import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N;
        int[] towers;
        N = Integer.parseInt(br.readLine());
        towers = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            towers[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("0 ");
        stack.push(1);
        for(int i = 2; i <= N; i++){
            while(!stack.isEmpty()){
                if(towers[stack.peek()] > towers[i]){
                    sb.append(Integer.toString(stack.peek())+ " ");
                    stack.push(i);
                    break;
                }
                else{
                    stack.pop();
                    if(stack.isEmpty()){
                        sb.append("0 ");
                        stack.push(i);
                        break;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}