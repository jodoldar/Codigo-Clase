#!/bin/sh
#PBS -l nodes=4,walltime=00:10:00
#PBS -q cpa
#PBS -d .
#PBS -o hola_procesador.txt

cat $PBS_NODEFILE
mpiexec hello_procesador
