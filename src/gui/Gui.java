package gui;

import actions.Application;
import actions.MouseHandler;
import actions.MouseMotionHandler;
import data.TaskGrabber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Gui {

    //private static DecimalFormat df = new DecimalFormat("#,##");

    JFrame jf, jfnew, jfedit;
    JTextField[] tf = new JTextField[2];
    JLabel[] lbl = new JLabel[2];
    JTextField[] tf2 = new JTextField[3];
    JLabel[] lbl2 = new JLabel[3];
    Draw d;
    JButton add, hinzufügen, löschen, bearbeiten, editsave, editabort;
    public static int width = 800, height = 600;
    public static ArrayList<Application> apps = new ArrayList<>();
    public static int topy = 0, maxy = 75;
    public static int index = 0;

    //public static String[] programs = {"idea64.exe", "firefox.exe"};

    public void create() {
        jf = new JFrame();
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setTitle("TimeTracker");
        jf.setIconImage(IL.icon.getImage());
        jf.setLayout(null);

        jfnew = new JFrame("Hinzufügen");
        jfnew.setSize(300, 200);
        jfnew.setLocationRelativeTo(null);
        jfnew.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jfnew.setResizable(false);
        jfnew.setLayout(null);
        jfnew.setVisible(false);


        jfedit = new JFrame("Bearbeiten");
        jfedit.setSize(300, 250);
        jfedit.setLocationRelativeTo(null);
        jfedit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jfedit.setResizable(false);
        jfedit.setLayout(null);
        jfedit.setVisible(false);

        for (int i = 0; i < 2; i++) {
            tf[i] = new JTextField();
            tf[i].setBounds(15, 20 + i * 50, 100, 25);
            tf[i].setVisible(true);
            jfnew.add(tf[i]);

            lbl[i] = new JLabel();
            lbl[i].setBounds(120, 20 + i * 50, 150, 25);
            lbl[i].setVisible(true);
            jfnew.add(lbl[i]);
        }

        for (int i = 0; i < 3; i++) {
            tf2[i] = new JTextField();
            tf2[i].setBounds(15, 20 + i * 50, 100, 25);
            tf2[i].setVisible(true);
            jfedit.add(tf2[i]);

            lbl2[i] = new JLabel();
            lbl2[i].setBounds(120, 20 + i * 50, 150, 25);
            lbl2[i].setVisible(true);
            jfedit.add(lbl2[i]);
        }

        lbl[0].setText("Name eingeben.");
        lbl[1].setText("Name der EXE eingeben");

        lbl2[0].setText("Name eingeben.");
        lbl2[1].setText("Name der EXE eingeben");
        lbl2[2].setText("Stunden eingeben");

        hinzufügen = new JButton("Hinzufügen");
        hinzufügen.setBounds(100, 120, 100, 30);
        hinzufügen.setFocusPainted(false);
        hinzufügen.setBackground(new Color(31, 40, 122));
        hinzufügen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hinzufügen.setForeground(Color.WHITE);
        hinzufügen.setVisible(true);
        hinzufügen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tf[0].getText().isEmpty() && !tf[1].getText().isEmpty()) {
                    apps.add(new Application(maxy + topy, tf[0].getText(), tf[1].getText()));
                    apps.get(apps.size() - 1).setIndex(index);
                    maxy += 50;
                    index++;
                    tf[0].setText("");
                    tf[1].setText("");
                    jfnew.setVisible(false);

                }
            }
        });
        jfnew.add(hinzufügen);

        editabort = new JButton("Abbrechen");
        editabort.setBounds(150, 165, 100, 30);
        editabort.setFocusPainted(false);
        editabort.setBackground(new Color(31, 40, 122));
        editabort.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        editabort.setForeground(Color.WHITE);
        editabort.setVisible(true);
        editabort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tf2[0].setText("");
                tf2[1].setText("");
                tf2[2].setText("");

                jfedit.setVisible(false);

            }
        });
        jfedit.add(editabort);

        editsave = new JButton("Speichern");
        editsave.setBounds(25, 165, 100, 30);
        editsave.setFocusPainted(false);
        editsave.setBackground(new Color(31, 40, 122));
        editsave.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        editsave.setForeground(Color.WHITE);
        editsave.setVisible(true);
        editsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (!tf2[0].getText().isEmpty() && !tf2[1].getText().isEmpty() && !tf2[2].getText().isEmpty()) {

                    for(int i = 0; i<apps.size(); i++){
                        if(apps.get(i).isActive()){
                            apps.get(i).setName(tf2[0].getText());
                            apps.get(i).setExename(tf2[1].getText());
                            apps.get(i).setStunden(Double.parseDouble(tf2[2].getText()));
                        }
                    }
                    tf2[0].setText("");
                    tf2[1].setText("");
                    tf2[2].setText("");
                    jfedit.setVisible(false);
                }
            }
        });
        jfedit.add(editsave);

        d = new Draw();
        d.setBounds(0, 0, width, height);
        d.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getX() < Gui.width / 2) {
                    if (apps.size() > 0) {
                        if (e.getWheelRotation() == 1) {

                            for (int i = 0; i < apps.size(); i++) {
                                apps.get(i).setY(apps.get(i).getY() - 20);
                            }
                            topy -= 20;

                        } else if (e.getWheelRotation() == -1) {
                            if (topy < 0) {
                                for (int i = 0; i < apps.size(); i++) {
                                    apps.get(i).setY(apps.get(i).getY() + 20);
                                }
                                topy += 20;
                            }
                        }
                    }
                }
            }
        });
        d.addMouseMotionListener(new MouseMotionHandler());
        d.addMouseListener(new MouseHandler());
        jf.add(d);


        add = new JButton("+");
        add.setBounds(10, 12, 50, 50);
        add.setBackground(new Color(31, 40, 122));
        add.setFont(new Font("Arial", Font.BOLD, 20));
        add.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add.setForeground(Color.WHITE);
        add.setFocusPainted(false);
        add.setVisible(true);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfnew.setVisible(true);
            }
        });
        d.add(add);

        löschen = new JButton("Löschen");
        löschen.setBounds(width - 150, height - 75, 100, 30);
        löschen.setBackground(new Color(31, 40, 122));
        löschen.setForeground(Color.WHITE);
        löschen.setFocusPainted(false);
        löschen.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        löschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < apps.size(); i++) {
                    if (apps.get(i).isActive()) {
                        int n = JOptionPane.showConfirmDialog(
                                new JFrame(),
                                "Ausgewählte App wirklich löschen?",
                                "Löschen",
                                JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.OK_OPTION) {


                            for (int j = 0; j < apps.size(); j++) {
                                if (apps.get(j).getIndex() > i) {
                                    apps.get(j).setIndex(apps.get(j).getIndex() - 1);
                                    apps.get(j).setY(apps.get(j).getY() - 50);
                                    apps.get(j).setIndex(apps.get(j).getIndex() - 1);
                                    apps.get(j).setBasey(apps.get(j).getBasey() - 50);
                                }
                            }
                            apps.remove(i);
                            index -= 1;
                            maxy -= 50;
                        }

                    }
                }
            }
        });
        löschen.setVisible(true);
        d.add(löschen);

        bearbeiten = new JButton("Bearbeiten");
        bearbeiten.setBounds(width - 275, height - 75, 100, 30);
        bearbeiten.setBackground(new Color(31, 40, 122));
        bearbeiten.setForeground(Color.WHITE);
        bearbeiten.setFocusPainted(false);
        bearbeiten.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        bearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfedit.setVisible(true);

                for(int i = 0; i<apps.size(); i++){
                    if(apps.get(i).isActive()){
                        tf2[0].setText(apps.get(i).getName());
                        tf2[1].setText(apps.get(i).getExename());
                        tf2[2].setText(String.format(Locale.US, "%.1f", apps.get(i).getStunden()));
                    }
                }
            }
        });
        bearbeiten.setVisible(true);
        d.add(bearbeiten);

        jf.setVisible(true);
    }


}
