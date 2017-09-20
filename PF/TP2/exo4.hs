
carre:: Num a => a -> a 
carre x = x ^ 2
-- \c -> (c ^ 2)

moyenne:: [Double] -> Double
moyenne ns = sum ns/fromIntegral (length ns)
-- \m -> (sum m/fromIntegral (length m))

norme:: [Double] -> Double
norme ns = sqrt(moyenne (map carre ns))
-- \n -> sqrt( \m ( map \c n))

main = print(norme [1..5])


                
mainLambda = print ( (\n -> (sqrt( (\m -> (sum m/fromIntegral (length m))) ( map (\c -> (c ^ 2)) n)))) [1..5] )

