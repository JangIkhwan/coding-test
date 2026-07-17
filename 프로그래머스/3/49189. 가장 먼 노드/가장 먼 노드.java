import java.util.*;

class Solution {
    /*
    26/7/17 21:16 ~ 
    
    1번 노드에서 가장 먼 노드의 갯수를 구하자
    
    ---
    
    다익스트라 or BFS 모두 가능해보인다
    
    bfs를 이용해보자
    
    */
    
    private List<List<Integer>> graph = new ArrayList<>();
    private int N;
    private int[] dists;
    private boolean[] visited;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        init(n, edge);
        bfs(1);
        answer = getFarthestNodeNum();
    
        return answer;
    }
    
    private void init(int n, int[][] edge){
        N = n;
        
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        dists = new int[N + 1];
        
        visited = new boolean[N + 1];
    }
    
    private void bfs(int start){
        Queue<int []> qu = new ArrayDeque<>();
        
        qu.offer(new int[] { start, 0 });
        visited[start] = true;
        
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            
            for(int next : graph.get(cur[0])){
                if(visited[next]){
                    continue;
                }
                
                qu.offer(new int[] { next, cur[1] + 1 });
                visited[next] = true;
                dists[next] = cur[1] + 1;
            }
        }
    }
    
    private int getFarthestNodeNum(){
        int maxDist = 0;
        for(int d : dists){
            maxDist = Math.max(maxDist, d);
        }
        
        int nodeNum = 0;
        for(int d : dists){
            if(d == maxDist){
                nodeNum++;
            }
        }
        
        return nodeNum;
    }
}