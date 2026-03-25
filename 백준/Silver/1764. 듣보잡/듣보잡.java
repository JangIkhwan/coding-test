import java.io.*;
import java.util.*;

public class Main {
 
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    private void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        String[] notHeard;
        HashSet<String> notSeen;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        notHeard = new String[N];
        for(int i = 0; i < N; i++){
            notHeard[i] = br.readLine();
        }

        notSeen = new HashSet<>();
        for(int i = 0; i < M; i++){
            notSeen.add(br.readLine());
        }

        List<String> neither = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(notSeen.contains(notHeard[i])){
                neither.add(notHeard[i]);
            }
        }

        neither.sort(String::compareTo);
        System.out.println(neither.size());
        for(int i = 0; i < neither.size(); i++){
            System.out.println(neither.get(i));
        }
    }
}
