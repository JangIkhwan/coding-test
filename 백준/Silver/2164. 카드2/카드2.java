import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /*
    https://www.acmicpc.net/problem/2164

    25/1/27 22:25 ~
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N;
        Queue<Integer> qu = new LinkedList<>();
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++){
            qu.add(i);
        }
        while(qu.size() > 1){
            qu.poll();
            int front = qu.poll();
            qu.add(front);
        }
        System.out.println(qu.poll());
    }
}
