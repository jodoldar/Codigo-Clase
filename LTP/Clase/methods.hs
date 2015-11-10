import Data.Char

type Fecha = (Int,Int,Int)
type Anyos = Int

type Point = (Float,Float)
type Path = [Point]

length' xs = sum [1 | _ <- xs]
removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z']]

ordena :: Int -> Int -> (Int,Int)
ordena x y = if x > y then (y, x) else (x, y)

siglet :: Char -> Char 
siglet x 
        | (not.isAlpha) x = x
        | x=='Z' = 'A'
        | x=='z' = 'a'
        | otherwise = chr((ord x) + 1)

third(_,_,x) = x

calculaEdad :: Fecha -> Fecha -> Anyos
calculaEdad x y = (third y) - (third x)

sigma :: Int -> Int -> Int -> Int
sigma f a b 
        | a < b = f + (sigma f (a+1) b)
        |otherwise = f

pi' :: Int -> Int -> Int -> Int
pi' f a b
        | a < b = f * (pi' f (a+1) b)
        |otherwise = f

-- Funcion que devuelve la posición de un elemento en una lista
position :: (Eq a) => a -> [a] -> Int
position 0 [] = 0
position x xs = head[pos | (x',pos)<-zip xs[1..],x'==x]

--Definir una funcion que calcule la longitud de un camino
pathLenght :: Path -> Float
pathLenght [] = 0
pathLenght xs = sum'[distance p q | (p,q)<-zip (init xs)(tail xs)]

-- Función que calcula el sumatorio desde 0 hasta un numero
sum' :: [Float]->Float
sum' [] = 0.0
sum' (x:xs) = x + sum' xs

-- Función que calcula la distancia entre 2 puntos en un espacio 2D
distance :: Point -> Point -> Float
distance(p1,p2)(q1,q2) = sqrt((p1-q1)*(p1-q1)+(p2-q2)*(p2-q2))

-- Función que mezcla dos listas de enteros
merge :: [Int] -> [Int] -> [Int]
merge a@(x:xs) b@(y:ys)
        |x<=y = x:merge xs b
        |otherwise = y:merge a ys
merge [] ys = ys
merge xs [] = xs

-- Función que elimina elementos duplicados en una lista de enteros
elimDups :: [Int]->[Int]
elimDups [] = []
elimDups [x] = [x]
elimDups (x:y:xs)
    |x==y = elimDups(y:xs)
    |otherwise = x:elimDups(y:xs)

-- Función que indica si el elemento a está en la lista.
any' :: (a -> Bool) -> [a] -> Bool
any' p xs = if length [ x | x <- xs, p x] >= 1 then True else False

-- Función que devuelve el conjunto de los elementos que se encuentran
--   en una lista.
all :: (a -> Bool) -> [a] -> Bool
all' p xs = if length [ x | x <- xs, p x] == length xs then True else False

-- Ejercicio 1.- Sobrecargar los operadores aritmeticos (+ y *) de la clase
--  Num para poder usarlos para sumar y multiplicar naturales.
(+)::Nat a=> a -> a -> a
















