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
    List<Double> votes = new ArrayList<>();
    
    int padding = 20;
    int labelpadding = 12;
    int numberYDivisions = 8;
    int  pointWidth = 10 ;  

    private Color gridColor = new Color(200,200,200,200);

    //get the min votes 
    private double getMinVotes() { 
        double minVote = Double.MAX_VALUE;
        
        for (Double vote : votes) {
                minVote = Math.min(minVote, vote);
        } 
        return minVote;
    }

    //get the max votes
    private double getMaxVotes() {
        double maxVote = Double.MIN_VALUE;
        
        for (Double vote : votes) {
            maxVote = Math.max(maxVote, vote);
        }
        return maxVote;
    }

    public visualize(List<Double> votes){
        this.votes = votes; //initializes the votes from input to votes attribute
    }

    public static void createGui(){
        Random random = new Random();
        //retrieve data from the input (data) file
        //==========================================
        List<Double> ward = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Double> votes = new ArrayList<>();
        double temp;

        try {
            File myObj = new File("input.txt");
            Scanner Reader = new Scanner(myObj);

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                String[] separation = data.split(",");
                temp = Double.parseDouble(separation[0]);
                ward.add(temp);
                names.add(separation[1]);
                temp = Double.parseDouble(separation[2]);
                votes.add(temp);
            }
            Reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        //==========================================
        int maxDataPoints=20;
        int maxScore=8;

        visualize mainPanel = new visualize(votes);
        mainPanel.setPreferredSize(new Dimension(700,600));

        //Set up GUI components
        JFrame frame = new JFrame("Election Dataset");
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

        //scale both axis
        double scaleX=((double)getWidth()-(3* padding)-labelpadding)/(votes.size()-1);
        double scaleY=((double)getWidth()-(3* padding)-labelpadding)/(getMaxVotes()-getMinVotes());

        List<Point> graphPoints = new ArrayList<>();
        for(int i=0;i<votes.size();i++){
            int x1=(int)(i*scaleX+padding+labelpadding);
            int y1=(int)((getMaxVotes()-votes.get(i)*scaleY)+padding);
            graphPoints.add(new Point(x1,y1));
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(padding+labelpadding,padding,getWidth()-(2*padding)-labelpadding,getHeight()-2*padding-labelpadding);
        g2.setColor(Color.BLUE);

        //adding value to y-axis
        for(int i=0;i<numberYDivisions+1;i++){
            int x0=padding+labelpadding;
            int x1=pointWidth+labelpadding+padding;
            int y0=getHeight()-((i*(getHeight()-padding*2-labelpadding))/numberYDivisions+padding+labelpadding);
            
            int y1=y0;
            //plot points onto the line
            if(votes.size()>0){
                g2.setColor(gridColor);
                g2.drawLine(padding+labelpadding+1+pointWidth,y0,getWidth()-padding,y1);
                g2.setColor(Color.BLACK);
                String yLabel=((int)((getMinVotes()+(getMaxVotes()-getMinVotes())*((i*8.0)/numberYDivisions))*100))/100.0+"";
                
                FontMetrics metric = g2.getFontMetrics();
                int labelWidth = metric.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metric.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        //adding values to the x-axis
        for (int i = 0; i < votes.size(); i++) {
            if(votes.size() > 1) {
                int x0 = i * (getHeight() - padding * 2 - labelpadding) / (votes.size() - 1) + padding + labelpadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelpadding;
                int y1 = y0 - pointWidth;

                //plot points onto the line
                if ((i % ((int) ((votes.size() / 8.0)) + 3)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelpadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";

                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
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