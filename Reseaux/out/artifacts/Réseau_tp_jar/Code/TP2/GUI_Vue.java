package TP2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI_Vue extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private JFrame frame;
    private JPanel panel;

    private JFrame indications;
    private JLabel indication1;
    private JLabel indication2;

    // Liste contenant les élements représentant graphiquement les routeurs ( [0 .. g.ns-1] )
    private ArrayList<RouterVue> routers;

    private Graphe g;
    private ArrayList<TableRoutage> tr;

    // Indicateur du routeur selectionné pour déplacement
    private int btn_move_selected = -1;
    // Indicateur des deux routeurs selectionnés pour transmettre un paquet ([0] = source, [1] destination))
    private ArrayList<Integer> routers_paquet;

    private ArrayList<Integer> routers_paquet_chemin;

    int bool_ecrire_indications = 0;

    // Constructeur et initialisateur de l'interface graphique
    public GUI_Vue(Graphe g, ArrayList<TableRoutage> tr){
        this.frame = new JFrame("Simulateur Réseaux");
        this.frame.setSize(Constantes.FRAME_WIDTH,Constantes.FRAME_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.g = g;
        this.tr = tr;

        this.panel = this;
        this.panel.addMouseMotionListener(this);

        this.indications = new JFrame("Indications");
        this.indications.setLayout(new GridLayout(2,1));
        this.indications.setLocationRelativeTo(null);
        this.indication1 = new JLabel("Cliquez (gauche) sur un routeur pour le sélectionner pour deplacement");
        this.indication2 = new JLabel("Cliquez (droite) sur un routeur pour le sélectionner pour transmission");


        this.indications.add(indication1);
        this.indications.add(indication2);


        this.routers = new ArrayList<RouterVue>();

        try {
            for (int i = 0; i < g.ns; i++) {
                routers.add(new RouterVue(i));
                routers.get(i).addMouseListener(this);
                this.panel.add(routers.get(i));
            }
        }catch(Exception e){ e.printStackTrace(); }

        this.frame.add(panel);
        this.frame.setVisible(true);

        this.indications.pack();
        this.indications.setVisible(true);

        for(int i=0; i < g.ns; i++)
            routers.get(i).setLastPosition( routers.get(i).getX(), routers.get(i).getY());

        routers_paquet = new ArrayList<Integer>();
        routers_paquet_chemin = new ArrayList<Integer>();
    }


    // Methode de dessin de la fenetre, redéfinie afin de dessiner les arcs
    @Override
    protected void paintComponent(Graphics gc) {

        super.paintComponent(gc);

        for(int i=0; i < g.ns; i++)
            routers.get(i).setLocation(routers.get(i).getLastPosition());

        Graphics2D g2 = (Graphics2D) gc;
        g2.setStroke(new BasicStroke(5));
        g2.setFont(new Font(g2.getFont().getName(), Font.PLAIN, 20));


        // On trace les arcs entre les routeurs qui en possedent
        for(int i=0; i < g.ns; i++){
            for(int j=0; j < g.ns; j++){
                if(j != i && g.m[i][j] != 0){


                    // Les deux premiers IF permettent de colorier
                    if(routers_paquet_chemin.size() != 0 && routers_paquet_chemin.contains(i)
                            && (routers_paquet_chemin.indexOf(i) < routers_paquet_chemin.size()-1)
                            && routers_paquet_chemin.get(routers_paquet_chemin.indexOf(i)+1) == j) {
                        g2.setColor(Color.GREEN);
                    }
                    else if(routers_paquet_chemin.size() != 0 && routers_paquet_chemin.contains(j)
                            && (routers_paquet_chemin.indexOf(j) < routers_paquet_chemin.size()-1)
                            && routers_paquet_chemin.get(routers_paquet_chemin.indexOf(j)+1) == i) {
                        g2.setColor(Color.GREEN);
                    }
                    else
                        g2.setColor(Color.DARK_GRAY);

                    int x1 = routers.get(i).getX() + routers.get(i).getWidth()/2;
                    int y1 = routers.get(i).getY() + routers.get(i).getHeight()/2;
                    int x2 = routers.get(j).getX() + routers.get(j).getWidth()/2;
                    int y2 = routers.get(j).getY() + routers.get(j).getHeight()/2;

                    g2.drawString(g.m[i][j]+"", (x1+x2)/2+15, (y1+y2)/2+30);
                    g2.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    // Gestion des évenements cliqués
    // Gère les clics (gauche/droite) sur les routeurs
    @Override
    public void mouseClicked(MouseEvent e) {

        int btn_id = Integer.parseInt(((JLabel) e.getComponent()).getName());

        if(e.getButton() == MouseEvent.BUTTON1){
            // En cas de clic gauche sur un routeur :
            // On le sélectionne pour déplacement
            // Il suit la position de la souris jusqu'au prochain clic gauche

            if(this.btn_move_selected == -1) {
                btn_move_selected = btn_id;
                routers.get(btn_move_selected).change_state(Constantes.ROUTER_STATE_MOVING);
                //routers.get(btn_move_selected).setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            }
            else {
                //routers.get(btn_move_selected).setBorder(BorderFactory.createEmptyBorder());
                routers.get(btn_move_selected).change_state(Constantes.ROUTER_STATE_DEFAULT);
                this.btn_move_selected = -1;
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            // En cas de clic droit sur un routeur :
            // Si le routeur n'est pas dans la liste des deux routeurs qui souhaitent communiquer, on l'ajouter
            // Si le routeur est déja dans la liste des deux routeurs qui souhaitent communiquer, on le retire
            // Si la liste contient deux routeurs, on charge la fonction chargée de "commuter"

            routers_paquet_chemin = new ArrayList<Integer>();

            if(routers_paquet.size() == 2){
                routers.get(routers_paquet.get(0)).change_state(Constantes.ROUTER_STATE_DEFAULT);
                routers.get(routers_paquet.get(1)).change_state(Constantes.ROUTER_STATE_DEFAULT);
                routers_paquet = new ArrayList<Integer>();
            }

            if(routers.get(btn_id).getState() == Constantes.ROUTER_STATE_PACKET) {
                routers.get(btn_id).change_state(Constantes.ROUTER_STATE_DEFAULT);
                routers_paquet.remove(routers_paquet.indexOf(btn_id));
            }
            else if(routers.get(btn_id).getState() == Constantes.ROUTER_STATE_DEFAULT){
                routers.get(btn_id).change_state(Constantes.ROUTER_STATE_PACKET);
                routers_paquet.add(btn_id);
                if(routers_paquet.size() == 2){
                    System.out.println("JE DOIS ENVOYER UN PAQUET ENTRE r" + routers_paquet.get(0) + " et r" + routers_paquet.get(1));
                    calculer_chemin();
                }
            }

        }
        repaint();
    }


    // Rempli le tableau qui donne le chemin de source à destination
    private void calculer_chemin(){

        routers_paquet_chemin = new ArrayList<Integer>();
        int fini = 0;

        int source = routers_paquet.get(0);
        int destination = routers_paquet.get(1);

        routers_paquet_chemin.add(source);

        while(fini == 0){
            int predecesseur = tr.get(source).tableRoutage[1][destination];

            if(predecesseur == destination){
                fini = 1;
            }
            else{
                source = predecesseur;
                routers_paquet_chemin.add(predecesseur);
            }
        }

        routers_paquet_chemin.add(destination);

        System.out.println("Chemin a parcourir de " + routers_paquet.get(0) + " à " + routers_paquet.get(1));
        System.out.println(routers_paquet_chemin);
    }



    @Override
    public void mousePressed(MouseEvent e) {  }
    @Override
    public void mouseReleased(MouseEvent e) {  }
    @Override
    public void mouseEntered(MouseEvent e) {  }
    @Override
    public void mouseExited(MouseEvent e) {  }
    @Override
    public void mouseDragged(MouseEvent e) {  }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Si un bouton est selectionné pour déplacement
        // On le met à jour en fonction des mouvements de la souris et on redessine
        if(this.btn_move_selected == -1) return;
        routers.get(this.btn_move_selected).setLastPosition(e.getX(), e.getY());
        paint(this.getGraphics());
    }
}
