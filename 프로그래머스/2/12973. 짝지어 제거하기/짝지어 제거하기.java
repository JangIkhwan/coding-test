import java.util.*;

class Solution
{
    /*
    26-7-27 8:37 ~ 
    
    --- 
    
    스택?
    
    */
    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(!st.isEmpty() && st.peek() == s.charAt(i)){
                st.pop();
                continue;
            }
            st.push(s.charAt(i));
        }

        if(st.isEmpty()){
            return 1;
        }
        return 0;
    }
}