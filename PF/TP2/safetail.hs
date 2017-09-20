
-- Conditionnel : 
safetail1 xs =
    if length xs == 0 then [] else tail(xs)
 


-- Conditions de garde : 
safetail2 xs
    | length xs == 0 = []
    | otherwise = tail(xs)


-- Filtrage par motifs
safetail3 [] = []
safetail3 [_] = []
safetail3 (_:ys) = ys


-- Filtrage par motifs (case of)

safetail4 xs = case xs of
    [] -> []
    [_] -> []
    (_:xs) -> xs
