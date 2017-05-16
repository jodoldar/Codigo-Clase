#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Archivo: SAR_searcher.py
# Nombre: Josep Vicent Dols Dart

# Input Args:
#   file - Nombre del archivo desde el que cargar el ínidice

import re
import sys
import pickle

clean_re = re.compile('\W+')
def clean_text(text):
    return clean_re.sub(' ', text)

def load_object(file_name):
    with open(file_name,'rb') as fh:
        obj = pickle.load(fh)
    return obj

def AND(list1,list2):
    # AND de las posting lists
    resultado = []
    i1 = 0; i2 = 0
    while i1 < len(list1) and i2 < len(list2):
        if(list1[i1]==list2[i2]):
            resultado.append(list1[i1])
            i1 = i1+1
            i2 = i2+1
        elif(list1[i1]<list2[i2]):
            i1=i1+1
        else:
            i2=i2+1
    return resultado

def OR(list1,list2):
    #OR de las posting lists
    resultado = []
    i1 = 0; i2 = 0
    while i1<len(list1) and i2<len(list2):
        if(list1[i1]==list2[i2]):
            resultado.append(list1[i1])
            i1 = i1+1
            i2 = i2+1
        elif(list1[i1]<list2[i2]):
            resultado.append(list1[i1])
            i1 = i1+1
        else:
            resultado.append(list2[i2])
            i2 = i2+1
    while i1<len(list1):
        resultado.append(list1[i1])
        i1 = i1+1
    while i2<len(list2):
        resultado.append(list2[i2])
        i2 = i2+1
    return resultado

def NOT(list1,allPosts):
    resultado = []
    i1 = 0; i2 = 0
    while i1<len(list1) and i2<len(allPosts):
        if(list1[i1]==allPosts[i2]):
            i1 = i1+1
            i2 = i2+1
        elif(allPosts[i2]<list1[i1]):
            resultado.append(allPosts[i2])
            i2 = i2+1
    while i2<len(allPosts):
        resultado.append(allPosts[i2])
        i2 = i2+1
    return resultado

if __name__ == '__main__':
    if len(sys.argv)<2:
        print('Not enough arguments. Correct usage: SAR_searcher.py file')
    archivo = load_object(sys.argv[1])
    postingList = archivo[0]
    docList = archivo[1]
    newsList = archivo[2]
    postingListTitulo = archivo[3]
    postingListCategoria = archivo[4]
    postingListFecha = archivo[5]
    
    while True:
        query = input('Consulta: ')
        if(len(query)>0):
            #query = clean_text(query).split()
            query = query.split()
            #print(query)
            queryOps = []
            postings = []
            resultado = []
            match_words = ['AND','OR','NOT']
            for term in query:
                if(term in match_words):
                    if (term!='AND'):
                        postings.append(term)
                else:
                    if(term.startswith('headline:')):
                        #Buscar en titulares
                        post = postingListTitulo.get(term.split(':')[1],[])
                    elif(term.startswith('category:')):
                        #Buscar en categorias
                        post = postingListCategoria.get(term.split(':')[1],[])
                    elif(term.startswith('date:')):
                        #Buscar en fechas
                        post = postingListFecha.get(term.split(':')[1],[])
                    else:
                        #Buscar en texto
                        if(term.startswith('text:')):
                            term=term.split(':')[1]
                        post = postingList.get(term,[])
                #if(len(post)>0):
                    postings.append(post)
            
            # Calcular la posting list resultado
            if(len(postings)>1):
                #Dos pasadas de izquierda a derecha para ir calculando los OR y los NOT
                #print(len(postings))
                for x in range(0,len(postings)-1):
                    if(postings[x]=='NOT'):
                        posts2 = NOT(postings[x+1],list(range(0,len(newsList))))
                        postings[x] = posts2
                        postings.remove(postings[x+1])
                #print(len(postings))
                
                while 1 <= (len(postings)-1):
                    if(postings[1]=='OR'):
                        postings[0] = OR(postings[0],postings[2])
                        del postings[1]
                        del postings[1]
                    else:
                        postings[0] = AND(postings[0],postings[1])
                        del postings[1]
#                for x in range(1,len(postings)-1):
#                    if(postings[x]=='OR'):
#                        prim = postings[x-1]; seg = postings[x+1]
#                        posts2 = OR(prim,seg)
#                        del postings[x-1]
#                        del postings[x-1]
#                        del postings[x-1]
#                        #postings.remove(prim)
#                        #postings.remove(seg)
#                        #postings.remove('OR')
#                        postings.append(posts2)
#                print(len(postings))
#                opNum = 0
#                aux = postings[0]
##                if(len(queryOps)==0):
#                for x in range(0,len(postings)-1):
#                    aux = AND(aux,postings[x+1])
#                else:
#                    for x in range(0,len(postings)-1):
#                        if(queryOps[opNum]=='AND'):
#                            if(opNum<len(queryOps)-1 and queryOps[opNum+1]=='NOT'):
#                                post2 = NOT(postings[x+1],list(range(1,len(newsList))))
#                                aux = AND(aux,post2)
#                                opNum = opNum+1
#                            else:
#                                aux = AND(aux,postings[x+1])
#                            opNum = opNum+1
#                        elif(queryOps[opNum]=='OR'):
#                            if(opNum<len(queryOps)-1 and queryOps[opNum+1]=='NOT'):
#                                post2 = NOT(postings[x+1],list(range(1,len(newsList))))
#                                aux = AND(aux,post2)
#                                opNum = opNum+1
#                            else:
#                                aux = AND(aux,postings[x+1])
#                            opNum = opNum+1
#                        else:
#                            aux = NOT(aux,list(range(1,len(newsList))))
#                            opNum = opNum+1
#                            x = x-1
                resultado=postings[0]
                #print(resultado)
            elif(len(postings)==0):
                print("Las palabras buscadas no se encuentran en la colección")
                continue
            else:
                #print(postings)
                resultado = postings[0]
            if(len(resultado)<=2):
                # Mostrar el titular y todo el cuerpo
                for i in range(0,len(resultado)):
                    newsDic = newsList[resultado[i]]
                    document = docList[newsDic[0]]
                    docText = open(document).read()
                    docs = docText.split('</DOC>\n<DOC>')
                    noticia = docs[newsDic[1]]
                    titulo = noticia[noticia.index('<TITLE>')+7:noticia.index('</TITLE>')]
                    noticia = noticia[noticia.index('<TEXT>')+6:noticia.index('</TEXT>')]
                    print(clean_text(titulo)+'\n'+noticia)
            elif(len(resultado)<=5):
                # Mostrar el titular y un snippet
                for i in range(0,len(resultado)):
                    newsDic = newsList[resultado[i]]
                    document = docList[newsDic[0]]
                    docText = open(document).read()
                    docs = docText.split('</DOC>\n<DOC>')
                    noticia = docs[newsDic[1]]
                    titulo = noticia[noticia.index('<TITLE>')+7:noticia.index('</TITLE>')]
                    noticia = noticia[noticia.index('<TEXT>')+6:noticia.index('</TEXT>')]
                    noticia2 = clean_text(noticia).split()
                    noticia3 = clean_text(noticia).lower().split()
                    print(clean_text(titulo))
                    # Sacar snippet de los terminos
                    positions=[]
                    for element in query:
                        positions.append(noticia3.index(element))
                    print('...',' '.join(noticia2[min(positions)-5:max(positions)+5]),'...\n')   
            else:
                # Mostrar el titular de las 10 primeras
                for i in range(0,min(10,len(resultado))):
                    auxRes = sorted(resultado)
                    newsDic = newsList[resultado[i]]
                    document = docList[newsDic[0]]
                    docText = open(document).read()
                    docs = docText.split('</DOC>\n<DOC>')
                    noticia = docs[newsDic[1]]
                    titulo = noticia[noticia.index('<TITLE>')+7:noticia.index('</TITLE>')]
                    print(clean_text(titulo),'\n')
            #Mostrar los ficheros y el numero de aciertos
            outputDocs=[]
            for i in range(0,len(resultado)):
                outputDocs.append(docList[newsList[resultado[i]][0]])
            print('Ficheros con noticias:\n'+'\n'.join(set(outputDocs)))
            print("Numero de noticias encotradas:",len(resultado))
        else:
            break
