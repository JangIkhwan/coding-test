import java.util.*;

class Solution {
    public int solution(String[] spell, String[] dic) {
        // 주어진 알파벳 각 글자의 카운트를 0으로 초기화
        // 모든 단어에 대해서
            // 한 단어에 등장하는 문자를 차례로 점검
            // spell에 있고 카운트가 0이면 카운트 증가
            // 카운트가 0이 아니면 다음 문자열을 탐색
        
        for(String word : dic){
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < spell.length; i++){
                map.put(spell[i].charAt(0), 0);
            } 
            
            for(int i = 0; i < word.length(); i++){
                Integer count = map.get(word.charAt(i));
                if(count == null){
                    break;
                }  
                else {
                    map.put(word.charAt(i), count + 1);
                }
            }

            boolean found = true;
            for(Character key : map.keySet()){
                if(map.get(key) != 1){
                    found = false;
                }
            }
            if(found){
                return 1;
            }
        }
        return 2;
    }
}