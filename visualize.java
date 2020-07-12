import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class visualize extends JPanel {
    List<Double> scores =  new ArrayList();
    int padding=20;
    int labelpadding=12;
    int numberYDivisions=6;
    int  pointWidth = 10 ;  

    private Color gridColor=new Color(200,200,200,200);

    private double getMinScores() { 
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        } 
        return minScore;
        }

        private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public visualize(List<Double> scores){
        this.scores=scores;
    }

    public static void createGui(){
        //===========================================
        List<String> ward = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> votes = new ArrayList<>();
        //===========================================
        try {
            File myObj = new File("input.txt");
            Scanner Reader = new Scanner(myObj);

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                String[] separation = data.split(",");
                ward.add(separation[0]);
                names.add(separation[1]);
                votes.add(separation[2]);
            }
            Reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        //===========================================
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

        g2.setColor(Color.WHITE);
        g2.fillRect(padding+labelpadding,padding,getWidth()-(2*padding)-labelpadding,getHeight()-2*padding-labelpadding);
        g2.setColor(Color.BLUE);

        for(int i=0;i<numberYDivisions+1;i++){
            int x0=padding+labelpadding;
            int x1=pointWidth+labelpadding+padding;
            int y0=getHeight()-((i*(getHeight()-padding*2-labelpadding))/numberYDivisions+padding+labelpadding);
            
            int y1=y0;
            if(scores.size()>0){
                g2.setColor(gridColor);
                g2.drawLine(padding+labelpadding+1+pointWidth,y0,getWidth()-padding,y1);
                g2.setColor(Color.BLACK);
                String yLabel=((int)((getMinScores()+(getMaxScore()-getMinScores())*((i*8.0)/numberYDivisions))*100))/100.0+"";
                
                //adding values to the y-axis
                FontMetrics metric = g2.getFontMetrics();
                int labelWidth = metric.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metric.getHeight() / 2) - 3);
                g2.drawLine(x0, y0, x1, y1);
            }
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