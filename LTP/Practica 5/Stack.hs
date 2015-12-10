
module Stack (Stack, empty, push, pop, top, isEmpty) where
data Stack a = EmptyStack | Stk a (Stack a) deriving Show
--data Stack a       	= Stk [a]
empty            = EmptyStack
push x s  = Stk x s
pop (Stk _ s) = s
top (Stk x s) = x
isEmpty (Stk x s) = False
isEmpty EmptyStack = True

--show :: (Show a) => (Stack a) -> String

--instance (Show a) => Show (Stack a) where
--	show EmptyStack = "|"
--	show (Stk x y) = (show x) ++ " <- " ++ (show y)