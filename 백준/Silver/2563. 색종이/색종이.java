import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/2563

    2025/01/03 12:06 ~
     */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        // todo 배열을 도화지로 보고 색종이가 덮는 위치에 1을 저장한 후에 배열에 있는 1의 개수로 넓이를 계산
        int[][] paper = new int[101][101];
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int leftBottomX = Integer.parseInt(st.nextToken());
            int leftBottomY = Integer.parseInt(st.nextToken());
            for(int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    paper[leftBottomY + y][leftBottomX + x] = 1;
                }
            }
        }
        int blackArea = 0;
        for(int y = 0; y < 100; y++){
            for(int x = 0; x < 100; x++){
                if(paper[y][x] == 1){
                    blackArea++;
                }
            }
        }
        System.out.println(blackArea);
    }
}