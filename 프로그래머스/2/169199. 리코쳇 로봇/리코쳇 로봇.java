import java.util.*;

class Solution {
    /*
    26/5/15 12:30 ~ 
    
    G까지 도달하기 위해 필요한 최소 이동 횟수를 구하자
    
    
    각 점에서 상하좌우로 끝까지 이동했을 때 만나는 지점들을 연결해서 그래프를 만들 수 있을까?
    만들 수 있다면 BFS로 G에 도달할 수 있는지 알 수 있음
    */
    private final int[] dr = { -1, 0, 1, 0 };
    private final int[] dc = { 0, 1, 0, -1 };
    private List<Integer>[] graph;
    private int answer = -1;
    private int start;
    
    public int solution(String[] board) {
        makeGraph(board);
        bfs(board);
        return answer;
    }
    
    private void makeGraph(String[] board){
        graph = new ArrayList[100 * 100 + 1];
        for(int i = 0; i < 100 * 100 + 1; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length(); c++){
                if(board[r].charAt(c) == 'D'){
                    continue;
                }
                
                if(board[r].charAt(c) == 'R'){
                    start = r * board[0].length() + c;
                }
                
                // System.out.println("r, c = " + r + ", " + c);
                
                for(int i = 0; i < 4; i++){
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if(nr < 0 || nr > board.length - 1 || nc < 0 || nc > board[0].length() -1 ){
                        continue;
                    }
                    
                    if(board[nr].charAt(nc) == 'D'){
                        continue;
                    }
                    
                    while(!isEdge(i, nr, nc, board) && board[nr].charAt(nc) != 'D'){
                        nr += dr[i];
                        nc += dc[i];
                    }  
                    
                    if(board[nr].charAt(nc) == 'D'){
                        nr -= dr[i];
                        nc -= dc[i];
                    }
                    
                    // System.out.println("nr, nc = " + nr + ", " + nc);
                    
                    int srcVertex = r * board[0].length() + c;
                    int dstVertex = nr * board[0].length() + nc;
                    graph[srcVertex].add(dstVertex);
                }
            }
        }
        
        // for(int i = 0; i < board.length * board[0].length(); i++){
        //     System.out.println("from " + i + " to = " + graph[i]);
        // }
    }
    
    private boolean isEdge(int direct, int r, int c, String[] board){
        if(direct == 0){
            return r <= 0;
        }
        if(direct == 1){
            return c >= board[0].length() - 1;
        }
        if(direct == 2){
            return r >= board.length - 1;  
        }
        return c <= 0;
    }
    
    private void bfs(String[] board){
        boolean[] visited = new boolean[100 * 100 + 1];
        Queue<int []> qu = new LinkedList<>();
        
        qu.offer(new int [] { start, 0 });
        visited[start] = true;
        
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            
            for(int next : graph[cur[0]]){
                if(visited[next]){
                    continue;
                }
                
                qu.offer(new int [] { next, cur[1] + 1 });
                visited[next] = true;
                
                int nextR = next / board[0].length();
                int nextC = next % board[0].length();
                if(board[nextR].charAt(nextC) == 'G'){
                    answer = cur[1] + 1;
                    break;
                }
            }
        }
    }
}