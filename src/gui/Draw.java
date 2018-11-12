package gui;

import data.TaskGrabber;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Draw extends JLabel {

    int width;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(IL.bg, 0, 0, null);

        g.setColor(Color.WHITE);
        g.drawLine(0, 75, Gui.width, 75);
        g.drawLine(Gui.width / 2, 75, Gui.width / 2, Gui.height);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        width = g.getFontMetrics().stringWidth("Time Tracker");
        g.drawString("Time Tracker", Gui.width / 2 - width / 2, 40);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        if (Gui.apps.size() > 0) {
            for (int i = 0; i < Gui.apps.size(); i++) {
                if (Gui.apps.get(i).getY() >= 75) {

                    //Hintergrund
                    if (Gui.apps.get(i).isHover()) {
                        g.setColor(new Color(100, 100, 100, 100));
                    } else if (Gui.apps.get(i).isActive()) {
                        g.setColor(new Color(60, 60, 60, 100));
                    } else {
                        g.setColor(new Color(0, 0, 0, 100));
                    }

                    g.fillRect(Gui.apps.get(i).getX(), Gui.apps.get(i).getY(),
                            Gui.apps.get(i).getWidth(), Gui.apps.get(i).getHeight());

                    g.setColor(Color.WHITE);

                    //Obere Linie
                    g.drawLine(Gui.apps.get(i).getX(),
                            Gui.apps.get(i).getY(),
                            Gui.apps.get(i).getX() + Gui.apps.get(i).getWidth(),
                            Gui.apps.get(i).getY());
                    //Untere Linie
                    g.drawLine(Gui.apps.get(i).getX(),
                            Gui.apps.get(i).getY() + Gui.apps.get(i).getHeight(),
                            Gui.apps.get(i).getX() + Gui.apps.get(i).getWidth(),
                            Gui.apps.get(i).getY() + Gui.apps.get(i).getHeight());

                    //Name
                    if (Gui.apps.get(i).isActive()) {
                        g.setColor(new Color(108, 242, 99));
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.drawString(Gui.apps.get(i).getName(), Gui.apps.get(i).getX() + 25,
                            Gui.apps.get(i).getY() + Gui.apps.get(i).getHeight() / 2 + 5);

                    g.setColor(new Color(108, 242, 99));
                    if(Gui.apps.get(i).isRunning()){
                        g.fillOval(Gui.apps.get(i).getX()+ 370, Gui.apps.get(i).getY() + 16, 15,15);
                    }

                }
            }


            for (int i = 0; i < Gui.apps.size(); i++) {
                //Stunden
                g.setColor(Color.WHITE);
                if (Gui.apps.get(i).isActive()) {
                    g.setFont(new Font("Arial", Font.PLAIN, 22));
                    width = g.getFontMetrics().stringWidth(String.format("%1$,.1f", Gui.apps.get(i).getStunden())
                            + " Stunden");
                    g.drawString(String.format(Locale.US, "%.1f", Gui.apps.get(i).getStunden()) + " Stunden",
                            Gui.width / 2 + Gui.width / 4 - width / 2, 120);
                    //Stundencurrent
                    width = g.getFontMetrics().stringWidth(String.format("%1$,.1f", Gui.apps.get(i).getStundencurrent()) + " Stunden aktuell");
                    g.drawString(String.format(Locale.US, "%.1f", Gui.apps.get(i).getStundencurrent()) + " Stunden aktuell",
                            Gui.width / 2 + Gui.width / 4 - width / 2, 195);
                }
                g.setFont(new Font("Arial", Font.PLAIN, 18));
            }
        }

        //Linie unter Stunden
        g.setColor(Color.WHITE);
        g.drawLine(Gui.width / 2, 150, Gui.width, 150);





        g.drawLine(Gui.width / 2, 225, Gui.width, 225);

        repaint();
    }
}
