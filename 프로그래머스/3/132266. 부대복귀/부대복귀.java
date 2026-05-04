import java.util.*;

class Solution {
    /*
    26/5/4 11:30 ~ 
    
    부대원 순서대로 복귀할 수 있는 최단시간을 구하자
    
    dest에서 다익스트라를 이용하면 모든 정점 사이의 최단경로를 구할 수 있다
    그래프가 양방향이므로 그냥 써도 될 듯?
    */    
    private final int INF = 500001;
    private int[] dists;
    private List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dists = new int[n + 1];
        Arrays.fill(dists, INF);
        
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        dijkstra(destination);
                
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            if(dists[sources[i]] == INF){
                answer[i] = -1;
                continue;
            }
            answer[i] = dists[sources[i]];
        }
        
        return answer;
    }
    
    private void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        dists[start] = 0;
        for(int next : graph[start]){
            pq.offer(new Node(start, next, 1));
        }
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dists[cur.to] < cur.cost){
                continue;
            }
            
            dists[cur.to] = cur.cost;
            
            for(int next : graph[cur.to]){
                int nextDist = cur.cost + 1;
                if(nextDist < dists[next] ){
                    pq.offer(new Node(cur.to, next, nextDist));
                }
            }
        }
    }
    
    static class Node{
        int from;
        int to;
        int cost;
        
        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}