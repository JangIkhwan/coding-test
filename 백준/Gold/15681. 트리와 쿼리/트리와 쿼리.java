import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer>[] tree;
    private static int[] treeNodes;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException{
        int N, R, Q;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N - 1; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        int[] queries = new int[Q];
        for(int i = 0; i < Q; i++){
            line = br.readLine();
            queries[i] = Integer.parseInt(line);
        }

        // 모든 서브 트리의 총 노드 수 구하기
        treeNodes = new int[N + 1];
        visited = new boolean[N + 1];
        calcSubTreeNodes(R);

        // 쿼리마다 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int query : queries){
            sb.append(treeNodes[query] + "\n");
        }
        System.out.println(sb.toString());
    }

    private static int calcSubTreeNodes(int root){
        int nodeNumber = 0;
        visited[root] = true;
        for(int child : tree[root]){
            if(!visited[child]){
                nodeNumber += calcSubTreeNodes(child);
            }
        }
        return treeNodes[root] = nodeNumber + 1;
    }
}