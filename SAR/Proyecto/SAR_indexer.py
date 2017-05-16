#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Archivo: SAR_indexer.py
# Nombre: Josep Vicent Dols Dart

# Input Args:
#   colection - Colección de noticias a indexar
#   file - Nombre del archivo donde guardar el ínidice

import sys
import re
import pickle
from os import walk
from os import listdir

clean_re = re.compile('\W+')
def clean_text(text):
    return clean_re.sub(' ', text)

def save_object(object,file_name):
    with open(file_name,'wb') as fh:
        pickle.dump(object,fh)

if __name__ == '__main__':
    if len(sys.argv)<3:
        print('Not enough arguments. Correct usage: SAR_indexer.py colection file')
    lclPath = sys.argv[1]
    outFile = sys.argv[2]

    #for(dirpath,dirnames,filenames) in walk(lclPath):
    #    documents = filenames
    documents = listdir(lclPath)
    print("Nº documentos:",len(documents))
    postingList={}
    postingListTitulo={}
    postingListCategoria={}
    postingListFecha={}
    docList={}
    newsList={}
    docId=0
    newsId=0
    for document in documents:
        docList.setdefault(docId,lclPath+'/'+document)
        
        docText = open(lclPath+'/'+document).read()
        docs = docText.split('</DOC>\n<DOC>')
        internalPos = 0
        for noticia in docs:
            titulo = noticia[noticia.index('<TITLE>')+7:noticia.index('</TITLE>')]
            cleanTitulo = clean_text(titulo)
            categoria = noticia[noticia.index('<CATEGORY>')+10:noticia.index('</CATEGORY>')]
            cleanCategoria = clean_text(categoria)
            fecha = noticia[noticia.index('<DATE>')+6:noticia.index('</DATE>')]
            cleanFecha = clean_text(fecha)
            noticia = noticia[noticia.index('<TEXT>')+6:noticia.index('</TEXT>')]
            cleanNoticia = clean_text(noticia)
            
            for term in list(set(cleanNoticia.lower().split())):
                if term in postingList:
                    posts = postingList.get(term)
                    #if newsId not in posts:
                    posts.append(newsId)
                    postingList[term] = posts
                else:
                    postingList.setdefault(term,[newsId])
            
            for term in list(set(cleanTitulo.lower().split())):
                if term in postingListTitulo:
                    posts = postingListTitulo.get(term)
                    posts.append(newsId)
                    postingListTitulo[term] = posts
                else:
                    postingListTitulo.setdefault(term,[newsId])
            
            for term in list(set(cleanCategoria.lower().split())):
                if term in postingListCategoria:
                    posts = postingListCategoria.get(term)
                    posts.append(newsId)
                    postingListCategoria[term] = posts
                else:
                    postingListCategoria.setdefault(term,[newsId])
                    
            for term in list(set(cleanFecha.lower().split())):
                if term in postingListFecha:
                    posts = postingListFecha.get(term)
                    posts.append(newsId)
                    postingListFecha[term] = posts
                else:
                    postingListFecha.setdefault(term,[newsId])
            
            newsList.setdefault(newsId,[docId,internalPos])
            newsId = newsId+1
            internalPos = internalPos+1
        docId=docId+1
    #for element in postingList.keys():
    #    postingList[element]=list(set(postingList.get(element)))
    print("Nº noticias:",newsId)
    print("Nº palabras en texto:",len(postingList))
    print("Nº palabras en titulo:",len(postingListTitulo))
    print("Nº palabras en categoria:",len(postingListCategoria))
    print("Nº palabras en fecha:",len(postingListFecha))
    save_object((postingList,docList,newsList,postingListTitulo,postingListCategoria,postingListFecha),outFile)
