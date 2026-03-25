import java.io.*;
import java.util.*;

public class Main {
   
    public static void main(String[] args) throws IOException{
        solution2();
    }

    private static void solution2() throws IOException {
        int N, M;
        List<Integer>[] graph;
        int[] inDegrees;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        inDegrees = new int[N + 1];
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            graph[small].add(big);
            inDegrees[big]++;
        }

        Queue<Integer> qu = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            if(inDegrees[i] == 0){
                qu.offer(i);
            }
        }

        // 위상 정렬
        StringBuilder sb = new StringBuilder();
        while(!qu.isEmpty()){
            int poll = qu.poll();
            sb.append(poll + " ");
            for(int next : graph[poll]){
                inDegrees[next]--;
                if(inDegrees[next] == 0){
                    qu.offer(next);
                }
            }
        }

        // 출력
        System.out.println(sb.toString());
    }
}