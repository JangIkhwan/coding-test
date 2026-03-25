import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M, R;
    private static int [] itemNumber;
    private static int[][] dist;
    private static final int INF = 100000;


    public static void main(String[] args) throws IOException {
        init();
        findMaximumItems();
    }

    private static void init() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        line = br.readLine();
        st = new StringTokenizer(line);
        itemNumber = new int[N + 1];
        for(int i = 1; i <= N; i++){
            itemNumber[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= R; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            dist[end][start] = dist[start][end] = distance;
        }
    }

    private static void findMaximumItems(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int answer = 0;
        for(int center = 1; center <= N; center++){
            int sumOfItems = 0;
            for(int dest = 1; dest <= N; dest++){
                if(dist[center][dest] <= M){
                    sumOfItems += itemNumber[dest];
                }
            }
            answer = Math.max(answer, sumOfItems);
        }
        System.out.println(answer);
    }
}
