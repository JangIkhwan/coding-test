import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int liquidSum = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;
        int answerLeft = 0;
        int answerRight = N - 1;
        if(A[left] >= 0 && A[right] >= 0) {
            System.out.println(A[left] + " " + A[left + 1]);
            return;
        }
        if(A[left] <= 0 && A[right] <= 0){
            System.out.println(A[right - 1] + " " + A[right]);
            return;
        }
        while(left < right){
            if(Math.abs(A[left] + A[right]) < Math.abs(liquidSum)){
                liquidSum = A[left] + A[right];
                answerLeft = left;
                answerRight = right;
            }
            if(Math.abs(A[left]) > Math.abs(A[right])){
                left++;
            }
            else{
                right--;
            }
        }
        System.out.println(A[answerLeft] + " " + A[answerRight]);
    }
}