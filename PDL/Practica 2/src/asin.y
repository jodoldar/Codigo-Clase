%{
	#include <stdio.h>
	extern int yylineno;
	extern FILE *yyin;
	int numErrores;
%}

%token SPACE_ TAB_ CRLF_ OPENPAR_ CLOSEPAR_ COMMADOT_ OPENCOR_ CLOSECOR_ OPENLLAV_ CLOSELLAV_
%token OPASIG_ OPASIGADD_ OPASIGSUB_ OPASIGMUL_ OPASIGDIV_
%token LOGAND_ LOGOR_
%token EQEQ_ EQDIFF_
%token RELBIG_ RELSMALL_ RELBIGEQ_ RELSMALLEQ_
%token OPMAS_ OPRES_
%token OPMULT_ OPDIV_ OPRESTO_
%token UNMAS_ UNMIN_ UNNOT_
%token INCMAS_ INCMIN_
%token cte_
%token id_
%token int_ bool_
%token read_ print_
%token if_ elseif_ else_
%token while_ do_
%token TRUE_ FALSE_

%%

programa: OPENLLAV_ secuenciaSentencias CLOSELLAV_;

secuenciaSentencias: sentencia
	| secuenciaSentencias sentencia;

sentencia: declaracion
	| instruccion;

declaracion: tipoSimple id_ COMMADOT_
	| tipoSimple id_ OPENCOR_ cte_ CLOSECOR_ COMMADOT_;

tipoSimple: int_
	| bool_;

instruccion: OPENLLAV_ listaInstrucciones CLOSELLAV_
	| instruccionEntradaSalida
	| instruccionIteracion
	| instruccionExpresion
	| instruccionSeleccion;

listaInstrucciones: 
	|listaInstrucciones instruccion;

instruccionExpresion: expresion COMMADOT_
	| COMMADOT_;

instruccionEntradaSalida: read_ OPENPAR_ id_ CLOSEPAR_ COMMADOT_
	| print_ OPENPAR_ expresion CLOSEPAR_ COMMADOT_;

instruccionSeleccion: if_ OPENPAR_ expresion CLOSEPAR_ instruccion restoIf;

restoIf: elseif_ OPENPAR_ expresion CLOSEPAR_ instruccion restoIf
	| else_ instruccion;

instruccionIteracion: while_ OPENPAR_ expresion CLOSEPAR_ instruccion
	| do_ instruccion while_ OPENPAR_ expresion CLOSEPAR_;

expresion: expresionLogica
	| id_ operadorAsignacion expresion
	| id_ OPENCOR_ expresion CLOSECOR_ operadorAsignacion expresion;

expresionLogica: expresionIgualdad
	| expresionLogica operadorLogico expresionIgualdad;

expresionIgualdad: expresionRelacional
	| expresionIgualdad operadorIgualdad expresionRelacional;

expresionRelacional: expresionAditiva
	| expresionRelacional operadorRelacional expresionAditiva;

expresionAditiva: expresionMultiplicativa
	| expresionAditiva operadorAditivo expresionMultiplicativa;

expresionMultiplicativa: expresionUnaria
	| expresionMultiplicativa operadorMultiplicativo expresionUnaria;

expresionUnaria: expresionSufija
	| operadorUnario expresionUnaria;
	| operadorIncremento id_;

expresionSufija: OPENPAR_ expresion CLOSEPAR_
	| id_ operadorIncremento
	| id_ OPENCOR_ expresion CLOSECOR_
	| id_
	| cte_
	| TRUE_
	| FALSE_;

operadorAsignacion: OPASIG_
	| OPASIGADD_
	| OPASIGSUB_
	| OPASIGMUL_
	| OPASIGDIV_;

operadorLogico: LOGAND_
	| LOGOR_;

operadorIgualdad: EQEQ_
	| EQDIFF_;

operadorRelacional: RELBIG_
	| RELSMALL_
	| RELBIGEQ_
	| RELSMALLEQ_;

operadorAditivo: OPMAS_
	| OPRES_;

operadorMultiplicativo: OPMULT_
	| OPDIV_
	| OPRESTO_;

operadorUnario: UNMAS_
	| UNMIN_
	| UNNOT_;

operadorIncremento: INCMAS_
	| INCMIN_;

%%

/*void yyerror(char* msg){
	numErrores++;
	fprintf(stdout,"\nError at line %d: %s\n",yylineno,msg);
}
*/
/*int main(int argc, char **argv){
	if((yyin=fopen(argv[1],"r"))==NULL)
		fprintf(stderr, "Fichero no valido \%s", argv[1]);
	yyparse();
}*/