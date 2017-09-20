factorielle :: Int -> Int

factorielle x = product [1..x] 


binomial :: (Int , Int) -> Int

binomial (n,k) = factorielle n `div` ( factorielle k * factorielle (n - k) )  