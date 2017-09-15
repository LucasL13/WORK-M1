#define n 10
typedef int TABLEAU[n];


int estTrie(TABLEAU t){
    int resultat = 1;

    for(int i=0; i<n-1; i++){
        if(t[i] > t[i+1])
            resultat = 0;
    }

    return resultat;
}


int main(){
    TABLEAU t;
    printf("t[");
      for(int i=0; i<n; i++){
        t[i] = i;
        printf("%d,", t[i]);
    }

    printf("]\n\nResultat tri = %d\n", estTrie(t));
}