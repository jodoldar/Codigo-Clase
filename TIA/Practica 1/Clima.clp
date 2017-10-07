(deftemplate SITUACION
    (slot codigo)
    (slot nubes (allowed-values nil despejado estratos nimboestratos cirroestratos cumulos cumulonimbos))
    (slot despejado (allowed-values nil si no))
    (slot humedad (allowed-values nil baja media alta))
    (slot presion (allowed-values nil baja normal alta))
    (slot variacionPresion (allowed-values nil sube baja mantiene))
    (slot viento (allowed-values nil norte noreste este sureste sur suroeste oeste noroeste));De que dirección proviene
    (slot velocidadViento (allowed-values nil calma debil fuerte))
    (slot prediccion (default nil)))

(do-backward-chaining SITUACION)

(deffacts situaciones
    ;(SITUACION (codigo situacion1))
    (SITUACION (codigo situacion2) (humedad baja)(viento oeste))
    (SITUACION (codigo situacion3) (humedad alta)(viento este)(presion baja))
    (SITUACION (codigo situacion4) (humedad alta)(viento este)(presion baja)(nubes cumulonimbos))
	(SITUACION (codigo situacion5) (humedad baja) (viento suroeste) (presion alta) (nubes estratos))
    )

(defrule tormenta
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(despejado no)(humedad alta)(viento ?v)(velocidadViento fuerte)(presion baja)(nubes cumulonimbos)(prediccion nil))
    (test (and(neq ?v oeste)(neq ?v suroeste)(neq ?v sur)))
    =>
    (modify ?sit (prediccion tormenta))
    (printout t "Caso: " ?c ". En las proximas horas habran tormentas" crlf))

(defrule poniente
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(despejado si)(humedad baja)(viento ?v)(prediccion nil))
    (test (or (eq ?v suroeste)(eq ?v oeste)))
    =>
    (modify ?sit (prediccion poniente))
    (printout t "Caso: " ?c ". En las proximas horas hara un calor intenso" crlf))

(defrule soleado
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c) (despejado si) (humedad ?h) (viento ?v) (velocidadViento ?vv) (presion ?p) (nubes ?n)(prediccion nil))
    (test (or (eq ?vv debil)(eq ?vv calma)))
    (test (or (eq ?n despejado)(eq ?n cumulos)))
    =>
    (modify ?sit (prediccion buen_tiempo))
    (printout t "Caso: "?c". En las proximas horas va a estar soleado" crlf))

(defrule nublado
    (declare (salience 100)(no-loop TRUE))
    ?sit <- (SITUACION(codigo ?c)(despejado no)(humedad baja)(presion normal)(variacionPresion ?vp)(velocidadViento ?vv)(nubes cirroestratos)(prediccion nil))
    (test (or (eq ?vv debil)(eq ?vv calma)))
    (test (or (eq ?vp sube)(eq ?vp mantiene)))
    =>
    (modify ?sit (prediccion nublado))
    (printout t "Caso: "?c". En las proximas horas estara nublado" crlf))

(defrule chubascos
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(despejado no) (humedad ?h) (viento ?v) (velocidadViento ?vv) (presion ?p) (nubes ?n)(prediccion nil))
    (test (or (eq ?p baja)(eq ?p normal)))
    (test (or (eq ?vv debil)(eq ?vv calma)))
    (test (or (eq ?n cumulonimbos)(eq ?n nimboestratos)))
    (test (and(neq ?v oeste)(neq ?v suroeste)(neq ?v sur)))
    =>
    (modify ?sit (prediccion chubascos))
    (printout t "Caso: "?c". En las proximas horas habran chubascos" crlf)) 

(defrule futuros_chubascos
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(despejado no) (humedad ?h) (viento ?v) (variacionPresion baja)(presion ?p) (nubes ?n)(prediccion nil))
    (test (or(eq ?n cumulos)(eq ?n cirroestratos)))
    (test (and(neq ?v oeste)(neq ?v suroeste)(neq ?v sur)))
    =>
    (modify ?sit (prediccion futuros_chubascos))
    (printout t "Caso: "?c". Mañana es posible que llueva." crlf)) 
        
(defrule futuras_tormentas
    (declare (salience 100) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(despejado no) (humedad ?h) (viento ?v)(velocidadViento ?vv)  (variacionPresion baja)(presion ?p) (prediccion nil))
    (test (eq ?vv fuerte))
    (test (and(neq ?v oeste)(neq ?v suroeste)(neq ?v sur)))
    =>
    (modify ?sit (prediccion futuras_tormentas))
    (printout t "Caso: "?c". Mañana es posible que hayan tormentas." crlf))        
    

(defrule nubes1
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes nimboestrato)(prediccion nil))
    =>
    (modify ?sit (despejado no)))

(defrule nubes2
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes cumulonimbos)(prediccion nil))
    =>
    (modify ?sit (despejado no)))

(defrule nubes3
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes estratos)(prediccion nil))
    =>
    (modify ?sit (despejado si)))

(defrule nubes4
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes cirroestratos)(prediccion nil))
    =>
    (modify ?sit (despejado si)))

(defrule nubes5
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes cumulos)(prediccion nil))
    =>
    (modify ?sit (despejado no)))

(defrule nubes6
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(nubes despejado)(prediccion nil))
    =>
    (modify ?sit (despejado si)))

(defrule humedad1
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(viento ?v)(prediccion nil))
    (test (or (eq ?v este)(eq ?v sureste)(eq ?v noreste)))
    =>
    (modify ?sit (humedad alta)))

(defrule humedad2
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(viento ?v)(prediccion nil))
    (test (or (eq ?v oeste)(eq ?v sur)(eq ?v suroeste)))
    =>
    (modify ?sit (humedad baja)))

(defrule humedad3
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(viento ?v)(prediccion nil))
    (test (or (eq ?v noroeste)(eq ?v norte)))
    =>
    (modify ?sit (humedad media)))

(defrule viento1
    (declare (salience 50)(no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(viento ?v)(nubes nil)(prediccion nil))
    (test (or (eq ?v oeste)(eq ?v sur)(eq ?v suroeste)))
    =>
    (modify ?sit (nubes cirroestratos)))

(defrule viento2
    (declare (salience 50)(no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(viento ?v)(nubes nil)(prediccion nil))
    (test (and (neq ?v oeste)(neq ?v sur)(neq ?v suroeste)))
    =>
    (modify ?sit (nubes cumulos)))

(defrule presion
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(variacionPresion ?vp)(prediccion nil))
    (test (or (eq ?vp sube)(eq ?vp baja)))
    =>
    (modify ?sit (viento debil)))

(defrule presion2
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(variacionPresion ?vp)(prediccion nil))
    (test (or (eq ?vp mantiene)))
    =>
    (modify ?sit (viento calma)))

(defrule variacionPresion1
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(velocidadViento ?vv)(presion ?p)(prediccion nil))
    (test (and (eq ?p alta)(eq ?vv fuerte)))
    =>
    (modify ?sit (variacionPresion baja)))

(defrule variacionPresion2
    (declare (salience 50) (no-loop TRUE))
    ?sit <- (SITUACION (codigo ?c)(velocidadViento ?vv)(presion ?p)(prediccion nil))
    (test (and (eq ?p baja)(eq ?vv fuerte)))
    =>
    (modify ?sit (variacionPresion sube)))

(defrule pregunta-nubes
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(nubes nil)))
    ?sit <- (SITUACION (codigo ?c)(nubes nil)(prediccion nil))
    =>
    (printout t "Dime que nubes hay ahora (despejado estratos nimboestratos cirroestratos cumulos cumulonimbos): " crlf)
    (modify ?sit (nubes (read))))

(defrule pregunta-humedad
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(humedad nil)))
    ?sit <- (SITUACION (codigo ?c)(humedad nil)(prediccion nil))
    =>
    (printout t "Dime que humedad hay ahora(baja media alta): " crlf)
    (modify ?sit (humedad (read))))

(defrule pregunta-presion
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(presion nil)))
    ?sit <- (SITUACION (codigo ?c)(presion nil)(prediccion nil))
    =>
    (printout t "Dime que presion hay ahora(baja normal alta): " crlf)
    (modify ?sit (presion (read))))

(defrule pregunta-viento
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(viento nil)))
    ?sit <- (SITUACION (codigo ?c)(viento nil)(prediccion nil))
    =>
    (printout t "Dime de donde viene el viento ahora(norte noreste este sureste sur suroeste oeste noroeste): " crlf)
    (modify ?sit (viento (read))))

(defrule pregunta-vel
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(velocidadViento nil)))
    ?sit <- (SITUACION (codigo ?c)(velocidadViento nil)(prediccion nil))
    =>
    (printout t "Dime que velocidad tiene el viento ahora(calma debil fuerte): " crlf)
    (modify ?sit (velocidadViento (read))))

(defrule pregunta-var
    (declare (salience 0)(no-loop TRUE))
    (exists (need-SITUACION (codigo ?c)(variacionPresion nil)))
    ?sit <- (SITUACION (codigo ?c)(variacionPresion nil)(prediccion nil))
    =>
    (printout t "Dime que tendencia tiene la presion(sube baja mantiene): " crlf)
    (modify ?sit (variacionPresion (read))))

(reset)
(facts)
(run)
