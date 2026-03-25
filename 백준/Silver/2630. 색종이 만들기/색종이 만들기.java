import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int whiteNumber = 0;
    private static int blueNumber = 0;
    private static int N;
    private static int [][] paper;

    public static void main(String[] args) throws IOException {
        input();
        dividePaper(0, 0, N, paper);
        System.out.println(whiteNumber);
        System.out.println(blueNumber);
    }

    private static void input() throws IOException {
        String line = br.readLine();
        N = Integer.parseInt(line);
        paper = new int[N][N];
        for(int y = 0; y < N; y++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int x = 0; x < N; x++){
                paper[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dividePaper(int leftTopX, int leftTopY, int size, int[][] paper){
        if(isWhite(leftTopX, leftTopY, size, paper)){
            whiteNumber++;
            return;
        }
        if(isBlue(leftTopX, leftTopY, size, paper)){
            blueNumber++;
            return;
        }
        int nextSize = size / 2;
        dividePaper(leftTopX, leftTopY, nextSize, paper);
        dividePaper(leftTopX + nextSize, leftTopY, nextSize, paper);
        dividePaper(leftTopX, leftTopY + nextSize, nextSize, paper);
        dividePaper(leftTopX + nextSize, leftTopY + nextSize, nextSize, paper);
    }

    private static boolean isBlue(int leftTopX, int leftTopY, int size, int[][] paper) {
        for(int y = leftTopY; y < leftTopY + size; y++){
            for(int x = leftTopX; x < leftTopX + size; x++){
                if(paper[y][x] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWhite(int leftTopX, int leftTopY, int size, int[][] paper) {
        for(int y = leftTopY; y < leftTopY + size; y++){
            for(int x = leftTopX; x < leftTopX + size; x++){
                if(paper[y][x] != 0){
                    return false;
                }
            }
        }
        return true;
    }
}
