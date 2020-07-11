import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class visualize extends JPanel {
    //test
    List<Double> scores =  new ArrayList();

    public visualize(List<Double> scores){
        this.scores=scores;
    }

    public static void createGui(){
        Random random = new Random();
    List<Double> scores =  new ArrayList();

        int maxDataPoints=20;
        int maxScore=8;

        for(int i=0;i<maxDataPoints;i++){
            scores.add((double)random.nextDouble()*maxScore);
        }
        visualize mainPanel=new visualize(scores);
        mainPanel.setPreferredSize(new Dimension(700,600));

        JFrame frame = new JFrame("A visualize dataset");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Graphics2D g2=(Graphics2D) g;
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createGui();
            }
        });
    }
}