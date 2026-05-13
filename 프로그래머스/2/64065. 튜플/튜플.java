import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    26/5/13 15:25 ~ 
    
    집합이 나타내는 튜플을 구하자
    
    집합의 원소의 순서는 바뀔 수 있다
    
    
    */
    public int[] solution(String s) {
        // 문자열을 sets 리스트에 저장
                
        List<List<Integer>> sets = new ArrayList<>();
        
        s = s.substring(2, s.length() - 2);
        String[] strs = s.split("\\},\\{");
        
        // System.out.println(Arrays.toString(strs));
        
        
        for(String str : strs){
            String[] numStrs = str.split(",");
            List<Integer> set = new ArrayList<>();
            for(String n : numStrs){
                set.add(Integer.parseInt(n));
            }
            sets.add(set);
        }
        
        
        // System.out.println(sets);
        
        // sets를 길이에 대해 오름차순으로 정렬
        sets.sort((a, b) -> a.size() - b.size());
        
        // sets의 각 원소에 대해서 
            // 포함되지 않은 원소를 answer 리스트에 추가
        List<Integer> answer = new ArrayList<>();
        Set<Integer> seens = new HashSet<>();
        for(List<Integer> set : sets){
            for(int e : set){
                if(!seens.contains(e)){
                    answer.add(e);
                    seens.add(e);
                    break;
                }
            }
        }
        
        return answer.stream().mapToInt(a -> a).toArray();
    }
}