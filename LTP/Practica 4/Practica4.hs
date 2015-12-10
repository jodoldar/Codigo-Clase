module Practica4 where

import Data.Char

repeat' :: a -> [a]
repeat' x = xs where xs = x:xs

take' :: Int -> [a] -> [a]
take' _ [] = []
take' n (x:t)
    | n<=0 = []
    |otherwise = x:take' (n-1) t

-- Ejercicio 1 
decBin :: Int -> [Int]
decBin x = if x < 2 then [x] else (x`mod`2):decBin(x`div`2)

--Ejercicio 2
binDec :: [Int] -> Int
binDec (x:[]) = x
binDec (x:y) = x + binDec y * 2

--Ejercicio 3
divisores :: Int -> [Int]
divisores x = divisoresAux x 1

divisoresAux :: Int -> Int -> [Int]
divisoresAux num contador
    |num == contador = [contador]
    |num`mod`contador == 0 = [contador] ++ (divisoresAux num (contador+1))
    |num`mod`contador /= 0 = (divisoresAux num (contador+1))

-- Método usando listas intensionales
divisores' :: Int -> Int
divisores' x = length[y | y <- [1..x],mod x y == 0]

--Ejercicio 4
member :: Int -> [Int] -> Bool
member _ [] = False
member n (x:xs)
    |n == x = True
    |otherwise = member n xs

-- member :: Int -> [Int] -> Bool
-- member x xs = if x `elem` xs then True else False

--memberAux :: Int -> [Int] -> Int -> Bool
--memberAux num lista contador
--    |contador == length[lista] = False
--    |(lista !! contador) == num = True
--    |otherwise = (memberAux num lista (contador+1)) 

--Ejercicio 5
isPrime :: Int -> Bool
isPrime x = length(divisores x)==2

primes :: Int -> [Int]
primes x = take x (primesAux 1 x)

primesAux :: Int -> Int -> [Int]
primesAux y x 
    | isPrime y = [y]++(primesAux (y+1) x)
    | otherwise = primesAux (y+1) x

-- Métodos usando listas intensionales
primos :: [Int]
primos = [x | x <- [1..],primo x]

primo :: Int -> Bool
primo x = divisores' x == 2

--Ejercicio 6
repeated :: Int -> [Int] -> Int
repeated x xs = length (filter (==x) xs)

--Ejercicio 7
concat' :: [[a]] -> [a]
concat' [] = []
concat' (x:ys) = x ++ concat' ys

--Ejercicio 8
selectEven :: [Int] -> [Int]
selectEven xs = [x | x <- xs, even x]

--selectEven' :: [Int] -> [Int]
--selectEven' xs = filter (even) xs

--Ejercicio 9
selectEvenPos :: [Int] -> [Int]
selectEvenPos (x:xs)  = auxEvenPos (x:xs) 0

auxEvenPos :: [Int] -> Int -> [Int]
auxEvenPos [] _ = []
auxEvenPos (x:xs) par 
    | par `mod` 2 == 0 =  [x] ++ (auxEvenPos xs (par+1))
    |otherwise = auxEvenPos xs (par+1)

--Ejercicio 10
iSort :: [Int] -> [Int]
iSort [] = []
iSort (x:xs) = ins x (iSort xs)

ins :: Int -> [Int] -> [Int]
ins x [] = [x]
ins x (y:ys) 
    | x < y = (x:y:ys)
    |otherwise = [y] ++ ins x ys

--Ejercicio 11
doubleAll :: [Int] -> [Int]
doubleAll x = map (*2) x

--Ejercicio 12
map' :: (a -> b) -> [a] -> [b]
map' p (x:xs) = [p x | x <- xs]

filter' :: (a -> Bool) -> [a] -> [a]
filter' p xs = [x | x <- xs, p x]

--Ejercicio 13
-- La expresion saca primero los impares de la lista de 1 a 10, luego 
-- los que quedan, se elevan al cuadrado y se suman todos los elementos.

--Ejercicio 14
type Person = String
type Book = String
type Database = [(Person,Book)]

exampleBase :: Database
exampleBase = [("Alicia","El nombre de la rosa"),("Juan","La hija del canibal"),
    ("Pepe","Odesa"),("Alicia","La ciudad de las bestias")]

obtain :: Database -> Person -> [Book]
obtain dBase thisPerson 
    = [book | (person,book) <- dBase, person == thisPerson]

borrow :: Database -> Book -> Person -> Database
borrow dBase book person = dBase ++ [(book,person)]

return' :: Database -> (Person,Book) -> Database
return' dBase (person,book) 
    = [(persona,books)|(persona,books) <- dBase, (person,book)/=(persona,books)]

--Ejercicio 15
data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show
numleaves :: Tree a -> Int
numleaves (Leaf x) = 1
numleaves (Branch a b) = numleaves a + numleaves b

--Ejercicio 16
symmetric :: Tree a -> Tree a
symmetric (Leaf b) = (Leaf b)
symmetric (Branch a b) = (Branch (symmetric b) (symmetric a))

--Ejercicio 17
listToTree :: [a] -> Tree a
listToTree lista = Branch (listToTree mitad1) (listToTree mitad2)
    where longitud = length lista
          mitad = div longitud 2
          mitad1 = take mitad lista
          mitad2 = drop mitad lista

treeToList :: Tree a -> [a]
treeToList (Leaf x) = [x]
treeToList (Branch a b) = (treeToList a) ++ (treeToList b)

--Ejercicio 18
insTree :: Int -> BinTreeInt -> BinTreeInt
insTree i Void = Node i Void Void
insTree i (Node a b c)
    | i <= a    = Node a (insTree i b) c
    | otherwise = Node a b (insTree i c)

--Ejercicio 19
creaTree :: [Int] -> BinTreeInt
creaTree [] = Void
creaTree (x:xs) = insTree x (creaTree xs)

--Ejercicio 20
treeElem :: Int -> BinTreeInt -> Bool
treeElem x Void = False
treeElem x (Node a b c)
    | x == a = True
    | otherwise = or [treeElem x b, treeElem x c]

data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

--Ejercicio 21
dupElem :: BinTreeInt -> BinTreeInt
dupElem Void = Void
dupElem (Node a b c) = (Node (2*a) (dupElem b) (dupElem c))

data Tree2 a = Branch2 a (Tree2 a) (Tree2 a) | Void2 deriving Show

--Ejercicio 22
countProperty :: (a -> Bool) -> (Tree2 a) -> Int
countProperty f Void2 = 0
countProperty f (Branch2 a b c)
    | f a == True = 1 + (countProperty f b) + (countProperty f c)
    | f a == False = (countProperty f b) + (countProperty f c)
    