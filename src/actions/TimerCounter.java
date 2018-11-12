package actions;

import data.IO;
import data.TaskGrabber;
import gui.Gui;

import java.util.Timer;
import java.util.TimerTask;

public class TimerCounter {
    Timer t = new Timer();

    public TimerCounter() {
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                for (int i = 0; i < Gui.apps.size(); i++) {
                    if (TaskGrabber.programsRunning()[i]) {
                        Gui.apps.get(i).add(0.1 / 60);
                        Gui.apps.get(i).addcurrent(0.1 / 60);
                        Gui.apps.get(i).setRunning(true);
                    } else {
                        Gui.apps.get(i).setRunning(false);
                        Gui.apps.get(i).setStundencurrent(0);
                    }
                }
                IO.safe();

            }
        }, 0, 6000);
    }
}
