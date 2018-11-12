package actions;

import gui.Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() <= Gui.width / 2) {
            for (int i = 0; i < Gui.apps.size(); i++) {
                if (Gui.apps.get(i).inside(e.getX(), e.getY())) {
                    Gui.apps.get(i).setHover(true);
                } else {
                    Gui.apps.get(i).setHover(false);
                }
            }
        } else {
            for (int i = 0; i < Gui.apps.size(); i++) {
                Gui.apps.get(i).setHover(false);
            }
        }
    }
}
