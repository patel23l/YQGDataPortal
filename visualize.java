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
    List<Double> scores =  new ArrayList();
    int padding=20;
    int lablepadding=12;
    int numberYDivisions=6;


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

        Graphics2D g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double scaleX=((double)getWidth()-(3* padding)-labelpadding)/(scores.size()-1);
        double scaleY=((double)getWidth()-(3* padding)-labelpadding)/(getMaxScore()-getMinScores());

        List<Point> graphPoints = new ArrayList<>();
        for(int i=0;i<scores.size();i++){
            int x1=(int)(i*scaleX+padding+labelpadding);
            int y1=(int)((getMaxScore()-scores.get(i)*scaleY)+padding);
            graphPoints.add(new Point(x1,y1));
        }

        g2.setColor(COLOR.WHITE);
        g2.fillRect(padding+labelpadding,padding,getWidth()-(2*padding)-labelpadding,getHeight()-2*padding-labelpadding);
        g2.setColor(Color.BLUE);

        for(int i=0;i<numberYDivisions+1;i++){
            int x0=padding+labelpadding;
                int x1=pointWidth+labelpadding+padding;
                int y0=getHeight()-((i*(getHeight()-padding*2-labelpadding))/numberYDivison+padding+labelpadding)
        }

    } 
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createGui();
            }
        });
    }
}