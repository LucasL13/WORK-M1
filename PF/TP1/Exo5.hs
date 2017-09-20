average :: [Float] -> Float

average xs = sum xs / fromIntegral(length xs)


ecartmoyen :: [Float] -> Float

ecartmoyen xs = average (map ex xs)
    where 
        ex e = abs( e - average xs)
        