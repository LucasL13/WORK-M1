package TP2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

public class GUI_Vue extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private JFrame frame;
    private JPanel panel;
    // Liste contenant les élements représentant graphiquement les routeurs ( [0 .. g.ns-1] )
    private ArrayList<JLabel> routers;
    private ArrayList<Point> lastRoutersPosition;

    private Graphe g;

    // Indicateur du routeur selectionné pour déplacement
    private int btn_move_selected = -1;
    // Indicateur des deux routeurs selectionnés pour transmettre un paquet ([0] = source, [1] destination))
    private int[] btn_msg_selected;


    // Constructeur et initialisateur de l'interface graphique
    public GUI_Vue(Graphe g){
        this.frame = new JFrame("Simulateur Réseaux");
        this.frame.setSize(1000,1000);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.g = g;

        this.panel = this;
        this.panel.addMouseMotionListener(this);

        this.routers = new ArrayList<JLabel>();
        this.lastRoutersPosition = new ArrayList<Point>();

        try {
            Image img = ImageIO.read(new File("router.png"));
            img = img.getScaledInstance(100,100,0);
            ImageIcon img_ico = new ImageIcon(img);
            for (int i = 0; i < g.ns; i++) {
                routers.add(new JLabel());
                routers.get(i).setIcon(new ImageIcon(img));
                routers.get(i).setSize(50,50);
                routers.get(i).setPreferredSize(new Dimension(100,100));
                routers.get(i).setText("R1");
                routers.get(i).addMouseListener(this);
                routers.get(i).setName(i+"");
                routers.get(i).setText(i+"");
                routers.get(i).setHorizontalTextPosition(JLabel.CENTER);
                routers.get(i).setFont(new Font(routers.get(i).getFont().getName(), Font.PLAIN, 20));
                this.panel.add(routers.get(i));
            }

        }catch(Exception e){ e.printStackTrace(); }

        this.frame.add(panel);
        this.frame.setVisible(true);

        for(int i=0; i < g.ns; i++)
            lastRoutersPosition.add(new Point(routers.get(i).getX(), routers.get(i).getY()));

        btn_msg_selected = new int[2];
    }


    // Methode de dessin de la fenetre, redéfinie afin de dessiner les arcs
    @Override
    protected void paintComponent(Graphics gc) {

        super.paintComponent(gc);

        for(int i=0; i < g.ns; i++)
            routers.get(i).setLocation(lastRoutersPosition.get(i));

        Graphics2D g2 = (Graphics2D) gc;
        g2.setStroke(new BasicStroke(5));
        g2.setFont(new Font(g2.getFont().getName(), Font.PLAIN, 20));

        g2.setColor(Color.DARK_GRAY);

        // On trace les arcs entre les routeurs qui en possedent
        for(int i=0; i < g.ns; i++){
            for(int j=0; j < g.ns; j++){
                if(j != i && g.m[i][j] != 0){

                    int x1 = routers.get(i).getX() + routers.get(i).getWidth()/2;
                    int y1 = routers.get(i).getY() + routers.get(i).getHeight()/2;
                    int x2 = routers.get(j).getX() + routers.get(j).getWidth()/2;
                    int y2 = routers.get(j).getY() + routers.get(j).getHeight()/2;

                    g2.drawString(g.m[i][j]+"", (x1+x2)/2+15, (y1+y2)/2+30);
                    g2.drawLine(x1, y1, x2, y2);
                    g2.drawLine( (x1+x2)/2, (y1+y2)/2, x2, y2);

                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int btn_id = Integer.parseInt(((JLabel) e.getComponent()).getName());

        if(e.getButton() == MouseEvent.BUTTON1){
            // En cas de clic gauche sur un routeur :
            // On le sélectionne pour déplacement
            // Il suit la position de la souris jusqu'au prochain clic gauche

            if(this.btn_move_selected == -1) {
                btn_move_selected = btn_id;
                //routers.get(btn_move_selected).setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            }
            else {
                //routers.get(btn_move_selected).setBorder(BorderFactory.createEmptyBorder());
                this.btn_move_selected = -1;
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            // En cas de clic droit sur un routeur :
            // Premier clic droit : on l'ajoute comme source pour un envoi de paquet
            // Second clic droit : on l'ajoute comme destination et on envoi le paquet
            // On reset le tableau btn_msg_selected[source, destination]

            if(btn_msg_selected[0] == btn_id) {
                routers.get(btn_id).setBorder(BorderFactory.createEmptyBorder());
                btn_msg_selected[0] = -1;
            }
            else if(btn_msg_selected[1] == btn_id){
                routers.get(btn_id).setBorder(BorderFactory.createEmptyBorder());
                btn_msg_selected[1] = -1;
            }
            else{
                routers.get(btn_id).setBorder(BorderFactory.createLineBorder(Color.green, 5));
                if(btn_msg_selected[0] == -1)
                    btn_msg_selected[0] = btn_id;
                else
                    btn_msg_selected[1] = btn_id;
            }

        }


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
        routers.get(this.btn_move_selected).setLocation(e.getX(), e.getY());
        lastRoutersPosition.get(this.btn_move_selected).x = e.getX();
        lastRoutersPosition.get(this.btn_move_selected).y = e.getY();
        paint(this.getGraphics());
    }
}