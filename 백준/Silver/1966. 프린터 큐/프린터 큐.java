import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T, N, M;
        Queue<Pair> qu;
        int[] priorityCount;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            qu = new LinkedList<>();
            priorityCount = new int[10];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int priority = Integer.parseInt(st.nextToken());
                qu.add(new Pair(i, priority));
                priorityCount[priority]++;
            }
            int i = 1;
            while (true) {
                Pair front = qu.poll();
                if(upperPriorityExists(front, priorityCount)){
                    qu.add(front);
                    continue;
                }
                if(front.order == M){
                    System.out.println(i);
                    break;
                }
                priorityCount[front.priority]--;
                i++;
            }
        }
    }

    private static boolean upperPriorityExists(Pair front, int[] priorityCount) {
        for(int j = front.priority + 1; j <= 9; j++){
            if(priorityCount[j] > 0) {
                return true;
            }
        }
        return false;
    }

    static class Pair{
        int order;
        int priority;

        public Pair(int order, int priority){
            this.order = order;
            this.priority = priority;
        }
    }
}
