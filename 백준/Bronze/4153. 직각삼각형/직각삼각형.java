import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            String line = br.readLine();
            if(line.equals("0 0 0")){
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a >= b && a >= c){
                int temp = a;
                a = c;
                c = temp;
            }
            else if (b >= a && b >= c){
                int temp = b;
                b = c;
                c = temp;
            }
            if(c * c == a * a + b * b){
                bw.write("right\n");
            }
            else{
                bw.write("wrong\n");
            }
        }
        bw.flush();
    }
}