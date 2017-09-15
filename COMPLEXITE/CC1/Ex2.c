#define n 11
typedef int TABLEAU[n];


int estTrie(TABLEAU t, int i){
    int total = 0;
    if(i < n){
        if(t[i]%2 != 0){
            total = 1 + estTrie(t, i+1);
        }
        else
           total = estTrie(t, i+1);
    }
    return total;
}


int main(){
    TABLEAU t;
    printf("t[");
      for(int i=0; i<n; i++){
        t[i] = i;
        printf("%d,", t[i]);
    }
    printf("]\n\n");

    printf("\n\nResultat tri = %d\n", estTrie(t,0));
}