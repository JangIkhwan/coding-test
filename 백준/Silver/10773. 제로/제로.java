import java.io.*;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < K; i++){
            int number = Integer.parseInt(br.readLine());
            if(number == 0){
                st.pop();
            }
            else{
                st.push(number);
            }
        }
        int sum = 0;
        while(!st.empty()){
            int number = st.pop();
            sum += number;
        }
        System.out.println(sum);
    }
}
