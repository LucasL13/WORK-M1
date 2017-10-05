package TP2;

import java.util.Set;
import java.util.TreeSet;

public class Dijkstra {

    int[] d;
    int[] pi;

    Set<Integer> E;
    Set<Integer> F;

    Graphe g;

    TableRoutage calculer_table(Graphe g, int sommet){

        System.out.println("\n-------------------------------------------------");
        System.out.print("Calcul table de routage de " + sommet);
        System.out.println("\n-------------------------------------------------");

        TableRoutage tr = new TableRoutage(sommet, g.ns);

        this.g = g;

        init(g, tr);

        // TreeSet utilisé car équivalent aux ArrayList mais avec garantie de l'unicité des élements
        E = new TreeSet<>();
        F = new TreeSet<>();

        F.add(sommet);
        d[sommet] = 0;

        decouvrir_adjacence(sommet);
//        System.out.println("F = " + F.toString());
//        System.out.println("E= " + E.toString());

        while (!F.isEmpty()) {
            int nextLowStep = chercher_min();
//            System.out.println("Sommet a plus petite valeur : " + nextLowStep);
            decouvrir_adjacence(nextLowStep);
//            System.out.println("F = " + F.toString());
//            System.out.println("E= " + E.toString());
        }

        // Construction de la table de routage à partir des valeurs de d[] et pi[] calculées
        for(int i=0; i < g.ns; i++){
            tr.tableRoutage[0][i] = i;

            int previous_step = i;
            while(pi[previous_step] != sommet)
                previous_step = pi[previous_step];

            tr.tableRoutage[1][i] = previous_step;
            tr.tableRoutage[2][i] = d[i];

            if(tr.tableRoutage[2][i] == Integer.MAX_VALUE-1){
                tr.tableRoutage[2][i] = -1;
                tr.tableRoutage[1][i] = -1;
            }

        }

        return tr;
    }

    // Trouver le prochain sommet du front à exploiter (moindre coût)
    private int chercher_min(){
        int min = Integer.MAX_VALUE;
        int s_min = 0;
        for(int s : F){
            // System.out.println("d["+s+"] = " + d[s] + " | min = " + min);
            if(d[s] < min){
                min = d[s];
                s_min = s;
            }
        }
        return s_min;
    }

    // Remplit le front F avec les adjacences du sommet s pas encore découvertes
    private void decouvrir_adjacence(int s){
        F.remove(s);
        E.add(s);
        for(int i=0; i < g.ns; i++){
            //System.out.println("g.m["+s+"]["+i+"] = " + g.m[s][i]);
            if(!E.contains(i) && g.m[s][i] != 0){
                F.add(i);
                //System.out.println("F contient : " + F.size() + " élements");
                //System.out.println("d["+d[pi[i]]+"]");
                if(g.m[s][i] + d[s] < d[i]){
                    d[i] = g.m[s][i] + d[s];
                    pi[i] = s;
                }
            }
        }
    }

    // Initialisation des structures utilisées pour le calcul
    private void init(Graphe g, TableRoutage tr){
        d = new int[g.ns];
        pi = new int[g.ns];
        //System.out.println("D[i] et P[i]");
        for(int i = 0; i < g.ns; i++){
            d[i] = Integer.MAX_VALUE-1;
            pi[i] = i;
            System.out.println(d[i] + " | " + pi[i]);
        }
        d[tr.sommet] = 0;
    }


}
