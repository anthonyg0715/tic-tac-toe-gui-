import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //This invoke later method is necessary since the application thread needs to update the GUI
        //otherwise it would look weird sometimes or miss pieces that it needs if it isn't updated whenever you run this
        //program
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new tttGUI();
            }
        });
    }
}
