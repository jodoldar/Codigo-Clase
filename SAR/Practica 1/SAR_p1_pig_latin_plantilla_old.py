#!/usr/bin/env python
#! -*- encoding: utf8 -*-

"""
1.- Pig Latin

Nombre Alumno: Josep Vicent Dols Dart
"""

import sys


def piglatin_word(word):
    """
    Esta función recibe una palabra en inglés y la traduce a Pig Latin

    :param word: la palabra que se debe pasar a Pig Latin
    :return: la palabra traducida
    """
    # COMPLETAR
    espSeq = ',.;?!'; vowels = 'aeiouAEIOU'; vowelsExt = 'aeiouyAEIOUY'
    isUp = word[0].isupper()
    allUpper = word.isupper()
    ending = ''
    longitud = len(word)
    principio = ''; final = ''
    encontrado = False
    if(not word[0].isalpha()):
        return word
    if(word[-1] in espSeq):
        longitud=longitud-1
        ending = word[-1]
    for i in range(0,longitud):
        if(encontrado):
            principio=principio+word[i]
        else:
            if(word[i] in vowels):
                encontrado=True
                principio=principio+word[i]
            else:
                final=final+word[i]
    if(word[0] in vowelsExt):
        word=principio+final+'yay'+ending
    else:
        word = principio+final+'ay'+ending
    if(allUpper):
        word=word.upper()
    else:
        word = word.lower()
    if(isUp):
        word=word[0].upper()+word[1:]
    return word


def piglatin_sentence(sentence):
    """
    Esta función recibe una frase en inglés i la traduce a Pig Latin

    :param sentence: la frase que se debe pasar a Pig Latin
    :return: la frase traducida
    """
    # COMPLETAR
    palabras = sentence.split()
    for x in range(0,len(palabras)):
        palabras[x]=piglatin_word(palabras[x])
    sentence = ' '.join(palabras)
    return  sentence


if __name__ == "__main__":
    if len(sys.argv) > 1:
        if(sys.argv[1]=='-f'):
            nom = sys.argv[2]
            if(nom.endswith('.txt')):
                #todo
                f=open(nom)
                linea=f.readline()
                while(len(linea)!=0):
                    print(piglatin_sentence(linea))
                    linea=f.readline()
            else:
                print('El formato del fichero no es correcto')
        else:
            print (piglatin_sentence(sys.argv[1]))
    else:
        while True:
            # COMPLETAR
            sentence = input('Introduzca una frase para traducir:')
            if len(sentence)==0:
                break
            else:
                print(piglatin_sentence(sentence))
            #pass
