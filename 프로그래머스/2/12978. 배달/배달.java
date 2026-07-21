import java.util.*;

class Solution {
    /*
    26-7-21 14:24 ~ 
    
    1번 마을에서 K 시간 안에 배달할 수 있는 마을의 개수를 구하자
    
    ---
    
    다익스트라로 1번에서 각 마을로 가는 최단 거리를 구하면 된다
    
    */
    private int[] dist;
    private int N;
    private List<List<Node>> graph = new ArrayList<>();
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        init(N, road);
        dijkstra(1);
        answer = getPossibleVillage(K);

        return answer;
    }
    
    private void init(int N, int[][] road){
        this.N = N;
        
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] r : road){
            graph.get(r[0]).add(new Node(r[1], r[2]));
            graph.get(r[1]).add(new Node(r[0], r[2]));
        }
        
        // System.out.println(graph.get(1));
    }
    
    private void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dist[cur.to] < cur.dist){
                continue;
            }
            
            dist[cur.to] = cur.dist;
            
            for(Node next : graph.get(cur.to)){
                int nextDist = next.dist + cur.dist;
                
                if(dist[next.to] > nextDist){
                    pq.offer(new Node(next.to, nextDist));
                }
            }
        }
    }
    
    private int getPossibleVillage(int maxTime){
        int village = 0;
        for(int i = 1; i <= N; i++){
            if(dist[i] <= maxTime){
                village++;
            }
        }
        return village;
    }
    
    static class Node{
        int to;
        int dist;
        
        public Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
        
        public String toString(){
            return "(" + to + ", " + dist + ")";
        }
    }
}