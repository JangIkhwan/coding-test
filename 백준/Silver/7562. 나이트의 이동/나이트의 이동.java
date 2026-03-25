import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T, I;
        String line = br.readLine();
        T = Integer.parseInt(line);
        while(T-- > 0){
            int[][] map;
            int[][] visited;
            int curX, curY;
            int dstX, dstY;

            line = br.readLine();
            I = Integer.parseInt(line);
            map = new int[I][I];
            visited = new int[I][I];
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            curX = Integer.parseInt(st.nextToken());
            curY = Integer.parseInt(st.nextToken());
            line = br.readLine();
            st = new StringTokenizer(line);
            dstX = Integer.parseInt(st.nextToken());
            dstY = Integer.parseInt(st.nextToken());

            bfs(map, visited, curX, curY, dstX, dstY);
            bw.write(Integer.toString(visited[dstY][dstX] - 1) + "\n");
        }
        bw.flush();
    }

    private static void bfs(int[][] map, int[][] visited, int curX, int curY, int dstX, int dstY){
        int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(curX, curY, 0));
        while(!qu.isEmpty()){
            Node front = qu.poll();
            if(front.x < 0 || front.x >= map[0].length || front.y < 0 || front.y >= map.length) continue;
            if(visited[front.y][front.x] > 0) continue;
            visited[front.y][front.x] = front.prevStep + 1;
            for(int direct = 0; direct < 8; direct++){
                int newX = front.x + dx[direct];
                int newY = front.y + dy[direct];
                qu.add(new Node(newX, newY, front.prevStep + 1));
            }
        }
    }

    static class Node {
        int x;
        int y;
        int prevStep;

        Node(int x, int y, int prevStep){
            this.x = x;
            this.y = y;
            this.prevStep = prevStep;
        }
    }
}