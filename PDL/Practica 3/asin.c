/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C

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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0



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




/* Copy the first part of user declarations.  */
#line 1 "./src/asin.y"

	#include <stdio.h>
	#include "header.h"
	#include "libtds.h"
	#include "libgci.h"

	extern int dvar;
	extern int yylineno;
	extern FILE *yyin;

	int numErrores;


/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif

#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 14 "./src/asin.y"
{
	char* ident;
	int cent;
	EXPR expr;
}
/* Line 193 of yacc.c.  */
#line 211 "asin.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 224 "asin.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int i)
#else
static int
YYID (i)
    int i;
#endif
{
  return i;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  41
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   226

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  49
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  36
/* YYNRULES -- Number of rules.  */
#define YYNRULES  79
/* YYNRULES -- Number of states.  */
#define YYNSTATES  135

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   303

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint8 yyprhs[] =
{
       0,     0,     3,     7,     9,    12,    14,    16,    20,    27,
      29,    31,    35,    37,    39,    41,    43,    44,    47,    50,
      52,    58,    64,    65,    66,    75,    76,    77,    86,    89,
      90,    91,    99,   100,   108,   110,   114,   121,   123,   127,
     129,   133,   135,   139,   141,   145,   147,   151,   153,   156,
     159,   163,   166,   171,   173,   175,   177,   179,   181,   183,
     185,   187,   189,   191,   193,   195,   197,   199,   201,   203,
     205,   207,   209,   211,   213,   215,   217,   219,   221,   223
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      50,     0,    -1,    11,    51,    12,    -1,    52,    -1,    51,
      52,    -1,    53,    -1,    55,    -1,    54,    37,     8,    -1,
      54,    37,     9,    36,    10,     8,    -1,    38,    -1,    39,
      -1,    11,    56,    12,    -1,    58,    -1,    65,    -1,    57,
      -1,    59,    -1,    -1,    56,    55,    -1,    69,     8,    -1,
       8,    -1,    40,     6,    37,     7,     8,    -1,    41,     6,
      69,     7,     8,    -1,    -1,    -1,    42,     6,    69,     7,
      60,    55,    61,    62,    -1,    -1,    -1,    43,     6,    69,
       7,    63,    55,    64,    62,    -1,    44,    55,    -1,    -1,
      -1,    45,    66,     6,    69,     7,    67,    55,    -1,    -1,
      46,    68,    55,    45,     6,    69,     7,    -1,    70,    -1,
      37,    77,    69,    -1,    37,     9,    69,    10,    77,    69,
      -1,    71,    -1,    70,    78,    71,    -1,    72,    -1,    71,
      79,    72,    -1,    73,    -1,    72,    80,    73,    -1,    74,
      -1,    73,    81,    74,    -1,    75,    -1,    74,    82,    75,
      -1,    76,    -1,    83,    75,    -1,    84,    37,    -1,     6,
      69,     7,    -1,    37,    84,    -1,    37,     9,    69,    10,
      -1,    37,    -1,    36,    -1,    47,    -1,    48,    -1,    13,
      -1,    14,    -1,    15,    -1,    16,    -1,    17,    -1,    18,
      -1,    19,    -1,    20,    -1,    21,    -1,    22,    -1,    23,
      -1,    24,    -1,    25,    -1,    26,    -1,    27,    -1,    28,
      -1,    29,    -1,    30,    -1,    31,    -1,    32,    -1,    33,
      -1,    34,    -1,    35,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    44,    44,    48,    49,    51,    52,    54,    62,    76,
      77,    80,    81,    82,    83,    84,    86,    87,    89,    90,
      92,   103,   111,   118,   111,   128,   135,   128,   143,   146,
     146,   146,   157,   157,   165,   168,   199,   234,   237,   256,
     259,   275,   278,   294,   297,   311,   314,   328,   331,   346,
     364,   367,   384,   405,   416,   421,   426,   432,   435,   438,
     441,   444,   448,   451,   456,   459,   464,   467,   470,   473,
     477,   480,   484,   487,   490,   494,   498,   502,   507,   510
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "SPACE_", "TAB_", "CRLF_", "OPENPAR_",
  "CLOSEPAR_", "COMMADOT_", "OPENCOR_", "CLOSECOR_", "OPENLLAV_",
  "CLOSELLAV_", "OPASIG_", "OPASIGADD_", "OPASIGSUB_", "OPASIGMUL_",
  "OPASIGDIV_", "LOGAND_", "LOGOR_", "EQEQ_", "EQDIFF_", "RELBIG_",
  "RELSMALL_", "RELBIGEQ_", "RELSMALLEQ_", "OPMAS_", "OPRES_", "OPMULT_",
  "OPDIV_", "OPRESTO_", "UNMAS_", "UNMIN_", "UNNOT_", "INCMAS_", "INCMIN_",
  "cte_", "id_", "int_", "bool_", "read_", "print_", "if_", "elseif_",
  "else_", "while_", "do_", "TRUE_", "FALSE_", "$accept", "programa",
  "secuenciaSentencias", "sentencia", "declaracion", "tipoSimple",
  "instruccion", "listaInstrucciones", "instruccionExpresion",
  "instruccionEntradaSalida", "instruccionSeleccion", "@1", "@2",
  "restoIf", "@3", "@4", "instruccionIteracion", "@5", "@6", "@7",
  "expresion", "expresionLogica", "expresionIgualdad",
  "expresionRelacional", "expresionAditiva", "expresionMultiplicativa",
  "expresionUnaria", "expresionSufija", "operadorAsignacion",
  "operadorLogico", "operadorIgualdad", "operadorRelacional",
  "operadorAditivo", "operadorMultiplicativo", "operadorUnario",
  "operadorIncremento", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    49,    50,    51,    51,    52,    52,    53,    53,    54,
      54,    55,    55,    55,    55,    55,    56,    56,    57,    57,
      58,    58,    60,    61,    59,    63,    64,    62,    62,    66,
      67,    65,    68,    65,    69,    69,    69,    70,    70,    71,
      71,    72,    72,    73,    73,    74,    74,    75,    75,    75,
      76,    76,    76,    76,    76,    76,    76,    77,    77,    77,
      77,    77,    78,    78,    79,    79,    80,    80,    80,    80,
      81,    81,    82,    82,    82,    83,    83,    83,    84,    84
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     3,     1,     2,     1,     1,     3,     6,     1,
       1,     3,     1,     1,     1,     1,     0,     2,     2,     1,
       5,     5,     0,     0,     8,     0,     0,     8,     2,     0,
       0,     7,     0,     7,     1,     3,     6,     1,     3,     1,
       3,     1,     3,     1,     3,     1,     3,     1,     2,     2,
       3,     2,     4,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,    19,    16,    75,    76,    77,    78,
      79,    54,    53,     9,    10,     0,     0,     0,    29,    32,
      55,    56,     0,     3,     5,     0,     6,    14,    12,    15,
      13,     0,    34,    37,    39,    41,    43,    45,    47,     0,
       0,     1,     0,     0,     0,    57,    58,    59,    60,    61,
       0,    51,     0,     0,     0,     0,     0,     2,     4,     0,
      18,    62,    63,     0,    64,    65,     0,    66,    67,    68,
      69,     0,    70,    71,     0,    72,    73,    74,     0,    53,
      48,    49,    50,    11,    17,     0,    35,     0,     0,     0,
       0,     0,     7,     0,    38,    40,    42,    44,    46,     0,
      52,     0,     0,    22,     0,     0,     0,     0,     0,    20,
      21,     0,    30,     0,     0,    52,    36,    23,     0,     0,
       8,     0,    31,    33,     0,     0,    24,     0,    28,     0,
      25,     0,    26,     0,    27
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,    22,    23,    24,    25,    26,    43,    27,    28,
      29,   111,   121,   126,   131,   133,    30,    55,   118,    56,
      31,    32,    33,    34,    35,    36,    37,    38,    50,    63,
      66,    71,    74,    78,    39,    40
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -43
static const yytype_int16 yypact[] =
{
      -6,    80,    42,   160,   -43,   -43,   -43,   -43,   -43,   -43,
     -43,   -43,    40,   -43,   -43,    14,    59,    60,   -43,   -43,
     -43,   -43,    -2,   -43,   -43,    11,   -43,   -43,   -43,   -43,
     -43,    63,   -11,     5,    -1,   -15,    30,   -43,   -43,   178,
      31,   -43,    65,   123,   160,   -43,   -43,   -43,   -43,   -43,
     160,   -43,    36,   160,   160,    61,   141,   -43,   -43,    53,
     -43,   -43,   -43,   178,   -43,   -43,   178,   -43,   -43,   -43,
     -43,   178,   -43,   -43,   178,   -43,   -43,   -43,   178,    -7,
     -43,   -43,   -43,   -43,   -43,    67,   -43,    71,    72,    73,
     160,    37,   -43,    45,     5,    -1,   -15,    30,   -43,   160,
       2,    76,    77,   -43,    83,    86,    84,    85,   160,   -43,
     -43,   141,   -43,   160,    89,   -43,   -43,   -43,   141,    91,
     -43,    20,   -43,   -43,    87,   141,   -43,   160,   -43,    92,
     -43,   141,   -43,    20,   -43
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int8 yypgoto[] =
{
     -43,   -43,   -43,    78,   -43,   -43,   -42,   -43,   -43,   -43,
     -43,   -43,   -43,   -32,   -43,   -43,   -43,   -43,   -43,   -43,
      -3,   -43,    39,    38,    32,    33,   -26,   -43,     6,   -43,
     -43,   -43,   -43,   -43,   -43,    -9
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint8 yytable[] =
{
      42,    84,    99,    51,     3,     1,     4,    61,    62,     5,
      57,    72,    73,    80,    91,    45,    46,    47,    48,    49,
      52,    67,    68,    69,    70,    64,    65,     9,    10,     6,
       7,     8,     9,    10,    11,    12,    13,    14,    15,    16,
      17,    85,    41,    18,    19,    20,    21,    86,    59,    44,
      88,    89,    98,    45,    46,    47,    48,    49,    75,    76,
      77,    92,    93,   124,   125,    53,    54,    90,    81,   117,
      51,    60,    82,    87,     9,    10,   122,   100,   101,   102,
     103,   106,   105,   128,   109,   110,     3,   104,     4,   132,
     112,     5,   113,   127,   114,   115,   107,   120,   123,   130,
      58,   134,    94,    96,    95,   116,   108,    97,     0,     0,
     119,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,     0,   129,    18,    19,    20,    21,     3,
       0,     4,     0,     0,     5,    83,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     3,     0,     4,
       0,     0,     5,     0,     6,     7,     8,     9,    10,    11,
      12,     0,     0,    15,    16,    17,     3,     0,    18,    19,
      20,    21,     6,     7,     8,     9,    10,    11,    12,     0,
       0,    15,    16,    17,     3,     0,    18,    19,    20,    21,
       0,     6,     7,     8,     9,    10,    11,    12,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    20,    21,     6,
       7,     8,     9,    10,    11,    79,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    20,    21
};

static const yytype_int16 yycheck[] =
{
       3,    43,     9,    12,     6,    11,     8,    18,    19,    11,
      12,    26,    27,    39,    56,    13,    14,    15,    16,    17,
       6,    22,    23,    24,    25,    20,    21,    34,    35,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    44,     0,    45,    46,    47,    48,    50,    37,     9,
      53,    54,    78,    13,    14,    15,    16,    17,    28,    29,
      30,     8,     9,    43,    44,     6,     6,     6,    37,   111,
      79,     8,     7,    37,    34,    35,   118,    10,     7,     7,
       7,    36,    45,   125,     8,     8,     6,    90,     8,   131,
       7,    11,     6,     6,    10,    10,    99,     8,     7,     7,
      22,   133,    63,    71,    66,   108,   100,    74,    -1,    -1,
     113,    31,    32,    33,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    -1,   127,    45,    46,    47,    48,     6,
      -1,     8,    -1,    -1,    11,    12,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,     6,    -1,     8,
      -1,    -1,    11,    -1,    31,    32,    33,    34,    35,    36,
      37,    -1,    -1,    40,    41,    42,     6,    -1,    45,    46,
      47,    48,    31,    32,    33,    34,    35,    36,    37,    -1,
      -1,    40,    41,    42,     6,    -1,    45,    46,    47,    48,
      -1,    31,    32,    33,    34,    35,    36,    37,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    47,    48,    31,
      32,    33,    34,    35,    36,    37,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    47,    48
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    11,    50,     6,     8,    11,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    45,    46,
      47,    48,    51,    52,    53,    54,    55,    57,    58,    59,
      65,    69,    70,    71,    72,    73,    74,    75,    76,    83,
      84,     0,    69,    56,     9,    13,    14,    15,    16,    17,
      77,    84,     6,     6,     6,    66,    68,    12,    52,    37,
       8,    18,    19,    78,    20,    21,    79,    22,    23,    24,
      25,    80,    26,    27,    81,    28,    29,    30,    82,    37,
      75,    37,     7,    12,    55,    69,    69,    37,    69,    69,
       6,    55,     8,     9,    71,    72,    73,    74,    75,     9,
      10,     7,     7,     7,    69,    45,    36,    69,    77,     8,
       8,    60,     7,     6,    10,    10,    69,    55,    67,    69,
       8,    61,    55,     7,    43,    44,    62,     6,    55,    69,
       7,    63,    55,    64,    62
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *bottom, yytype_int16 *top)
#else
static void
yy_stack_print (bottom, top)
    yytype_int16 *bottom;
    yytype_int16 *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      fprintf (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      fprintf (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The look-ahead symbol.  */
int yychar;

/* The semantic value of the look-ahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  int yystate;
  int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Look-ahead token as an internal (translated) token number.  */
  int yytoken = 0;
#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  yytype_int16 yyssa[YYINITDEPTH];
  yytype_int16 *yyss = yyssa;
  yytype_int16 *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  YYSTYPE *yyvsp;



#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     look-ahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to look-ahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a look-ahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid look-ahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the look-ahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 44 "./src/asin.y"
    {
															emite(FIN,crArgNul(),crArgNul(),crArgNul());
														;}
    break;

  case 7:
#line 54 "./src/asin.y"
    {	
														    if(!insertarTDS((yyvsp[(2) - (3)].ident), (yyvsp[(1) - (3)].expr).tipo, dvar, 1)){
														        yyerror("Identificador repetido");
														    }else{
														        dvar+=TALLA_TIPO_SIMPLE;
														    }
														    mostrarTDS();
                                                        ;}
    break;

  case 8:
#line 62 "./src/asin.y"
    { 	int numelem = (yyvsp[(4) - (6)].cent); int refe;
															if((yyvsp[(4) - (6)].cent)<=0){
																yyerror("Talla inapropiada del array");
																numelem = 0;
															}
															refe = insertaTDArray((yyvsp[(1) - (6)].expr).tipo,numelem);
															if(!insertarTDS((yyvsp[(2) - (6)].ident), T_ARRAY, dvar, refe)){
																yyerror("Identificador repetido");
															}else{
																dvar+=numelem * TALLA_TIPO_SIMPLE;
															}
															mostrarTDS();
														;}
    break;

  case 9:
#line 76 "./src/asin.y"
    {(yyval.expr).tipo = T_ENTERO;;}
    break;

  case 10:
#line 77 "./src/asin.y"
    {(yyval.expr).tipo = T_LOGICO;;}
    break;

  case 20:
#line 92 "./src/asin.y"
    {
																		SIMB sim = obtenerTDS((yyvsp[(3) - (5)].ident));
																		if(sim.tipo == T_ERROR){
																			yyerror("Objeto no declarado");
																		}else{
																			if(sim.tipo != T_ENTERO){
																				yyerror("El argumento debe de ser entero");
																			}
																		}
																		emite(EREAD,crArgNul(),crArgNul(),crArgPos(sim.desp));
																	;}
    break;

  case 21:
#line 103 "./src/asin.y"
    {
																		if((yyvsp[(3) - (5)].expr).tipo != T_ENTERO){
																			yyerror("La expresion del print debe de ser entera");
																		}
																		emite(EWRITE,crArgNul(),crArgNul(),crArgPos((yyvsp[(3) - (5)].expr).pos));
																	;}
    break;

  case 22:
#line 111 "./src/asin.y"
    {
															if((yyvsp[(3) - (4)].expr).tipo != T_ERROR && (yyvsp[(3) - (4)].expr).tipo != T_LOGICO){
																yyerror("La expresion del \"if\" debe de ser logica");
															}
															(yyval.cent) = creaLans(si);
															emite(EIGUAL,crArgPos((yyvsp[(3) - (4)].expr).pos),crArgEnt(0),crArgNul());
														;}
    break;

  case 23:
#line 118 "./src/asin.y"
    {
																		(yyval.cent) = creaLans(si);
																		emite(GOTOS,crArgNul(),crArgNul(),crArgNul());
																		completaLans((yyvsp[(5) - (6)].cent),crArgEtq(si));
																	;}
    break;

  case 24:
#line 123 "./src/asin.y"
    {
																	completaLans((yyvsp[(7) - (8)].cent),crArgEtq(si));
																;}
    break;

  case 25:
#line 128 "./src/asin.y"
    {
													if((yyvsp[(3) - (4)].expr).tipo != T_ERROR && (yyvsp[(3) - (4)].expr).tipo != T_LOGICO){
														yyerror("La expresion del \"else-if\" debe de ser logica");
													}
													(yyval.cent) = creaLans(si);
													emite(EIGUAL,crArgPos((yyvsp[(3) - (4)].expr).pos),crArgEnt(0),crArgNul());
												;}
    break;

  case 26:
#line 135 "./src/asin.y"
    {
																(yyval.cent) = creaLans(si);
																emite(GOTOS,crArgNul(),crArgNul(),crArgNul());
																completaLans((yyvsp[(5) - (6)].cent),crArgEtq(si));
															;}
    break;

  case 27:
#line 140 "./src/asin.y"
    {
															completaLans((yyvsp[(7) - (8)].cent),crArgEtq(si));
														;}
    break;

  case 29:
#line 146 "./src/asin.y"
    {(yyval.cent) = si; ;}
    break;

  case 30:
#line 146 "./src/asin.y"
    {
																			if((yyvsp[(4) - (5)].expr).tipo != T_LOGICO){
																				yyerror("La expresion del \"while\" debe ser logica");
																			}
																			(yyval.cent) = creaLans(si);
																			emite(EIGUAL,crArgPos((yyvsp[(4) - (5)].expr).pos),crArgEnt(0),crArgNul());
																		;}
    break;

  case 31:
#line 153 "./src/asin.y"
    {
																			emite(GOTOS,crArgNul(),crArgNul(),crArgEtq((yyvsp[(2) - (7)].cent)));
																			completaLans((yyvsp[(6) - (7)].cent),crArgEtq(si));
																		;}
    break;

  case 32:
#line 157 "./src/asin.y"
    {(yyval.cent) = si; ;}
    break;

  case 33:
#line 157 "./src/asin.y"
    {
																					if((yyvsp[(6) - (7)].expr).tipo != T_LOGICO){
																						yyerror("La expresion del \"do-while\" debe ser logica");
																					}
																					emite(EDIST,crArgPos((yyvsp[(6) - (7)].expr).pos),crArgEnt(0),crArgEtq((yyvsp[(2) - (7)].cent)));
																				;}
    break;

  case 34:
#line 165 "./src/asin.y"
    {
																			(yyval.expr) = (yyvsp[(1) - (1)].expr);
																		;}
    break;

  case 35:
#line 168 "./src/asin.y"
    {	
																			char aux[1024];
																			SIMB sim = obtenerTDS((yyvsp[(1) - (3)].ident)); (yyval.expr).tipo = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{ 
																				if((yyvsp[(3) - (3)].expr).tipo != T_ERROR){
																					if(!((sim.tipo == (yyvsp[(3) - (3)].expr).tipo)&&((yyvsp[(3) - (3)].expr).tipo == T_ENTERO || (yyvsp[(3) - (3)].expr).tipo == T_LOGICO))){
																						sprintf(aux,"El identificador tiene que ser de tipo %d", sim.tipo);
																						yyerror(aux);
																					}else{
																						(yyval.expr).tipo = sim.tipo;
																						(yyval.expr).pos = creaVarTemp();
																						if((yyvsp[(2) - (3)].cent) == EASIG){
																							emite((yyvsp[(2) - (3)].cent),crArgPos((yyvsp[(3) - (3)].expr).pos),crArgNul(),crArgPos(sim.desp));
																						}else{
																							emite((yyvsp[(2) - (3)].cent),crArgPos(sim.desp),crArgNul(),crArgPos((yyval.expr).pos));
																						}
																						emite(EASIG,crArgPos(sim.desp),crArgNul(),crArgPos((yyval.expr).pos));
																					}
																				}
																			}
																			/*** Expresion a partir de una asignacion ***/
																			/*
																			if($2 == EASIG){
																				emite($2, crArgPos(sim.desp), crArgNul(), crArgPos($$.pos));
																			}else{
																				emite($2, crArgPos(sim.desp), crArgPos($3.pos), crArgPos($$.pos));
																			}*/
																			
																		;}
    break;

  case 36:
#line 199 "./src/asin.y"
    {
																			SIMB sim = obtenerTDS((yyvsp[(1) - (6)].ident)); (yyval.expr).tipo = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{
																				if((yyvsp[(6) - (6)].expr).tipo != T_ERROR){
																					if(sim.tipo != T_ARRAY){
																						yyerror("El identificador tiene que ser de tipo Array");
																					}else{
																						DIM dim = obtenerInfoArray(sim.ref);
																						if(!((dim.telem == (yyvsp[(6) - (6)].expr).tipo)&&((yyvsp[(6) - (6)].expr).tipo == T_ENTERO || (yyvsp[(6) - (6)].expr).tipo == T_LOGICO))){
																							yyerror("Error de tipos en la asignación");
																						}else{ 
																							if((yyvsp[(3) - (6)].expr).tipo != T_ENTERO){
																								yyerror("El indice del array tiene que ser un entero");
																							}else{
																								(yyval.expr).tipo = sim.tipo;
																								(yyval.expr).pos = creaVarTemp();
																								if((yyvsp[(5) - (6)].cent) == EASIG){
																									emite(EVA, crArgPos(sim.desp), crArgPos((yyvsp[(3) - (6)].expr).pos), crArgPos((yyvsp[(6) - (6)].expr).pos));
																								}else{
																									int tempVar = creaVarTemp();
																									emite(EAV,crArgPos(sim.desp),crArgPos((yyvsp[(3) - (6)].expr).pos),crArgPos(tempVar));
																									emite((yyvsp[(5) - (6)].cent),crArgPos(tempVar),crArgPos((yyvsp[(6) - (6)].expr).pos),crArgPos(tempVar));
																									emite(EVA, crArgPos(sim.desp), crArgPos((yyvsp[(3) - (6)].expr).pos), crArgPos(tempVar));
																								}
																								emite(EAV,crArgPos(sim.desp),crArgPos((yyvsp[(3) - (6)].expr).pos),crArgPos((yyval.expr).pos));
																							}
																						}
																					}
																				}
																			}
																		;}
    break;

  case 37:
#line 234 "./src/asin.y"
    {
																(yyval.expr) = (yyvsp[(1) - (1)].expr);
															;}
    break;

  case 38:
#line 237 "./src/asin.y"
    {
																char aux[1024];
																(yyval.expr).tipo = T_ERROR;
																if((yyvsp[(1) - (3)].expr).tipo != T_LOGICO || (yyvsp[(3) - (3)].expr).tipo != T_LOGICO){
																	sprintf(aux,"Intentado operacion logica entre tipos %d y %d",(yyvsp[(1) - (3)].expr).tipo,(yyvsp[(3) - (3)].expr).tipo);
																	yyerror(aux);
																}else{
																	(yyval.expr).tipo = T_LOGICO;
																	(yyval.expr).pos = creaVarTemp();
																	int varTemp = creaVarTemp();

																	emite((yyvsp[(2) - (3)].cent),crArgPos((yyvsp[(1) - (3)].expr).pos),crArgPos((yyvsp[(3) - (3)].expr).pos),crArgPos(varTemp));
                                    								emite(EASIG,crArgEnt(1),crArgNul(),crArgPos((yyval.expr).pos));
                                    								emite(EMAYEQ,crArgPos(varTemp),crArgEnt(1),crArgEtq(si+2));
                                    								emite(EASIG,crArgEnt(0),crArgNul(),crArgPos((yyval.expr).pos));
																}
															;}
    break;

  case 39:
#line 256 "./src/asin.y"
    {
																	(yyval.expr) = (yyvsp[(1) - (1)].expr);	
																;}
    break;

  case 40:
#line 259 "./src/asin.y"
    {
																	char aux[1024];
																	(yyval.expr).tipo = T_ERROR;
																	if((yyvsp[(1) - (3)].expr).tipo==(yyvsp[(3) - (3)].expr).tipo){
																		(yyval.expr).tipo = T_LOGICO;
																	}else{
																		sprintf(aux,"Intentando operacion de igualdad entre tipos %d y %d",(yyvsp[(1) - (3)].expr).tipo,(yyvsp[(3) - (3)].expr).tipo);
																		yyerror(aux);
																	}
																	(yyval.expr).pos = creaVarTemp();
                                 									emite(EASIG,crArgEnt(1),crArgNul(),crArgPos((yyval.expr).pos));
                                 									emite((yyvsp[(2) - (3)].cent),crArgPos((yyvsp[(1) - (3)].expr).pos),crArgPos((yyvsp[(3) - (3)].expr).pos),crArgEtq(si+2));
                                 									emite(EASIG,crArgEnt(0),crArgNul(),crArgPos((yyval.expr).pos));
																;}
    break;

  case 41:
#line 275 "./src/asin.y"
    {
																	(yyval.expr) = (yyvsp[(1) - (1)].expr);	
																;}
    break;

  case 42:
#line 278 "./src/asin.y"
    {
																	char aux[1024];
																	(yyval.expr).tipo = T_ERROR;
																	if((yyvsp[(1) - (3)].expr).tipo != T_ENTERO || (yyvsp[(3) - (3)].expr).tipo != T_ENTERO){
																		sprintf(aux,"Intentando operacion relacional entre tipos %d y %d",(yyvsp[(1) - (3)].expr).tipo,(yyvsp[(3) - (3)].expr).tipo);
																		yyerror(aux);
																	}else{
																		(yyval.expr).tipo = T_LOGICO;

																		(yyval.expr).pos = creaVarTemp();
                                    									emite(EASIG,crArgEnt(1),crArgNul(),crArgPos((yyval.expr).pos));
                                    									emite((yyvsp[(2) - (3)].cent),crArgPos((yyvsp[(1) - (3)].expr).pos),crArgPos((yyvsp[(3) - (3)].expr).pos),crArgEtq(si+2));
                                    									emite(EASIG,crArgEnt(0),crArgNul(),crArgPos((yyval.expr).pos));
																	}
																;}
    break;

  case 43:
#line 294 "./src/asin.y"
    {
																	(yyval.expr) = (yyvsp[(1) - (1)].expr);	
																;}
    break;

  case 44:
#line 297 "./src/asin.y"
    {
																	char aux[1024];
																	(yyval.expr).tipo = T_ERROR;
																	if((yyvsp[(1) - (3)].expr).tipo != T_ENTERO || (yyvsp[(3) - (3)].expr).tipo != T_ENTERO){
																		sprintf(aux,"Intentando operacion aditiva entre tipos %d y %d",(yyvsp[(1) - (3)].expr).tipo,(yyvsp[(3) - (3)].expr).tipo);
																		yyerror(aux);
																	}else{
																		(yyval.expr).tipo = T_ENTERO;
																	}
																	(yyval.expr).pos = creaVarTemp();
																	/*** Expresion a partir de un operador aritmetico ***/
																	emite((yyvsp[(2) - (3)].cent), crArgPos((yyvsp[(1) - (3)].expr).pos), crArgPos((yyvsp[(3) - (3)].expr).pos), crArgPos((yyval.expr).pos));
																;}
    break;

  case 45:
#line 311 "./src/asin.y"
    {
																			(yyval.expr) = (yyvsp[(1) - (1)].expr);	
																		;}
    break;

  case 46:
#line 314 "./src/asin.y"
    {
																			char aux[1024];
																			(yyval.expr).tipo = T_ERROR;
																			if((yyvsp[(1) - (3)].expr).tipo != T_ENTERO || (yyvsp[(3) - (3)].expr).tipo != T_ENTERO){
																				sprintf(aux,"Intentando operacion multiplicativa entre tipos %d y %d",(yyvsp[(1) - (3)].expr).tipo,(yyvsp[(3) - (3)].expr).tipo);
																				yyerror(aux);
																			}else{
																				(yyval.expr).tipo = T_ENTERO;
																			}
																			(yyval.expr).pos = creaVarTemp();
																			/*** Expresion a partir de un operador aritmetico ***/
																			emite((yyvsp[(2) - (3)].cent), crArgPos((yyvsp[(1) - (3)].expr).pos), crArgPos((yyvsp[(3) - (3)].expr).pos), crArgPos((yyval.expr).pos));
																		;}
    break;

  case 47:
#line 328 "./src/asin.y"
    {
											(yyval.expr) = (yyvsp[(1) - (1)].expr);
										;}
    break;

  case 48:
#line 331 "./src/asin.y"
    {
											char aux[1024];
											(yyval.expr).tipo = T_ERROR;
											if((yyvsp[(2) - (2)].expr).tipo != T_LOGICO){
												sprintf(aux,"Intentando operacion unaria entre tipos %d y %d",(yyvsp[(1) - (2)].cent),(yyvsp[(2) - (2)].expr).tipo);
												yyerror(aux);
											}else{
												(yyval.expr).tipo = T_LOGICO;

												(yyval.expr).pos = creaVarTemp(); //$2.pos;
                                    			emite(EASIG,crArgEnt(0),crArgNul(),crArgPos((yyval.expr).pos));
                                    			emite(EDIST,crArgPos((yyvsp[(2) - (2)].expr).pos),crArgEnt(0),crArgEtq(si+2));
                                    			emite(EASIG,crArgEnt(1),crArgNul(),crArgPos((yyval.expr).pos));
											}
										;}
    break;

  case 49:
#line 346 "./src/asin.y"
    {
											(yyval.expr).tipo = T_ERROR;
											SIMB sim = obtenerTDS((yyvsp[(2) - (2)].ident));
											if(sim.tipo == T_ERROR){
												yyerror("Objeto no declarado");
											}else{
												if(sim.tipo != T_ENTERO){
													yyerror("Error en expresion unaria. Se esparaba un entero");
												}else{
													(yyval.expr).tipo = T_ENTERO;

													(yyval.expr).pos = creaVarTemp();
                                       				emite((yyvsp[(1) - (2)].cent), crArgPos(sim.desp) ,crArgEnt(1) ,crArgPos(sim.desp) );
                                       				emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos((yyval.expr).pos) );
												}
											}
										;}
    break;

  case 50:
#line 364 "./src/asin.y"
    {
													(yyval.expr) = (yyvsp[(2) - (3)].expr);		
												;}
    break;

  case 51:
#line 367 "./src/asin.y"
    {
													(yyval.expr).tipo = T_ERROR;
													SIMB sim = obtenerTDS((yyvsp[(1) - (2)].ident));
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ENTERO){
															yyerror("Error en expresion unaria. Se esparaba un entero");
														}else{
															(yyval.expr).tipo = T_ENTERO;

															(yyval.expr).pos = creaVarTemp();
                                       						emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos((yyval.expr).pos) ); // first return id
                                       						emite((yyvsp[(2) - (2)].cent), crArgPos(sim.desp) ,crArgEnt(1) ,crArgPos(sim.desp) );  // then id += id + 1 (id++)
														}
													}
												;}
    break;

  case 52:
#line 384 "./src/asin.y"
    {
													(yyval.expr).tipo = T_ERROR;
													SIMB sim = obtenerTDS((yyvsp[(1) - (4)].ident));
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ARRAY){
															yyerror("Se esperaba un identificador de tipo Array");
														}else{
															if((yyvsp[(3) - (4)].expr).tipo != T_ENTERO){
																yyerror("El indice del array tiene que ser positivo");
															}else{
																DIM dim = obtenerInfoArray(sim.ref);
																(yyval.expr).tipo = dim.telem;

																(yyval.expr).pos = creaVarTemp();
                                          						emite(EAV,crArgPos(sim.desp),crArgPos((yyvsp[(3) - (4)].expr).pos),crArgPos((yyval.expr).pos));
															}
														}
													}
												;}
    break;

  case 53:
#line 405 "./src/asin.y"
    {
													(yyval.expr).tipo = T_ERROR;
													SIMB sim = obtenerTDS((yyvsp[(1) - (1)].ident));
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														(yyval.expr).tipo = sim.tipo;
														(yyval.expr).pos = creaVarTemp();
                                    					emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos((yyval.expr).pos) );
													}
												;}
    break;

  case 54:
#line 416 "./src/asin.y"
    {
													(yyval.expr).tipo = T_ENTERO;
													(yyval.expr).pos = creaVarTemp();
													emite(EASIG, crArgEnt((yyvsp[(1) - (1)].cent)) ,crArgNul() , crArgPos((yyval.expr).pos) );
												;}
    break;

  case 55:
#line 421 "./src/asin.y"
    {
													(yyval.expr).tipo = T_LOGICO;
													(yyval.expr).pos = creaVarTemp();
													emite(EASIG, crArgEnt(1) ,crArgNul() ,crArgPos((yyval.expr).pos) );
												;}
    break;

  case 56:
#line 426 "./src/asin.y"
    {
													(yyval.expr).tipo = T_LOGICO;
													(yyval.expr).pos = creaVarTemp();
                                 					emite(EASIG, crArgEnt(0) ,crArgNul() ,crArgPos((yyval.expr).pos) );
												;}
    break;

  case 57:
#line 432 "./src/asin.y"
    {
									(yyval.cent) = EASIG;
								;}
    break;

  case 58:
#line 435 "./src/asin.y"
    {
									(yyval.cent) = ESUM;
								;}
    break;

  case 59:
#line 438 "./src/asin.y"
    {
									(yyval.cent) = EDIF;
								;}
    break;

  case 60:
#line 441 "./src/asin.y"
    {
									(yyval.cent) = EMULT;
								;}
    break;

  case 61:
#line 444 "./src/asin.y"
    {
									(yyval.cent) = EDIVI;
								;}
    break;

  case 62:
#line 448 "./src/asin.y"
    {
									(yyval.cent) = EMULT;
								;}
    break;

  case 63:
#line 451 "./src/asin.y"
    {	
									(yyval.cent) = ESUM;
								;}
    break;

  case 64:
#line 456 "./src/asin.y"
    {
									(yyval.cent) = EIGUAL;
								;}
    break;

  case 65:
#line 459 "./src/asin.y"
    {	
									(yyval.cent) = EDIST;
								;}
    break;

  case 66:
#line 464 "./src/asin.y"
    {
									(yyval.cent) = EMAY;
								;}
    break;

  case 67:
#line 467 "./src/asin.y"
    {
									(yyval.cent) = EMEN;
								;}
    break;

  case 68:
#line 470 "./src/asin.y"
    {
									(yyval.cent) = EMAYEQ;
								;}
    break;

  case 69:
#line 473 "./src/asin.y"
    {	
									(yyval.cent) = EMENEQ;
								;}
    break;

  case 70:
#line 477 "./src/asin.y"
    {
							(yyval.cent) = ESUM;
						;}
    break;

  case 71:
#line 480 "./src/asin.y"
    {
							(yyval.cent) = EDIF;
						;}
    break;

  case 72:
#line 484 "./src/asin.y"
    {
									(yyval.cent) = EMULT;
								;}
    break;

  case 73:
#line 487 "./src/asin.y"
    {
									(yyval.cent) = EDIVI;
								;}
    break;

  case 74:
#line 490 "./src/asin.y"
    {
									(yyval.cent) = RESTO;
								;}
    break;

  case 75:
#line 494 "./src/asin.y"
    {
							//$$.tipo = T_ENTERO;
							(yyval.cent) = ESUM;	
						;}
    break;

  case 76:
#line 498 "./src/asin.y"
    {
							//$$.tipo = T_ENTERO;
							(yyval.cent) = EDIF;
						;}
    break;

  case 77:
#line 502 "./src/asin.y"
    {
							//$$.tipo = T_LOGICO;
							(yyval.cent) = ESIG;
						;}
    break;

  case 78:
#line 507 "./src/asin.y"
    {
									(yyval.cent) = ESUM;
								;}
    break;

  case 79:
#line 510 "./src/asin.y"
    {
									(yyval.cent) = EDIF;
								;}
    break;


/* Line 1267 of yacc.c.  */
#line 2243 "asin.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse look-ahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse look-ahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEOF && yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


#line 516 "./src/asin.y"


