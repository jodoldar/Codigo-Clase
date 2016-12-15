#!/bin/sh
#PBS -l nodes=2:ppn=1,walltime=00:10:00
#PBS -q cpa
#PBS -d .
#PBS -o ping.txt

mpiexec -np 2 ping_pong 1 100
mpiexec -np 2 ping_pong 100000 100
mpiexec -np 2 ping_pong 200000 100
mpiexec -np 2 ping_pong 300000 100
mpiexec -np 2 ping_pong 400000 100
mpiexec -np 2 ping_pong 500000 100
mpiexec -np 2 ping_pong 600000 100
mpiexec -np 2 ping_pong 700000 100
mpiexec -np 2 ping_pong 800000 100
mpiexec -np 2 ping_pong 900000 100
mpiexec -np 2 ping_pong 1000000 100
