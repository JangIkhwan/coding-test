import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();

        Stack<Character> st = new Stack<>();

        StringBuilder sb = new StringBuilder();
        boolean inTag = false;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '<'){
                while(!st.isEmpty()){
                    sb.append(st.pop());
                }
                inTag = true;
                sb.append(line.charAt(i));
                continue;
            }
            if(line.charAt(i) == '>'){
                sb.append(line.charAt(i));
                inTag = false;
                continue;
            }
            if(line.charAt(i) == ' '){
                while(!st.isEmpty()){
                    sb.append(st.pop());
                }
                sb.append(' ');
                continue;
            }
            if(inTag){
                sb.append(line.charAt(i));
                continue;
            }
            st.push(line.charAt(i));
        }
        while(!st.isEmpty()){
            sb.append(st.pop());
        }


        System.out.println(sb.toString());
    }
}