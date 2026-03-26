#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> park, vector<string> routes) {
    vector<int> answer;
    int height = park.size();
    int width = park[0].length(); 
    int rx, ry;
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
            if(park[y][x] == 'S'){
                ry = y;
                rx = x;
            }
        }
    }
    for(string route : routes){
        char op = route[0];
        int step = stoi(route.substr(2)); 
        bool canMove = true;
        switch(op){
            case 'N':
                if(ry - step < 0) break;
                for(int i = 1; i <= step; i++){
                    if(park[ry - i][rx] == 'X'){
                        canMove = false;
                    }
                }
                if(canMove) ry -= step;
                break;
            case 'S':
                if(ry + step > height - 1) break;
                for(int i = 1; i <= step; i++){
                    if(park[ry + i][rx] == 'X'){
                        canMove = false;
                    }
                }
                if(canMove) ry += step;
                break;
            case 'W':
                if(rx - step < 0) break;
                for(int i = 1; i <= step; i++){
                    if(park[ry][rx - i] == 'X'){
                        canMove = false;
                    }
                }
                if(canMove) rx -= step;
                break;
            case 'E':
                if(rx + step > width - 1) break;
                for(int i = 1; i <= step; i++){
                    if(park[ry][rx + i] == 'X'){
                        canMove = false;
                    }
                }
                if(canMove) rx += step;
                break;
        }
    }
    answer.push_back(ry);
    answer.push_back(rx);
    return answer;
}