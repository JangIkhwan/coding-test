import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        int N, M;
        int[] waitingTimes;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        waitingTimes = new int[N];
        for(int i = 0; i < N; i++){
            waitingTimes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(waitingTimes);

//        for(int i = 0; i < N; i++){
//            System.out.println(waitingTimes[i]);
//        }

        // 파라매트릭 서치를 이용한다
        long lo = 0;
        long hi = ((long) waitingTimes[N - 1]) * M;
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            if(isPossibleTotalTime(mid, M, waitingTimes)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
        // hi가 결과이다?
    }

    private static boolean isPossibleTotalTime(long totalTime, int M, int[] waitingTimes){
        // 가장 적은 대기시간부터 사람들을 분배한다
        long peopleToAddress = M;
        for(int i = 0; i < waitingTimes.length; i++){
            peopleToAddress -= totalTime / waitingTimes[i];
            if(peopleToAddress <= 0){
                return true;
            }
        }
        return false;
    }
}