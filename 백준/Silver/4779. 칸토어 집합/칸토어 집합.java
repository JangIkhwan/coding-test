import java.io.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/4779

    25/1/24 20:32
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N;
        while(true){
            String line = br.readLine();
            if(line == null){
                break;
            }
            N = Integer.parseInt(line);
            printCantour(N);
            bw.write("\n");
            bw.flush();
        }
    }

    private static void printCantour(int powOfLength) throws IOException {
        if(powOfLength == 0){
            bw.write("-");
            return;
        }
        printCantour(powOfLength - 1);
        long oneThirdOfLength = (long)Math.pow(3, powOfLength - 1);
        for(int i = 0; i < oneThirdOfLength; i++){
            bw.write(" ");
        }
        printCantour(powOfLength - 1);
    }
}