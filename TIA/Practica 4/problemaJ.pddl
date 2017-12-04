(define (problem problemaJ-1)
(:domain dominioJ)
(:objects
	p1 - paquete
	p2 - paquete
	c1 - camion
	c2 - camion
	d1 - conductor
	d2 - conductor
	A - ciudad
	B - ciudad
	C - ciudad
	D - ciudad
	E - ciudad
	)
(:init
	(at p1 A)
	(at p2 D)
	(at c1 A)
	(= (HayConductor c1) 0)
	(at c2 A)
	(= (HayConductor c2) 0)
	(at d1 C)
	(= (EstaEnCamion d1) 0)
	(at d2 D)
	(= (EstaEnCamion d2) 0)
	
	(= (tiempo-carga) 1)
	(= (coste-carga) 1)
	(= (tiempo-descarga) 1)
	(= (coste-descarga) 1)
	(= (tiempo-subir-camion) 1)
	(= (coste-subir-camion) 1)
	(= (tiempo-bajar-camion) 1)
	(= (coste-bajar-camion) 1)
	
	(= (coste-total) 0)

	(= (distancia A B) 5)
	(= (distancia A C) 3)
	(= (distancia A D) 0)
	(= (distancia A E) 0)
	
	(= (distancia B A) 5)
	(= (distancia B C) 3)
	(= (distancia B D) 2)
	(= (distancia B E) 4)
	
	(= (distancia C A) 3)
	(= (distancia C B) 3)
	(= (distancia C D) 0)
	(= (distancia C E) 8)
	
	(= (distancia D A) 0)
	(= (distancia D B) 2)
	(= (distancia D C) 0)
	(= (distancia D E) 0)
	
	(= (distancia E A) 0)
	(= (distancia E B) 4)
	(= (distancia E C) 8)
	(= (distancia E D) 0)

	(= (coste A B) 2)
	(= (coste A C) 1)
	(= (coste A D) 0)
	(= (coste A E) 0)
	
	(= (coste B A) 2)
	(= (coste B C) 1)
	(= (coste B D) 2)
	(= (coste B E) 3)
	
	(= (coste C A) 1)
	(= (coste C B) 1)
	(= (coste C D) 0)
	(= (coste C E) 5)
	
	(= (coste D A) 0)
	(= (coste D B) 2)
	(= (coste D C) 0)
	(= (coste D E) 0)
	
	(= (coste E A) 0)
	(= (coste E B) 3)
	(= (coste E C) 5)
	(= (coste E D) 0)
)
(:goal (and
	(at p1 E)
	(at p2 C)
	(at c2 A)
	(at d1 B))
	)

(:metric minimize (+ (* 0.1 (total-time))  (* 0.2 (coste-total))))
)
