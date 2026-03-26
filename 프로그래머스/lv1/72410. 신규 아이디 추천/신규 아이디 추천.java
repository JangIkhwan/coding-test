class Solution {
    public String solution(String new_id) {
        return new KakaoId(new_id)
            .toLowercase()
            .removeNotAllowedChars()
            .get();
    }
    
    static class KakaoId{
        String kakaoId;
        
        public KakaoId(String kakaoId){
            this.kakaoId = kakaoId;
        }
        
        public KakaoId toLowercase(){
            kakaoId = kakaoId.toLowerCase();
            return this;
        }
        
        public KakaoId removeNotAllowedChars(){
            kakaoId.replace("[^a-z0-9-_.]", "");
            return this;
        }
        
        public String get(){
            return kakaoId;
        }
    }
}