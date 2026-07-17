import java.util.*;

class Solution {
    /*
    26/7/17 20:46 ~ 
    
    두 네트워크의 크기를 최대한 비슷하게 만들 때 크기의 차이를 구하자
    
    ---
    
    처음에는 트리 형태다
    
    전선을 하나를 끊으면 두 네트워크로 분리된다
    
    n <= 100이다
    
    ---
    
    브루트포스가 가능해보인다
    
    100개를 다 끊어보고 두 네트워크의 크기의 차이 중 가장 작은 값을 구하면 된다
    
    
    */
    private int N;
    private List<List<Integer>> graph = new ArrayList<>();
    private int[] cuttedWire;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        init(n, wires);
        
        for(int[] wire : wires){
            cut(wire);
            int diff = getDiff();
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    private void init(int n, int[][] wires){
        N = n;
        
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] wire : wires){
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
    }
    
    private void cut(int[] wire){
        cuttedWire = wire;
    }
    
    private int getDiff(){
        List<Integer> sizes = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        
        for(int v = 1; v <= N; v++){
            if(!visited[v]){
                int size = bfs(v, visited);
                sizes.add(size);
            }
        }
        return Math.abs(sizes.get(0) - sizes.get(1));
    }
    
    private int bfs(int start, boolean[] visited){
        int size = 1;
        Queue<Integer> qu = new ArrayDeque<>();
        
        qu.offer(start);
        visited[start] = true;
        
        while(!qu.isEmpty()){
            int cur = qu.poll();
            
            for(int next : graph.get(cur)){
                if(isCutted(next, cur)){
                    continue;
                }
                
                if(visited[next]){
                    continue;
                }
                
                qu.offer(next);
                visited[next] = true;
                size++;
            }
        }
        
        return size;
    }
    
    private boolean isCutted(int u, int v){
        return cuttedWire[0] == u && cuttedWire[1] == v|| cuttedWire[1] == u && cuttedWire[0] == v;
    }
}