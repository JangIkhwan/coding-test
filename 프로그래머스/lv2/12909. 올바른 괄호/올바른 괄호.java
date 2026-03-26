import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> st = new Stack();
        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if(cur == '('){
                st.push(cur);
            }
            else {
                if(!st.isEmpty() && st.peek() == '('){
                    st.pop();
                }
                else{
                    answer = false;
                    break;
                }
            }
        }
        if(!st.isEmpty()){
            answer = false;
        }
        
        return answer;
    }
}