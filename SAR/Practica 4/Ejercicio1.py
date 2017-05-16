#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Ejercicio 1
# Nombre: Josep Vicent Dols Dart

# Parte 1
from nltk.corpus import cess_esp

# Parte 2
print(len(cess_esp.words()))

# Parte 3
print(len(cess_esp.sents()))

# Parte 4
from nltk.probability import *
text = cess_esp.words(cess_esp.fileids()[0])
fdist = FreqDist(text)
print(fdist.most_common(20))

# Parte 5
print([w for w,f in fdist.most_common()])

# Parte 6
print(sorted([w for w,f in fdist.most_common() if f>2 and len(w)>7]))

# Parte 7
print([fdist[w] for w,f in fdist.most_common()])

# Parte 8
print("No de palabras que aparecen una sole vez: ",len([w for w,f in fdist.most_common() if fdist[w]==1]))

# Parte 9
print("La palabra más frecuente es",fdist.max())

# Parte 10
from nltk.corpus import PlaintextCorpusReader
corpus_root = '.'
wordlists = PlaintextCorpusReader(corpus_root, '.*\.txt')
for element in wordlists.fileids():
    print(element,len(wordlists.words(element)),len(set(wordlists.words(element))),len(wordlists.sents(element)))