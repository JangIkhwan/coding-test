import java.util.*;

class Solution {
    /*
    2026/6/1 13:31 ~ 
    
    합이 S이고 곱이 최대인 자연수 집합을 구하자
    배열에서 원소는 오름차순이다. 
    존재하지 않으면 -1을 리턴한다
    
    n <= 1만
    s <= 1억
    
    S = 9 N = 3
    (1, 1, 7), (1, 2, 6), (1, 3, 5), (1, 4, 4), (2, 2, 5), (2, 3, 4)
    (3, 3, 3)
    
    완전탐색은 어렵다. 3 ^ (S - N) 복잡도로 예상
    
    S=10
    (1, 1, 8), (1, 2, 7), (1, 3, 6), (1, 4, 5)
    (2, 2, 6), (2, 3, 5), (2, 4, 4)
    (3, 3, 4)
    
    합이 K인 두 수의 곱을 최대로 만드는 방법은?
    거의 균등하게 나눈다?
    
    a + b = S
    argmax (a * b) = ?
    
    */
    public int[] solution(int n, int s) {
        if(s < n){
            return new int[] { -1 };
        }
        
        int sum = s;
        // List<Integer> list = new ArrayList<>(n);
        int[] answer = new int[n];
        for(int size = n; size >= 1; size--){
            int elem = sum / size;
            // list.add(elem);
            answer[n - size] = elem; 
            sum -= elem;
        }
        // list.sort((a, b) -> a - b);
        
        // return list.stream().mapToInt(a -> a).toArray();
        return answer;
    }
}