import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by work on 22/09/17.
 */
public class Clock {


    public static void main(String[] args) {

        int delay = 100;
        ActionListener  taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clock");
            }

        };

        new Timer(delay, taskPerformer).start();

        while(true){}
    }

}
