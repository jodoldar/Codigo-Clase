/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     SPACE_ = 258,
     TAB_ = 259,
     CRLF_ = 260,
     OPENPAR_ = 261,
     CLOSEPAR_ = 262,
     COMMADOT_ = 263,
     OPENCOR_ = 264,
     CLOSECOR_ = 265,
     OPENLLAV_ = 266,
     CLOSELLAV_ = 267,
     OPASIG_ = 268,
     OPASIGADD_ = 269,
     OPASIGSUB_ = 270,
     OPASIGMUL_ = 271,
     OPASIGDIV_ = 272,
     LOGAND_ = 273,
     LOGOR_ = 274,
     EQEQ_ = 275,
     EQDIFF_ = 276,
     RELBIG_ = 277,
     RELSMALL_ = 278,
     RELBIGEQ_ = 279,
     RELSMALLEQ_ = 280,
     OPMAS_ = 281,
     OPRES_ = 282,
     OPMULT_ = 283,
     OPDIV_ = 284,
     OPRESTO_ = 285,
     UNMAS_ = 286,
     UNMIN_ = 287,
     UNNOT_ = 288,
     INCMAS_ = 289,
     INCMIN_ = 290,
     cte_ = 291,
     id_ = 292,
     int_ = 293,
     bool_ = 294,
     read_ = 295,
     print_ = 296,
     if_ = 297,
     elseif_ = 298,
     else_ = 299,
     while_ = 300,
     do_ = 301,
     TRUE_ = 302,
     FALSE_ = 303
   };
#endif
/* Tokens.  */
#define SPACE_ 258
#define TAB_ 259
#define CRLF_ 260
#define OPENPAR_ 261
#define CLOSEPAR_ 262
#define COMMADOT_ 263
#define OPENCOR_ 264
#define CLOSECOR_ 265
#define OPENLLAV_ 266
#define CLOSELLAV_ 267
#define OPASIG_ 268
#define OPASIGADD_ 269
#define OPASIGSUB_ 270
#define OPASIGMUL_ 271
#define OPASIGDIV_ 272
#define LOGAND_ 273
#define LOGOR_ 274
#define EQEQ_ 275
#define EQDIFF_ 276
#define RELBIG_ 277
#define RELSMALL_ 278
#define RELBIGEQ_ 279
#define RELSMALLEQ_ 280
#define OPMAS_ 281
#define OPRES_ 282
#define OPMULT_ 283
#define OPDIV_ 284
#define OPRESTO_ 285
#define UNMAS_ 286
#define UNMIN_ 287
#define UNNOT_ 288
#define INCMAS_ 289
#define INCMIN_ 290
#define cte_ 291
#define id_ 292
#define int_ 293
#define bool_ 294
#define read_ 295
#define print_ 296
#define if_ 297
#define elseif_ 298
#define else_ 299
#define while_ 300
#define do_ 301
#define TRUE_ 302
#define FALSE_ 303




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

