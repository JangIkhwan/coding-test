import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int T, N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 케이스 별로 입력 처리
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            // 그래프를 형성
            N = Integer.parseInt(br.readLine());
            int[] graph = new int[N + 1];
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int i = 1; i <= N; i++){
                graph[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프에서 사이클에 있는 노드의 개수를 센다
            boolean[] visited = new boolean[N + 1];
            List<Integer> cycleNodes = new ArrayList<>(N);
            for(int v = 1; v <= N; v++){
                if(!visited[v]) {
//                    System.out.println("visit node " + v);
                    LinkedList<Integer> visitNodes = new LinkedList();
                    dfs(visited, v, graph, visitNodes, cycleNodes);
                }

//                System.out.println("cycleNodes = ");
//                for(int node : cycleNodes){
//                    System.out.print(node + " ");
//                }
//                System.out.println();
            }

            // 답을 구한다
            bw.write(String.valueOf(N - cycleNodes.size()) + "\n");
        }
        bw.flush();
    }

    private static void dfs(boolean[] visited, int current, int[] graph, LinkedList<Integer> visitNodes, List<Integer> cycleNodes){
        if(visited[current]){
            while (!visitNodes.isEmpty()) {
                if (visitNodes.peek() != graph[visitNodes.peekLast()]) {
                    visitNodes.poll();
                }
                else{
                    break;
                }
            }

            while (!visitNodes.isEmpty()) {
                int v = visitNodes.poll();
                cycleNodes.add(v);
            }

            return;
        }

        visited[current] = true;
        visitNodes.add(current);

        dfs(visited, graph[current], graph, visitNodes, cycleNodes);
    }
}