#!/bin/bash
# system_process

for STATUS_FILE in /proc/[0-9]*/status; 
do
	STATUS_FILE=${STATUS_FILE:6}
	len=${#STATUS_FILE}
	./inf_process ${STATUS_FILE:0:$len-7}
done