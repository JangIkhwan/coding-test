import java.io.*;
import java.util.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/1941

    26/1/12 22:03 ~

    S가 4개 이상이도록 가로 세로로 인접한 경우를 모두 찾는다

    브루트포스로 풀 수 있나?
    -

    */

    private String[] grid;
    private static int sTeam = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private boolean[] selected;
    private int answer = 0;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        grid = new String[5];
        for (int i = 0; i < 5; i++) {
            grid[i] = br.readLine();
        }

        selected = new boolean[25];
        for (int cell = 0; cell < 25; cell++) {
            findCase(cell, 1);
        }

        System.out.println(answer);
    }

    private void findCase(int cell, int depth) {
        if (selected[cell]) {
            return;
        }

        int y = cell / 5;
        int x = cell % 5;

//        System.out.println("select " + cell);
        selected[cell] = true;

        if (grid[y].charAt(x) == 'S') {
            sTeam++;
        }

        if (depth >= 7) {
            if (sTeam >= 4 && connected()) {
//                System.out.println("found");
//                for(int i = 0; i < 25; i++){
//                    System.out.print(selected[i] + " ");
//                    if(i % 5 == 4){
//                        System.out.println();
//                    }
//                }

                answer++;
            }
        }
        else{
            for(int nCell = cell + 1; nCell < 25; nCell++) {
                findCase(nCell, depth + 1);
            }
        }

//        System.out.println("exclude " + cell);

        selected[cell] = false;
        if (grid[y].charAt(x) == 'S') {
            sTeam--;
        }
    }

    private boolean connected(){
        int component = 0;
        boolean[][] checked = new boolean[5][5];
        for(int i = 0; i < 25; i++){
            int y = i / 5;
            int x = i % 5;
            if(selected[i] && !checked[y][x]){
                bfs(x, y, checked);
                component++;
            }
        }
        return component == 1;
    }

    private void bfs(int x, int y, boolean[][] checked){
        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[] { x, y });
        checked[y][x] = true;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5){
                    continue;
                }
                if(checked[ny][nx]) {
                    continue;
                }
                if(!selected[ny * 5 + nx]){
                    continue;
                }
                qu.add(new int[] { nx, ny });
                checked[ny][nx] = true;
            }
        }
    }
}