import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    25/10/6 13:02 ~ 
    
    icn에서 출발하여서 주어진 엣지를 한번씩 거치는 경로를 찾아야 한다
    
    노드의 수 <= 10000
    항공권의 수 <= 
    
    완전탐색의 느낌으로 풀면 어떨까?
    
    */
    
    List<Integer>[] graph;
    private boolean[][] visited;
    private HashMap<String, Integer> nameToIdx = new HashMap<>();
    private HashMap<Integer, String> idxToName = new HashMap<>();
    private List<String> path = new ArrayList<>();
    private int ticketNum = 0;
    private List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        // 항공권을 엣지로 그래프를 만든다.
        ticketNum = tickets.length;
        
        List<String> names = new ArrayList<>();
        for(String[] row : tickets){
            if(!names.contains(row[0])){
                names.add(row[0]);
            }
            if(!names.contains(row[1])){
                names.add(row[1]);
            }
        }
        names.sort((a, b) -> a.compareTo(b));
        
        // System.out.println("names " + Arrays.toString(names.stream().toArray(String[]::new)));
        
        for(int i = 0; i < names.size(); i++){
            nameToIdx.put(names.get(i), i + 1);
            idxToName.put(i + 1, names.get(i));
        }
        
        graph = new ArrayList[names.size() + 1];
        for(int i = 1; i <= names.size(); i++){
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[names.size() + 1][ticketNum + 1];
        for(int i = 1; i < visited.length; i++){
            Arrays.fill(visited[i], true);
        }
        for(int i = 0; i < tickets.length; i++){
            String s = tickets[i][0];
            String e = tickets[i][1];
            graph[nameToIdx.get(s)].add(nameToIdx.get(e));
            visited[nameToIdx.get(s)][graph[nameToIdx.get(s)].size() - 1] = false;
        }
        
        // for(int i = 1; i < graph.length; i++){
        //     System.out.print("start " + i + " " + "ends ");
        //     for(int e : graph[i]){
        //         System.out.print(e + " ");
        //     }
        //     System.out.println();
        // }
        
        // dfs로 그래프를 탐색한다.
        
        dfs(nameToIdx.get("ICN"));
        
        // 정답을 반환한다.
       
        return answer.stream().toArray(String[]::new);
    }
    
    private void dfs(int cur){  
        path.add(idxToName.get(cur));
        
        // 끝에 도달했을때, 주어진 엣지를 모두 사용했는지 확인한다. 
        // 알파벳 순으로 더 앞선 것을 정답으로 저장한다.
        if(path.size() == ticketNum + 1){
            // System.out.println("found path ");
            // System.out.println("path[] " + Arrays.toString(path.stream().toArray(String[]::new)));
            // System.out.println("answer[] " + Arrays.toString(answer.stream().toArray(String[]::new)));
            
            if(answer.size() == 0){
                answer = List.copyOf(path);
            }
            else{
                for(int i = 0; i < path.size(); i++){
                    if(path.get(i).compareTo(answer.get(i)) < 0){
                        answer = List.copyOf(path);
                        break;
                    }
                    else if(path.get(i).compareTo(answer.get(i)) > 0){
                        break;
                    }
                }
            }
        }
        
        // 다음 노드를 차례로 방문한다
        
        for(int ticket = 0; ticket < graph[cur].size(); ticket++){
            if(!visited[cur][ticket]){
                int next = graph[cur].get(ticket);
                // System.out.println("path[] " + Arrays.toString(path.stream().toArray(String[]::new)));
                visited[cur][ticket] = true;
                dfs(next);
                visited[cur][ticket] = false;
            }
        }
        
        path.remove(path.size() - 1);
    }
}