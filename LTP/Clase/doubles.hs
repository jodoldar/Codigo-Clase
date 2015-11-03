import Data.Char
import Data.Numbers.Primes

doubleMe x = x + x
doubleUs x y = doubleMe x + doubleMe y
doubleSmallNumber x = if x > 100 then x else x*2
doubleSmallNumber' x = (if x > 100 then x else x*2) + 1
conanO'Brien = "¡Soy yo, Conan O'Brien!"
length' xs = sum [1 | _ <- xs]
removeNonUppercase st = [ c | c <- st, c `elem` ['A'..'Z']]

ordMay x y = if x > y then (y, x) else (x, y)

siglet :: Char -> Char 
siglet x 
        | (not.isAlpha) x = x
        | x=='Z' = 'A'
        | x=='z' = 'a'
        | otherwise = chr((ord x) + 1)

calculaEdad (da, ma, aa) (dn, mn, an) 
        |ma==mn && da<dn = aa-an-1
        |ma==mn = aa-an
        |ma>mn = aa-an
        |otherwise = aa-an-1

sigma :: Int -> Int -> Int -> Int
sigma f a b 
        | a < b = f + (sigma f (a+1) b)
        |otherwise = f

pi' :: Int -> Int -> Int -> Int
pi' f a b
        | a < b = f * (pi' f (a+1) b)
        |otherwise = f

divisores n = [i|i<-[1..n],n`mod`i==0] 