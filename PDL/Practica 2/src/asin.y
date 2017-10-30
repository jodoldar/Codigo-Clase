%{
	#include <stdio.h>
	#include "header.h"
	#include "libtds.h"

	extern int dval;
	extern int yylineno;
	extern FILE *yyin;

	int numErrores;
%}

%union{
	char* ident;
	int cent;
}

%type <cent> tipoSimple
%type <ident> id_

%token SPACE_ TAB_ CRLF_ OPENPAR_ CLOSEPAR_ COMMADOT_ OPENCOR_ CLOSECOR_ OPENLLAV_ CLOSELLAV_
%token OPASIG_ OPASIGADD_ OPASIGSUB_ OPASIGMUL_ OPASIGDIV_
%token LOGAND_ LOGOR_
%token EQEQ_ EQDIFF_
%token RELBIG_ RELSMALL_ RELBIGEQ_ RELSMALLEQ_
%token OPMAS_ OPRES_
%token OPMULT_ OPDIV_ OPRESTO_
%token UNMAS_ UNMIN_ UNNOT_
%token INCMAS_ INCMIN_
%token <cent> cte_
%token id_
%token <cent> int_ bool_
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
	| tipoSimple id_ OPENCOR_ cte_ CLOSECOR_ COMMADOT_ { 	int numelem = $4; int refe;
															if($4<=0){
																yyerror("Talla inapropiada del array");
																numelem = 0;
															}
															refe = insertaTDArray($1,numelem);
															if(!insertarTDS($2, T_ARRAY, dval, refe)){
																yyerror("Identificador repetido");
															}else{
																dval+=numelem * TALLA_TIPO_SIMPLE;
															}
														};

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
