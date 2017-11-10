import Text.Parsec
import Text.Parsec.String (Parser)
import Data.Either

commentaire :: Parser String
commentaire = char '#' >> (many $ noneOf "\n")


testerCommentaire (x:xs) = show x ++ ":" ++ testerCommentaire xs
testerCommentaire [] = ""

data String2 = Chaine [Char] deriving Show


string2 :: Parser String2
--string = between (char '[') (char ']') (many $ noneOf ("[]\127"++['\0'..'\31']))



exp = term - exp | term + exp | term
term = unit * term | unit / term | unit
unit = - exp | ( exp ) | int


string2 = 
	do
		char '['
		str <- many letter
		char ']'
		return (Chaine str)

myParser str = parse string2 "" str 


parserT :: Parser String
parserT =
	do
		x <- many digit
		tt <- many $ oneOf " "
		op <- oneOf "\43\47"
		ttt <- many $ oneOf " "
		y <- many digit
		return (x ++ [op] ++ y)
		
		
--mangeEspace :: Parser String
--mangeEspace =
--	try
--	(do
	--	spaces
	--	x <- oneOf (['\40'..'\122'])
	--	y <- mangeEspace
	--	return (y))
--	<|>
--	eof


data Exp = Add Exp Exp | Mult Exp Exp | Constante Double

eval (Add a b) = a+b
eval (Mult a b) = a*b


let x= Add 4 5

eval x


mangeEspaceFonc :: Parser String
mangeEspaceFonc = 
	do
		spaces
		sepEndBy (anyChar) spaces
		

--mangeEspace str = parse mangeEspaceFonc "" str

mangeEspace str = head (rights ((parse mangeEspaceFonc "" str):[])) 


tarace :: Parser String
tarace = 
	do
		d <- oneOf "-" <|> many $ oneOf "0123456789()"
		a <- many $ oneOf "0123456789()"
		op <- oneOf "+-*/"
		b <- many $ oneOf "0123456789()"
		return ("a = " ++ a ++ "  b = " ++ b)


exp = 
	do
		x<-T
		y<-exp' x
		return y

exp':: Parser Exp
exp' x =
	do
		char '+'
		x1<-T
		y<-exp' x1
		return (Add y x)
		<|>
		return x


test str = head (rights ((parse tarace "" (mangeEspace str)):[])) 
		

--firstPar :: String -> String
--firstPar str = 
--	do
--		head (rights ((mangeEspace str):[]))
--		tt <- anyChar
--		return tt