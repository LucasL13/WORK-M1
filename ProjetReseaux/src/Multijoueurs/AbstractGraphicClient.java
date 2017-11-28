package Multijoueurs;

import javax.swing.*;
import java.net.Socket;

/**
 * Created by work on 28/11/17.
 */
public abstract class AbstractGraphicClient extends ReseauxToolbox {

    protected final String letters_text_text = "LE MOT CACHÉ : ";

    protected JFrame        frame;
    protected Socket        sock;

    protected JLabel        letters;
    protected JLabel        letters_text;

    protected JTextField    letter_proposal;
    protected JButton       letter_submit;

    protected JPanel        panel_top;
    protected JPanel        panel_bottom;

    
    protected abstract void update_word();
    protected abstract void send_word();

    protected abstract void display_failure();
    protected abstract void display_success();

    protected void display_endgame_failure(){};

    protected void display_endgame_success(){};
}
