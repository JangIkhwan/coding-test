import java.io.*;
import java.util.*;

public class Main {
   

    public static void main(String[] args) throws IOException{
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
            // 각 입력마다 집합을 합치거나
            // 쿼리에 대해서 출력

        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        DisjointSet set = new DisjointSet(1000001);
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            String command = st.nextToken();
            if(command.equals("I")){
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                set.union(u, v);
            }
            else {
                int u = Integer.parseInt(st.nextToken());
                sb.append(set.numOfMembers(u) + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    static class DisjointSet{
        int[] parents;
        int[] members;

        public DisjointSet(int n){
            parents = new int[n];
            members = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
                members[i] = 1;
            }
        }

        public int find(int u){
            if(parents[u] == u){
                return u;
            }
            return parents[u] = find(parents[u]);
        }

        public void union(int u, int v){
            int up = find(u);
            int vp = find(v);
            if(up != vp){
                int temp = members[up];
                parents[up] = vp;
                members[vp] += temp;
            }
        }

        public int numOfMembers(int u){
            return members[find(u)];
        }
    }
}