import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        int[] h;

        N = Integer.parseInt(br.readLine());
        h = new int[N];
        for(int i = 0; i < N; i++){
            h[i] = Integer.parseInt(br.readLine());
        }

        int [] seeableBuildings = new int[N];
        seeableBuildings[N - 1] = 0;
        Stack<int []> st = new Stack<>(); // 높이와 인덱스 저장
        st.push(new int [] { h[N - 1], N - 1 });

        for(int i = N - 2; i >= 0; i--){
            while(!st.isEmpty() && h[i] > st.peek()[0]){
                st.pop();
            }
            if(st.isEmpty()){
                seeableBuildings[i] = N - i - 1;
                st.push(new int[] { h[i], i });
                continue;
            }
            seeableBuildings[i] = st.peek()[1] - i - 1;
            st.push(new int[] { h[i], i });
        }

        long answer = 0;
        for(int i = 0; i < N; i++){
            answer += seeableBuildings[i];
        }
        System.out.println(answer);
    }
}