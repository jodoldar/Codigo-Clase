#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc,char *argv[])
{
    int n,rep,myid,numprocs, i;
    double t;
    
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD,&numprocs);
    MPI_Comm_rank(MPI_COMM_WORLD,&myid);

    // n = tamaño del mensaje
    // rep = veces que se envia el mensaje
    if (argc==3){
        n = atoi(argv[1]);
        rep = atoi(argv[2]);
    }else{
        n = 100000;
        rep = 100;
    }
    if (n<=0 || rep<=0){
        MPI_Abort(MPI_COMM_WORLD,MPI_ERR_ARG);
    }

    char buff[n];
    
    if(myid==0){
        t = MPI_Wtime();
        for(i=1; i<=rep; i++){

            MPI_Send(&buff,n,MPI_CHAR,1,01,MPI_COMM_WORLD);
            MPI_Recv(&buff,n,MPI_CHAR,1,10,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        }
    }else if(myid==1){
        for(i=1; i<=rep; i++){
            MPI_Recv(&buff,n,MPI_CHAR,0,01,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
            MPI_Send(&buff,n,MPI_CHAR,0,10,MPI_COMM_WORLD);
        }
    }
    if(myid==0){
        t = MPI_Wtime()-t;
        t = t/(2*rep);
        printf("%d\t%f\n",t*1000,n);
    }
    
    MPI_Finalize(); 
    return 0; 
} 

