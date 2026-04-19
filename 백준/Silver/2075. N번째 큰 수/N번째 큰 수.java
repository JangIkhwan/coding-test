import java.util.*;
import java.io.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/2075

    26/4/19 17:38 ~

    N번째로 큰 수를 찾자

    K = N^2 <= 225만

    K log K <= 225만 * 22

    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                list.add(num);
            }
        }

        list.sort((a, b) -> b - a);

        System.out.println(list.get(N - 1));
    }
}