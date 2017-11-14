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
	int tipo;
}

%type <tipo> tipoSimple
%type <tipo> expresion

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
%token <ident> id_
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

declaracion: tipoSimple id_ COMMADOT_                   {
														    if(!insertarTDS($2, $1, dval, 1)){
														        yyerror("Identificador repetido");
														    }else{
														        dval+=TALLA_TIPO_SIMPLE;
														    }
														    mostrarTDS();
                                                        }
	| tipoSimple id_ OPENCOR_ cte_ CLOSECOR_ COMMADOT_  { 	int numelem = $4; int refe;
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
															mostrarTDS();
														};

tipoSimple: int_ {$$ = T_ENTERO;}
	| bool_	{$$ = T_LOGICO;};


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
	| id_ operadorAsignacion expresion									{	
																			char aux[1024];
																			SIMB sim = obtenerTDS($1); $$ = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{ 
																				if(!((sim.tipo == $3)&&($3 == T_ENTERO || $3 == T_LOGICO))){
																					sprintf(aux,"El identificador tiene que ser de tipo %d", sim.tipo);
																					yyerror(aux);
																				}else{
																					$$ = sim.tipo;
																				}
																			}
																		}
	| id_ OPENCOR_ expresion CLOSECOR_ operadorAsignacion expresion 	{
																			SIMB sim = obtenerTDS($1); $$ = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{
																				if(sim.tipo != T_ARRAY){
																					yyerror("El identificador tiene que ser de tipo Array");
																				}else{
																					DIM dim = obtenerInfoArray(sim.ref);
																					if(!((dim.telem == $6)&&($6 == T_ENTERO || $6 == T_LOGICO))){
																						yyerror("Error de tipos en la asignación");
																					}else{ 
																						if($3!=T_ENTERO){
																							yyerror("El indice del array tiene que ser un entero");
																						}else{
																							$$ = sim.tipo;
																						}
																					}
																				}
																			}
																		};

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
