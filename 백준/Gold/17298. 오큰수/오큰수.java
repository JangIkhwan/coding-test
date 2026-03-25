import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N;
        int[] A;
        int[] nge;
        Stack<Integer> st = new Stack<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer strTok = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(strTok.nextToken());
        }
        nge = new int[N];
        st.push(0);
        for(int i = 1; i < N; i++){
            if(A[st.peek()] >= A[i]){
                st.push(i);
            }
            else{
                while(!st.isEmpty() && A[st.peek()] < A[i]){
                    int top = st.pop();
                    nge[top] = A[i];
                }
                st.push(i);
            }
        }
        while(!st.isEmpty()){
            int top = st.pop();
            nge[top] = -1;
        }
        for(int i = 0; i < N; i++){
            bw.write(Integer.toString(nge[i]) + " ");
        }
        bw.flush();
    }
}
