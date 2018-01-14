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
%type <cent> operadorUnario
%type <cent> operadorAditivo operadorMultiplicativo operadorAsignacion operadorLogico operadorIgualdad operadorRelacional operadorIncremento

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
																		emite(EREAD,crArgNul(),crArgNul(),crArgPos(sim.desp));
																	}
	| print_ OPENPAR_ expresion CLOSEPAR_ COMMADOT_					{
																		if($3.tipo != T_ENTERO){
																			yyerror("La expresion del print debe de ser entera");
																		}
																		emite(EWRITE,crArgNul(),crArgNul(),crArgPos($3.pos));
																	};


instruccionSeleccion: if_ OPENPAR_ expresion CLOSEPAR_  {
															if($3.tipo != T_ERROR && $3.tipo != T_LOGICO){
																yyerror("La expresion del \"if\" debe de ser logica");
															}
															$<cent>$ = creaLans(si);
															emite(EIGUAL,crArgPos($3.pos),crArgEnt(0),crArgNul());
														}
														instruccion {
																		$<cent>$ = creaLans(si);
																		emite(GOTOS,crArgNul(),crArgNul(),crArgNul());
																		completaLans($<cent>5,crArgEtq(si));
																	}
														restoIf{
																	completaLans($<cent>7,crArgEtq(si));
																};


restoIf: elseif_ OPENPAR_ expresion CLOSEPAR_ 	{
													if($3.tipo != T_ERROR && $3.tipo != T_LOGICO){
														yyerror("La expresion del \"else-if\" debe de ser logica");
													}
													$<cent>$ = creaLans(si);
													emite(EIGUAL,crArgPos($3.pos),crArgEnt(0),crArgNul());
												}
												instruccion {
																$<cent>$ = creaLans(si);
																emite(GOTOS,crArgNul(),crArgNul(),crArgNul());
																completaLans($<cent>5,crArgEtq(si));
															}
												restoIf	{
															completaLans($<cent>7,crArgEtq(si));
														}
	| else_ instruccion;


instruccionIteracion: while_ {$<cent>$ = si; } OPENPAR_ expresion CLOSEPAR_ 				{
																			if($4.tipo != T_LOGICO){
																				yyerror("La expresion del \"while\" debe ser logica");
																			}
																			$<cent>$ = creaLans(si);
																			emite(EIGUAL,crArgPos($4.pos),crArgEnt(0),crArgNul());
																		}
														instruccion 	{
																			emite(GOTOS,crArgNul(),crArgNul(),crArgEtq($<cent>2));
																			completaLans($<cent>6,crArgEtq(si));
																		}
	| do_  {$<cent>$ = si; } instruccion while_ OPENPAR_ expresion CLOSEPAR_	{
																					if($6.tipo != T_LOGICO){
																						yyerror("La expresion del \"do-while\" debe ser logica");
																					}
																					emite(EDIST,crArgPos($6.pos),crArgEnt(0),crArgEtq($<cent>2));
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
																						$$.pos = creaVarTemp();
																						if($2 == EASIG){
																							emite($2,crArgPos($3.pos),crArgNul(),crArgPos(sim.desp));
																						}else{
																							emite($2,crArgPos(sim.desp),crArgNul(),crArgPos($$.pos));
																						}
																						emite(EASIG,crArgPos(sim.desp),crArgNul(),crArgPos($$.pos));
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
																								$$.pos = creaVarTemp();
																								if($5 == EASIG){
																									emite(EVA, crArgPos(sim.desp), crArgPos($3.pos), crArgPos($6.pos));
																								}else{
																									int tempVar = creaVarTemp();
																									emite(EAV,crArgPos(sim.desp),crArgPos($3.pos),crArgPos(tempVar));
																									emite($5,crArgPos(tempVar),crArgPos($6.pos),crArgPos(tempVar));
																									emite(EVA, crArgPos(sim.desp), crArgPos($3.pos), crArgPos(tempVar));
																								}
																								emite(EAV,crArgPos(sim.desp),crArgPos($3.pos),crArgPos($$.pos));
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
																	$$.pos = creaVarTemp();
																	int varTemp = creaVarTemp();

																	emite($2,crArgPos($1.pos),crArgPos($3.pos),crArgPos(varTemp));
                                    								emite(EASIG,crArgEnt(1),crArgNul(),crArgPos($$.pos));
                                    								emite(EMAYEQ,crArgPos(varTemp),crArgEnt(1),crArgEtq(si+2));
                                    								emite(EASIG,crArgEnt(0),crArgNul(),crArgPos($$.pos));
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
																	$$.pos = creaVarTemp();
                                 									emite(EASIG,crArgEnt(1),crArgNul(),crArgPos($$.pos));
                                 									emite($2,crArgPos($1.pos),crArgPos($3.pos),crArgEtq(si+2));
                                 									emite(EASIG,crArgEnt(0),crArgNul(),crArgPos($$.pos));
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

																		$$.pos = creaVarTemp();
                                    									emite(EASIG,crArgEnt(1),crArgNul(),crArgPos($$.pos));
                                    									emite($2,crArgPos($1.pos),crArgPos($3.pos),crArgEtq(si+2));
                                    									emite(EASIG,crArgEnt(0),crArgNul(),crArgPos($$.pos));
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
											if($2.tipo != T_LOGICO){
												sprintf(aux,"Intentando operacion unaria entre tipos %d y %d",$1,$2.tipo);
												yyerror(aux);
											}else{
												$$.tipo = T_LOGICO;

												$$.pos = creaVarTemp(); //$2.pos;
                                    			emite(EASIG,crArgEnt(0),crArgNul(),crArgPos($$.pos));
                                    			emite(EDIST,crArgPos($2.pos),crArgEnt(0),crArgEtq(si+2));
                                    			emite(EASIG,crArgEnt(1),crArgNul(),crArgPos($$.pos));
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

													$$.pos = creaVarTemp();
                                       				emite($1, crArgPos(sim.desp) ,crArgEnt(1) ,crArgPos(sim.desp) );
                                       				emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos($$.pos) );
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

															$$.pos = creaVarTemp();
                                       						emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos($$.pos) ); // first return id
                                       						emite($2, crArgPos(sim.desp) ,crArgEnt(1) ,crArgPos(sim.desp) );  // then id += id + 1 (id++)
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

																$$.pos = creaVarTemp();
                                          						emite(EAV,crArgPos(sim.desp),crArgPos($3.pos),crArgPos($$.pos));
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
														$$.pos = creaVarTemp();
                                    					emite(EASIG, crArgPos(sim.desp) ,crArgNul() ,crArgPos($$.pos) );
													}
												}
	| cte_										{
													$$.tipo = T_ENTERO;
													$$.pos = creaVarTemp();
													emite(EASIG, crArgEnt($1) ,crArgNul() , crArgPos($$.pos) );
												}
	| TRUE_										{
													$$.tipo = T_LOGICO;
													$$.pos = creaVarTemp();
													emite(EASIG, crArgEnt(1) ,crArgNul() ,crArgPos($$.pos) );
												}
	| FALSE_									{
													$$.tipo = T_LOGICO;
													$$.pos = creaVarTemp();
                                 					emite(EASIG, crArgEnt(0) ,crArgNul() ,crArgPos($$.pos) );
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

operadorLogico: LOGAND_			{
									$$ = EMULT;
								}
	| LOGOR_					{	
									$$ = ESUM;
								};


operadorIgualdad: EQEQ_			{
									$$ = EIGUAL;
								}
	| EQDIFF_					{	
									$$ = EDIST;
								};


operadorRelacional: RELBIG_ 	{
									$$ = EMAY;
								}
	| RELSMALL_ 				{
									$$ = EMEN;
								}
	| RELBIGEQ_ 				{
									$$ = EMAYEQ;
								}
	| RELSMALLEQ_ 				{	
									$$ = EMENEQ;
								};

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
							//$$.tipo = T_ENTERO;
							$$ = ESUM;	
						}
	| UNMIN_			{
							//$$.tipo = T_ENTERO;
							$$ = EDIF;
						}
	| UNNOT_			{
							//$$.tipo = T_LOGICO;
							$$ = ESIG;
						};

operadorIncremento: INCMAS_ 	{
									$$ = ESUM;
								}
	| INCMIN_ 					{
									$$ = EDIF;
								};



%%
