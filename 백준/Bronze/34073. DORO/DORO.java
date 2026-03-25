import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String sentence = br.readLine();
        StringTokenizer st = new StringTokenizer(sentence);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < N; i++){
            sb.append(st.nextToken() + "DORO ");
        }
        System.out.println(sb.toString());
    }
}