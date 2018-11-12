package actions;

import data.IO;
import gui.Gui;
import gui.IL;

public class Main {
    public static void main(String[] args) {
        IL.load();
        Gui g = new Gui();
        g.create();
        IO.load();
        new TimerCounter();
    }
}
