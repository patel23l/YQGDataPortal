package Iteration_I;
/**
 * Write a description of class visualize here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
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
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class visualize extends JPanel {
   static List<Double> votes = new ArrayList<>();
    List<Double> votesOnY = new ArrayList<>();
    int countDigit = 0;
    String fname = "";

   static List<Double> ward = new ArrayList<>();
    static List<String> names = new ArrayList<>();

    int padding = 20;
    int labelpadding = 12;
    int numberYDivisions = 8;
    int pointWidth = 10;
    int maxVotes=10*countDigit;
    private Color gridColor = new Color(200, 200, 200, 200);
    private Color lineColor = new Color(255, 255, 254);
    private Color pointColor = new Color(255, 0, 255);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    // get the min votes
    double minVote = Double.MAX_VALUE;
    private double getMinVotes() {

        for (double vote : votes) {
            minVote = Math.min(minVote, vote);
        }
        return minVote;
    }

    // get the max votes
    private double getMaxVotes() {
        double maxVote = Double.MIN_VALUE;

        for (double vote : votes) {
            maxVote = Math.max(maxVote, vote);
        }
        return maxVote;
    }

    public visualize() {
        setPreferredSize(new Dimension(700, 600));
    }

    public String getName() {
        return this.fname;
    }

    public void setName(String filename) {
        this.fname = filename;
    }

    public void scanFile(String fileName) {
        double temp;

        try {

            setName(fileName);
            File myObj = new File(getName());
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
            for (int k = 0; k < votes.size(); k++) {
                System.out.println(
                        "Mayor name: " + names.get(k) + " Ward number: " + ward.get(k) + " Votes: " + votes.get(k));
            }
            Reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void copyList(){
        votesOnY.addAll(votes);
        Collections.sort(votesOnY);
    }
    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // scale both axis
        double scaleX = ((double) getWidth() - (3 * padding) - labelpadding) / (votes.size() - 1);
        double scaleY = ((double) getWidth() - (3 * padding) - labelpadding) / (getMaxVotes() - getMinVotes());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < votes.size(); i++) {
            int x1 = (int) (i * scaleX + padding + labelpadding);
            int y1 = (int) ((getMaxVotes() - votes.get(i) * scaleY) + padding);
            graphPoints.add(new Point(x1, y1));
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelpadding, padding, getWidth() - (2 * padding) - labelpadding,
                getHeight() - 2 * padding - labelpadding);
        g2.setColor(Color.BLUE);

        copyList();

        // adding value to y-axis
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelpadding;
            int x1 = pointWidth + labelpadding + padding;
            int y0 = getHeight()
                    - ((i * (getHeight() - padding * 2 - labelpadding)) / numberYDivisions + padding + labelpadding);

            int y1 = y0;
            // plot points onto the line
            if (votes.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelpadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                double temp=votesOnY.get(i);
                String yLabel = ((int) temp)*100.0 / 100.0 + "";
                FontMetrics metric = g2.getFontMetrics();
                int labelWidth = metric.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metric.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // adding values to the x-axis
        for (int i = 0; i < ward.size(); i++) {
            if (ward.size() > 1) {
                int x0 = i * (getHeight() - padding * 2 - labelpadding) / (ward.size() - 1) + padding + labelpadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelpadding;
                int y1 = y0 - pointWidth;

                // plot points onto the line
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelpadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String[] tempname=names.get(i).split(" ");
                    String xLabel = ward.get(i) +" "+tempname[1].charAt(0)+tempname[2].charAt(0)+ " ";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        g2.drawLine(padding + labelpadding, getHeight() - padding - labelpadding, padding + labelpadding, padding);
        g2.drawLine(padding + labelpadding, getHeight() - padding - labelpadding, getWidth() - padding,
                getHeight() - padding - labelpadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
}