import Text.Parsec
import Text.Parsec.String (Parser)
import Data.Either


-- data String2 = Chaine [Char] deriving Show

-- exp = term - exp | term + exp | term
-- term = unit * term | unit / term | unit
-- unit = - int | int | - exp | ( exp )


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
                Number Double deriving Show

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
    <|>
    try(
        do
            a <- term
            return (Term a)
    )
    


term :: Parser ExprType
term = 
    try(
    do
       a <- unit
       char '*'
       b <- term
       return (Mult a b)
    )
    <|>
    try(
        do
            a <- unit
            char '/'
            b <- term
            return (Div a b)
    )
    <|>
    try(
        do
            a <- unit
            return (Unit a)
    )



unit :: Parser ExprType
unit = 
    try(
        do
            char '-'
            x <- digit
            y <- many digit
            char '.'
            x2 <- digit
            y2 <- many digit
            return  (Negative (Number (read ((x:y)++"."++(x2:y2))::Double)))
    )
    <|>
    try(
        do
            x <- digit
            y <- many digit
            char '.'
            x2 <- digit
            y2 <- many digit
            return (Number (read ((x:y)++"."++(x2:y2))::Double))
    )
    <|>
    try(
        do
            char '-'
            x <- digit
            y <- many digit
            return  (Negative (Number (read (x:y)::Double)))
    )
    <|>
    try(
        do
            x <- digit
            y <- many digit
            return (Number (read (x:y)::Double))
    )
    <|>
    try(
        do
            x <- digit
            y <- many digit 
            return  (Number (read (x:y)::Double))
    )
    <|>
    try(
    do
        char '-'
        a <- expr
        return  (Negative a)
    )
    <|>
    try(
        do
            char '('
            a <- expr
            char ')'
            return a
    )
    
fromDigits = foldl addDigit 0
    where addDigit num d = 10*num + d




-- test str = head (rights ((parse tarace "" (mangeEspace str)):[])) 
		

--firstPar :: String -> String
--firstPar str = 
--	do
--		head (rights ((mangeEspace str):[]))
--		tt <- anyChar
--		return tt