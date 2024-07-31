#include <stdio.h>
int main(){
    int i;
    char x[10000]={};
    scanf("%s %d", &x, &i);
    printf("%c", x[i-1]);
}