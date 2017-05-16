#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Práctica 4 - NLTK
# Nombre: Josep Vicent Dols Dart

# EJERCICIO 1
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

# EJERCICIO 2
from nltk.corpus import brown
from nltk.probability import *

res=[]

palabras=['what','when','where','who','why']
for palabra in palabras:
    res.append(palabra)
    lista=[]
    for cat in brown.categories():
        pal=FreqDist(brown.words(categories=cat))
        lista.append(cat)
        lista.append(pal[palabra])
    res.append(lista)
print(res)

# EJERCICIO 3
import re
import nltk
from nltk.probability import *
from nltk.corpus import stopwords

# Parte 1,2
raw=open('quijote.txt',encoding='utf-8').read()
fdist = nltk.FreqDist(ch.lower() for ch in raw)
print(sorted([w for w in set(raw)]))

# Parte 3
raw_filtered = re.sub(r'([^\s\w]|_)+', '', raw)

# Parte 4
print(sorted([w for w in set(raw_filtered)]))

# Parte 5
raw_filtered_words = raw_filtered.split()
fdist2 = FreqDist(raw_filtered_words)
print(len(raw_filtered_words),len(fdist2))
keys = list(fdist2.keys())
keys.sort()
print(' '.join(keys[:10]))
print(' '.join(keys[-10:]))

# Parte 6
print(fdist2.most_common(20))

# Parte 7
stopwords = stopwords.words('spanish')
sin_stopwords = [w for w in raw_filtered_words if w.lower() not in stopwords]

# Parte 8
fdist3 = FreqDist(sin_stopwords)
print(len(sin_stopwords),len(fdist3))
keys2 = list(fdist3.keys())
keys2.sort()
print(' '.join(keys2[:10]))
print(' '.join(keys2[-10:]))

# Parte 9
print(fdist3.most_common(20))

# Parte 10
from nltk.stem import SnowballStemmer
stemmed_text=[]
stemmer = SnowballStemmer("spanish")
for word in sin_stopwords:
	stemmed_text.append(stemmer.stem(word))
    
# Parte 11
fdist4 = FreqDist(stemmed_text)
print(len(stemmed_text),len(fdist4))
keys3 = list(fdist4.keys())
keys3.sort()
print(' '.join(keys3[:10]))
print(' '.join(keys3[-10:]))

# Parte 12
print(fdist4.most_common(20))