package data;

import actions.Application;
import gui.Gui;

import java.io.*;
import java.util.Scanner;

public class IO {

    private static File folder = new File("data");
    private static File file;
    private static StringBuilder sb = new StringBuilder();

    public static void safe() {
        file = new File("data/safe.txt");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

                System.out.println("IO safe error");
            }
        }

        try {

            sb.delete(0, sb.length());
            for (int i = 0; i < Gui.apps.size(); i++) {
                sb.append(Gui.apps.get(i).getName());
                sb.append("\n");
                sb.append(Gui.apps.get(i).getExename());
                sb.append("\n");
                sb.append(Gui.apps.get(i).getStunden());
                sb.append("\n");
            }

            try {
                OutputStream stream = new FileOutputStream(file);
                stream.write(sb.toString().getBytes());
                stream.close();


            } catch (IOException e) {

                System.out.println("IO safe error");
            }
        } catch (Exception e) {

            System.out.println("IO Value safe error");
        }

    }

    public static void load() {

        String path = "data/safe.txt";

        file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNext()) {

                Gui.apps.add(new Application(Gui.maxy + Gui.topy, sc.nextLine(), sc.nextLine()));
                Gui.apps.get(Gui.apps.size() - 1).setIndex(Gui.index);
                Gui.maxy += 50;
                Gui.index++;

                Gui.apps.get(i).setStunden(Double.parseDouble(sc.nextLine()));
                i++;
            }
            sc.close();
            System.out.println("loaded...");
        } catch (FileNotFoundException e) {
            System.out.println("IO data parse error");
        }


    }
}
