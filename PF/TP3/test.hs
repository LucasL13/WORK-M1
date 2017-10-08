afficheEtIncrementeComplexe (x,y) =
    putStr (show x ++" + i"++ show y)
    >>=
    putStr  "\n"
    >>=
    return (x+1,y+1)