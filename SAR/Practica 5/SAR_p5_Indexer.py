#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
from whoosh.index import create_in
from whoosh.fields import Schema, ID, TEXT
from os import walk

nombres=[]
for (path,ficheros,archivos) in walk('./enero'):
    nombres=archivos
print(nombres)

schema = Schema(title=TEXT(stored=True), path=ID(stored=True), content=TEXT)
idir = "./indice"

if not os.path.exists(idir):
    os.mkdir(idir)
ix = create_in(idir, schema)

writer = ix.writer()
for nombre in nombres:
    writer.add_document(title=nombre, path=idir,
                        content=open('./enero/'+nombre).read())
writer.commit()



