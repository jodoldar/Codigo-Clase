%{
#include <stdio.h>
#include <asin.h>
extern FILE *yyin;
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
int               {return(int_);}
bool              {return(bool_);}
if                {return(if_);}
else              {return(else_);}
elseif            {return(elseif_);}
while             {return(while_);}
do                {return(do_);}
cte               {return(cte_);}
"{"               {return(OPENLLAV_);}
"}"               {return(CLOSELLAV_);}
"="               {return(OPASIG_);}
"*="              {return(OPASIGMUL_);}
"/="              {return(OPASIGDIV_);}
"-="              {return(OPASIGSUB_);}
"+="              {return(OPASIGADD_);}
"+"               {return(OPMAS_);}
"-"               {return(OPRES_);}
"*"               {return(OPMULT_);}
"/"               {return(OPDIV_);}
"%"               {return(OPRESTO_);}
"++"              {return(INCMAS_);}
"--"              {return(INCMIN_);}
"=="              {return(EQEQ_);}
">="              {return(RELBIGEQ_);}
"<="              {return(RELSMALLEQ_);}
"!="              {return(EQDIFF_);}
">"               {return(RELBIG_);}
"<"               {return(RELSMALL_);}
"&&"              {return(LOGAND_);}
"||"              {return(LOGOR_);}
"!"               {return(UNNOT_);}
true              {return(TRUE_);}
false             {return(FALSE_);}
"("               {return(OPENPAR_);}
")"               {return(CLOSEPAR_);}
"]"               {return(CLOSECOR_);}
"["               {return(OPENCOR_);}
";"               {return(COMMADOT_);}
read              {return(read_);}
print             {return(print_);}
{id}              {return(id_);}
{cte}             {return(cte_);}
.                 {printf("Lex err in line %s \n",yylineno);}
%%
