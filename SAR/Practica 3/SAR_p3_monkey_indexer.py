# -*- coding: utf-8 -*-
# Nombre: Josep Vicent Dols Dart
# Fichero: SAR_p3_monkey_indexer.py

from operator import itemgetter
import sys
import re
import pickle

clean_re = re.compile('\W+')
def clean_text(text):
    return clean_re.sub(' ', text)

def sort_dic(d):
    for key, value in sorted(sorted(d.items()), key=itemgetter(1), reverse=True):
        yield key, value
        
def syntax():
    print ("\n%s filename.txt filename2\n" % sys.argv[0])
    sys.exit()

def save_object(object,file_name):
    with open(file_name,'wb') as fh:
        pickle.dump(object,fh)
    
def indexCreator(entrada,salida):
    dicc={}
    f = open(entrada,"r")
    text = f.read().lower()
    text = text.replace("\n\n",".").split(".")
    for frase in text:
        palabras = clean_text(frase).strip().split()
        if len(palabras)>0:
            # Para la posición 0
            if '$' in dicc:
                tuplaDic = dicc.get('$')
                encontrado = False; pos = -1
                for i in range(0,len(tuplaDic[1])):
                    if tuplaDic[1][i][1] == palabras[0]:
                        encontrado=True;pos=i;break
                if not encontrado:
                    newList = tuplaDic[1]
                    newList.append((1,palabras[0]))
                else:
                    newList = tuplaDic[1]
                    newList[pos] = (newList[pos][0]+1,palabras[0])
                dicc['$']=(tuplaDic[0]+1,newList)
            else:
                dicc.setdefault('$',(1,[(1,palabras[0])]))
            # Para las posiciones de 1 a n-1
            for j in range(0,len(palabras)-1):
                if palabras[j] in dicc:
                    tuplaDic = dicc.get(palabras[j])
                    encontrado=False;pos=-1
                    for i in range(0,len(tuplaDic[1])):
                        if tuplaDic[1][i][1]==palabras[j+1]:
                            encontrado=True;pos=i;break
                    if not encontrado:
                        newList = tuplaDic[1]
                        newList.append((1,palabras[j+1]))
                    else:
                        newList = tuplaDic[1]
                        newList[pos] = (newList[pos][0]+1,palabras[j+1])
                    dicc[palabras[j]]=(tuplaDic[0]+1,newList)
                else:
                    dicc.setdefault(palabras[j],(1,[(1,palabras[j+1])]))
            # Para la posición n
            if palabras[-1] in dicc:
                tuplaDic = dicc.get(palabras[-1])
                encontrado=False;pos=-1
                for i in range(0,len(tuplaDic[1])):
                    if tuplaDic[1][i][1]=='$':
                        encontrado=True;pos=i;break
                if not encontrado:
                    newList = tuplaDic[1]
                    newList.append((1,'$'))
                else:
                    newList = tuplaDic[1]
                    newList[pos] = (newList[pos][0]+1,'$')
                dicc[palabras[-1]]=(tuplaDic[0]+1,newList)
            else:
                dicc.setdefault(palabras[-1],(1,[(1,'$')]))

    for element in dicc.keys():
        tup = dicc.get(element)
        dicc[element] = (tup[0],sorted(tup[1],reverse=True))
    for x,y in sorted(dicc.items()):
        print(str(x)+"\t"+str(y))
    save_object(dicc,salida)

if __name__ == "__main__":
    if len(sys.argv) < 3:
        syntax()
    inputFile = sys.argv[1]
    outputFile = sys.argv[2]
    indexCreator(inputFile,outputFile)