#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    vector<int> answer;
    for(vector<string> people : photo){
        int score = 0;
        for(string person : people){
            for(int i = 0; i < name.size(); i++){
                if(person == name[i]){
                    score += yearning[i];
                }
            }
        }
        answer.push_back(score);
    }
    return answer;
}