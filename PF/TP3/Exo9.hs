type  Pixel = Int
type  Ligne = [Pixel]
type  Image = [Ligne]
type  Effet = (Pixel ,[Pixel]) -> Pixel
type  Point = (Int ,Int)


taille_x ,taille_y  :: Image  -> Int
taille_x = length . head
taille_y = length

sousliste  :: Int  -> Int  -> [a] -> [a]
sousliste i j xs = drop (i-1) (take j xs)

voisinage  :: Image  -> Point  -> (Pixel ,[Pixel])
voisinage  img (x,y) = (p,ps)
    where
        p = (img !! (y-1)) !! (x-1)
        ps = concat (map (sousliste (x-1) (x+1)) (sousliste (y-1) (y+1) img))
        
appliquerEffet  :: Effet  -> Image  -> Image
appliquerEffet  effet  image =
    let
        (xmax ,ymax) = (taille_x  image ,taille_y  image)
        pts = [ [ (x,y) | x <- [1.. xmax] ]   |   y <- [1.. ymax] ]
    in
        map (map (\p -> effet (voisinage  image p))) pts


showImageLigne :: Ligne -> String
showImageLigne (x:xs) = case x of
        0 -> " " ++ (showImageLigne xs)
        1 -> "*" ++ (showImageLigne xs)
showImageLigne x = ""

showImage :: Image -> String
showImage (x:xs) = showImageLigne x ++ "\n" ++ showImage xs
showImage [] = "\n"

image1 :: Image
image1 = [[1,1,1,0],[0,0,1,1],[1,0,1,0],[0,0,0,1]]