%{
#include <stdio.h>
#include <asin.h>
#include "header.h"

extern FILE *yyin;
extern int verbosidad;

void visualizar(char* texto);
%}

%option yylineno
delimiter   [ \t\n]+ 
char_minus  [a-z]
char_Mayus  [A-Z]
num         [0-9]
char        ({char_minus}|{char_Mayus})        
id          {char}("_"|{char}|{num})*
int_cte     {num}+
real_cte    {int_cte}"."{int_cte}(("e"|"E"){int_cte})*
cte         ({int_cte}|{real_cte})
comment     "//".*

%%

{comment}         {}
{delimiter}       {}
int               {if(verbosidad)visualizar("int"); return(int_);}
bool              {if(verbosidad)visualizar("bool"); return(bool_);}
if                {if(verbosidad)visualizar("if"); return(if_);}
else              {if(verbosidad)visualizar("else"); return(else_);}
elseif            {if(verbosidad)visualizar("elseif"); return(elseif_);}
while             {if(verbosidad)visualizar("while"); return(while_);}
do                {if(verbosidad)visualizar("do"); return(do_);}
"{"               {if(verbosidad)visualizar("{"); return(OPENLLAV_);}
"}"               {if(verbosidad)visualizar("}"); return(CLOSELLAV_);}
"="               {if(verbosidad)visualizar("="); return(OPASIG_);}
"*="              {if(verbosidad)visualizar("*="); return(OPASIGMUL_);}
"/="              {if(verbosidad)visualizar("/="); return(OPASIGDIV_);}
"-="              {if(verbosidad)visualizar("-="); return(OPASIGSUB_);}
"+="              {if(verbosidad)visualizar("+="); return(OPASIGADD_);}
"+"               {if(verbosidad)visualizar("+"); return(OPMAS_);}
"-"               {if(verbosidad)visualizar("-"); return(OPRES_);}
"*"               {if(verbosidad)visualizar("*"); return(OPMULT_);}
"/"               {if(verbosidad)visualizar("/"); return(OPDIV_);}
"%"               {if(verbosidad)visualizar("%"); return(OPRESTO_);}
"++"              {if(verbosidad)visualizar("++"); return(INCMAS_);}
"--"              {if(verbosidad)visualizar("--"); return(INCMIN_);}
"=="              {if(verbosidad)visualizar("=="); return(EQEQ_);}
">="              {if(verbosidad)visualizar(">="); return(RELBIGEQ_);}
"<="              {if(verbosidad)visualizar("<="); return(RELSMALLEQ_);}
"!="              {if(verbosidad)visualizar("!="); return(EQDIFF_);}
">"               {if(verbosidad)visualizar(">"); return(RELBIG_);}
"<"               {if(verbosidad)visualizar("<"); return(RELSMALL_);}
"&&"              {if(verbosidad)visualizar("&&"); return(LOGAND_);}
"||"              {if(verbosidad)visualizar("||"); return(LOGOR_);}
"!"               {if(verbosidad)visualizar("!"); return(UNNOT_);}
true              {if(verbosidad)visualizar("TRUE"); return(TRUE_);}
false             {if(verbosidad)visualizar("FALSE"); return(FALSE_);}
"("               {if(verbosidad)visualizar("("); return(OPENPAR_);}
")"               {if(verbosidad)visualizar(")"); return(CLOSEPAR_);}
"]"               {if(verbosidad)visualizar("]"); return(CLOSECOR_);}
"["               {if(verbosidad)visualizar("["); return(OPENCOR_);}
";"               {if(verbosidad)visualizar(";"); return(COMMADOT_);}
read              {if(verbosidad)visualizar("read"); return(read_);}
print             {if(verbosidad)visualizar("print"); return(print_);}
{id}              {yylval.ident = (char *)malloc(sizeof(char)*yyleng);strcpy(yylval.ident,yytext);if(verbosidad) ECHO; return(id_);}
{cte}             {yylval.cent = atoi(yytext); if(verbosidad) ECHO; return(cte_);}
.                 {printf("Lex err in line %d \n",yylineno);}
%%

void visualizar(char* texto){
	printf("%3d- %s\n",yylineno,texto);
}