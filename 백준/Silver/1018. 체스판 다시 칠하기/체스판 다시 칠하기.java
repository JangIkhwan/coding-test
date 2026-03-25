import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String[] board;
        String[] blackStartChess = { "BWBWBWBW", "WBWBWBWB",
                "BWBWBWBW", "WBWBWBWB",
                "BWBWBWBW", "WBWBWBWB",
                "BWBWBWBW", "WBWBWBWB" };
        String[] whiteStartChess = { "WBWBWBWB", "BWBWBWBW",
                "WBWBWBWB", "BWBWBWBW",
                "WBWBWBWB", "BWBWBWBW",
                "WBWBWBWB", "BWBWBWBW" };

        int N, M;
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        board = new String[N];
        for(int y = 0; y < N; y++){
            board[y] = br.readLine();
        }
        int answer = 50 * 50;
        for(int y = 0; y + 7 < board.length; y++){
            for(int x = 0; x + 7 < board[y].length(); x++){
                int minCellsToRepaint = Math.min(countCellToRepaint(x, y, board, blackStartChess), countCellToRepaint(x, y, board, whiteStartChess));
                if (minCellsToRepaint < answer){
                    answer = minCellsToRepaint;
                }
            }
        }
        System.out.println(answer);
    }

    private static int countCellToRepaint(int x, int y, String[] board, String[] chess){
        int repaintCount = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[y + i].charAt(x + j) != chess[i].charAt(j)){
                    repaintCount += 1;
                }
            }
        }
        return repaintCount;
    }
}