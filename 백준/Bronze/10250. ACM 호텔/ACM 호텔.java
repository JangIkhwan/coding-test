import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, H, W, N;
        T = Integer.parseInt(br.readLine());
        List<Integer> results = new ArrayList<>();
        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            int floor;
            int dist;
            if (N % H == 0) {
                floor = H;
                dist = N / H;
            }
            else{
                floor = N % H;
                dist = N / H + 1;
            };
            int roomNumber = floor * 100 + dist;
            results.add(roomNumber);
        }
        for(int roomNumber : results)
            System.out.println(roomNumber);
    }
}
