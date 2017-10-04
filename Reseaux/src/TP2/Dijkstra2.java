package TP2;

import java.util.Set;
import java.util.TreeSet;

import static java.lang.Thread.sleep;

/**
 * Created by work on 28/09/17.
 */
public class Dijkstra2 {

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

        E = new TreeSet<>();
        F = new TreeSet<>();

        F.add(sommet);
        d[sommet] = sommet;
        pi[sommet] = sommet;

        decouvrir_adjacence(sommet);
//        System.out.println("F = " + F.toString());
//        System.out.println("E= " + E.toString());

        while (!F.isEmpty()) {

            int nextLowStep = chercher_min();
            //System.out.println("Sommet a plus petite valeur : " + nextLowStep);

            decouvrir_adjacence(nextLowStep);

//            System.out.println("F = " + F.toString());
//            System.out.println("E= " + E.toString());


        }

        for(int i=0; i < g.ns; i++){
            if(i != sommet){
                tr.tableRoutage[0][i] = i;
                tr.tableRoutage[1][i] = pi[i];
                tr.tableRoutage[2][i] = d[i];
            }
        }

        for(int i=0; i < g.ns; i++)
            System.out.print(i + " | ");

        System.out.println();

        for(int i=0; i < g.ns; i++)
            System.out.print(d[i] + " | ");

        System.out.println();

        for(int i=0; i < g.ns; i++)
            System.out.print(pi[i] + " | ");

        return tr;
    }

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

        //System.out.println("D[i] et P[i]");
        for(int i = 0; i < g.ns; i++){
            d[i] = Integer.MAX_VALUE;
            pi[i] = i;

            System.out.println(d[i] + " | " + pi[i]);
        }

        d[tr.sommet] = 0;

    }


}
