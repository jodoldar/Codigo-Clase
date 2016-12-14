#!/bin/bash
# inf_process

echo -en "PID\tPPID\tESTADO\tCOMANDO\n"
awk '/^Pid/ { printf $2}' /proc/$1/status
echo -en "\t"
awk '/PPid/ {printf $2}' /proc/$1/status
echo -en "\t"
awk '/State/ {printf $2}' /proc/$1/status
echo -en "\t"
awk '/Name/ {print $2}' /proc/$1/status