module Practica3 where

import Data.Char

nextchar :: Char -> Char
nextchar c = chr ((ord c) + 1)

nextchar' :: Char -> Char
nextchar' c = succ c

fact :: Int -> Int
fact 0 = 1
fact n = n * fact(n-1)

numCbetw2 :: Char -> Char -> Int
numCbetw2 a b = abs(((ord b)-(ord a)))-1

sumatorio :: Int -> Int -> Int
sumatorio ini fin
    |ini==fin = ini
    |otherwise = ini + (sumatorio(ini+1) fin) 

max' :: (Ord a)=> a -> a -> a
max' x y
    |x >= y = x
    |x < y = y

leapyear :: Int -> Bool
leapyear x 
    |x`mod`400 == 0 = True
    |x`mod`100 == 0 = False 
    |x`mod`4 == 0 = True
    |otherwise = False

daysAmonth :: Int -> Int -> Int
daysAmonth x y 
    |x<8 && odd x = 31
    |x>=8 && even x = 31
    |x==2 = (if leapyear y then 29 else 28)
    |otherwise = 30

remainder :: Int -> Int -> Int
remainder x y 
    |x>y = (x-y) `remainder` y
    |x==y = 0
    |otherwise = x

sumFacts :: Int -> Int
sumFacts x 
    |x<=0 = 1
    |otherwise = fact x  + sumFacts(x-1)