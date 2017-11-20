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
%type <tipo> expresion expresionLogica expresionIgualdad expresionRelacional expresionAditiva expresionMultiplicativa expresionUnaria expresionSufija
%type <tipo> operadorUnario

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

instruccionEntradaSalida: read_ OPENPAR_ id_ CLOSEPAR_ COMMADOT_	{
																		SIMB sim = obtenerTDS($3);
																		if(sim.tipo == T_ERROR){
																			yyerror("Objeto no declarado");
																		}else{
																			if(sim.tipo != T_ENTERO){
																				yyerror("El argumento debe de ser entero");
																			}
																		}
																	}
	| print_ OPENPAR_ expresion CLOSEPAR_ COMMADOT_					{
																		if($3 != T_ENTERO){
																			yyerror("La expresion del print debe de ser entera");
																		}
																	};

instruccionSeleccion: if_ OPENPAR_ expresion CLOSEPAR_  {
															if($3!=T_ERROR && $3!=T_LOGICO){
																yyerror("La expresion del \"if\" debe de ser logica");
															}
														}
														instruccion restoIf;

restoIf: elseif_ OPENPAR_ expresion CLOSEPAR_ instruccion restoIf			{
																				if($3!=T_LOGICO){
																					yyerror("La expresion del \"if-else\" debe de ser logica");
																				}
																			}
	| else_ instruccion;

instruccionIteracion: while_ OPENPAR_ expresion CLOSEPAR_ 				{
																			if($3 != T_LOGICO){
																				yyerror("La expresion del \"while\" debe ser logica");
																			}
																		}
														instruccion 	
	| do_ instruccion while_ OPENPAR_ expresion CLOSEPAR_				{
																			if($5 != T_LOGICO){
																				yyerror("La expresion del \"do-while\" debe ser logica");
																			}
																		};


expresion: expresionLogica												{
																			$$ = $1;
																		}
	| id_ operadorAsignacion expresion									{	
																			char aux[1024];
																			SIMB sim = obtenerTDS($1); $$ = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{ 
																				if($3!=T_ERROR){
																					if(!((sim.tipo == $3)&&($3 == T_ENTERO || $3 == T_LOGICO))){
																						sprintf(aux,"El identificador tiene que ser de tipo %d", sim.tipo);
																						yyerror(aux);
																					}else{
																						$$ = sim.tipo;
																					}
																				}
																			}
																		}
	| id_ OPENCOR_ expresion CLOSECOR_ operadorAsignacion expresion 	{
																			SIMB sim = obtenerTDS($1); $$ = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{
																				if($6 != T_ERROR){
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
																			}
																		};

expresionLogica: expresionIgualdad							{
																$$ = $1;
															}
	| expresionLogica operadorLogico expresionIgualdad		{
																char aux[1024];
																$$ = T_ERROR;
																if($1!=T_LOGICO || $3 != T_LOGICO){
																	sprintf(aux,"Intentado operacion logica entre tipos %d y %d",$1,$3);
																	yyerror(aux);
																}else{
																	$$ = T_LOGICO;
																}
															};

expresionIgualdad: expresionRelacional							{
																	$$ = $1;	
																}
	| expresionIgualdad operadorIgualdad expresionRelacional	{
																	char aux[1024];
																	$$ = T_ERROR;
																	if($1==$3){
																		$$ = T_LOGICO;
																	}else{
																		sprintf(aux,"Intentando operacion de igualdad entre tipos %d y %d",$1,$3);
																		yyerror(aux);
																	}
																};

expresionRelacional: expresionAditiva							{
																	$$ = $1;	
																}
	| expresionRelacional operadorRelacional expresionAditiva	{
																	char aux[1024];
																	$$ = T_ERROR;
																	if($1!=T_ENTERO || $3 != T_ENTERO){
																		sprintf(aux,"Intentando operacion relacional entre tipos %d y %d",$1,$3);
																		yyerror(aux);
																	}else{
																		$$ = T_LOGICO;
																	}
																};	

expresionAditiva: expresionMultiplicativa						{
																	$$ = $1;	
																}
	| expresionAditiva operadorAditivo expresionMultiplicativa	{
																	char aux[1024];
																	$$ = T_ERROR;
																	if($1!=T_ENTERO || $3 != T_ENTERO){
																		sprintf(aux,"Intentando operacion aditiva entre tipos %d y %d",$1,$3);
																		yyerror(aux);
																	}else{
																		$$ = T_ENTERO;
																	}
																};

expresionMultiplicativa: expresionUnaria								{
																			$$ = $1;	
																		}
	| expresionMultiplicativa operadorMultiplicativo expresionUnaria	{
																			char aux[1024];
																			$$ = T_ERROR;
																			if($1!=T_ENTERO || $3!=T_ENTERO){
																				sprintf(aux,"Intentando operacion multiplicativa entre tipos %d y %d",$1,$3);
																				yyerror(aux);
																			}else{
																				$$ = T_ENTERO;
																			}
																		};

expresionUnaria: expresionSufija		{
											$$ = $1;
										}
	| operadorUnario expresionUnaria	{
											char aux[1024];
											$$ = T_ERROR;
											if($2 != T_ENTERO){
												sprintf(aux,"Intentando operacion unaria entre tipos %d y %d",$1,$2);
												yyerror(aux);
											}else{
												$$ = T_ENTERO;
											}
										}
	| operadorIncremento id_			{
											$$ = T_ERROR;
											SIMB sim = obtenerTDS($2);
											if(sim.tipo == T_ERROR){
												yyerror("Objeto no declarado");
											}else{
												if(sim.tipo != T_ENTERO){
													yyerror("Error en expresion unaria. Se esparaba un entero");
												}else{
													$$ = T_ENTERO;
												}
											}
										};

expresionSufija: OPENPAR_ expresion CLOSEPAR_	{
													$$ = $2;		
												}
	| id_ operadorIncremento					{
													$$ = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ENTERO){
															yyerror("Error en expresion unaria. Se esparaba un entero");
														}else{
															$$ = T_ENTERO;
														}
													}
												}
	| id_ OPENCOR_ expresion CLOSECOR_			{
													$$ = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ARRAY){
															yyerror("Se esperaba un identificador de tipo Array");
														}else{
															if($3 != T_ENTERO){
																yyerror("El indice del array tiene que ser positivo");
															}else{
																DIM dim = obtenerInfoArray(sim.ref);
																if($3<0 || $3 > dim.nelem){
																	yyerror("El indice del array esta fuera del rango");
																}else{
																	$$ = dim.telem;
																}
															}
														}
													}
												}
	| id_										{
													$$ = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														$$ = sim.tipo;
													}
												}
	| cte_										{
													$$ = T_ENTERO;
												}
	| TRUE_										{
													$$ = T_LOGICO;
												}
	| FALSE_									{
													$$ = T_LOGICO;
												};

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

operadorUnario: UNMAS_	{
							$$ = T_ENTERO;	
						}
	| UNMIN_			{
							$$ = T_ENTERO;
						}
	| UNNOT_			{
							$$ = T_LOGICO;
						};

operadorIncremento: INCMAS_
	| INCMIN_;

%%
