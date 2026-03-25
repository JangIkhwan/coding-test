import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int N;
        int[] arr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 음수와 양수 나누어서 최대합 계산 후 결과 합치기
        Arrays.sort(arr);

        List<Integer> nonPositives = new ArrayList<>();
        int front = 0;
        for(;front < N; front++){
            if(arr[front] <= 0)
                nonPositives.add(arr[front]);
            else
                break;
        }

//        System.out.print("nonPositives = ");
//        for(int i = 0; i < nonPositives.size(); i++){
//            System.out.print(nonPositives.get(i) + " ");
//        }
//        System.out.println();

        List<Integer> positives = new ArrayList<>();
        for(int end = N - 1; end >= front; end--){
            if(arr[end] > 0)
                positives.add(arr[end]);
            else
                break;
        }

//        System.out.print("positives = ");
//        for(int i = 0; i < positives.size(); i++){
//            System.out.print(positives.get(i) + " ");
//        }
//        System.out.println();

        int sum = 0;
        int i = 0;
        int leftNonPositive = 0;
        for(; i < nonPositives.size(); i += 2){
            if(i + 1 < nonPositives.size()){
                if(nonPositives.get(i + 1) < 0){
                    sum += nonPositives.get(i) * nonPositives.get(i + 1);
                }
            }
            else{
                leftNonPositive = nonPositives.get(i);
                break;
            }
        }

//        System.out.println("left neg = " + leftNonPositive);

        int j = 0;
        int leftPositive = -1;
        for(; j < positives.size(); j += 2){
            if(j + 1 < positives.size()){
                if(positives.get(j + 1) == 1){
                    sum += positives.get(j) + 1;
                }
                else{
                    sum += positives.get(j) * positives.get(j + 1);
                }
            }
            else{
                leftPositive = positives.get(j);
                break;
            }
        }

//        System.out.println("left non neg = " + leftPositive);

        if(leftNonPositive < 0){
            if(leftPositive > 0){
                sum += leftNonPositive + leftPositive;
            }
            else{
                sum += leftNonPositive;
            }
        }
        else{
            if(leftPositive > 0){
                sum += leftPositive;
            }
        }

        System.out.println(sum);
    }
}