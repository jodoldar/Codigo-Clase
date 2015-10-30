#!/bin/bash
# borrar_fichero

if test -s $1
then
	if test -d $1
   	then
   		echo "Esto es un directorio"
   	else
   		rm $1
   		echo "Archivo $1 eliminado"
   	fi
else
	echo "No existe el archivo o directorio"
fi