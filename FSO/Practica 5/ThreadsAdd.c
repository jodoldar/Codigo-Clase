//  Program ThreadsAdd.c
//
//  Created by Josep Dols on 19/10/15.
//
//  To compile do: gcc ThreadsAdd.c -o SeqAdd -lm -lpthread

#include <stdio.h>
#include <pthread.h>
#include <math.h>

#define DIMROW 1000000
#define NUMROWS 20

typedef struct row{
    int vector[DIMROW];
    long addition;
} row;

struct row matrix[NUMROWS];

void *AddRow(void *ptr){
    int k;
    row *fi;
    fi = (row *)ptr;
    
    fi->addition=0;
    for (k=0; k<DIMROW; k++) {
        fi->addition += exp((k*(fi->vector[k])+(k+1)*(fi->vector[k]))/(fi->vector[k]+2*k)/2);
    }
}

int main(){
    int i,j,l;
    long total_addition = 0;
    pthread_t threads[NUMROWS];
    pthread_attr_t atrib;
    
    //Vector elements are initialized to 1
    for (i=0; i<NUMROWS; i++) {
        for (j=0; j<DIMROW; j++) {
            matrix[i].vector[j]=1;
        }
    }
    
    //Thread attributes initialization
    pthread_attr_init(&atrib);
    
    //EXERCISE 2.a
    for (i=0; i<NUMROWS; i++) {
        pthread_create(&threads[i],&atrib,AddRow,&matrix[i]);
        //pthread_join(l,NULL);
        //AddRow(&matrix[i]);
    }
    for (l=0; l<NUMROWS; l++) {
        pthread_join(threads[l],NULL);
    }
    
    //EXERCISE 2.b
    for (i=0; i<NUMROWS; i++) {
        total_addition += matrix[i].addition;
    }
    printf("Total addition is: %ld \n", total_addition);
}