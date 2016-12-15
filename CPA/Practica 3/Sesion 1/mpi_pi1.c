#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc,char *argv[])
{
    int n, myid, numprocs, i;
    double mypi, pi, h, sum, x,pi1,pi2;
    
    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD,&numprocs);
    MPI_Comm_rank(MPI_COMM_WORLD,&myid);
    
    if (argc==2) n = atoi(argv[1]);
    else n = 100;
    if (n<=0) MPI_Abort(MPI_COMM_WORLD,MPI_ERR_ARG);
    
    /* Cálculo de PI. Cada proceso acumula la suma parcial de un subintervalo */
    if(myid==1){
        h   = 1.0 / (double) n;
        sum = 0.0;
        for (i = myid + 1; i <= n; i += numprocs) {
            x = h * ((double)i - 0.5);
            sum += (4.0 / (1.0 + x*x));
        }
        pi1 = h * sum;
        MPI_Send(&pi1,1,MPI_DOUBLE,0,10,MPI_COMM_WORLD);
    }else if(myid==2){
        h   = 1.0 / (double) n;
        sum = 0.0;
        for (i = myid + 1; i <= n; i += numprocs) {
            x = h * ((double)i - 0.5);
            sum += (4.0 / (1.0 + x*x));
        }
        pi2 = h * sum;
        MPI_Send(&pi2,1,MPI_DOUBLE,0,20,MPI_COMM_WORLD);
    }else if(myid==0){
        h   = 1.0 / (double) n;
        sum = 0.0;
        for (i = myid + 1; i <= n; i += numprocs) {
            x = h * ((double)i - 0.5);
            sum += (4.0 / (1.0 + x*x));
        }
        mypi = h * sum;
        
        MPI_Recv(&pi1,1,MPI_DOUBLE,1,MPI_ANY_TAG,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        mypi+=pi1;
        MPI_Recv(&pi2,1,MPI_DOUBLE,2,MPI_ANY_TAG,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        mypi+=pi2;
        
        printf("Cálculo de PI con %d procesos\n",numprocs);
        printf("Con %d intervalos, PI es aproximadamente %.16f (error = %.16f)\n",n,mypi,fabs(mypi-M_PI));
    }
    MPI_Finalize(); 
    return 0; 
} 

