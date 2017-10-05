package TP2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by work on 05/10/17.
 */
public class RouterVue extends JLabel {


    private int RouterNumber;
    private int state;

    private Point location;

    private Image img_default;
    private Image img_move;
    private Image img_packet;

    private ImageIcon img_i_default;
    private ImageIcon img_i_move;
    private ImageIcon img_i_packet;



    public RouterVue(int RouterNumber){

        super();

        this.location = new Point();
        this.state = Constantes.ROUTER_STATE_DEFAULT;
        this.RouterNumber = RouterNumber;

        try{
            this.img_default = ImageIO.read(new File(Constantes.IMG_DEFAULT_PATHNAME));
            this.img_move = ImageIO.read(new File(Constantes.IMG_MOVE_PATHNAME));
            this.img_packet = ImageIO.read(new File(Constantes.IMG_PACKET_PATHNAME));

            this.img_default = this.img_default.getScaledInstance(Constantes.ROUTER_IMG_SIZE, Constantes.ROUTER_IMG_SIZE,0);
            this.img_move = this.img_move.getScaledInstance(Constantes.ROUTER_IMG_SIZE, Constantes.ROUTER_IMG_SIZE,0);
            this.img_packet = this.img_packet.getScaledInstance(Constantes.ROUTER_IMG_SIZE, Constantes.ROUTER_IMG_SIZE,0);

            this.img_i_default = new ImageIcon(this.img_default);
            this.img_i_move = new ImageIcon(this.img_move);
            this.img_i_packet = new ImageIcon(this.img_packet);


            this.setIcon(img_i_default);
            this.setSize(Constantes.ROUTER_IMG_SIZE,Constantes.ROUTER_IMG_SIZE);
            this.setPreferredSize(new Dimension(Constantes.ROUTER_IMG_SIZE,Constantes.ROUTER_IMG_SIZE));
            this.setName(this.RouterNumber+"");
            this.setText(this.RouterNumber+"");
            this.setHorizontalTextPosition(JLabel.CENTER);
            this.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));

        }catch (Exception e){ e.printStackTrace(); }
    }

    public Point getLastPosition(){        return this.location;    }

    public void setLastPosition(int x, int y){
        this.location.x = x;
        this.location.y = y;
    }

    public int getState(){  return this.state; }


    public void change_state(int state){
        this.state = state;
        switch (this.state){
            case Constantes.ROUTER_STATE_DEFAULT: this.setIcon(img_i_default); break;
            case Constantes.ROUTER_STATE_MOVING: this.setIcon(img_i_move); break;
            case Constantes.ROUTER_STATE_PACKET: this.setIcon(img_i_packet); break;
            default: break;
        }

    }


}
