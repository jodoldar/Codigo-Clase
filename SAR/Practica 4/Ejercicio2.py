#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Ejercicio 2
# Nombre: Josep Vicent Dols Dart

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