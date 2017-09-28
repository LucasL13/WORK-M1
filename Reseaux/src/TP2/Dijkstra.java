package TP2;

import javafx.scene.control.Tab;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 28/09/17.
 */
public class Dijkstra {

    int[] d;
    int[] pi;

    TableRoutage calculer_table(Graphe g, int sommet){
        TableRoutage tr = new TableRoutage(sommet, g.ns);

        init(g, tr);

        Set<Integer> E = new TreeSet<>();
        Set<Integer> F = new TreeSet<>();

        for(int i=0; i < g.ns; i++){
            F.add(i);
        }

        while(!F.isEmpty()){
            int u = extraire_min(F);
            E.add(u);
            F.remove(u);
            for(int i=0; i < g.ns; i++){
                if(g.m[u][i] != 0){
                    relacher(u, i, g.m[u][i]);
                }
            }
        }

        for(int i=0; i < g.ns; i++){
            if(i != sommet){
                tr.tableRoutage[0][i] = i;
                tr.tableRoutage[1][i] = pi[i];
                tr.tableRoutage[2][i] = d[i];
            }
        }

        return tr;
    }


    private int trouver_prochain_saut(){
        return 0;
    }

    private int extraire_min(Set<Integer> F){
        int min = Integer.MAX_VALUE;
        int sMin = -1;
        for(int x : F){
            if(d[x] < min){
                min = d[x];
                sMin = x;
            }
        }
        return sMin;
    }


    private void init(Graphe g, TableRoutage tr){
        d = new int[g.ns];
        pi = new int[g.ns];

        for(int i = 0; i < g.ns-1; i++){
            d[i] = Integer.MAX_VALUE;
            pi[i] = -1;
        }

        d[tr.sommet] = 0;
    }


    private void relacher(int u, int v, int w){
        if(d[v] > (d[u] + w)){
            d[v] = d[u] + w;
            pi[v] = u;
        }
    }

}
