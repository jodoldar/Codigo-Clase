--Ejercicio 1.- Obtener ordenados ascendentemente los códigos de los países de donde son los actores.
SELECT DISTINCT cod_pais
FROM CS_Actor
ORDER BY cod_pais;

--Ejercicio 2.- Obtener el código y el título de las películas de año anterior a 1970 que no estén basadas en ningún libro ordenadas por título.
SELECT cod_peli, titulo
FROM CS_Pelicula
WHERE anyo<=1970
    AND cod_lib IS NULL
ORDER BY cod_peli;

--Ejercicio 3.- Obtener el código y el nombre de los actores cuyo nombre incluye “John”.
SELECT nombre, cod_act
FROM CS_Actor
WHERE nombre LIKE ‘John%’;

--Ejercicio 4.- Obtener el código y el título de las películas de más de 120 minutos de la década de los 80.
SELECT cod_peli, titulo
FROM CS_Pelicula
WHERE anyo>=1980
    AND anyo<1990
    AND duracion>=120;

--Ejercicio 5.- Obtener el código y el título de las películas que estén basadas en algún libro y cuyo director se apellide ‘Pakula’.
SELECT cod_peli, titulo
FROM CS_Pelicula
WHERE director LIKE ‘%Pakula’
    AND cod_lib IS NOT NULL;

--Ejercicio 6.- ¿Cuántas películas hay de más de 120 minutos de la década de los 80?
SELECT COUNT(*)
FROM CS_Pelicula
WHERE duracion>=120
    AND anyo>=1980
    AND anyo<1990;

--Ejercicio 7.- ¿Cuántas películas se han clasificado de los géneros de código 'BB5' o 'GG4' o'JH6'.
SELECT COUNT(DISTINCT cod_peli)
FROM CS_Clasificacion
WHERE cod_gen = ‘BB5’
    OR cod_gen = ‘GG4’
    OR cod_gen = ‘JH6’;

--Ejercicio 8.- ¿De qué año es el libro más antiguo?
SELECT MIN(anyo)
FROM CS_Libro;

--Ejercicio 9.- ¿Cuál es la duración media de las películas del año 1987?
SELECT AVG(duracion)
FROM CS_Pelicula
WHERE anyo=1987;

--Ejercicio 10.- ¿Cuántos minutos ocupan todas las películas dirigidas por ‘Steven Spielberg’?
SELECT SUM(duracion)
FROM CS_Pelicula
WHERE director = ‘Steven Spielberg’;

--Ejercicio 11.- Obtener el código y el título de las películas en las que actúa un actor con el mismo nombre que el director de la película (ordenadas por título).
SELECT CS_Pelicula.cod_peli, CS_Pelicula.titulo
FROM CS_Actua, CS_Pelicula, CS_Actor
WHERE CS_Actua.cod_act = CS_Actor.cod_act
    AND CS_Actua.cod_peli = CS_Pelicula.cod_peli
    AND CS_Actor.nombre = CS_Pelicula.director
ORDER BY CS_Pelicula.titulo;

--Ejercicio 12.- Obtener el código y el título de las películas clasificadas del género de nombre ‘Comedia’ (ordenadas por título).
SELECT CS_Pelicula.cod_peli, CS_Pelicula.titulo
FROM CS_Pelicula, CS_Clasificacion, CS_Genero
WHERE CS_Clasificacion.cod_gen = CS_Genero.cod_gen
    AND CS_Pelicula.cod_peli = CS_Clasificacion.cod_peli
    AND CS_Genero.nombre = ‘Comedia’
ORDER BY CS_Pelicula.titulo;

--Ejercicio 13.- Obtener el código y el título de las películas basadas en algún libro anterior a 1950.
SELECT CS_Pelicula.cod_peli, CS_Pelicula.titulo
FROM CS_Pelicula, CS_Libro
WHERE CS_Pelicula.cod_lib = CS_Libro.cod_lib
    AND CS_Libro.anyo < 1950
ORDER BY CS_Pelicula.titulo;

--Ejercicio 14.- Obtener el código y el nombre de los países de los actores que actúan en películas clasificadas del género de nombre ‘Comedia’ (ordenados por nombre).
SELECT DISTINCT CS_Pais.cod_pais, CS_Pais.nombre
FROM CS_Pais, CS_Actor, CS_Clasificacion, CS_Genero, CS_Actua, CS_Pelicula
WHERE CS_Pelicula.cod_peli = CS_Clasificacion.cod_peli
    AND CS_Pelicula.cod_peli = CS_Actua.cod_peli
    AND CS_Genero.cod_gen = CS_Clasificacion.cod_gen
    AND CS_Actor.cod_act = CS_Actua.cod_act
    AND CS_Genero.nombre = ‘Comedia’
    AND CS_Actor.cod_pais = CS_Pais.cod_pais
ORDER BY CS_Pais.cod_pais;


------------------------------
--CONSULTAS CON SUBCONSULTAS
------------------------------

--Ejercicio 15-11.- Obtener el código y el título de las películas en las que actúa un actor con el mismo nombre que el director de la película (ordenadas por título).
SELECT P.cod_peli, P.titulo
FROM CS_PELICULA P
WHERE EXISTS (SELECT *
    FROM CS_ACTUA AC, CS_ACTOR A
    WHERE AC.cod_peli = P.cod_peli
    AND AC.cod_act = A.cod_act
    AND A.nombre = P.director);

--Ejercicio 15-12.-

--Ejercicio 15-13.-

--Ejercicio 15-14.-
SELECT PA.cod_pais, PA.nombre
FROM CS_PAIS PA
WHERE EXISTS (SELECT *
    FROM CS_ACTOR A, CS_ACTUA AC, CS_PELICULA P, CS_CLASIFICACION CL, CS_GENERO G
    WHERE A.cod_pais = PA.cod_pais
        AND CL.cod_peli = P.cod_peli
        AND CL.cod_gen = G.cod_gen
        AND AC.cod_act = A.cod_act
        AND AC.cod_peli = P.cod_peli
        AND G.nombre = 'Comedia'
    ORDER BY PA.nombre);

--Ejercicio 16.- Obtener el código y el nombre de los actores nacidos antes de 1950 que actúan con un papel ‘Principal’ en alguna película (ordenados por nombre).
SELECT A.cod_act, A.nombre
FROM CS_ACTOR A
WHERE EXISTS (SELECT *
    FROM CS_ACTUA AC
    WHERE EXTRACT(YEAR FROM A.fecha_nac) < 1950
        AND A.cod_act = AC.cod_act
        AND AC.papel = 'Principal');

--Ejercicio 17.- Obtener el código, el título y el autor de los libros en los que se ha basado alguna película de la década de los 90 (ordenados por título).
SELECT L.cod_lib, L.titulo, L.autor
FROM CS_LIBRO L
WHERE EXISTS (SELECT *
    FROM CS_PELICULA P
    WHERE P.cod_lib = L.cod_lib
        AND P.anyo >= 1990
        AND P.anyo < 2000);

--Ejercicio 18.- Obtener el código, el título y el autor de los libros en los que no se haya basado ninguna película.
SELECT L.cod_lib, L.titulo, L.autor
FROM CS_LIBRO L
WHERE NOT EXISTS (SELECT *
    FROM CS_PELICULA P
    WHERE P.cod_lib = L.cod_lib);

--Ejercicio 19.-Obtener el nombre del género o géneros a los que pertenecen películas en las que no actúa ningún actor (ordenados por nombre).
SELECT G.nombre
FROM CS_GENEROS G
WHERE G.cod_gen IN (SELECT CL.cod_gen
    FROM CS_CLASIFICACION CL
    WHERE CL.cod_peli NOT IN (SELECT A.cod_peli
        FROM ACTUA AC))
ORDER BY G.nombre;

--Ejercicio 20.- Obtener el título de los libros en los que se haya basado alguna película en la que no hayan actuado actores del país de nombre ‘USA’ (ordenados por título).
SELECT L.titulo
FROM CS_LIBRO L
WHERE L.cod_lib NOT IN (SELECT P.cod_peli
    FROM CS_PELICULA P, CS_ACTUA AC, CS_ACTOR A, CS_PAIS PA
    WHERE P.cod_peli = AC.cod_peli
        AND AC.cod_act = A.cod_act
        AND A.cod_pais = PA.cod_pais
        AND PA.nombre IS NOT 'USA');
        
----------------------------------------
--CONSULTAS UNIVERSALMENTE CUANTIFICADAS
----------------------------------------

--Ejercicio 32.- Obtener el código y el nombre de los países con actores y tales que todos los actores de ese país han nacido en el siglo XX (ordenados por nombre).
SELECT P.cod_pais, P.nombre
FROM CS_PAIS P
WHERE EXISTS (SELECT * FROM CS_ACTOR A WHERE A.cod_pais = P.cod_pais) 
	AND NOT EXISTS (SELECT *
					FROM CS_ACTOR A1
					WHERE A1.cod_pais=p.cod_pais
						AND EXTRACT (YEAR FROM A1.fecha_nac) NOT LIKE '19__')
ORDER BY P.nombre;

--Ejercicio 33.- Obtener el código y el nombre de los actores tales que todos los papeles que han tenido son de ‘Secundario’. Sólo interesan aquellos actores que hayan actuado en alguna película.
SELECT A.cod_act, A.nombre
FROM CS_ACTOR A
WHERE EXISTS (SELECT * FROM CS_ACTUA AC
              WHERE AC.papel = 'Secundario'
              AND A.cod_act = AC.cod_act) 
  AND NOT EXISTS (SELECT * FROM CS_ACTUA AC1
              WHERE AC1.papel = 'Principal'
              AND A.cod_act = AC1.cod_act)
ORDER BY A.nombre;

--Ejercicio 34.- Obtener el código y el nombre de los actores que han aparecido en todas las películas del director ‘Guy Ritchie’ (sólo si ha dirigido al menos una).
SELECT A.cod_act, A.nombre
FROM CS_ACTOR A
WHERE EXISTS (SELECT * FROM CS_PELICULA P WHERE P.director = 'Guy Ritchie')
  AND NOT EXISTS (SELECT * FROM CS_PELICULA P1
              WHERE P1.director = 'Guy Ritchie'
              AND NOT EXISTS (SELECT * 
                              FROM CS_ACTUA AC 
                              WHERE AC.cod_act = A.cod_act
                                AND AC.cod_peli = P1.cod_peli));
                                
--Ejercicio 35.- Resolver la consulta anterior pero para el director de nombre ‘John Steel’.
SELECT A.cod_act, A.nombre
FROM CS_ACTOR A
WHERE EXISTS (SELECT * FROM CS_PELICULA P WHERE P.director = 'John Steel')
  AND NOT EXISTS (SELECT * FROM CS_PELICULA P1
              WHERE P1.director = 'John Steel'
              AND NOT EXISTS (SELECT * 
                              FROM CS_ACTUA AC 
                              WHERE AC.cod_act = A.cod_act
                                AND AC.cod_peli = P1.cod_peli));
                                
--Ejercicio 36.- Obtener el código y el título de las películas de menos de 100 minutos en las que todos los actores que han actuado son de un mismo país.
SELECT P.cod_peli, P.titulo
FROM CS_PELICULA P
WHERE P.duracion < 100
  AND EXISTS (SELECT * 
                  FROM CS_ACTUA AC, CS_ACTOR A
                  WHERE AC.cod_peli = P.cod_peli
                  AND A.cod_act = AC.cod_act
                  AND EXISTS (SELECT *
                                  FROM CS_ACTOR A1, CS_ACTUA AC1
                                  WHERE AC1.cod_peli = P.cod_peli
                                  AND A1.cod_act = AC1.cod_act
                                  AND NOT EXISTS(SELECT *
                                                 FROM CS_ACTOR A2, CS_ACTUA AC2
                                                 WHERE AC2.cod_act = A2.cod_act
                                                 AND AC2.cod_peli = P.cod_peli
                                                 AND NOT A1.cod_pais = A2.cod_pais)));
                                                 
--Ejercicio 37.- Obtener el código, el título y el año de las películas en las que haya actuado algún actor si se cumple que todos los actores que han actuado en ella han nacido antes del año 1943 (hasta el 31/12/1942).
SELECT P.cod_peli, P.titulo, P.anyo
FROM CS_PELICULA P
WHERE EXISTS (SELECT *
              FROM CS_ACTUA AC, CS_ACTOR A
              WHERE AC.cod_peli = P.cod_peli
                AND A.cod_act = AC.cod_act)
      AND NOT EXISTS(SELECT *
                     FROM CS_ACTOR A1, CS_ACTUA AC1
                     WHERE AC1.cod_peli = P.cod_peli
                      AND AC1.cod_act = A1.cod_act
                      AND EXTRACT(YEAR FROM A1.fecha_nac) >= 1943)
ORDER BY P.titulo;

--Ejercicio 38.- Obtener el código y el nombre de cada país si se cumple que todos sus actores han actuado en al menos una película de más de 120 minutos. (Ordenados por nombre).

---------------------------
--CONSULTAS AGRUPADAS
---------------------------

--Ejercicio 39.- Obtener el código y el título del libro o libros en que se ha basado más de una película, indicando cuántas películas se han hecho sobre él.
SELECT L.cod_lib, L.titulo, COUNT(*)
FROM CS_LIBRO L, CS_PELICULA P
WHERE L.cod_lib = P.cod_lib
GROUP BY L.cod_lib,L.titulo
HAVING COUNT(*)>1;

--Ejercicio 40.- Obtener para cada género en el que se han clasificado más de 5 películas, el código y el nombre del género indicando la cantidad de películas del mismo y duración media de sus películas. (Ordenados por nombre). (La función ROUND redondea al entero más cercano).
SELECT G.cod_gen, G.nombre, COUNT(*), ROUND(AVG(P.duracion))
FROM CS_GENERO G, CS_PELICULA P, CS_CLASIFICACION CL
WHERE G.cod_gen = CL.cod_gen
  AND CL.cod_peli = P.cod_peli
GROUP BY G.cod_gen, G.nombre
HAVING COUNT(*) > 5
ORDER BY G.nombre;

--Ejercicio 41.- Obtener el código y el título de las películas de año posterior al 2000 junto con el número de géneros en que están clasificadas, si es que están en alguno. (Ordenadas por título).
SELECT P.cod_peli, P.titulo, COUNT(*)
FROM CS_PELICULA P, CS_CLASIFICACION CL
WHERE P.cod_peli = CL.cod_peli
  AND P.anyo > 2000
GROUP BY P.cod_peli, P.titulo
HAVING COUNT(*)>0
ORDER BY P.titulo;

--Ejercicio 42.- Obtener los directores que tienen la cadena ‘George’ en su nombre y que han dirigido exactamente dos películas.
SELECT P.director
FROM CS_PELICULA P
WHERE P.director LIKE '%George%'
GROUP BY P.director
HAVING COUNT(*)=2;

--Ejercicio 43.- Obtener para cada película clasificada exactamente en un género y en la que haya actuado algún actor, el código, el título y la cantidad de actores que actúan en ella.
SELECT P.cod_peli, P.titulo, COUNT(*)
FROM CS_PELICULA P, CS_CLASIFICACION CL, CS_ACTUA A
WHERE P.cod_peli = CL.cod_peli
  AND A.cod_peli = P.cod_peli
GROUP BY P.cod_peli, P.titulo
HAVING COUNT(DISTINCT CL.cod_gen)=1
ORDER BY P.titulo;

--Ejercicio 44.- Obtener el código y el nombre de todos los países con actores indicando cuántos actores de cada país han actuado en al menos una película de la década de los 60.
SELECT P.cod_pais, P.nombre, COUNT(DISTINCT A.cod_act)
FROM CS_PAIS P,CS_ACTUA AC, CS_ACTOR A, CS_PELICULA PE
WHERE P.cod_pais = A.cod_pais
  AND A.cod_act = AC.cod_act
  AND AC.cod_peli = PE.cod_peli
  AND PE.anyo >=1960
  AND PE.anyo <1970
GROUP BY P.cod_pais, P.nombre
HAVING COUNT(*)>0
ORDER BY P.nombre;

--Ejercicio 45.- Obtener el código, el nombre del género en el que hay clasificadas más películas (puede haber más de uno).
SELECT G.cod_gen, G.nombre
FROM CS_GENERO G, CS_CLASIFICACION CL
WHERE G.cod_gen = CL.cod_gen
GROUP BY G.cod_gen, G.nombre
HAVING COUNT(*)=(SELECT MAX(COUNT(*))
                  FROM CS_CLASIFICACION C1
                  GROUP BY C1.cod_gen);

--Ejercicio 46.- Obtener el código, el título y el autor del libro en el que se han basado más películas (puede haber más de uno).
SELECT L.cod_lib, L.titulo, L.AUTOR
FROM CS_LIBRO L, CS_PELICULA P  
WHERE P.cod_lib = L.cod_lib
GROUP BY L.cod_lib, L.titulo, L.autor
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM CS_LIBRO L2,CS_PELICULA P2
                    WHERE L2.cod_lib = P2.cod_lib
                    GROUP BY P2.cod_lib);
                    
--Ejericio 47.- Obtener el código y el nombre del país que más actores tiene que hayan participado exactamente en 2 películas.
SELECT P.cod_pais, P.nombre
FROM CS_PAIS P, CS_ACTOR A
WHERE A.cod_pais = P.cod_pais
  AND 2=(SELECT COUNT(*)
          FROM CS_ACTUA AC
          WHERE AC.cod_act = A.cod_act)
GROUP BY P.cod_pais, P.nombre
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM CS_ACTOR A1
                    WHERE 2=(SELECT COUNT(*) FROM CS_ACTUA AC1 WHERE AC1.cod_act = A1.cod_act)
                    GROUP BY A1.cod_pais);
                    
--------------------------------------------
----CONSULTAS CON CONCATENACIÓN-------------
--------------------------------------------

--Ejercicio 50.- Obtener para todos los países que hay en la base de datos, el código, el nombre y la cantidad de actores que hay de ese país.
SELECT P.cod_pais, P.nombre, COUNT(A.cod_act) AS Cuantos
FROM CS_PAIS P LEFT JOIN CS_ACTOR A ON P.cod_pais=A.cod_pais
GROUP BY P.cod_pais, P.nombre
ORDER BY P.nombre;

--Ejercicio 51.- Obtener el código y el título de todos los libros de la base de datos de año posterior a 1980 junto con la cantidad de películas a que han dado lugar.
SELECT L.cod_lib, L.titulo, COUNT(P.cod_peli) AS Cuantos
FROM CS_LIBRO L LEFT JOIN CS_PELICULA P ON L.cod_lib = P.cod_lib
WHERE L.anyo>1980
GROUP BY L.cod_lib, L.titulo;

--Ejercicio 52.- Obtener para todos los países que hay en la base de datos, el código, el nombre y la cantidad de actores que hay de ese país que hayan tenido un papel como “Secundario” en alguna película.
SELECT DISTINCT P.cod_pais, P.nombre, COUNT(DISTINCT A.cod_act) AS Cuantos
FROM (CS_ACTUA AC JOIN CS_ACTOR A ON A.cod_act = AC.cod_act AND AC.papel = 'Secundario') RIGHT JOIN CS_PAIS P ON A.cod_pais = P.cod_pais 
GROUP BY P.cod_pais, P.nombre
ORDER BY P.nombre;

--Ejercicio 53.- Obtener para cada película que hay en la base de datos que dure más de 140 minutos, el código, el título, la cantidad de géneros en los que está clasificado y la cantidad de actores que han actuado en ella.
SELECT P.cod_peli, P.titulo, COUNT(G.cod_gen) AS Generos, COUNT(A.cod_act) AS Cuantos
FROM CS_PELICULA P
WHERE P.duracion > 140

