import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.SwingUtilities;
public class Main{
    public static void main(String args[]){
        visualize visual = new visualize();
        JFrame frame = new JFrame("Visualize");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(visual);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        visual.scanFile();
        visual.repaint();
    }       
}