#define n 11
typedef int TABLEAU[n];


int nb_inf(TABLEAU t, int i){
    int total = 0;
    for(int x=0; x<n; x++){
        if(t[x] < i)
            total += 1;
    }
    return total;
}

int median(TABLEAU t){
    int trouve = 0;
    int w = 0;
    while (!trouve && w<n){
        if(nb_inf(t, t[w]) == ((n-1)/2)){
            trouve = 1;
            
        }
        else
            w++;
    }
    
    return t[w];
}


int main(){
    TABLEAU t;
    printf("t[");
      for(int i=0; i<n; i++){
        t[i] = rand()%(100-5)+100;
        printf("%d,", t[i]);
    }
    printf("]\n\n");

    printf("\n\nResultat tri = %d\n", median(t));
}