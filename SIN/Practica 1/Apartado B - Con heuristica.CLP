;Realizado por:
;Dols Dart, Josep Vicent
;Cañadas Vacas, Cristian
;Coleto Muñoz, Miguel


(defglobal ?*nod-gen* = 0)
(defglobal ?*f* = 1)

;Funcion encargada de calcular la distancia minima entre dos puntos de la cuadricula
(deffunction distancias(?p1x ?p1y ?p2x ?p2y)
	(bind ?dist (+ (abs(- ?p1x ?p2x))(abs(- ?p1y ?p2y))))
	?dist)

;Función heuristica, tras calcular cuantas farolas quedan, comprueba cuantos pasos quedan para llegar a la solución.
;La función calcula la distancia entre el almacen y una farola, en caso de que no sea la ultima, se duplica el valor 
;obtenido, simulando que el robot tiene que ir y volver, y que lo hara por el mismo camino, excepto en la ultima
;farola, en la que solo ira
(deffunction descolocados (?estados)
	(bind ?sol 0)
	(bind ?far 7)
	(bind ?numfar 0)
	(while (eq (nth$ ?far ?estados) farola) do
		(bind ?numfar (+ ?numfar 1))
		(bind ?far (+ ?far 4))
	)
	(bind ?far 7)
	(bind ?sol (distancias(nth$ 2 ?estados)(nth$ 3 ?estados)(nth$ 5 ?estados)(nth$ 6 ?estados)))
	(bind ?sol (+ ?sol 1))
	(while (> ?numfar 0) do
		(bind ?temp (distancias(nth$ 2 ?estados)(nth$ 3 ?estados)(nth$ (+ ?far 1) ?estados)(nth$ (+ ?far 2) ?estados)))
		(if (= ?numfar 1)
			then (bind ?temp (+ ?temp 1))
			else (bind ?temp (* (+ ?temp 1) 2))
		)
		(bind ?sol (+ ?sol ?temp))
		(bind ?numfar (- ?numfar 1))
		(bind ?far (+ ?far 4))
	)
	?sol)

;Funcion encargada de juntar los dos valores necesarios para sacar f(n), el nivel y la función heuristica
(deffunction control(?estado ?nivel)
	(bind ?*f* (descolocados ?estado))
	(bind ?*f* (+ ?*f* ?nivel)))

;Funcion inicial para introducir los parametros necesarios para la ejecucion
;En este caso, no se elige entre metodos de busqueda, ya que el problema se resuelve mediante prioridades
(deffunction inicio()
	(set-salience-evaluation when-activated)
	(reset)
	(printout t "Profundidad Maxima: ")
	(bind ?prof (read))
	(assert (profundidad ?prof))	;Hecho estatico con la profundidad maxima alcanzable
	(assert (tamaño 4 3))			;Hecho estatico con el tamaño de la cuadricula(se empieza a contar en 0)
	(assert (almacen 1 1))			;Hecho estatico con la posicion del almacen
	;Hecho dinamico, incluye la posicion del robot y las farolas que quedan por ser reparadas
	(assert (robot 0 1 0 farola 2 0 3 farola 4 0 2 farola 3 2 2 nivel 0))
	(printout t "Ejecute el comando (run) para iniciar la ejecución" crlf))

;Regla para mover el robot a la casilla situada a su derecha, aumenta la posicion en el Eje X
(defrule derecha
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y $?f nivel ?n)
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	?f4<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (< ?x ?tx))		;Comprobacion de que no se esta en el borde derecho de la cuadricula
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?f) ?n))	;Compueba la heuristica al aplicar esta regla
=>
	(assert (robot (+ ?x 1) ?y $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada a su izquierda, disminuye la posicion en el Eje X
(defrule izquierda
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y $?f nivel ?n)
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	?f4<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (> ?x 0))			;Comprobacion de que no se esta en el borde izquierdo de la cuadricula
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?f) ?n))	;Compueba la heuristica al aplicar esta regla
=>
	(assert (robot (- ?x 1) ?y $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada encima, disminuye la posicion en el Eje Y
(defrule arriba
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y $?f nivel ?n)
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	?f4<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (> ?y 0))			;Comprobacion de que no se esta en el borde superior de la cuadricula
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?f) ?n))	;Compueba la heuristica al aplicar esta regla
=>
	(assert (robot ?x (- ?y 1) $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada abajo, aumenta la posicion en el Eje Y
(defrule abajo
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y $?f nivel ?n)
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	?f4<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (< ?y ?ty))		;Comprobacion de que no se esta en el borde inferior de la cuadricula
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?f) ?n))	;Compueba la heuristica al aplicar esta regla
=>
	(assert (robot ?x (+ ?y 1) $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para reparar una farola con bombillas fundidas, cuando se repara, se quita del hecho dinamico
(defrule reparar
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y ?bomb $?resto1 farola ?fx ?fy ?fund $?resto2 nivel ?n)
	?f2<-(profundidad ?p)
	?f3<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (= ?x ?fx))		;Comprobacion de que el robot esta en la misma posicion en el Eje X
	(test (= ?y ?fy))		;Comprobacion de que el robot esta en la misma posicion en el Eje Y
	(test (>= ?bomb ?fund))	;Comprobación de que el robot lleva suficientes bombillas
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?resto1 $?resto2) ?n))	;Compueba la heuristica al aplicar esta regla
=>
	(assert (robot ?x ?y (- ?bomb ?fund) $?resto1 $?resto2 nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para cargar el robot en el almacen
(defrule recargar
	(declare (salience (- 0 ?*f*)))
	?f1<-(robot ?x ?y ?bomb $?resto1 farola ?fx ?fy ?fund $?resto2 nivel ?n)
	?f2<-(profundidad ?p)
	?f3<-(almacen ?xa ?ya)
	(test (< ?n ?p))		;Comprobacion de que no se ha superado la profundidad maxima
	(test (= ?x ?xa))		;Comprobacion de que el robot esta en la misma posicion en el Eje X
	(test (= ?y ?ya))		;Comprobacion de que el robot esta en la misma posicion en el Eje Y
	(test (> ?fund ?bomb))	;Comprobacion de que el robot se queda con mas o las mismas bombillas que cuando llega
	;Compueba la heuristica al aplicar esta regla
	(test (control (create$ almacen ?xa ?ya robot ?x ?y $?resto1 farola ?fx ?fy ?fund $?resto2) ?n))	
=>
	(assert (robot ?x ?y ?fund $?resto1 farola ?fx ?fy ?fund $?resto2 nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para comprobar si se cumple la condicion final
(defrule fin
	(declare (salience 100))
	?f1<-(robot ?x ?y ?bomb nivel ?n)	;Solo queda el robot y ya no hay farolas
=>
	(printout t "Solución encontrada en el nivel " ?n crlf)
	(printout t "Numero de nodos expandidos: " ?*nod-gen* crlf)
	(printout t "Hecho final: " ?f1 crlf)
	(halt))