#!/usr/bin/env python3
# -*- coding: utf8 -*-

# Ejercicio 3
# Nombre: Josep Vicent Dols Dart
import re
import nltk
from nltk.probability import *
from nltk.corpus import stopwords

# Ejercicio1,2
raw=open('quijote.txt',encoding='utf-8').read()
fdist = nltk.FreqDist(ch.lower() for ch in raw)
print(sorted([w for w in set(raw)]))

# Ejercicio 3
raw_filtered = re.sub(r'([^\s\w]|_)+', '', raw)

# Ejercicio 4
print(sorted([w for w in set(raw_filtered)]))

#Ejercicio 5
raw_filtered_words = raw_filtered.split()
fdist2 = FreqDist(raw_filtered_words)
print(len(raw_filtered_words),len(fdist2))
keys = list(fdist2.keys())
keys.sort()
print(' '.join(keys[:10]))
print(' '.join(keys[-10:]))

# Ejercicio 6
print(fdist2.most_common(20))

# Ejercicio 7
stopwords = stopwords.words('spanish')
sin_stopwords = [w for w in raw_filtered_words if w.lower() not in stopwords]

# Ejercicio 8
fdist3 = FreqDist(sin_stopwords)
print(len(sin_stopwords),len(fdist3))
keys2 = list(fdist3.keys())
keys2.sort()
print(' '.join(keys2[:10]))
print(' '.join(keys2[-10:]))

# Ejercicio 9
print(fdist3.most_common(20))

# Ejercicio 10
from nltk.stem import SnowballStemmer
stemmed_text=[]
stemmer = SnowballStemmer("spanish")
for word in sin_stopwords:
	stemmed_text.append(stemmer.stem(word))
    
# Ejercicio 11
fdist4 = FreqDist(stemmed_text)
print(len(stemmed_text),len(fdist4))
keys3 = list(fdist4.keys())
keys3.sort()
print(' '.join(keys3[:10]))
print(' '.join(keys3[-10:]))

# Ejercicio 12
print(fdist4.most_common(20))

