import java.util.*;

class Solution {
    /*
    25/12/21 14:23 ~ 
    
    각 부대원이 목적지로 가는 최단경로의 길이를 구하자
    
    목적지를 시작점으로 다익스트라 적용하면 각 부대원의 최단경로를 알 수 있을 것임
    */
    private int[] dist;
    private List<Integer> graph[];
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // bfs 준비
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        // bfs 수행
        bfs(n, destination);
        
        // 정답 반환
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
    
    void bfs(int n, int start){
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> qu = new LinkedList<>();
        dist[start] = 0;
        visited[start] = true;
        qu.add(new int[] {start, 0});
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int next : graph[cur[0]]){
                if(visited[next]){
                    continue;
                }
                dist[next] = cur[1] + 1;
                visited[next] = true;
                qu.add(new int[] {next, cur[1] + 1});
            }
        }
    }
}