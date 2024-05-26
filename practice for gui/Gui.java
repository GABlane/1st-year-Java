import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui {
    public static void main(String[] args) {
        JLabel label = new JLabel();
        label.setText("Angas");

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setTitle("Start");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);

        ImageIcon image = new ImageIcon("logo.jpg");
        frame.setIconImage(image.getImage());
        label.setIcon(image);
    }
}