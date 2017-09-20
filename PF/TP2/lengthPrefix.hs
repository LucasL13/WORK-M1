import  Data.Char(isAlpha)

lengthPrefixAlpha  ::  String  -> Int
lengthPrefixAlpha  xs = if xs == [] then 0 else
    if  isAlpha (head xs) then
        1 + lengthPrefixAlpha (tail xs)
    else 0



-- Conditions de garde
lengthPrefixAlpha2 :: String -> Int
lengthPrefixAlpha2 xs 
        | length xs == 0 = 0
        | isAlpha (head xs) = 1 + lengthPrefixAlpha2 (tail xs)
        | otherwise = 0


-- Filtrage par motifs
lengthPrefixAlpha3 :: String -> Int
lengthPrefixAlpha3 "" = 0;
lengthPrefixAlpha3 (x:xs) = if( isAlpha(x) ) then ( 1 + lengthPrefixAlpha3(xs) ) else 0

-- Filtrage par motifs (case of)
lengthPrefixAlpha4 :: String -> Int
lengthPrefixAlpha4 xs = case xs of
    "" -> 0
    (x:xs) -> if( isAlpha(x) ) then ( 1 + lengthPrefixAlpha4(xs) ) else 0


-- Avec takeWhile (mauvaise façon)
lengthPrefixAlpha5 :: String -> Int
lengthPrefixAlpha5 xs =  length px where
                            px = takeWhile(isAlpha)xs where
                                x = head(xs)

-- Avec takeWhile (bonne façon)
lengthPrefixAlpha6 :: String -> Int
lengthPrefixAlpha6 xs = length ( takeWhile ( isAlpha)xs )

