import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    25/10/8 14:45  ~
    
    힌트를 보니 내가 생각한대로 이분탐색을 사용해야하고, 각 조건에 맞게 정렬한 info 배열이 필요하다
    
     
    */
    public int[] solution(String[] info, String[] query) {
        // 각 조건에 맞게 info 배열을 준비
        HashMap<String, List<Integer>> infoMap = new HashMap();
        
        String[] langs = {"-", "cpp", "java", "python"};
        String[] parts = {"-", "backend", "frontend"};
        String[] careers = {"-", "junior", "senior"};
        String[] foods = {"-", "chicken", "pizza"};
        
        for(String l : langs){
            for(String p : parts){
                for(String c : careers){
                    for(String f : foods){
            
                        String key = l + " "+ p + " " + c + " " + f;

                        List<Integer> value = Arrays.stream(info).filter(person->{
                            String[] tokens = person.split(" ");
                            if(!l.equals("-") && !tokens[0].equals(l)){
                                return false;
                            }
                            if(!p.equals("-") && !tokens[1].equals(p)){
                                return false;
                            }
                            if(!c.equals("-") && !tokens[2].equals(c)){
                                return false;
                            }
                            if(!f.equals("-") && !tokens[3].startsWith(f)){
                                return false;
                            }
                            return true;
                        })
                        .map(person -> {
                            String[] tokens = person.split(" ");
                            int score = Integer.parseInt(tokens[4]);
                            return score;
                        })
                        .collect(Collectors.toList());

                        value.sort(Integer::compare);

                        infoMap.put(key, value);
                        
                    }
                }
            }
        }
        
        int i = 0;
        int[] answer = new int[query.length];
        // 각 쿼리에 대해서 이분탐색을 수행
        for(String q : query){
            String[] tokens = q.split(" and ");
            
            String lang = tokens[0];
            String part = tokens[1];
            String career = tokens[2];
            String food = tokens[3].split(" ")[0];
            int score = Integer.parseInt(tokens[3].split(" ")[1]);
            
            String key = lang + " " + part + " " + career + " " + food;
            
            List<Integer> list = infoMap.get(key);
            
            if(list.isEmpty()){
                answer[i++] = 0;   
                continue;
            }
            
            int lo = -1;
            int hi = list.size();
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(list.get(mid) >= score){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
                
            answer[i++] = list.size() - hi;
            
        }
        return answer;
    }
}