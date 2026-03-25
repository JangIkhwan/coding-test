import java.io.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/2941
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        int alphabetCounter = 0;
        int i = 0;
        while(i < line.length()){
            char cur = line.charAt(i);
            char next = ' ';
            if (i + 1 < line.length())
                next = line.charAt(i + 1);
            char nextOfnext = ' ';
            if(i + 2 < line.length()){
                nextOfnext = line.charAt(i + 2);
            }
            if(cur == 'c' && (next == '=' || next == '-')){
                alphabetCounter++;
                i += 2;
                continue;
            }
            else if (cur == 'd'){
                if (next == 'z' && nextOfnext == '='){
                    alphabetCounter++;
                    i += 3;
                    continue;
                }
                else if(next == '-'){
                    alphabetCounter++;
                    i += 2;
                    continue;
                }
            }
            else if((cur == 'l' || cur == 'n') && next == 'j'){
                alphabetCounter++;
                i += 2;
                continue;
            }
            else if((cur == 's' || cur == 'z') && next == '='){
                alphabetCounter++;
                i += 2;
                continue;
            }
            alphabetCounter++;
            i += 1;
        }
        System.out.println(alphabetCounter);
    }
}