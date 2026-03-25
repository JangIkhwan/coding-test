import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/12789

    25/1/28 11:10 ~
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N;
        Queue<Integer> qu = new LinkedList<>();
        Stack<Integer> st = new Stack<>();
        boolean failed = false;
        N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            qu.add(Integer.parseInt(st1.nextToken()));
        }

        int i = 1;
        while(i <= N){
            if(!qu.isEmpty() && qu.peek() == i){
                qu.poll();
                i++;
            }
            else if(!st.isEmpty() && st.peek() == i){
                st.pop();
                i++;
            }
            else{
                if(!qu.isEmpty()){
                    int front = qu.poll();
                    if(!st.isEmpty() && front > st.peek()) {
                        failed = true;
                        break;
                    }
                    st.push(front);
                }
                else{
                    failed = true;
                    break;
                }
            }
        }

        if(failed) {
            System.out.println("Sad");
        }
        else{
            System.out.println("Nice");
        }
    }
}
