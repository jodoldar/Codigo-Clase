#! -*- encoding: utf-8 -*-
# inspired by Lluís Ulzurrun and Víctor Grau work
# Nombre: Josep Vicent Dols Dart

from operator import itemgetter
import re
import sys

clean_re = re.compile('\W+')
def clean_text(text):
    return clean_re.sub(' ', text)

def sort_dic(d):
    for key, value in sorted(sorted(d.items()), key=itemgetter(1), reverse=True):
        yield key, value

def text_statistics(filename, to_lower=True, remove_stopwords=True):
    # COMPLETAR
    word_dic = {}; biword_dic = {}
    symbol_dic = {}; bisymbol_dic = {}
    stopwords = ""
    num_words=0;num_words_filtered=0;num_chars=0
    f = open(filename,"r")
    if remove_stopwords:
        f_stop = open("stopwords_en.txt","r")
        stopwords = f_stop.read()
    text_lines = f.readlines()
    for line in text_lines:
        # Para cada linea
        if to_lower:
            line = line.lower() 
        word_file = clean_text(line)
        word_file = word_file.split();
        # Separacion por palabras
        for word in word_file:
            # Para cada palabra
            if remove_stopwords & (word in stopwords):
                num_words = num_words+1
            else:
                if word in word_dic:
                    rep = word_dic.get(word)
                    word_dic[word]=rep+1
                    num_words_filtered=num_words_filtered+1
                    num_words = num_words+1
                else:
                    word_dic.setdefault(word,1)
                    num_words_filtered=num_words_filtered+1
                    num_words = num_words+1
                for letter in word:
                    # Para cada caracter
                    if letter in symbol_dic:
                        rep_l = symbol_dic.get(letter)
                        symbol_dic[letter] = rep_l+1
                        num_chars = num_chars+1
                    else:
                        symbol_dic.setdefault(letter,1)
                        num_chars = num_chars+1
                # Para cada par de caracteres
                lcl_word = word
                for j in range(0,len(lcl_word)-1):
                    aux_char = lcl_word[j]+lcl_word[j+1]
                    if aux_char in bisymbol_dic:
                        rep = bisymbol_dic.get(aux_char)
                        bisymbol_dic[aux_char] = rep+1
                    else:
                        bisymbol_dic.setdefault(aux_char,1)
        # Separacion por bigramas AMPLIACION
        for i in range(0,len(word_file)):
            # Si es inicio de palabra
            if i==0:
                aux_word = "$ "+word_file[i]
                if aux_word in biword_dic:
                    rep = biword_dic.get(aux_word)
                    biword_dic[aux_word]=rep+1
                else:
                    biword_dic.setdefault(aux_word,1)
            # Si es al final de palabra
            elif i==len(word_file)-1:
                aux_word2 = word_file[i-1]+" "+word_file[i]
                aux_word = word_file[i]+" $"
                if aux_word in biword_dic:
                    rep = biword_dic.get(aux_word)
                    biword_dic[aux_word]=rep+1
                else:
                    biword_dic.setdefault(aux_word,1)
                if aux_word2 in biword_dic:
                    rep = biword_dic.get(aux_word2)
                    biword_dic[aux_word2]=rep+1
                else:
                    biword_dic.setdefault(aux_word2,1)
            # Si es en medio de la palabra
            else:
                aux_word = word_file[i-1]+" "+word_file[i]
                if aux_word in biword_dic:
                    rep = biword_dic.get(aux_word)
                    biword_dic[aux_word]=rep+1
                else:
                    biword_dic.setdefault(aux_word,1)
            
                    
    print("Lines:",len(text_lines))
    print("Number words (with stopwords):",num_words)   
    if remove_stopwords:
        print("Number words (without stopwords):",num_words_filtered)
    print("Vocabulary size:",len(word_dic.keys()))  
    print("Number of symbols:",num_chars)
    print("Number of different symbols:",len(symbol_dic.keys()))         
    print("Words (alphabetical order):")
    for x,y in sorted(word_dic.items()):
        print("\t"+str(x)+"\t"+str(y))
    print("Words (by frequency):")
    for x,y in sort_dic(word_dic):
        print("\t"+str(x)+"\t"+str(y))
    print("Symbols (alphabetical order):")
    for x,y in sorted(symbol_dic.items()):
        print("\t"+str(x)+"\t"+str(y))
    print("Symbols (by frequency):")
    for x,y in sort_dic(symbol_dic):
        print("\t"+str(x)+"\t"+str(y))
    print("Word pairs (alphabetical order):")
    for x,y in sorted(biword_dic.items()):
        print("\t"+str(x)+"\t"+str(y))
    print("Word pairs (by frequency):")
    for x,y in sort_dic(biword_dic):
        print("\t"+str(x)+"\t"+str(y))
    print("Symbol pairs (alphabetical order):")
    for x,y in sorted(bisymbol_dic.items()):
        print("\t"+str(x)+"\t"+str(y))
    print("Symbol pairs (by frequency):")
    for x,y in sort_dic(bisymbol_dic):
        print("\t"+str(x)+"\t"+str(y))
        
def syntax():
    print ("\n%s filename.txt [to_lower?[remove_stopwords?]\n" % sys.argv[0])
    sys.exit()

if __name__ == "__main__":
    if len(sys.argv) < 2:
        syntax()
    name = sys.argv[1]
    lower = False
    stop = False
    if len(sys.argv) > 2:
        lower = (sys.argv[2] in ('1', 'True', 'yes'))
        if len(sys.argv) > 3:
            stop = (sys.argv[3] in ('1', 'True', 'yes'))
    text_statistics(name, to_lower=lower, remove_stopwords=stop)
