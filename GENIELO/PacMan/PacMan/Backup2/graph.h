#ifndef GRAPH_H
#define GRAPH_H
#include "sommet.h"
#include "const.h"

using namespace std;

class Graph
{
public:
    Graph(const char *name);

private:
   Sommet* matrice[(W_WIDTH/T_SIZE)*3][(W_HEIGHT/T_SIZE)*3];
   void initMatrice();

};

#endif // GRAPH_H
