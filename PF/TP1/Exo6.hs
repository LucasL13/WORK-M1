second  xs = head (tail xs)             -- second :: [a] -> a     head :: [a] -> a         tail :: [a] -> [a]    --
                                        -- Fonction Polymorphe -- 

swap (x,y) = (y,x)                      -- swap :: (t1, t) -> (t, t1)   --
                                        -- Version currifiée : swap x y = (y, x)
                                        -- Fonction Polymorphe --

pair x y = (x,y)                        -- pair :: t -> t1 -> (t, t1)   -- -- 2 Arguments -- 
                                        -- Fonction Polymorphe --

mult x y = x * y                        -- mult :: Num a => a -> a -> a -- -- 2 Arguments -- 


double = mult 2                         -- double :: Integer -> Integer --


palindrome  xs = reverse  xs == xs      -- palindrome :: Eq a => [a] -> Bool   reverse :: [a] -> [a]    --
                                        -- Fonction Polymorphe --

twice (f,x) = f (f x)                   -- twice :: (t -> t, t) -> t    --
                                        -- Version currifiée : twice f x = f (f x) --
                                        -- Fonction d'ordre supérieur -- 