%{
	#include <stdio.h>
	#include "header.h"
	#include "libtds.h"
	#include "libgci.h"

	extern int dvar;
	extern int yylineno;
	extern FILE *yyin;

	int numErrores;
%}

%union{
	char* ident;
	int cent;
	EXPR expr;
}

%type <expr> tipoSimple
%type <expr> expresion expresionLogica expresionIgualdad expresionRelacional expresionAditiva expresionMultiplicativa expresionUnaria expresionSufija
%type <expr> operadorUnario
%type <cent> operadorAditivo operadorMultiplicativo operadorAsignacion

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

programa: OPENLLAV_ secuenciaSentencias CLOSELLAV_ 		{
															emite(FIN,crArgNul(),crArgNul(),crArgNul());
														};

secuenciaSentencias: sentencia
	| secuenciaSentencias sentencia;

sentencia: declaracion
	| instruccion;

declaracion: tipoSimple id_ COMMADOT_                   {	
														    if(!insertarTDS($2, $1.tipo, dvar, 1)){
														        yyerror("Identificador repetido");
														    }else{
														        dvar+=TALLA_TIPO_SIMPLE;
														    }
														    mostrarTDS();
                                                        }
	| tipoSimple id_ OPENCOR_ cte_ CLOSECOR_ COMMADOT_  { 	int numelem = $4; int refe;
															if($4<=0){
																yyerror("Talla inapropiada del array");
																numelem = 0;
															}
															refe = insertaTDArray($1.tipo,numelem);
															if(!insertarTDS($2, T_ARRAY, dvar, refe)){
																yyerror("Identificador repetido");
															}else{
																dvar+=numelem * TALLA_TIPO_SIMPLE;
															}
															mostrarTDS();
														};

tipoSimple: int_ {$$.tipo = T_ENTERO;}
	| bool_	{$$.tipo = T_LOGICO;};


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
																		if($3.tipo != T_ENTERO){
																			yyerror("La expresion del print debe de ser entera");
																		}
																	};

instruccionSeleccion: if_ OPENPAR_ expresion CLOSEPAR_  {
															if($3.tipo != T_ERROR && $3.tipo != T_LOGICO){
																yyerror("La expresion del \"if\" debe de ser logica");
															}
														}
														instruccion restoIf;

restoIf: elseif_ OPENPAR_ expresion CLOSEPAR_ instruccion restoIf			{
																				if($3.tipo != T_LOGICO){
																					yyerror("La expresion del \"if-else\" debe de ser logica");
																				}
																			}
	| else_ instruccion;

instruccionIteracion: while_ OPENPAR_ expresion CLOSEPAR_ 				{
																			if($3.tipo != T_LOGICO){
																				yyerror("La expresion del \"while\" debe ser logica");
																			}
																		}
														instruccion 	
	| do_ instruccion while_ OPENPAR_ expresion CLOSEPAR_				{
																			if($5.tipo != T_LOGICO){
																				yyerror("La expresion del \"do-while\" debe ser logica");
																			}
																		};


expresion: expresionLogica												{
																			$$ = $1;
																		}
	| id_ operadorAsignacion expresion									{	
																			char aux[1024];
																			SIMB sim = obtenerTDS($1); $$.tipo = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{ 
																				if($3.tipo != T_ERROR){
																					if(!((sim.tipo == $3.tipo)&&($3.tipo == T_ENTERO || $3.tipo == T_LOGICO))){
																						sprintf(aux,"El identificador tiene que ser de tipo %d", sim.tipo);
																						yyerror(aux);
																					}else{
																						$$.tipo = sim.tipo;
																					}
																				}
																			}
																			/*** Expresion a partir de una asignacion ***/
																			if($2 == EASIG){
																				emite($2, crArgPos(sim.desp), crArgNul(), crArgPos($$.pos));
																			}else{
																				emite($2, crArgPos(sim.desp), crArgPos($3.pos), crArgPos($$.pos));
																			}
																			
																		}
	| id_ OPENCOR_ expresion CLOSECOR_ operadorAsignacion expresion 	{
																			SIMB sim = obtenerTDS($1); $$.tipo = T_ERROR;
																			if(sim.tipo == T_ERROR){
																				yyerror("Objeto no declarado");
																			}else{
																				if($6.tipo != T_ERROR){
																					if(sim.tipo != T_ARRAY){
																						yyerror("El identificador tiene que ser de tipo Array");
																					}else{
																						DIM dim = obtenerInfoArray(sim.ref);
																						if(!((dim.telem == $6.tipo)&&($6.tipo == T_ENTERO || $6.tipo == T_LOGICO))){
																							yyerror("Error de tipos en la asignación");
																						}else{ 
																							if($3.tipo != T_ENTERO){
																								yyerror("El indice del array tiene que ser un entero");
																							}else{
																								$$.tipo = sim.tipo;
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
																$$.tipo = T_ERROR;
																if($1.tipo != T_LOGICO || $3.tipo != T_LOGICO){
																	sprintf(aux,"Intentado operacion logica entre tipos %d y %d",$1.tipo,$3.tipo);
																	yyerror(aux);
																}else{
																	$$.tipo = T_LOGICO;
																}
															};

expresionIgualdad: expresionRelacional							{
																	$$ = $1;	
																}
	| expresionIgualdad operadorIgualdad expresionRelacional	{
																	char aux[1024];
																	$$.tipo = T_ERROR;
																	if($1.tipo==$3.tipo){
																		$$.tipo = T_LOGICO;
																	}else{
																		sprintf(aux,"Intentando operacion de igualdad entre tipos %d y %d",$1.tipo,$3.tipo);
																		yyerror(aux);
																	}
																};

expresionRelacional: expresionAditiva							{
																	$$ = $1;	
																}
	| expresionRelacional operadorRelacional expresionAditiva	{
																	char aux[1024];
																	$$.tipo = T_ERROR;
																	if($1.tipo != T_ENTERO || $3.tipo != T_ENTERO){
																		sprintf(aux,"Intentando operacion relacional entre tipos %d y %d",$1.tipo,$3.tipo);
																		yyerror(aux);
																	}else{
																		$$.tipo = T_LOGICO;
																	}
																};	

expresionAditiva: expresionMultiplicativa						{
																	$$ = $1;	
																}
	| expresionAditiva operadorAditivo expresionMultiplicativa	{
																	char aux[1024];
																	$$.tipo = T_ERROR;
																	if($1.tipo != T_ENTERO || $3.tipo != T_ENTERO){
																		sprintf(aux,"Intentando operacion aditiva entre tipos %d y %d",$1.tipo,$3.tipo);
																		yyerror(aux);
																	}else{
																		$$.tipo = T_ENTERO;
																	}
																	$$.pos = creaVarTemp();
																	/*** Expresion a partir de un operador aritmetico ***/
																	emite($2, crArgPos($1.pos), crArgPos($3.pos), crArgPos($$.pos));
																};

expresionMultiplicativa: expresionUnaria								{
																			$$ = $1;	
																		}
	| expresionMultiplicativa operadorMultiplicativo expresionUnaria	{
																			char aux[1024];
																			$$.tipo = T_ERROR;
																			if($1.tipo != T_ENTERO || $3.tipo != T_ENTERO){
																				sprintf(aux,"Intentando operacion multiplicativa entre tipos %d y %d",$1.tipo,$3.tipo);
																				yyerror(aux);
																			}else{
																				$$.tipo = T_ENTERO;
																			}
																			$$.pos = creaVarTemp();
																			/*** Expresion a partir de un operador aritmetico ***/
																			emite($2, crArgPos($1.pos), crArgPos($3.pos), crArgPos($$.pos));
																		};

expresionUnaria: expresionSufija		{
											$$ = $1;
										}
	| operadorUnario expresionUnaria	{
											char aux[1024];
											$$.tipo = T_ERROR;
											if($2.tipo != T_ENTERO){
												sprintf(aux,"Intentando operacion unaria entre tipos %d y %d",$1.tipo,$2.tipo);
												yyerror(aux);
											}else{
												$$.tipo = T_ENTERO;
											}
										}
	| operadorIncremento id_			{
											$$.tipo = T_ERROR;
											SIMB sim = obtenerTDS($2);
											if(sim.tipo == T_ERROR){
												yyerror("Objeto no declarado");
											}else{
												if(sim.tipo != T_ENTERO){
													yyerror("Error en expresion unaria. Se esparaba un entero");
												}else{
													$$.tipo = T_ENTERO;
												}
											}
										};

expresionSufija: OPENPAR_ expresion CLOSEPAR_	{
													$$ = $2;		
												}
	| id_ operadorIncremento					{
													$$.tipo = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ENTERO){
															yyerror("Error en expresion unaria. Se esparaba un entero");
														}else{
															$$.tipo = T_ENTERO;
														}
													}
												}
	| id_ OPENCOR_ expresion CLOSECOR_			{
													$$.tipo = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														if(sim.tipo != T_ARRAY){
															yyerror("Se esperaba un identificador de tipo Array");
														}else{
															if($3.tipo != T_ENTERO){
																yyerror("El indice del array tiene que ser positivo");
															}else{
																DIM dim = obtenerInfoArray(sim.ref);
																$$.tipo = dim.telem;
															}
														}
													}
												}
	| id_										{
													$$.tipo = T_ERROR;
													SIMB sim = obtenerTDS($1);
													if(sim.tipo == T_ERROR){
														yyerror("Objeto no declarado");
													}else{
														$$.tipo = sim.tipo;
													}
												}
	| cte_										{
													$$.tipo = T_ENTERO;
												}
	| TRUE_										{
													$$.tipo = T_LOGICO;
												}
	| FALSE_									{
													$$.tipo = T_LOGICO;
												};

operadorAsignacion: OPASIG_ 	{
									$$ = EASIG;
								}
	| OPASIGADD_				{
									$$ = ESUM;
								}
	| OPASIGSUB_ 				{
									$$ = EDIF;
								}
	| OPASIGMUL_ 				{
									$$ = EMULT;
								}
	| OPASIGDIV_				{
									$$ = EDIVI;
								};

operadorLogico: LOGAND_
	| LOGOR_;

operadorIgualdad: EQEQ_
	| EQDIFF_;

operadorRelacional: RELBIG_
	| RELSMALL_
	| RELBIGEQ_
	| RELSMALLEQ_;

operadorAditivo: OPMAS_	{
							$$ = ESUM;
						}
	| OPRES_ 			{
							$$ = EDIF;
						};

operadorMultiplicativo: OPMULT_ {
									$$ = EMULT;
								}
	| OPDIV_ 					{
									$$ = EDIVI;
								}
	| OPRESTO_ 					{
									$$ = RESTO;
								};	

operadorUnario: UNMAS_	{
							$$.tipo = T_ENTERO;	
						}
	| UNMIN_			{
							$$.tipo = T_ENTERO;
						}
	| UNNOT_			{
							$$.tipo = T_LOGICO;
						};

operadorIncremento: INCMAS_
	| INCMIN_;

%%
