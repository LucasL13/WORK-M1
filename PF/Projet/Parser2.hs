import Text.Parsec
import Text.Parsec.String (Parser)
import Data.Either


-- exp = term + exp | term - exp | term
-- term = positive * exp | positive / exp | negative * exp | negative / exp | positive | negative
-- positive = int | (exp)
-- negative = - int | - (exp)


-- data Exp = Add Exp Exp | Mult Exp Exp | Constante Double
-- eval (Add a b) = a+b
-- eval (Mult a b) = a*b

-- let x= Add 4 5
-- eval x

data ExprType = Add ExprType ExprType | 
                Minus ExprType ExprType | 
                Mult ExprType ExprType | 
                Div ExprType ExprType | 
                Term ExprType | 
                Unit ExprType |
                Negative ExprType |
                Number Int deriving Show

eval (Add a b) = eval a + eval b
eval (Minus a b) = eval a - eval b
eval (Term a) = eval a
eval (Number a) = a
eval (Unit a) = eval a
eval (Negative a) = -1 * (eval a)

mangeEspaceFonc :: Parser String
mangeEspaceFonc = 
	do
		spaces
		sepEndBy (anyChar) spaces
		
mangeEspace str = head (rights ((parse mangeEspaceFonc "" str):[])) 

test str =  eval (head (rights ((parse expr "" str):[])))


expr :: Parser ExprType
expr = 
    try(
        do
            a <- term
            return a
    )
    <|>
    try(
        do
            a <- term
            char '+'
            b <- expr
            return (Add a b)
    )
    <|>
    try(
        do
            a <- term
            char '-'
            b <- expr
            return (Minus a b)
    )
   

term :: Parser ExprType
term =
    try(
        do
            a <- positive
            return a
    )
    <|>
    try(
        do
            a <- negative
            return a
    )
    <|>
    try(
        do
            a <- negative
            char '*'
            b <- expr
            return (Mult a b)
    )
    <|>
    try(
        do
            a <- negative
            char '/'
            b <- expr
            return (Div a b)
    )
    <|>
    try(
        do
            a <- positive
            char '*'
            b <- expr
            return (Mult a b)
    )
    <|>
    try(
        do
            a <- positive
            char '/'
            b <- expr
            return (Div a b)
    )
    

positive :: Parser ExprType
positive = 
    try(
        do
            char '('
            a <- expr
            char ')'
            return a
    )
    <|>
    try(
        do
            u <- digit
            a <- many digit
            return (Number (read (u:a)::Int))
    )
   


negative :: Parser ExprType
negative = 
    try(
        do
            char '-'
            char '('
            a <- expr
            char ')'
            return (Negative a)
    )
    <|>
    try(
        do
            char '-'
            u <- digit
            a <- many digit
            return (Negative (Number (read (u:a)::Int)))
    )


-- test str = head (rights ((parse tarace "" (mangeEspace str)):[])) 
		

--firstPar :: String -> String
--firstPar str = 
--	do
--		head (rights ((mangeEspace str):[]))
--		tt <- anyChar
--		return tt