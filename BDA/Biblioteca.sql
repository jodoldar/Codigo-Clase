-------------------------
--TABLA BIBLIOTECA-------
-------------------------

--Ejercicio 1
SELECT A.nombre
FROM Autor A
WHERE A.nacionalidad = 'Argentina';

--Ejercicio 2
SELECT O.titulo
FROM Obra O
WHERE O.titulo LIKE '%mundo%';

--Ejercicio 3
SELECT L.id_lib, L.NUM_OBRAS
FROM Libro L
WHERE L."AÑO"<1990 
  AND L.NUM_OBRAS > 1;
  
--Ejercicio 4
SELECT COUNT(DISTINCT L.id_lib)
FROM Libro L
WHERE L."AÑO" IS NOT NULL;

--Ejercicio 5
SELECT COUNT(*)
FROM Libro L
WHERE L.NUM_OBRAS >1;

--Ejercicio 6
SELECT L.id_lib
FROM Libro L
WHERE L."AÑO" = 1997
  AND L.TITULO IS NULL;
  
--Ejercicio 7
SELECT L.titulo
FROM Libro L
WHERE L.titulo IS NOT NULL
ORDER BY L.titulo DESC;

--Ejercicio 8
SELECT SUM(L.num_obras)
FROM Libro L
WHERE L."AÑO" BETWEEN 1990 AND 1999;

--Ejercicio 9
SELECT COUNT(DISTINCT A.autor_id)
FROM Escribir A, Obra O
WHERE A.COD_OB = O.cod_ob
  AND O.TITULO LIKE '%ciudad%';
  
--Ejercicio 10
SELECT O.titulo
FROM Obra O, Escribir E, Autor A
WHERE O.COD_OB = E.COD_OB
  AND E.AUTOR_ID = A.autor_id
  AND A.NOMBRE = 'Camús, Albert';
  
--Ejercicio 11
SELECT A.NOMBRE
FROM Autor A, Escribir E, Obra O
WHERE A.AUTOR_ID = E.AUTOR_ID
  AND E.COD_OB = O.COD_OB
  AND O.TITULO = 'La tata';
  
--Ejercicio 12
SELECT DISTINCT A.nombre
FROM Amigo A, Leer L, Obra O, Escribir E
WHERE A.NUM = L.NUM
  AND L.COD_OB = O.cod_ob 
  AND O.cod_ob = E.cod_ob
  AND E.AUTOR_ID = 'RUKI';
  
--Ejercicio 13
SELECT DISTINCT L.id_lib, L.titulo
FROM Libro L, ESTA_EN E
WHERE L.TITULO IS NOT NULL
  AND L.ID_LIB = E.ID_LIB;

--Ejercicio 14
SELECT O.TITULO, A.nombre
FROM Obra O, Autor A, Escribir E
WHERE O.COD_OB = E.COD_OB
  AND E.AUTOR_ID = A.AUTOR_ID
  AND A.AUTOR_ID IN (SELECT A2.AUTOR_ID FROM Autor A2 WHERE A2.nacionalidad = 'Francesa');
  
--Ejercicio 15
SELECT COUNT(*)
FROM Autor A
WHERE A.AUTOR_ID NOT IN (SELECT E.AUTOR_ID FROM Escribir E);

--Ejercicio 16
SELECT A.NOMBRE
FROM Autor A
WHERE A.AUTOR_ID NOT IN (SELECT E.AUTOR_ID FROM Escribir E);

--Ejercicio 17
SELECT A.nombre
FROM Autor A
WHERE A.NACIONALIDAD = 'Española'
  AND 2<=(SELECT COUNT(*) FROM Escribir E
          WHERE E.autor_id = A.autor_id);
          
--Ejercicio 18
SELECT A.nombre
FROM Autor A, Escribir E, Obra O
WHERE A.NACIONALIDAD = 'Española'
  AND A.AUTOR_ID = E.AUTOR_ID
  AND E.COD_OB = O.COD_OB
  AND O.COD_OB IN (SELECT ES.cod_ob 
                    FROM Esta_en ES
                    WHERE 2<= (SELECT COUNT(DISTINCT ES2.ID_LIB) FROM Esta_en ES2 WHERE O.COD_OB = ES2.COD_OB));
                  
--Ejercicio 19
SELECT O.cod_ob, O.titulo
FROM Obra O
WHERE (SELECT COUNT(E.AUTOR_ID) FROM Escribir E WHERE E.COD_OB = O.COD_OB)>1;

--Ejercicio 20
SELECT A.nombre
FROM Amigo A
WHERE NOT EXISTS (SELECT * 
                  FROM Escribir E
                  WHERE E.autor_id = 'RUKI'
                    AND NOT EXISTS (SELECT * FROM Leer L WHERE L.COD_OB = E.COD_OB AND L.NUM = A.NUM));
                    
--Ejercicio 21
SELECT A.nombre
FROM Amigo A
WHERE NOT EXISTS (SELECT * 
                  FROM Escribir E
                  WHERE E.autor_id = 'GUAP'
                    AND NOT EXISTS (SELECT * FROM Leer L WHERE L.COD_OB = E.COD_OB AND L.NUM = A.NUM))
  AND EXISTS (SELECT * FROM Escribir E2 WHERE E2.autor_id <> 'GUAP');
  
--Ejercicio 25
SELECT A.nombre
FROM Amigo A
WHERE EXISTS(SELECT *
                  FROM Escribir E
                  WHERE E.autor_id = 'GUAP'
                    AND EXISTS (SELECT * FROM Leer L WHERE L.COD_OB = E.COD_OB AND L.NUM = A.NUM));
                    
--Ejercicio 26
SELECT A.nombre
FROM Amigo A
WHERE NOT EXISTS(SELECT * FROM Leer L, Escribir E, Leer L2, Escribir E2
                  WHERE L.num = A.num AND L.cod_ob = E.cod_ob
                   AND L2.num = A.num AND L2.cod_ob = E2.cod_ob
                   AND E2.autor_id <> E.autor_id);

--Ejercicio 29
SELECT L.titulo, L.id_lib
FROM Libro L, Esta_en E
WHERE L.id_lib = E.ID_LIB
  AND L.TITULO IS NOT NULL
GROUP BY L.titulo, L.id_lib
HAVING COUNT(E.COD_OB) >1;

--Ejercicio 30
SELECT A.nombre, COUNT(*)
FROM Amigo A, Leer L
WHERE A.NUM = L.num
GROUP BY A.nombre
HAVING COUNT(*)>3;

--Ejercicio 31
SELECT T.tematica, COUNT(*)
FROM Tema T, Obra O
WHERE T.tematica = O.tematica 
GROUP BY T.tematica
ORDER BY T.tematica;

--Ejercicio 33
SELECT A.nombre
FROM Autor A, Escribir E
WHERE A.autor_id = E.autor_id
GROUP BY A.nombre
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM Autor A2, Escribir E2
                    WHERE A2.AUTOR_ID = E2.autor_id
                    GROUP BY A2.autor_id);
                    
--Ejercicio 34
SELECT A.nacionalidad
FROM Autor A
GROUP BY A.nacionalidad
HAVING COUNT(*) = (SELECT MIN(COUNT(*))
                  FROM Autor A2
                  GROUP BY A2.nacionalidad);
                  
--Ejercicio 35
SELECT A.nombre
FROM Amigo A, Leer L
WHERE A.num = L.NUM
GROUP BY A.nombre
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM Amigo A2, Leer L2
                    WHERE A2.num = L2.num
                    GROUP BY A2.nombre);
                    
--Ejercicio 36
SELECT L.titulo, L.id_lib
FROM Libro L
WHERE L.titulo IS NOT NULL
AND (SELECT COUNT(E.cod_ob) FROM Esta_en E WHERE E.id_lib = L.ID_LIB) =1;

--Ejercicio 37
SELECT DISTINCT L.titulo
FROM Libro L
WHERE L.titulo IS NOT NULL
UNION
SELECT DISTINCT O.titulo
FROM Libro L2,Obra O,Esta_en E
WHERE L2.titulo IS NULL
  AND L2.id_lib = E.id_lib AND E.cod_ob = O.cod_ob;
  
--Ejercicio 38
SELECT A.nombre
FROM Amigo A
WHERE A.num IN (SELECT L.num FROM Leer L, Escribir E WHERE L.cod_ob = E.cod_ob AND E.AUTOR_ID = 'CAMA');

--Ejercicio 39
SELECT A.nombre
FROM Amigo A
WHERE NOT A.num IN (SELECT L.num FROM Leer L, Escribir E WHERE L.cod_ob = E.cod_ob AND E.AUTOR_ID = 'CAMA');

--Ejercicio 40
SELECT A.nombre
FROM Amigo A
WHERE A.num NOT IN (SELECT L.num FROM Leer L, Escribir E WHERE L.cod_ob = E.cod_ob AND E.AUTOR_ID = 'CAMA')
 AND A.num IN (SELECT L2.num FROM Leer L2);
