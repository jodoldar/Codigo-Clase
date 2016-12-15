---------------------
--TABLA CICLISTA-----
---------------------

--Ejercicio 24
SELECT E.netapa
FROM Etapa E
WHERE NOT EXISTS(SELECT * FROM Puerto P2 WHERE E.netapa = P2.netapa AND P2.altura <=700)
AND EXISTS(SELECT * FROM Puerto P WHERE P.NETAPA = E.NETAPA);

--EJERCICIO 25
SELECT E.nomeq, E.director
FROM Equipo E
WHERE NOT EXISTS(SELECT * FROM Ciclista C WHERE C.nomeq = E.nomeq AND C.edad <= 25)
AND EXISTS (SELECT * FROM Ciclista C2 WHERE C2.nomeq = E.nomeq)
ORDER BY E.nomeq;

--EJERCICIO 26
SELECT C.dorsal, C.nombre
FROM Ciclista C
WHERE NOT EXISTS(SELECT * FROM Etapa E WHERE E.dorsal = C.DORSAL AND E.km <=170)
AND EXISTS (SELECT * FROM Etapa E2 WHERE E2.dorsal = C.dorsal)
ORDER BY C.dorsal;

--EJERCICIO 27
SELECT C.nombre
FROM Ciclista C, Etapa E
WHERE C.dorsal = E.dorsal AND 
NOT EXISTS(SELECT * FROM Puerto P WHERE P.netapa = E.netapa AND NOT P.dorsal = C.dorsal)
AND EXISTS(SELECT * FROM PUERTO P2 WHERE P2.netapa = E.netapa)
ORDER BY C.nombre;

--EJERCICIO 28
SELECT E.nomeq
FROM Equipo E
WHERE NOT EXISTS (SELECT * FROM Ciclista C WHERE C.nomeq = E.nomeq AND NOT (C.dorsal IN (SELECT L.dorsal FROM Llevar L) OR C.dorsal IN (SELECT P.dorsal FROM Puerto P)))
AND EXISTS (SELECT * FROM Ciclista C WHERE C.nomeq = E.nomeq);

--EJERCICIO 29 --NO VA
SELECT M.codigo, M.color
FROM Maillot M
WHERE EXISTS (SELECT * 
              FROM Llevar L2, Ciclista C2 
              WHERE M.codigo = L2.codigo 
                  AND L2.dorsal = C2.dorsal)
AND NOT EXISTS (SELECT * 
                FROM Llevar L,Ciclista C, Equipo E 
                WHERE M.codigo = L.codigo 
                      AND L.dorsal = C.dorsal 
                      AND C.nomeq = E.nomeq 
                      AND EXISTS(SELECT * 
                                  FROM Equipo E3,Llevar L3,Ciclista C3 
                                  WHERE L3.codigo = M.codigo
                                        AND C3.dorsal = L3.dorsal
                                        AND C3.nomeq = E3.nomeq
                                        AND NOT (E3.nomeq = E.nomeq)));

--EJERCICIO 30
SELECT E.nomeq
FROM Equipo E
WHERE NOT EXISTS (SELECT * FROM Ciclista C WHERE C.nomeq = E.nomeq AND NOT EXISTS(SELECT * FROM PUERTO P WHERE P.dorsal = C.dorsal AND P.categoria = '1ª')
                                                                    AND EXISTS (SELECT * FROM Puerto P2 WHERE p2.dorsal = C.dorsal))
AND EXISTS (SELECT * FROM Ciclista C2 WHERE C2.nomeq = E.nomeq);

--EJERCICIO 31
SELECT E.netapa, COUNT(P.netapa)
FROM Etapa E, Puerto P
WHERE E.netapa = P.netapa
GROUP BY E.netapa
ORDER BY E.netapa;

--EJERCICIO 32
SELECT E.nomeq, COUNT(C.nombre)
FROM Equipo E, Ciclista C
WHERE C.nomeq = E.nomeq
GROUP BY E.nomeq
ORDER BY E.nomeq;

--EJERCICIO 33
SELECT DISTINCT E.nomeq, COUNT(C.dorsal)
FROM Equipo E LEFT JOIN Ciclista C ON E.nomeq = C.nomeq
GROUP BY E.nomeq;
  
--EJERCICIO 34
SELECT E.director, E.nomeq
FROM Equipo E, Ciclista C
WHERE C.nomeq = E.nomeq
GROUP BY E.nomeq, E.director
HAVING COUNT(C.dorsal)>3 AND AVG(C.edad)<=30
ORDER BY E.director;

--EJERCICIO 35
SELECT DISTINCT C.nombre, COUNT(C.dorsal)
FROM Ciclista C, Equipo E, Etapa Et
WHERE C.nomeq = E.nomeq
AND (SELECT COUNT(C1.dorsal) FROM Ciclista C1 WHERE C1.nomeq = C.nomeq) > 5
AND C.dorsal = Et.dorsal
GROUP BY C.nombre, E.nomeq
ORDER BY C.nombre;

--EJERCICIO 36
SELECT E.nomeq, AVG(C.edad)
FROM Equipo E, Ciclista C
WHERE C.nomeq = E.nomeq
GROUP BY E.nomeq
HAVING AVG(C.edad)= (SELECT MAX(AVG(C2.edad)) 
                    FROM Ciclista C2, Equipo E2 
                    WHERE C2.nomeq = E2.nomeq 
                    GROUP BY E2.nomeq);

--EJERCICIO 38
SELECT DISTINCT M.codigo, M.color
FROM Maillot M, Llevar L
WHERE M.codigo = L.codigo
AND L.dorsal IN (SELECT C.dorsal FROM Ciclista C WHERE C.dorsal NOT IN (SELECT C2.dorsal FROM Ciclista C2, Etapa E WHERE C2.dorsal = E.dorsal));

--EJERCICIO 39
SELECT E.netapa, E.salida, E.llegada
FROM Etapa E
WHERE E.km > 190
AND E.netapa IN (SELECT E2.netapa FROM Etapa E2, Puerto P WHERE P.netapa = E2.netapa GROUP BY E2.netapa HAVING COUNT (P.nompuerto)>1);

--EJERCICIO 40
SELECT DISTINCT C.dorsal, C.nombre
FROM Ciclista C, Llevar L2
WHERE C.dorsal = L2.dorsal
AND L2.codigo IN (SELECT DISTINCT M.codigo FROM Ciclista C2, LLevar L, Maillot M WHERE C2.dorsal = L.dorsal AND L.codigo = M.codigo AND C2.dorsal = 20)
ORDER BY C.dorsal;


--EJERCICIO 41
SELECT DISTINCT C.dorsal, C.nombre
FROM Ciclista C, Llevar L
WHERE C.dorsal <> 20
AND C.dorsal = L.dorsal
AND L.codigo IN (SELECT DISTINCT M.codigo
                  FROM Llevar L1, Maillot M
                  WHERE L1.dorsal = 20 AND L1.codigo = M.codigo)
ORDER BY C.dorsal;

--EJERCICIO 42
SELECT DISTINCT C.dorsal, C.nombre
FROM Ciclista C, Llevar L
WHERE C.dorsal = L.dorsal
AND L.codigo IN (SELECT M.codigo FROM Maillot M WHERE M.codigo NOT IN (SELECT M2.codigo
                                                                          FROM Maillot M2, Llevar L2
                                                                          WHERE M2.codigo = L2.codigo
                                                                            AND L2.dorsal = 20));
                                                                            
--EJERCICIO 44
SELECT C.dorsal, C.nombre
FROM Ciclista C, Llevar L
WHERE C.dorsal = L.dorsal
AND L.codigo IN (SELECT M2.codigo FROM Maillot M2, Llevar L2 WHERE M2.codigo = L2.codigo AND L2.dorsal = 20);

--EJERCICIO 46
SELECT C.dorsal, C.nombre
FROM Ciclista C, Llevar L
WHERE C.dorsal = L.dorsal
GROUP BY C.dorsal, C.nombre
HAVING COUNT(DISTINCT L.codigo)= (SELECT COUNT(DISTINCT L2.codigo) FROM Llevar L2 WHERE L2.dorsal = 1)-3;

--EJERCICIO 47
SELECT DISTINCT E.netapa, E.km
FROM Etapa E, Puerto P
WHERE E.netapa = P.netapa
ORDER BY E.netapa;