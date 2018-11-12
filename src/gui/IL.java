package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IL {

    public static BufferedImage bg;
    public static ImageIcon icon;

    public static void load(){
        try {
            bg = ImageIO.read(new FileInputStream(new File("rsc/bg.png")));
            icon = new ImageIcon("rsc/logo.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
