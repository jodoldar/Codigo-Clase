;Realizado por:
;Dols Dart, Josep Vicent
;Cañadas Vacas, Cristian
;Coleto Muñoz, Miguel

(defglobal ?*nod-gen* = 0)

;Regla para mover el robot a la casilla situada a su derecha, aumenta la posicion en el Eje X
(defrule derecha
	?f1<-(robot ?x ?y $?f nivel ?n)	;Solo se necesita la posicion del robot y el nivel actual
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (< ?x ?tx))	;Comprobacion de que no se esta en el borde derecho de la cuadricula
=>
	(assert (robot (+ ?x 1) ?y $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada a su izquierda, disminuye la posicion en el Eje X
(defrule izquierda
	?f1<-(robot ?x ?y $?f nivel ?n)	;Solo se necesita la posicion del robot y el nivel actual
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (> ?x 0))		;Comprobacion de que no se esta en el borde izquierdo de la cuadricula
=>
	(assert (robot (- ?x 1) ?y $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada encima, disminuye la posicion en el Eje Y
(defrule arriba
	?f1<-(robot ?x ?y $?f nivel ?n)	;Solo se necesita la posicion del robot y el nivel actual
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (> ?y 0))		;Comprobacion de que no se esta en el borde superior de la cuadricula
=>
	(assert (robot ?x (- ?y 1) $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para mover el robot a la casilla situada abajo, aumenta la posicion en el Eje Y
(defrule abajo
	?f1<-(robot ?x ?y $?f nivel ?n)	;Solo se necesita la posicion del robot y el nivel actual
	?f2<-(tamaño ?tx ?ty)
	?f3<-(profundidad ?p)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (< ?y ?ty))	;Comprobacion de que no se esta en el borde inferior de la cuadricula
=>
	(assert (robot ?x (+ ?y 1) $?f nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para reparar una farola con bombillas fundidas, cuando se repara, se quita del hecho dinamico
(defrule reparar
	?f1<-(robot ?x ?y ?bomb $?resto1 farola ?fx ?fy ?fund $?resto2 nivel ?n)
	?f2<-(profundidad ?p)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (= ?x ?fx))	;Comprobacion de que el robot esta en la misma posicion en el Eje X
	(test (= ?y ?fy))	;Comprobacion de que el robot esta en la misma posicion en el Eje Y
	(test (>= ?bomb ?fund))	;Comprobación de que el robot lleva suficientes bombillas
=>
	(assert (robot ?x ?y (- ?bomb ?fund) $?resto1 $?resto2 nivel (+ ?n 1)))
	(bind ?*nod-gen* (+ ?*nod-gen* 1)))

;Regla para cargar el robot en el almacen
(defrule recargar
	?f1<-(robot ?x ?y ?bomb $?resto1 farola ?fx ?fy ?fund $?resto2 nivel ?n)
	?f2<-(profundidad ?p)
	?f3<-(almacen ?xa ?ya)
	(test (< ?n ?p))	;Comprobacion de que no se ha superado la profundidad maxima
	(test (= ?x ?xa))	;Comprobacion de que el robot esta en la misma posicion en el Eje X
	(test (= ?y ?ya))	;Comprobacion de que el robot esta en la misma posicion en el Eje Y
	(test (> ?fund ?bomb))	;Comprobacion de que el robot se queda con mas o las mismas bombillas que cuando llega
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

;Funcion inicial para introducir los parametros necesarios para la ejecucion
(deffunction inicio()
	(reset)
	(printout t "Profundidad Maxima: ")
	(bind ?prof (read))
	(printout t "Tipo de Busqueda: " crlf "    1.- Anchura" crlf "    2.- Profundidad" crlf)
	(bind ?a (read))
	(if (= ?a 1)
		then (set-strategy breadth)
		else (set-strategy depth))
	(assert (profundidad ?prof)) ;Hecho estatico con la profundidad maxima alcanzable
	(assert (tamaño 4 3))	;Hecho estatico con el tamaño de la cuadricula(se empieza a contar en 0)
	(assert (almacen 1 1))	;Hecho estatico con la posicion del almacen
	;Hecho dinamico, incluye la posicion del robot y las farolas que quedan por ser reparadas
	(assert (robot 0 1 0 farola 2 0 3 farola 4 0 2 farola 3 2 2 nivel 0))	
	(printout t "Ejecute el comando (run) para iniciar la ejecución" crlf)
)