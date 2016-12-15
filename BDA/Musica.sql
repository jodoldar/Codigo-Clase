--Base de datos Musica

--Ejercicio 1
SELECT COUNT(*) DISCOS
FROM Disco;

--Ejercicio 2
SELECT nombre
FROM Grupo G
WHERE G.pais<>'España';

--Ejercicio 3
SELECT titulo
FROM Cancion C
WHERE C.duracion>5;

--Ejercicio 4
SELECT DISTINCT funcion
FROM Pertenece
ORDER BY funcion;

--Ejercicio 5
SELECT nombre, num
FROM Club
ORDER BY num;

--Ejercicio 6
SELECT nombre, sede
FROM Club C
WHERE C.num>500;

--Ejercicio 7
SELECT C.nombre, C.sede, G.nombre
FROM Club C, Grupo G
WHERE c.cod_gru = G.cod
  AND G.pais='España';

--Ejercicio 8
SELECT A.nombre
FROM Artista A, Pertenece P, Grupo G
WHERE A.dni = P.dni
  AND P.cod = G.cod
  AND G.pais = 'España'
ORDER BY A.nombre;

--Ejercicio 9
SELECT DISTINCT D.nombre
FROM Disco D, Esta E, Cancion C
WHERE D.cod = E.cod
  AND E.can = C.cod
  AND C.duracion > 5
ORDER BY D.nombre;

--Ejercicio 10
SELECT C.titulo
FROM Cancion C, Esta E, Disco D
WHERE C.cod = E.can
  AND E.cod = D.cod
  AND C.titulo = D.nombre;
  
--Ejercicio 11
SELECT C.nombre, C.dir
FROM Companyia C, Disco D
WHERE D.cod_comp = C.cod
  AND D.nombre LIKE 'A%';
  
--Ejercicio 12
SELECT DISTINCT A.dni
FROM Artista A, Pertenece P, Pertenece P2,Grupo G
WHERE A.dni = P.dni
  AND A.dni = P2.dni
  AND P.cod <> P2.cod;
  
--Ejercicio 13
SELECT D.nombre
FROM Disco D, Grupo G
WHERE D.cod_gru = G.cod
  AND G.fecha IN (SELECT MIN(G2.fecha)
                        FROM Grupo G2);
                        
--Ejercicio 14
SELECT D.nombre
FROM Disco D, Grupo G
WHERE D.cod_gru = G.cod
  AND EXISTS (SELECT *
              FROM Club C
              WHERE C.cod_gru = G.cod
                AND C.num > 5000);
      
--Ejercicio 15
SELECT C.nombre, C.num
FROM Club C
WHERE C.num IN (SELECT MAX(C2.num)
                FROM Club C2);
                
--Ejercicio 16
SELECT C.titulo, C.duracion
FROM Cancion C
WHERE C.duracion IN (SELECT MAX(C2.duracion)
                      FROM Cancion C2);
      
------------------------------------
--Base de Datos BIBLIOTECA----------
------------------------------------
--Ejercicio 1
SELECT A.nombre
FROM Autor A
WHERE A.nacionalidad = 'Argentina';

--Ejercicio 2
SELECT O.titulo
FROM Obra O
WHERE O.titulo LIKE '%mundo%';

--Ejercicio 3
SELECT L.id_lib, L.num_obras
FROM Libro L
WHERE L.año < 1990
  AND L.num_obras > 1;
  
--Ejercicio 4
SELECT COUNT(L.año)
FROM Libro L;

--Ejercicio 5
SELECT COUNT(*)
FROM Libro L
WHERE L.num_obras > 1;

--Ejercicio 6
SELECT L.id_lib
FROM Libro L
WHERE L.titulo IS NULL
  AND L.año = 1997;

--Ejercicio 7
SELECT L.titulo
FROM Libro L
WHERE L.titulo IS NOT NULL
ORDER BY L.titulo DESC;

--EJERCICIO 8
SELECT SUM(L.num_obras)
FROM Libro L
WHERE L.año >= 1990
  AND L.año <= 1999;

--Ejercicio 9
SELECT COUNT (DISTINCT A.nombre)
FROM Autor A, Escribir E, Obra O
WHERE A.autor_id = E.autor_id
  AND E.cod_ob = O.cod_ob
  AND O.titulo LIKE '%ciudad%';

--Ejercicio 10
SELECT O.titulo
FROM Obra O, Escribir E, Autor A
WHERE O.cod_ob = E.cod_ob
  AND E.autor_id = A.autor_id
  AND A.nombre = 'Camús, Albert';

--Ejercicio 11
SELECT A.nombre
FROM Autor A, Escribir E, Obra O
WHERE A.autor_id = E.autor_id
  AND E.cod_ob = O.cod_ob
  AND O.titulo = 'La tata';
  
--Ejercicio 12
SELECT DISTINCT A.nombre
FROM Amigo A, Leer L, Obra O, Escribir E
WHERE A.num = L.num
  AND L.cod_ob = O.cod_ob
  AND O.cod_ob = E.cod_ob
  AND E.autor_id = 'RUKI';
  
--Ejercicio 13
SELECT DISTINCT L.id_lib, L.titulo
FROM Libro L, Esta_en E, Esta_en E2, Obra O, Obra O2
WHERE L.titulo IS NOT NULL
  AND L.id_lib = E.id_lib
  AND E.cod_ob = O.cod_ob
  AND L.id_lib = E2.id_lib
  AND E2.cod_ob = O2.cod_ob
  AND O.cod_ob <> O2.cod_ob;
  
--Ejercicio 14
SELECT O.titulo, A.nombre
FROM Obra O, Autor A, Escribir E
WHERE A.nacionalidad = 'Francesa'
  AND A.autor_id = E.autor_id
  AND E.cod_ob = O.cod_ob
  AND NOT EXISTS (SELECT *
                  FROM Escribir E2, Autor A2
                  WHERE A2.autor_id = E2.autor_id
                    AND E2.cod_ob = O.cod_ob
                    AND A.autor_id <> A2.autor_id)
ORDER BY A.nombre;

--Ejercicio 15
SELECT COUNT(*)
FROM Autor A
WHERE A.autor_id NOT IN (SELECT A2.autor_id
                          FROM Autor A2, Escribir E, Obra O
                          WHERE A2.autor_id = E.autor_id
                            AND E.cod_ob = O.cod_ob);
                          
--Ejercicio 16
SELECT A.nombre
FROM Autor A
WHERE A.autor_id NOT IN (SELECT A2.autor_id
                          FROM Autor A2, Escribir E, Obra O
                          WHERE A2.autor_id = E.autor_id
                            AND E.cod_ob = O.cod_ob);
                            
--Ejercicio 17
SELECT DISTINCT A.nombre
FROM Autor A, Escribir E, Escribir E2, Obra O2, Obra O
WHERE A.autor_id = E.autor_id
  AND A.autor_id = E2.autor_id
  AND E.cod_ob = O.cod_ob
  AND E2.cod_ob = O2.cod_ob
  AND O.cod_ob <> O2.cod_ob
  AND A.nombre IN (SELECT A2.nombre
                    FROM Autor A2
                    WHERE A2.nacionalidad = 'Española');
  
--Ejercicio 18
SELECT DISTINCT A.nombre
FROM Autor A, Escribir E, Obra O, Esta_en ES, Libro L
WHERE A.nacionalidad = 'Española'
  AND A.autor_id = E.autor_id
  AND E.cod_ob = O.cod_ob
  AND O.cod_ob = ES.cod_ob
  AND ES.id_lib = L.id_lib
  AND EXISTS (SELECT *
              FROM Esta_en ES2, Libro L2
              WHERE O.cod_ob = ES2.cod_ob
                AND ES2.id_lib = L2.id_lib
                AND L2.id_lib <>L.id_lib);
                
--Ejercicio 19
SELECT DISTINCT O.cod_ob, O.titulo
FROM Obra O, Escribir E, Autor A
WHERE O.cod_ob = E.cod_ob
  AND E.autor_id = A.autor_id
  AND EXISTS (SELECT *
              FROM Escribir E2, Autor A2
              WHERE O.cod_ob = E2.cod_ob
                AND E2.autor_id = A2.autor_id
                AND A2.autor_id <> A.autor_id);