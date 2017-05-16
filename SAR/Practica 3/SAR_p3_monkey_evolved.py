# -*- coding: utf-8 -*-
# Nombre: Josep Vicent Dols Dart
# Fichero: SAR_p3_monkey_evolved.py

from operator import itemgetter
import pickle
import random
import re
import sys

def load_object(file_name):
    with open(file_name,'rb') as fh:
        obj = pickle.load(fh)
    return obj

def syntax():
    print ("\n%s filename.txt\n" % sys.argv[0])
    sys.exit()

def phrase_generator(dicc):
    list = []
    primTup = dicc.get('$')
    num = random.randint(1,primTup[0])
    lclCount=0
    for element in primTup[1]:
        if (lclCount+element[0])<num:
            lclCount = lclCount+element[0]
        else:
            list.append(element[1])
            break
    count=1
    while count<25:
        lclTup = dicc.get(list[-1])
        num = random.randint(1, lclTup[0])
        lclCount = 0
        for element in lclTup[1]:
            if (lclCount + element[0]) < num:
                lclCount = lclCount + element[0]
            else:
                if element[1]=='$':
                    return list
                else:
                    list.append(element[1])
                    break
        count = count+1
    return list

if __name__ == "__main__":
    if len(sys.argv) < 2:
        syntax()
    inputFile = sys.argv[1]
    diccionario = load_object(inputFile)
    for i in range(0,10):
        for word in phrase_generator(diccionario):
            print(str(word),end=" ")
        print("\n")